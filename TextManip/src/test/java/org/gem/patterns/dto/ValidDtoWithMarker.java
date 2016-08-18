package org.gem.patterns.dto;

import java.io.Serializable;

/**
 * A data transfer object (DTO), is an immutable object that can be used to
 * transfer a two or more data attributes/fields between architectural layers.
 * The immutability of a DTO prevents accidental value changes in the object's
 * fields. It also facilitates concurrency as two or more threads can process a
 * DTO w/o risk of a read/write conflict.
 * 
 * <br>
 * <br>
 * DTO's can be composed to return different combinations of data from the same
 * data source. DTO's are often returned by remote or external method
 * interfaces. For example, a web service facade that calls a method from a
 * third party library, or a invokes a remote method on an ejb that is deployed
 * in another JVM.
 * 
 * <br>
 * <br>
 * <b>Pattern Rules:</b>
 * <ul>
 * <li>A DTO must be immutable, implementing an interface that only has accessor
 * methods.
 * <li>it must extend the Serializable
 * <li>all dto fields must be private
 * </ul>
 * interface.
 * 
 * @author tim
 *
 */
public class ValidDtoWithMarker implements IValidDto, DtoPattern {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9142006948537055204L;

	private String field = "test";

	/**
	 * Implements a method from a DTO interface
	 */
	@Override
	public String getField1() {
		return field;
	}

}
