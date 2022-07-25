package org.secondopinion.utils;

import java.util.Objects;

/**
 * @author rswarna
 *
 */
public class ObjectUtil {
    	
	public static String getClassName(Object object) {
		if (object != null) {
			return object.getClass().getName();
		}
		return null;
	}

	/**
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean isEqual(Object obj1, Object obj2) {
		if (Objects.isNull(obj1 ) && Objects.isNull(obj2 ))
			return true;

		if (Objects.nonNull(obj1 ) && Objects.isNull(obj2 ))
			return false;

		return obj1.equals(obj2);
	}




}
