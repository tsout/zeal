package org.gem.text.model;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

public class StippelTest {

	@Test
	public void test() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Stippel stipple = new Stippel();
		char stippelValue = 'x';
		PropertyUtils.setProperty(stipple, "stipple", stippelValue);
		assertEquals(stippelValue, stipple.getStipple());
	}

	@Test
	public void testProperties() throws Exception {
		/**
		 * fields can be simple, indexed, or mapped objects, or primatives. Each
		 * case has to be handled differently
		 */
		Object uut = Stippel.class.newInstance();

		System.out.println(uut.getClass().getName() + "" + uut.getClass().getTypeName());
		System.out.println(uut.getClass().getDeclaredFields().length);

		System.out.println(uut.getClass().getFields().length);
		System.out.println(uut.getClass().getMethods().length);
		for (Field f : Stippel.class.getDeclaredFields()) {
			System.out.println("Checking\t'" + f.getName() + "' : " + f.getType().getName());

			/**
			 * if the field is of a primitive type, a new object can not be
			 * created
			 */
			if (!f.getType().isPrimitive()) {
				PropertyUtils.setProperty(uut, f.getName(), f.getType().newInstance());
			}
		}
	}

}
