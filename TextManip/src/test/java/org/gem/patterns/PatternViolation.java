package org.gem.patterns;

public class PatternViolation {
	
	private String violationCode;
	private String violationDetails;
	private String violationRecommendation;

	public PatternViolation(String code, String detail, String recommendation) {
	
		//TODO: validate inputs
		violationCode = code;
		violationDetails = detail;
		violationRecommendation = recommendation;
	}

	/**
	 * @return the violationCode
	 */
	public final String getViolationCode() {
		return violationCode;
	}

	/**
	 * @return the violationDetails
	 */
	public final String getViolationDetails() {
		return violationDetails;
	}

	/**
	 * @return the violationRecommendation
	 */
	public final String getViolationRecommendation() {
		return violationRecommendation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PatternViolation [violationCode=").append(violationCode).append(", violationDetails=")
				.append(violationDetails).append(", violationRecommendation=").append(violationRecommendation)
				.append("]");
		return builder.toString();
	}
	
	
}
