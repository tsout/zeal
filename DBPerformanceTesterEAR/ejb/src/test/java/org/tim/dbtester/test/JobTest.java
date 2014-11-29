package org.tim.dbtester.test;


import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.tim.dbtester.model.Job;
import org.zeal.common.persistence.test.PersistentObjectTest;

public class JobTest extends PersistentObjectTest<Job> {

	public JobTest() throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		super(Job.class);
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
