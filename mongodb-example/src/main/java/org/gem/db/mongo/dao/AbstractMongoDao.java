package org.gem.db.mongo.dao;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.gem.db.mongo.Utility;
import org.omg.PortableServer.IdAssignmentPolicyOperations;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public abstract class AbstractMongoDao<T> {

	/**
	 * Iterable Representation of a collection
	 */
	private MongoCollection<T> eventCollection = null;
	/**
	 * synchronous client of mongo db
	 */
	private MongoClient client = null;
	private MongoDatabase db = null;

	private Class<T> classType = null;

	private AbstractMongoDao() {
		// noop
	}

	public AbstractMongoDao(Class<T> clazz)

	{
		setClassType(clazz);

	}

	/**
	 * Assumes default codec registry and that dao connections are established
	 * in property file
	 * 
	 * issues: connection properties may change based on pojo type
	 * 
	 * @param collectionTypeClazz
	 */
	protected void initCollection(String hostName, int port, String dbName, String collectionName, Class<T> collectionTypeClazz) {

		//TODO:  account for properties specific to clazz type
		
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		// get the client
		setClient(new MongoClient(getHostName(hostName), getPort()));
		// connect to the database
		setDb(getClient().getDatabase(getDbName(dbName)));
		System.out.println("db connection created");
		// retrieve reference to collection of documents (aka doc table)
		setCollection(db.getCollection(getCollectionName(collectionName), collectionTypeClazz));
		// configure the codec at the collection level (vs. the db or client
		// level)
		setCollection(getCollection().withCodecRegistry(pojoCodecRegistry));
		System.out.println("initialized for collection of type:\t" + collectionTypeClazz.getName());
	}

	protected int getPort() {
		return 27017;
	}

	protected String getHostName(String defaultHostName) {
		return Utility.getProperty("db.hostname", defaultHostName);
	}

	protected String getDbName(String defaultDbName) {
		return Utility.getProperty("db.name", defaultDbName);
	}

	protected String getCollectionName(String defaultCollectionName) {
		return Utility.getProperty("db.collection", defaultCollectionName);
	}

	public void terminate() {
		client.close();
		eventCollection = null;
		db = null;
	}

	/**
	 * @return the eventCollection
	 */
	public final MongoCollection<T> getCollection() {
		return eventCollection;
	}

	/**
	 * @param eventCollection
	 *            the eventCollection to set
	 */
	public final void setCollection(MongoCollection<T> eventCollection) {
		this.eventCollection = eventCollection;
	}

	/**
	 * @return the client
	 */
	public final MongoClient getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	protected void setClient(MongoClient client) {
		this.client = client;
	}

	/**
	 * @return the db
	 */
	public final MongoDatabase getDb() {
		return db;
	}

	/**
	 * @param db
	 *            the db to set
	 */
	protected void setDb(MongoDatabase db) {
		this.db = db;
	}

	/**
	 * @return the classType
	 */
	public final Class<T> getClassType() {
		return classType;
	}

	/**
	 * @param clazz
	 *            the classType to set
	 */
	protected void setClassType(Class<T> clazz) {
		this.classType = clazz;
	}

}
