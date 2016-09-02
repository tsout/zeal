package org.gem.db.mongo.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.gem.db.mongo.dao.api.IDao;
import org.gem.db.mongo.dao.dto.Event;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class EventDao extends AbstractMongoDao<Event> implements IDao {

	public EventDao() {
		super(Event.class);

	}

	public void add(Event document) {
		getCollection().insertOne(document);
//		System.out.println("document added\t\t"+ document);
	}
	
	public void initCollection() {
		
		initCollection(getHostName(null),getPort(), getDbName(null),getCollectionName(null),Event.class);
	}

	/**
	 * Admittedly a terrible way to access a large data store
	 * @return
	 */
	public List<Event> findStuff(){
		//TODO fix implementation
		System.out.println(">findStuff");
		List<Event> list = new ArrayList<Event> ();
		
		Document filter = new Document("eventType","stuff");
		System.out.println("number of records: \t"+getCollection().count(filter));
		FindIterable<Event> stuff = getCollection().find(filter);
		MongoCursor<Event> it = stuff.iterator();
		while (it.hasNext()){
			list.add(it.next());
		}
		System.out.println("<findStuff");
		return list; 
	}


	public <T extends Document> void update(T object){
		
	}

	public <T extends Document>void  delete (T object){
		
	}

	public void deleteStuff(){
		getCollection().deleteMany(eq("eventType", "stuff"));
	}

	public void printCollectionStats() {
		System.out.println("docs found\t" + getCollection().count());
	}

	void printCollectionValues() {
		
		Block<Event> printEventBlock = new Block<Event>() {
		    public void apply(final Event e) {
		        System.out.println(e);
		    }
		};

		getCollection().find().forEach(printEventBlock);
		
		
	}
	
	void printDocsAsJson() {
		Block<Event> printEventBlock = new Block<Event>() {
		    public void apply(final Event e) {
		        System.out.println();
		    }
		};

		getCollection().find().forEach(printEventBlock);
	}

}
