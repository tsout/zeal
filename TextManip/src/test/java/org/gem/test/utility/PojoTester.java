package org.gem.test.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.gem.text.model.Stippel;
import org.mockito.Mockito;

public class PojoTester {
	private PojoTester() {
	}

	public static void testPojoSerialization(Class<?> uut){
		assertTrue("Pojos must implement the Serializable interface",hasInterface(uut,"java.io.Serializable"));
	}; 
	
	public static void testPojoEqualsMethod(Class<?> uut){assertTrue("Pojos must implement the equals method",hasMethod(uut,"equals", true));}
	public static void testPojoToStringMethod(Class<?> uut){ assertTrue("Pojos must implement the toString method",hasMethod(uut,"toString",true));}

	private static boolean hasInterface(Class<?>uut, String interfaceNameCriteria){
		
		for (Class<?> interfaceClass: uut.getInterfaces()){
			String interfaceName = interfaceClass.getName();
			System.out.println("Testing:"+interfaceName);
			if (interfaceName.equals(interfaceNameCriteria)){
				return true;
			}
		}
		return false;
	}
	private static boolean hasMethod(Class<?> uut, String methodNameCriteria, boolean mustBePublic) {
		for (Method m: uut.getDeclaredMethods()){
			String methodName = m.getName();
			
//			System.out.println("Testing:"+methodName);
			if (methodName.equals(methodNameCriteria)&&mustBePublic&&Modifier.isPublic(m.getModifiers())){
				return true;
			}
		}
		return false;
	}


	/**
	 * fields can be simple, indexed, or mapped objects, or primatives. Each
	 * case has to be handled differently.
	 * 
	 * NOTE: this utility does not support nested pojos
	 */
	public static void testPojoAccesorsAndMutators(Class<?> uut, List<String> propertyBlackList)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		System.out.println("Testing " + uut.getName() + "" + uut.getTypeName());
		System.out.println("Fields:" + uut.getDeclaredFields().length);
		System.out.println("Methods:" + uut.getDeclaredMethods().length);

		for (Field f : Stippel.class.getDeclaredFields()) {

			if (propertyBlackList.contains(f.getName())) {
				continue;
			}
			System.out.println("Checking\t'" + f.getName() + "' : " + f.getType().getName());
			/**
			 * test only those fields w/ types that can be instantiated
			 */

			Object testValue = generateTypeSafeTestValue(f);

			/**
			 * test accessor
			 */
			Object targetObject = uut.newInstance();
			PropertyUtils.setProperty(targetObject, f.getName(), testValue);
			assertEquals(testValue, PropertyUtils.getProperty(targetObject, f.getName()));
		}
	}

	/**
	 * Generates a test value based on the field's Type
	 * 
	 * @param f
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */

	private static Object generateTypeSafeTestValue(Field f) throws InstantiationException, IllegalAccessException {
//		System.out.println("Creating type safe value for:\t" + f.getType().getName());
		if (f.getType().getName().equals("java.lang.Integer") || f.getType().getName().equals("int")) {
			return Mockito.anyInt();
		}
		if (f.getType().getName().equals("long")) {
			return Mockito.anyLong();
		}
		if (f.getType().getName().equals("char")) {
			return Mockito.anyChar();
		}
		if (f.getType().getName().equals("boolean")) {
			return Mockito.anyBoolean();
		}
		return f.getType().newInstance();
	}

}
