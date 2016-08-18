package org.gem.patterns.dto;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gem.patterns.PatternValidator;
import org.gem.patterns.PatternViolation;
import org.gem.patterns.PatternViolationException;
import org.gem.test.utility.ReflectionUtil;
import org.junit.Test;

public class DtoPatternValidator<T> implements PatternValidator {

	T candidateDto;

	DtoPatternValidator(T candidate) {
		if (candidate == null) {
			throw new InvalidParameterException("Candidate can not be null");
		}
		this.candidateDto = candidate;
	}

	@Override
	public void validate() throws PatternViolationException {
		System.out.println("\n\nvalidating object pattern");
		List<PatternViolation> violationList = new ArrayList<PatternViolation>();

		if (!isSerializable(candidateDto)) {
			violationList.add(new PatternViolation("", "A DTO must implement the Serializable interface",
					"add 'implements Serializable' to DTO declaration or one of its interfaces"));
		}
		if (!hasAccessorMethodsOnly(candidateDto)) {
			violationList.add(new PatternViolation("", "A DTO must be immutable",
					"ensure that this DTO interface only has accessor ('getter') methods."));
		}

		if (!allFieldsArePrivate(candidateDto)) {
			violationList.add(new PatternViolation("", "Fields in a DTO should not be accessible",
					"Make all field modifiers private"));
		}

		if (!hasDtoPatternMarkerInterface(candidateDto)) {
			violationList.add(new PatternViolation("", "Object should implement DtoPattern interface",
					"add 'implements DtoPatternMarker' to class declaration"));
		}

		if (violationList.size() > 0)
			throw new PatternViolationException("Not a valid DTO", violationList);
	}

	private boolean hasDtoPatternMarkerInterface(T candidateDto2) {
		// TODO Auto-generated method stub
		return ReflectionUtil.hasInterface(candidateDto2.getClass(), "org.gem.pattern.dto.DtoPattern");
	}

	private boolean allFieldsArePrivate(T candidateDto2) {
		ArrayList<Field> invalidFields = new ArrayList<Field>();
		for (Field r : candidateDto2.getClass().getDeclaredFields()) {
			if (r.isAccessible()) {
				invalidFields.add(r);
				System.out.println("invalid field : " + r.getName());
			}
		}
		if (invalidFields.size() > 0) {
			return false;
		}
		return true;
	}

	// TODO: update rule evaluation methods
	private boolean hasAccessorMethodsOnly(T candidateDto2) {
		// System.out.println("verifying accesor methods only");
		ArrayList<Method> invalidMethods = new ArrayList<Method>();
		for (Method m : candidateDto2.getClass().getDeclaredMethods()) {
			if (hasInputParameters(m) || !hasVoidReturnType(m)) {
				invalidMethods.add(m);
				System.out.println("invalid method : " + m.getName());
			}
		}

		if (invalidMethods.size() > 0) {
			return false;
		}
		return true;
	}

	private boolean hasVoidReturnType(Method m) {
		// System.out.println("checking return type: "+ m.getReturnType());

		return m.getReturnType() != Void.class;
	}

	private boolean hasInputParameters(Method m) {
		System.out.println("checking for input params: " + m.getParameterCount());
		return m.getParameterCount() > 0;
	}

	private boolean isSerializable(T candidateDto2) {
		boolean isSerializable = ReflectionUtil.hasInterface(candidateDto2.getClass(), "java.io.Serializable");
		System.out.println("implements Serializable:" + isSerializable);
		return isSerializable;
	}

}
