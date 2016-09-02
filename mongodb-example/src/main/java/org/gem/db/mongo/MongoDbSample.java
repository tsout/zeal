package org.gem.db.mongo;

import java.io.IOException;
import java.util.Date;

import org.gem.db.mongo.dao.EventDao;
import org.gem.db.mongo.dao.MediaDao;
import org.gem.db.mongo.dao.dto.Event;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.gridfs.GridFS;

public class MongoDbSample {

	public static void main(String args[]) {
		EventDao eventDao= new EventDao();
		
		MediaDao mediaDao= new MediaDao(); 
	
		try {
			eventDao.initCollection();
			mediaDao.initCollection();
	
			eventDao.printCollectionStats();
//			dao.printCollectionValues();
			
			MongoDbSample.seedData(eventDao);
			
			eventDao.printCollectionStats();
//			dao.printCollectionValues();
			
			eventDao.findStuff(); 
			
			eventDao.deleteStuff();
			
			eventDao.printCollectionStats();
//			dao.printCollectionValues();
//			dao.printDocsAsJson();
			
			demonstrateImagePersistence(mediaDao);
			
			mediaDao.reset();
			mediaDao.browse();
			
			mediaDao.terminate();
			eventDao.terminate();
	
		} catch (Exception e) {
	System.err.println("error occurred" );
	System.err.println(e.getMessage());
	e.printStackTrace(System.err);
		} finally {
			eventDao.terminate();
		}
	
	}

	protected static void demonstrateImagePersistence(MediaDao mediaDao) throws IOException {
		mediaDao.upload("jordan and alyssa 1", "/Users/tim/Desktop/IMG_5840.jpg", null);
		mediaDao.upload("jordan and alyssa 2", "/Users/tim/Desktop/IMG_5840.jpg", null);
		String fileName = "jordan and alyssa 3";
		mediaDao.upload(fileName, "/Users/tim/Desktop/IMG_5840.jpg", null);
		mediaDao.browse();
		mediaDao.downloadToPath(fileName,"/Users/tim/Desktop/test.jpg");
		mediaDao.downloadToPath(fileName,"/Users/tim/Desktop/test2.jpg");
		mediaDao.downloadToPath(fileName,"/Users/tim/Desktop/test3.jpg");
	}

	static void seedData(EventDao dao) {
		System.out.println(">seedData");
		for (int x =0; x< 10000; x++)
		{
			Event event = new Event();
			event.description= "new description "+ x; 
			event.eventDateTime = new Date();
			event.eventId= Integer.toString(x); 
			event.location = new Point(new Position(-37.0003, new Double(x))); 
			event.eventName= "Event ["+x+"]";
			event.eventType="stuff";
//			event.media = new GridFS(dao.db, "media");
			dao.add(event);
		}
		System.out.println("<seedData");
	}

}
