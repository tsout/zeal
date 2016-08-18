package org.gem.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatternViolationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5809734205792422989L;
	private List<PatternViolation> violationList = new ArrayList<PatternViolation>();
	/**
	 * @return the violationList
	 */
	public final List<PatternViolation> getViolationList() {
		return violationList;
	}

	public PatternViolationException(String message,  PatternViolation... violations) {
		super(message);
		violationList = Arrays.asList(violations);
	}
	
	public PatternViolationException(String message, List<PatternViolation> violations) {
		super(message);
		violationList.addAll(violations);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PatternViolationException. ["+violationList.size()+"] violations. \n[violations:");
		for(PatternViolation v: violationList){
			builder.append("\n").append(v);
		}
		builder.append("]\n");
		return builder.toString();
	}
	
}
