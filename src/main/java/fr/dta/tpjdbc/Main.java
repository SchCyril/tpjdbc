package fr.dta.tpjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iocean.IOcean;

/**
 * Hello world!
 *
 */
public class Main {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(Traitement.class);

	public static void main(String[] args) {
		
		IOcean.printSomething();
		String url = "jdbc:postgresql://localhost:5432/tpJDBC";
		try (Connection conn = DriverManager.getConnection(url, "postgres", "azerty");
				Statement stmt = conn.createStatement()) {

			stmt.executeUpdate("drop table if exists Book cascade");
			stmt.executeUpdate("drop table if exists Client cascade");
			stmt.executeUpdate("drop table if exists BookAchete cascade");

			stmt.executeUpdate(
					"create table Book(id serial primary key, title varchar(255) NOT NULL, author varchar(255))");
			stmt.executeUpdate(
					"create table Client(id serial primary key, lastname varchar(255) NOT NULL, firstname varchar(255) NOT NULL, gender varchar(1), favbook int, constraint fk_favbook foreign key (favbook) references Book(id))");
			stmt.executeUpdate(
					"create table BookAchete(id_client int not null, id_book int not null, primary key(id_client, id_book),"
							+ " Constraint fk_client foreign key (id_client) references Client(id), Constraint fk_book foreign key (id_book) references Book(id))");

			Book b1 = new Book("Bonjour", "aaaaaaaaa");
			Book b2 = new Book("Arthas", "Cyril");
			Book b3 = new Book("Au revoir", "bbbbbbbbb");
			Traitement.addBook(b1);
			Traitement.addBook(b2);
			Traitement.addBook(b3);

			Client c1 = new Client("Payan", "Benjamin", "M", b2.getId());
			Client c2 = new Client("Mattera", "Lorick", "M", b3.getId());
			Client c3 = new Client("Desplat", "Matthieu", "M", b1.getId());
			Traitement.addClient(c1);
			Traitement.addClient(c2);
			Traitement.addClient(c3);

			Traitement.achatClient(b1, c1);
			Traitement.achatClient(b2, c1);
			Traitement.achatClient(b1, c2);

			Traitement.livreParClient(c1);
			Traitement.clientsParLivre(b1);
			// Traitement.achatClient(b3, c3);

		} catch (SQLException e) {

			LOGGER.warn("attention");
		}

	}

}
