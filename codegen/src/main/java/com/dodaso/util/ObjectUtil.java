package com.dodaso.util;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Method;

import com.dodaso.exception.CodeGenException;

/**
 * @author rswarna
 * 
 */
public class ObjectUtil {
	/**
	 * Attempt to find custom property editor on descriptor first, else try the
	 * property editor manager.
	 * 
	 * @param desc
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static PropertyEditor getPropertyEditor(PropertyDescriptor desc)
			throws InstantiationException, IllegalAccessException {
		Class cls = desc.getPropertyEditorClass();
		if (null != cls)
			return (PropertyEditor) cls.newInstance();
		return PropertyEditorManager.findEditor(desc.getPropertyType());
	}

	/**
	 * @param value
	 * @param editor
	 * @return
	 */
	public static Object convertValue(String value, PropertyEditor editor) {
		try {
			Object obj = value;
			if (null != editor) {
				editor.setAsText(value.trim());
				obj = editor.getValue();
			}
			return obj;
		} catch (Exception ex) {
			throw new RuntimeException("Error assigning bean attribute value: "
					+ value, ex);
		}
	}

	/**
	 * @param convertToType
	 * @return
	 */
	public static Object createBean(Object convertToType) {
		try {
			return convertToType.getClass().newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Error instantiatine type: ", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error instantiatine type: ", e);
		}
	}

	/**
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean isEqual(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null)
			return true;

		if (obj1 != null && obj2 == null)
			return false;

		return obj1.equals(obj2);
	}
	
	@SuppressWarnings("unchecked")
	public static Object getPropertyValue(Object object, String propertyName){
		try{
			Class cls = object.getClass();
			Method method = cls.getDeclaredMethod("get" + StringUtil.initCap(propertyName));
			return method.invoke(object);
		}catch(Exception ex){
			throw new CodeGenException("Error reading property value: " + propertyName, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Object invokeMethod(Class cls, Object object, String methoName){
		try{
			//Class cls = object.getClass();
			Method method = cls.getDeclaredMethod(methoName);
			return method.invoke(object);
		}catch(Exception ex){
			throw new CodeGenException("Error invoking method: " + methoName, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void assignProperty(Object bean, String propertyName, Object value){
		try{
			Class cls = bean.getClass();
			Method method = cls.getMethod("set"+StringUtil.initCap(propertyName), value.getClass());
			method.invoke(bean, value);
		}catch(Exception ex){
			throw new CodeGenException("Error setting property value: " + propertyName, ex);
		}
	}
	
	public static void printObjectProperties(Object bean){
		System.out.println(bean.getClass().getDeclaredFields());
	}
}
