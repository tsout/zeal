package org.gem.patterns.dto;

import static org.junit.Assert.*;

import org.gem.patterns.PatternValidator;
import org.gem.patterns.PatternViolationException;
import org.junit.Test;

public class DtoPatternValidatorTest {

	@Test
	public void test() throws PatternViolationException {
		PatternValidator pv = (PatternValidator) new DtoPatternValidator<IValidDto>((IValidDto)new ValidDto());
		pv.validate();
	}

	@Test
	public void testInvalidDto() {
		PatternValidator pv = (PatternValidator) new DtoPatternValidator<InvalidDtoWithNoInterface>(
				new InvalidDtoWithNoInterface());
		try {
			pv.validate();
		} catch (PatternViolationException e) {
			System.out.println(e.getViolationList());
			assertTrue(e.getViolationList().size() == 3);
		}
	}

	@Test
	public void testInvalidDto2(){
		PatternValidator pv = (PatternValidator) new DtoPatternValidator<InvalidDtoWithInterface>(
				new InvalidDtoWithInterface());
		try {
			pv.validate();
		} catch (PatternViolationException e) {
			System.out.println(e.getViolationList());
			assertTrue(e.getViolationList().size() == 3);
		}
	}
	@Test
	public void testInvalidDto3() {
		PatternValidator pv = (PatternValidator) new DtoPatternValidator<InvalidDtoInterface>(
				(InvalidDtoInterface)new InvalidDtoWithInterface());
		try {
			pv.validate();
		} catch (PatternViolationException e) {
			System.out.println(e.getViolationList());
			assertTrue(e.getViolationList().size() == 3);
		}
	}

}
