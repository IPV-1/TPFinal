package utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Commons {
	
	// ****************************************************************
	// ** REFLECTIONS
	// ****************************************************************
	protected static Object invokeMethodFromClass(Class<?> clazz, String methodName, Object obj) {
		Object objectInvoked = null;
		try {
			Method method = clazz.getDeclaredMethod(methodName);
			objectInvoked = method.invoke(obj);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return objectInvoked;
	}
	
	public static Object invokeMethodFromClass(Class<?> clazz, String methodName) {
		return Commons.invokeMethodFromClass(clazz, methodName, null);
	}

	public static Object invokeMethodFromClassInPackage(String packageName,
			String className, String methodName) {
		Object objectInvoked = null;
		try {
			Class<?> clazz = Class.forName(packageName + "." + className);
			objectInvoked = Commons.invokeMethodFromClass(clazz, methodName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return objectInvoked;
	}
	
	public static Object invokeMethod(Object obj, String methodName) {
		return Commons.invokeMethodFromClass(obj.getClass(), methodName, obj);
	}


}
