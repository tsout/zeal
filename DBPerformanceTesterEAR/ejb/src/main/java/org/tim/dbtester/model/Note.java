package org.tim.dbtester.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.zeal.common.persistence.PersistableObject;

/**
 * Entity implementation class for Entity: Note
 * 
 */
@Entity(name = Note.TABLE_NAME)
@XmlRootElement
public class Note implements PersistableObject, Serializable {

	public static final String TABLE_NAME = "Notes";

	@ManyToOne
	@JoinColumn(name = "fk_job")
	private Job job;

	@ManyToOne
	@JoinColumn(name = "fk_job_request")
	private JobRequest jobRequest;

	@Id
	private UUID id;
	private String title;
	private String description;
	private Integer version;
	private String author;
	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Note)) {
			return false;
		}
		Note other = (Note) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Note() {
		super();
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID noteId) {
		this.id = noteId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String getMappedTableName() {
		return Note.TABLE_NAME;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public JobRequest getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(JobRequest jobRequest) {
		this.jobRequest = jobRequest;
	}

	public Note(UUID noteId, String title, String description, Integer version,
			String author) {
		super();
		this.id = noteId;
		this.title = title;
		this.description = description;
		this.version = version;
		this.author = author;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + id + ", title=" + title + ", description="
				+ description + ", version=" + version + ", author=" + author
				+ "]";
	}

}
