package org.zeal.common.persistence.test;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zeal.common.persistence.PersistableObject;

public abstract class PersistentObjectTest<T> extends ObjectTester<T> {
	
	Class<T> clazz; 
	T uut; 

	private final Constructor<? extends T> ctor; 
	
	@SuppressWarnings("unchecked")
	public PersistentObjectTest(Class <T> clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		super();
		this.clazz = clazz;
		ctor = clazz.getConstructor(); 
		uut = ctor.newInstance();
		this.setObjectUnderTest(uut);
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		uut = null;
		clazz = null;
	}

		
	
	@Test
	public void testHasImplementsRequiredMethods() {
		assertHasRequiredMethods(PersistableObject.class.getMethods());
		assertHasRequiredMethods(Serializable.class.getMethods());
	}

	@Test
	public void testPersistantObjectsAreEqualByBusinessKey() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		uut = ctor.newInstance();
		T uut2 = ctor.newInstance();
		
				
		fail("Not yet Implemented");
		
	}

	@Test
	public void testExtendsRequiredInterfaces() {
		assertHasRequiredInterfaces(PersistableObject.class);
	}

}
