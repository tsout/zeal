package org.gem.test.utility;

public class ReflectionUtil {

	public static boolean hasInterface(Class<?>uut, String interfaceNameCriteria){
		
		for (Class<?> interfaceClass: uut.getInterfaces()){
			String interfaceName = interfaceClass.getName();
			System.out.println("Testing:"+interfaceName);
			if (interfaceName.equals(interfaceNameCriteria)){
				return true;
			}
		}
		return false;
	}

}
