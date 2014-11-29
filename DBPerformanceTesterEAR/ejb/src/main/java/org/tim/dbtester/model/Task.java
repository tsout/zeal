package org.tim.dbtester.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.zeal.common.persistence.PersistableObject;

@Entity (name = Task.TABLE_NAME)
public class Task implements PersistableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -669247948407916081L;
	public static final String TABLE_NAME="Tasks";
	
	@Id
	@GeneratedValue
	private Integer id; 
	private String title; 
	private String description; 
	private Calendar mustBeCompletedBy; 
	private Calendar mustBeStartedOn; 

	@ManyToOne
	@JoinColumn(name="fk_task_job_request")
	private JobRequest jobRequest; 
	
			
	public JobRequest getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(JobRequest jobRequest) {
		this.jobRequest = jobRequest;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description="
				+ description + ", mustBeCompletedBy=" + mustBeCompletedBy
				+ ", mustBeStartedOn=" + mustBeStartedOn + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Task)) {
			return false;
		}
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	@Override
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMappedTableName() {
		return TABLE_NAME;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getMustBeCompletedBy() {
		return mustBeCompletedBy;
	}

	public void setMustBeCompletedBy(Calendar mustBeCompletedBy) {
		this.mustBeCompletedBy = mustBeCompletedBy;
	}

	public Calendar getMustBeStartedOn() {
		return mustBeStartedOn;
	}

	public void setMustBeStartedOn(Calendar mustBeStartedOn) {
		this.mustBeStartedOn = mustBeStartedOn;
	}

	
}
