package org.tim.dbtester.test;

import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.tim.dbtester.model.BasicEntity;
import org.zeal.common.persistence.test.PersistentObjectTest;

public class BasicEntityTest extends PersistentObjectTest<BasicEntity> {

	public BasicEntityTest() throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		super(BasicEntity.class);
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

}
