package fr.iocean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOcean {

	private static final Logger LOG = LoggerFactory.getLogger(IOcean.class);
	
	private IOcean() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static void printSomething() {
		LOG.debug("IOCEAN");
	}
	
}
