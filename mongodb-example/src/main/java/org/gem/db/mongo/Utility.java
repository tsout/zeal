package org.gem.db.mongo;

public class Utility {

	/**
	 * Simulates a property file lookup
	 * @param propertyKey
	 * @param defaultKey
	 * @return String
	 */
	public static String getProperty(String propertyKey, String defaultKey) {
		if (propertyKey.equals("db.collection"))
			return EVENTS_COLLECTION;
	else if(propertyKey.equals("db.name"))
		 return WORLDOFDATA;
		else if(propertyKey.equals("db.hostname"))
			return LOCALHOST;
		
			return defaultKey;
	}

	static final String EVENTS_COLLECTION = "events";
	private static final String LOCALHOST = "localhost";
	private static final String WORLDOFDATA = "worldofdata";
}
