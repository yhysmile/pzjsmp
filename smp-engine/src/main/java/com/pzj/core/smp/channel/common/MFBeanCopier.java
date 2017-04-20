package com.pzj.core.smp.channel.common;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

public class MFBeanCopier {

	private static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

	public static void copyProperties(Object target, Object source) {
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier = null;
		if (!beanCopierMap.containsKey(beanKey)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), false);
			beanCopierMap.put(beanKey, copier);
		} else {
			copier = beanCopierMap.get(beanKey);
		}
		copier.copy(source, target, null);
	}

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + class2.toString();
	}
}
