package utils;

import java.lang.reflect.Constructor;

public class ClassUtil {
public static Class[] toClasses(ClassLoader classLoader, String[] classNames) throws Exception {
		
		if(classNames == null) return null;
		
		Class[] types = new Class[classNames.length];
		for (int i=0; i<types.length; i++) {
			types[i] = ClassUtil.loadClass(classLoader, classNames[i]);
		}
		return types;
	}

	public static Class[] toClasses(String[] classNames) throws Exception {
		return toClasses(getBaseClassLoader(), classNames);
	}

	public static String getClassName(Class clazz, boolean includePackage){
        String className = clazz.getName();
        if(includePackage){
            return className;
        }
        return className.substring(className.lastIndexOf('.') + 1); 
    }
    
//	public static ClassLoader getBootstrapClassLoader(){
//		return MtierBootstrap.getBootstrapClassLoader();
//	}
	
	public static ClassLoader getBaseClassLoader(){
//		ClassLoader classLoader = MtierBootstrap.getBootstrapClassLoader();
//		
//		if(classLoader == null){
//       	 	classLoader = Thread.currentThread().getContextClassLoader();
//		}
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		if(classLoader == null){
       	 	classLoader = ClassLoader.getSystemClassLoader();
		}
        return classLoader;
	}
	
	public static Class loadClass(ClassLoader loader, String className) throws ClassNotFoundException {
    	try{
	        return loader.loadClass(className);
    	}catch(ClassNotFoundException cnfe){
    		return getBaseClassLoader().loadClass(className);
    	}
    }
	
    public static Class loadClass(String className) throws ClassNotFoundException {
    	try{
	        return getBaseClassLoader().loadClass(className);
    	}catch(ClassNotFoundException cnfe){
    		throw cnfe;
    	}
    }
    
    public static Class loadClass(Class callerClass, String className) throws ClassNotFoundException {
    	return loadClass(callerClass.getClassLoader(), className);
    }
    
    public static Object newInstance(ClassLoader classLoader, String className, Class[] types, Object[] args) throws Exception {
    	Class clazz = loadClass(classLoader, className);
    	return newInstance(clazz, types, args);
    }
    
    public static Object newInstance(ClassLoader classLoader, String className) throws Exception {
    	Class clazz = loadClass(classLoader, className);
    	return clazz.newInstance();
    }
    
    public static Object newInstance(String className, Class[] types, Object[] args) throws Exception {
    	Class clazz = loadClass(className);
    	return newInstance(clazz, types, args);
    }
    
    public static Object newInstance(String className) throws Exception {
    	Class clazz = loadClass(className);
    	return clazz.newInstance();
    }
    
    public static Object newInstance(Class clazz, Class[] types, Object[] args) throws Exception {
    	Constructor constructor = clazz.getConstructor(types);
    	return constructor.newInstance(args);
    }
}
