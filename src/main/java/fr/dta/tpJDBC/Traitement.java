package fr.dta.tpJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Traitement {
	public static void addBook(Book b) {

		String url = "jdbc:postgresql://localhost:5432/tpJDBC";

		try (Connection conn = DriverManager.getConnection(url, "postgres", "azerty");
				PreparedStatement insertion = conn.prepareStatement("INSERT INTO Book(title, author) VALUES(?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {

			insertion.setString(1, b.getTitle());
			insertion.setString(2, b.getAuthor());
			insertion.executeUpdate();

			ResultSet generatedKeys = insertion.getGeneratedKeys();
			generatedKeys.next();
			int id = generatedKeys.getInt("id");
			b.setId(id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addClient(Client c) {
		String url = "jdbc:postgresql://localhost:5432/tpJDBC";
		try (Connection conn = DriverManager.getConnection(url, "postgres", "azerty");
				PreparedStatement addClient = conn.prepareStatement(
						"insert into Client(lastname, firstname, gender, favbook) values(?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {

			addClient.setString(1, c.getLastname());
			addClient.setString(2, c.getFirstname());
			addClient.setString(3, c.getGender());

			addClient.setInt(4, c.getFavbook());

			addClient.executeUpdate();

			ResultSet generatedKeysClient = addClient.getGeneratedKeys();
			generatedKeysClient.next();
			int idClient = generatedKeysClient.getInt("id");
			c.setId(idClient);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void achatClient(Book b, Client c) {

		String url = "jdbc:postgresql://localhost:5432/tpJDBC";
		try (Connection conn = DriverManager.getConnection(url, "postgres", "azerty");
				PreparedStatement achatClient = conn
						.prepareStatement("insert into Bookachete(id_client, id_book) values(?, ?)")) {

			achatClient.setInt(1, c.getId());
			achatClient.setInt(2, b.getId());

			achatClient.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void livreParClient(Client c) {
		String url = "jdbc:postgresql://localhost:5432/tpJDBC";
		try (Connection conn = DriverManager.getConnection(url, "postgres", "azerty");
				PreparedStatement livreParClient = conn.prepareStatement(
						"select title, author from book join bookachete on book.id = bookachete.id_book where bookachete.id_client = ?")) {

			livreParClient.setInt(1, c.getId());

			ResultSet resultSet = livreParClient.executeQuery();

			while (resultSet.next()) {

				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				System.out.println(title + " " + author);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void clientsParLivre(Book b) {
		String url = "jdbc:postgresql://localhost:5432/tpJDBC";
		try (Connection conn = DriverManager.getConnection(url, "postgres", "azerty");
				PreparedStatement clientsParLivre = conn.prepareStatement(
						"select lastname, firstname from client join bookachete on client.id = bookachete.id_client where bookachete.id_book = ?")) {

			clientsParLivre.setInt(1, b.getId());

			ResultSet resultSet = clientsParLivre.executeQuery();

			while (resultSet.next()) {
				System.out.println(b.getTitle());
				String nom = resultSet.getString("lastname");
				String prenom = resultSet.getString("firstname");
				System.out.println(nom + " " + prenom);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
