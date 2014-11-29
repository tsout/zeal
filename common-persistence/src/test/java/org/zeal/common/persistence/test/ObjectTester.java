package org.zeal.common.persistence.test;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public abstract class ObjectTester<T> {

	T uut=null; 
	
	public ObjectTester() {
		super();
	}

	public void setObjectUnderTest(T object){
		uut= object; 
	}
	
	protected void assertHasRequiredMethods(String ... whiteList) {
		for (String methodName : whiteList){
			assertTrue(methodName+" method required for class '"+uut.getClass().getName()+"' ", hasMethodNamed(methodName));	
		}
	}
	
	protected void assertHasRequiredMethods(Method [] methods) {
		
		if(methods.length>0){
			List<Method> mlist = Arrays.asList(methods);
			for (Method m : mlist){
				assertTrue("'"+m.getName()+"' method required for class '"+uut.getClass().getName()+"' ", hasMethodNamed(m.getName()));	
			}	
		}
	}
	

	private boolean hasMethodNamed(String methodName) {
		Method[] methods = this.uut.getClass().getMethods();
		List<Method> mList = Arrays.asList(methods);
		boolean methodNameFound = false;
	
		for (Method method : mList) {
			if ((method.getName() == methodName)&&(method.getDeclaringClass()==this.uut.getClass())){
				methodNameFound=true;
				}
		}
		return methodNameFound; 
	}

	protected void assertHasRequiredInterfaces(Class<?>...classes) {
		for(Class<?>iface : classes){
			assertTrue("'"+iface.getName()+"' interface declaration is required on class '"+uut.getClass().getName()+"'", implementsInterfaceNamed(iface));	
		}
	}

	private boolean implementsInterfaceNamed(Class <?> requiredInterface) {
			Class<?>[] interfaces = this.uut.getClass().getInterfaces();
			boolean interfaceDeclarationFound = false; 
			for (Class<?> iface : interfaces) {
	//			System.out.println(iface.getDeclaringClass()+"implements "+iface.getName());
				if ((iface.getName() == requiredInterface.getName())){
					//&&(iface.getDeclaringClass() ==objectUnderTest.getClass())) {
				
					interfaceDeclarationFound = true; 
				}
			}
			return interfaceDeclarationFound;
		}

}