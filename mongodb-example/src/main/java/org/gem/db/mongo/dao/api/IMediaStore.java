package org.gem.db.mongo.dao.api;

import java.util.List;

import org.bson.Document;

public interface IMediaStore {

	public String download(String fileName);
	public List<Document> find( Document criteria);
	String upload(String fileName, String path, String fileType);
}
