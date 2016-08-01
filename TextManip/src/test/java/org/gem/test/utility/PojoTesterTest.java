package org.gem.test.utility;

import static org.junit.Assert.*;

import org.junit.Test;

public class PojoTesterTest {

	

	@Test(expected=AssertionError.class)
	public void testEqualsCheck() throws InstantiationException, IllegalAccessException {

		PojoTester.<ValidClass>testPojoEqualsMethod(InvalidClass.class);
	}

	@Test(expected=AssertionError.class)
	public void testToStringCheck() {
		PojoTester.testPojoToStringMethod(InvalidClass.class);
	}
	
	@Test
	public void testEqualsCheck2() throws InstantiationException, IllegalAccessException {

		PojoTester.<ValidClass>testPojoEqualsMethod(ValidClass.class);
	}

	@Test
	public void testToStringCheck2() {
		PojoTester.testPojoToStringMethod(ValidClass.class);
	}
	
	@Test
	public void testHasConstructorInvalid() throws Exception {
		PojoTester.testPojoHasPublicConstructor(InvalidClass.class);
	}
	
	@Test
	public void testHasConstructorValid() throws Exception {
		PojoTester.testPojoHasPublicConstructor(ValidClass.class);
	}


}
