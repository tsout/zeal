package org.tim.dbtester.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.zeal.common.persistence.PersistableObject;

/**
 * Entity implementation class for Entity: BasicEntity
 * 
 */
@Entity(name = BasicEntity.TABLE_NAME)
public class BasicEntity implements PersistableObject {

	static final String TABLE_NAME = "basic_entity";

	@Override
	public String toString() {
		return "BasicEntity [Name=" + Name + ", Description=" + Description
				+ ", cost=" + cost + ", startDate=" + startDate + ", endDate="
				+ endDate + ", id=" + id + "]";
	}

	@Column(name = "name", nullable = false)
	private String Name;

	@Column(name = "description", nullable = true)
	private String Description;

	@Column(name = "cost", nullable = true)
	private double cost;

	@Column(name = "start_date", nullable = true)
	private Calendar startDate;
	@Column(name = "end_date", nullable = true)
	private Calendar endDate;

	@Column(name = "category", nullable = true)
	private String category;

	@Column(name = "category2", nullable = true)
	private String category2;

	@Column(name = "category3", nullable = true)
	private String category3;

	@Id
	@GeneratedValue
	private Integer id;
	private static final long serialVersionUID = 1L;

	public BasicEntity() {
		super();
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Calendar getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

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
		if (!(obj instanceof BasicEntity)) {
			return false;
		}
		BasicEntity other = (BasicEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String getMappedTableName() {
		return BasicEntity.TABLE_NAME;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

}
