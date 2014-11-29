package org.tim.dbtester.model;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import org.zeal.common.persistence.PersistableObject;

/**
 * Entity implementation class for Entity: JobRequest
 *
 */
@Entity (name=JobRequest.TABLE_NAME)

public class JobRequest implements PersistableObject, Serializable {

	   
	private static final long serialVersionUID = -3066596507270402138L;

	public static final String TABLE_NAME = "Job_Requests";
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable=false)
	private String customerName; 
	@Column(nullable=false)
	private String jobName; 
	private String jobDescription;
	private Calendar dateRequested;
	private Calendar lastUpdated; 
	private Calendar dateContractSigned; 
	private Calendar requestedStartDate; 
	private Calendar requestedEndDate; 
//	private List<Calendar> requestedBlackOutDates;
//	private List<Calendar> preferredWorkDates;
	private Double maxCost; 
	private Double preferredCost; 
	
	@OneToMany	
	private List<Task> tasks; 
	

	public JobRequest() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public Calendar getDateRequested() {
		return dateRequested;
	}
	public void setDateRequested(Calendar dateRequested) {
		this.dateRequested = dateRequested;
	}
	public Calendar getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Calendar lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Calendar getDateContractSigned() {
		return dateContractSigned;
	}
	public void setDateContractSigned(Calendar dateContractSigned) {
		this.dateContractSigned = dateContractSigned;
	}
	public Calendar getRequestedStartDate() {
		return requestedStartDate;
	}
	public void setRequestedStartDate(Calendar requestedStartDate) {
		this.requestedStartDate = requestedStartDate;
	}
	public Calendar getRequestedEndDate() {
		return requestedEndDate;
	}
	public void setRequestedEndDate(Calendar requestedEndDate) {
		this.requestedEndDate = requestedEndDate;
	}
//	public List<Calendar> getRequestedBlackOutDates() {
//		return requestedBlackOutDates;
//	}
//	public void setRequestedBlackOutDates(List<Calendar> requestedBlackOutDates) {
//		this.requestedBlackOutDates = requestedBlackOutDates;
//	}
//	public List<Calendar> getPreferredWorkDates() {
//		return preferredWorkDates;
//	}
//	public void setPreferredWorkDates(List<Calendar> preferredWorkDates) {
//		this.preferredWorkDates = preferredWorkDates;
//	}
	public Double getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(Double maxCost) {
		this.maxCost = maxCost;
	}
	public Double getPreferredCost() {
		return preferredCost;
	}
	public void setPreferredCost(Double preferredCost) {
		this.preferredCost = preferredCost;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	@Override
	public String toString() {
		return "JobRequest [id=" + id + ", customerName=" + customerName
				+ ", jobName=" + jobName + ", jobDescription=" + jobDescription
				+ ", dateRequested=" + dateRequested + ", lastUpdated="
				+ lastUpdated + ", dateContractSigned=" + dateContractSigned
				+ ", requestedStartDate=" + requestedStartDate
				+ ", requestedEndDate=" + requestedEndDate
//				+ ", requestedBlackOutDates=" + requestedBlackOutDates
//				+ ", preferredWorkDates=" + preferredWorkDates 
				+ ", maxCost="
				+ maxCost + ", preferredCost=" + preferredCost + "]";
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
		if (!(obj instanceof JobRequest)) {
			return false;
		}
		JobRequest other = (JobRequest) obj;
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
	@Override
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getMappedTableName() {
		return TABLE_NAME;
	}
   
}
