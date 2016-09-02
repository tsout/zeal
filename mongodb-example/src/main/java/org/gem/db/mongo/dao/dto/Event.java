package org.gem.db.mongo.dao.dto;

import java.util.Date;

import org.bson.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.geojson.Point;

public class Event {
	public Event() {
		super();
	}

	public String eventName;
	
	//e.g. visual event, thought, observation, etc.
	public String eventType;

	public String description;

	// Be careful when creating a id property AND using default codecs, mongo
	// tries to decode _id to String type when it should be mapped to a ObjectId
	public String eventId;
	public Date eventDateTime;
	// csv string w/ terms describing the event
	public  String eventTags;

	//location describing the event on earth
	public Point location;

	/**
	 * @return the eventName
	 */
	public final String getEventName() {
		return eventName;
	}


	/**
	 * @param eventName
	 *            the eventName to set
	 */
	public final void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the eventType
	 */
	public final String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public final void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the eventDateTime
	 */
	public final Date getEventDateTime() {
		return eventDateTime;
	}

	/**
	 * @param eventDateTime
	 *            the eventDateTime to set
	 */
	public final void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	/**
	 * @return the eventId
	 */
	public final String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public final void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the location
	 */
	public final Point getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public final void setLocation(Point location) {
		this.location = location;
	}


	/**
	 * @return the eventTags
	 */
	public final String getEventTags() {
		return eventTags;
	}


	/**
	 * @param eventTags the eventTags to set
	 */
	public final void setEventTags(String eventTags) {
		this.eventTags = eventTags;
	}

}
