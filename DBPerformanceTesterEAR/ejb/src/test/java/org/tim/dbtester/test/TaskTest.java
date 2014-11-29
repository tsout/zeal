package org.tim.dbtester.test;


import java.lang.reflect.InvocationTargetException;

import org.tim.dbtester.model.Task;
import org.zeal.common.persistence.test.PersistentObjectTest;

public class TaskTest extends PersistentObjectTest<Task> {

	public TaskTest() throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		super(Task.class);
	}


}
