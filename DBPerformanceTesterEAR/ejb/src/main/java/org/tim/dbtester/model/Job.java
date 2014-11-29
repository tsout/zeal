package org.tim.dbtester.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.zeal.common.persistence.PersistableObject;

/**
 * Entity implementation class for Entity: Job
 * 
 */
@Entity(name = Job.TABLE_NAME)
public class Job implements PersistableObject, Serializable {

	private static final long serialVersionUID = 4842969065843138489L;

	@Id
	@GeneratedValue
	private Integer id;
	private Calendar actualStartDate;
	private Calendar projectedStartDate;
	private Calendar estimatedStartDate;

	@OneToMany
	private List<Note> notes;

	private String jobName;
	private Double cost;
	private Double bid;
	private String location;
	private String billTo;
	private String jobType;
	private String customerName;
	public static final String TABLE_NAME = "Jobs";

	@OneToOne
	private JobRequest originatingRequest;

	@Override
	public String toString() {
		String jobRequestString = ", jobRequest ["
				+ originatingRequest.getJobName() + ","
				+ originatingRequest.getId() + "]";
		return "Job [id=" + id + ", actualStartDate=" + actualStartDate
				+ ", projectedStartDate=" + projectedStartDate
				+ ", estimatedStartDate=" + estimatedStartDate + ", notes="
				+ notes + ", jobName=" + jobName + ", cost=" + cost + ", bid="
				+ bid + ", location=" + location + ", billTo=" + billTo
				+ ", jobType=" + jobType + ", customerName=" + customerName
				+ ((jobRequestString != null) ? jobRequestString : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
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
		if (!(obj instanceof Job)) {
			return false;
		}
		Job other = (Job) obj;
		if (customerName == null) {
			if (other.customerName != null) {
				return false;
			}
		} else if (!customerName.equals(other.customerName)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (jobName == null) {
			if (other.jobName != null) {
				return false;
			}
		} else if (!jobName.equals(other.jobName)) {
			return false;
		}
		return true;
	}

	public Job() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getActualStartDate() {
		return this.actualStartDate;
	}

	public void setActualStartDate(Calendar actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Calendar getProjectedStartDate() {
		return this.projectedStartDate;
	}

	public void setProjectedStartDate(Calendar projectedStartDate) {
		this.projectedStartDate = projectedStartDate;
	}

	public Calendar getEstimatedStartDate() {
		return this.estimatedStartDate;
	}

	public void setEstimatedStartDate(Calendar estimatedStartDate) {
		this.estimatedStartDate = estimatedStartDate;
	}

	public List<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public String getTitle() {
		return this.jobName;
	}

	public void setTitle(String title) {
		this.jobName = title;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getBid() {
		return this.bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBillTo() {
		return this.billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
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

	public JobRequest getOriginatingRequest() {
		return originatingRequest;
	}

	public void setOriginatingRequest(JobRequest originatingRequest) {
		this.originatingRequest = originatingRequest;
	}

}
