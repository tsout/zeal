package org.gem.test.utility;

/**
 * test class to validate POJO tester behavior
 * @author tim
 *
 */
public class ValidClass {
	boolean test = false;
	
	@SuppressWarnings("unused")
	public ValidClass(){//noop
	}
	public String toString() {
		return "";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (test ? 1231 : 1237);
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidClass other = (ValidClass) obj;
		if (test != other.test)
			return false;
		return true;
	}


}
