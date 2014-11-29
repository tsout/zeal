package org.zeal.common.persistence;

import java.io.Serializable;

public interface PersistableObject extends Serializable {
	public Integer getVersion();
	public int hashCode();
	public boolean equals(Object o);
	public String toString(); 
	public String getMappedTableName();

}
