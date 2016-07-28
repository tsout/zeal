package org.gem.text.model;

import java.util.Arrays;
import java.util.List;

import org.gem.test.utility.PojoTester;
import org.junit.Test;

public class StippelTest {

	@Test
	public void testProperties() throws Exception {
		List<String> blackList = Arrays.asList("serialVersionUID");
		PojoTester.testPojoAccesorsAndMutators(Stippel.class, blackList);

	}

	@Test
	public void testToString() throws Exception {
		PojoTester.testPojoToStringMethod(Stippel.class);

	}

	@Test
	public void testSerializable() throws Exception {
		PojoTester.testPojoSerialization(Stippel.class);
	}

	@Test
	public void testEquals() throws Exception {
		PojoTester.testPojoEqualsMethod(Stippel.class);
	}

}
