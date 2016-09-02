package org.gem.db.mongo.dao.dto;

import com.mongodb.client.gridfs.model.GridFSFile;

public class Media {

	GridFSFile media; 
	// describes the type of media e.g. PNG, BPM, MP3, MP4,etc
	String mediaType;
	/**
	 * @return the media
	 */
	public final GridFSFile getMedia() {
		return media;
	}
	/**
	 * @param media the media to set
	 */
	public final void setMedia(GridFSFile media) {
		this.media = media;
	}
	/**
	 * @return the mediaType
	 */
	public final String getMediaType() {
		return mediaType;
	}
	/**
	 * @param mediaType the mediaType to set
	 */
	public final void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Media [media=").append(media).append(", mediaType=").append(mediaType).append("]");
		return builder.toString();
	} 
	
}
