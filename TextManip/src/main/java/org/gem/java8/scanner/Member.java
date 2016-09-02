package org.gem.java8.scanner;

import java.util.HashMap;
import java.util.Map;

public class Member {
	
	public String firstName,lastName,email,phone,city,state,zip, country, streetNumber,street1,street2,street3 = null;
	Map<String,String> phoneNumbers=new HashMap<String,String>();
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [firstName=").append(firstName).append(", lastName=").append(lastName).append(", email=")
				.append(email).append(", phone=").append(phone).append(", city=").append(city).append(", state=")
				.append(state).append(", zip=").append(zip).append(", country=").append(country)
				.append(", streetNumber=").append(streetNumber).append(", street1=").append(street1)
				.append(", street2=").append(street2).append(", street3=").append(street3).append(", phoneNumbers=")
				.append(phoneNumbers).append("]");
		return builder.toString();
	}
	

}
