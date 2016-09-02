package org.gem.db.mongo.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bson.Document;
import org.gem.db.mongo.dao.api.IDao;
import org.gem.db.mongo.dao.api.IMediaStore;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MediaDao extends AbstractMongoDao<GridFSInputFile>implements IMediaStore, IDao {

	private static final String MEDIA = "media";

	private static String dbName = null; // use default from properties

	private GridFS gfsPhoto = null;

	public MediaDao() {
		super(GridFSInputFile.class);
	}

	public String upload(String fileName, String path, String fileType) {

		String newFileName = fileName;
		File imageFile = new File(path);

		// initialize collection.. what if it already exists?

		GridFSInputFile gfsFile;
		try {
			gfsFile = gfsPhoto.createFile(imageFile);
			gfsFile.setFilename(newFileName);
			gfsFile.save();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return newFileName;
	}

	public String download(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Document> find(Document criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public void browse() {
		System.out.println("> browsing");
		DBCursor cursor = gfsPhoto.getFileList();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public void initCollection() {
		initCollection(getDbName("localhost"), getPort(), getDbName("worldofdata"), getCollectionName(),
				GridFSInputFile.class);
		gfsPhoto = new GridFS((DB) getClient().getDB(getDbName(dbName)), getCollectionName());
	}

	private String getCollectionName() {
		return MEDIA;
	}

	public void downloadToPath(String fileName, String targetPath) throws IOException {
		System.out.println(">downloadToPath");
		GridFSDBFile data = gfsPhoto.findOne(fileName);
		data.writeTo(targetPath);
	}

	/**
	 * eliminates the media GRIDFS data store (files and chunks).. USE WITH
	 * CAUTION
	 */
	public void reset() {
		System.out.println(">reset");
		getDb().getCollection(getCollectionName() + ".files").drop();
		getDb().getCollection(getCollectionName() + ".chunks").drop();

	}

}
