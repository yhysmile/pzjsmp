package com.pzj.core.smp.channel.common;

import java.util.Collection;
import java.util.Map;

public class ObjUtils {

	//	private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
	/**
	 * 检查对象是否为空
	 * @param objs（支持字符串，集合，map，普通对象）多对象传参
	 * @return 空：true ；非空：false
	 */
	public static boolean checkObjectIsNull(Object... objs) {
		boolean flag = Boolean.TRUE;
		if (ObjUtils.checkArrIsNull(objs)) {
			return flag;
		}

		for (Object obj : objs) {
			if (obj instanceof String) {
				flag = StrUtils.checkStringIsNull((String) obj);
			} else if (obj instanceof Collection) {
				flag = checkCollectionIsNull((Collection<?>) obj);
			} else if (obj instanceof Map) {
				flag = checkMapIsNull((Map<?, ?>) obj);
			} else {
				flag = obj == null;
			}
			if (flag) {
				break;
			}
		}
		return flag;
	}

	/**
	 * 检查数组是否是空（支持字符串，Collection，Map，基本对象等）
	 * @param objarr
	 * @return 空：true ；非空：false
	 */
	public static boolean checkArrIsNull(Object[] objarr) {
		boolean flag = Boolean.TRUE;
		if (null == objarr || objarr.length == 0) {
			return flag;
		}
		for (Object obj : objarr) {
			if (obj instanceof String) {
				flag = StrUtils.checkStringIsNull((String) obj);
			} else if (obj instanceof Collection) {
				flag = checkCollectionIsNull((Collection<?>) obj);
			} else if (obj instanceof Map) {
				flag = checkMapIsNull((Map<?, ?>) obj);
			} else {
				flag = obj == null;
			}
			if (flag) {
				break;
			}
		}
		return flag;
	}

	/**
	 * 检查集合是否是空
	 * @param collection
	 * @return 空：true；非空：false
	 */
	public static boolean checkCollectionIsNull(Collection<?> collection) {
		return null == collection || collection.isEmpty();
	}

	/**
	 * 检查map是否是空
	 * @param map
	 * @return 空：true；非空：false
	 */
	public static boolean checkMapIsNull(Map<?, ?> map) {
		return null == map || map.isEmpty();
	}

	/**
	 * 检查long类型参数是否合法
	 * 空：true ；非空：false
	 * @param id
	 * @param checkHigherZero
	 * @return
	 */
	public static boolean checkLongIsNull(Long id, boolean checkHigherZero) {
		boolean flag = Boolean.FALSE;
		if (null == id) {
			flag = Boolean.TRUE;
		}

		if (!flag && checkHigherZero && id <= 0) {
			flag = Boolean.TRUE;
		}
		return flag;
	}

	/**
	 * 检查int类型参数是否合法
	 * 空：true ；非空：false
	 * @param id
	 * @param checkHigherZero
	 * @return boolean
	 */
	public static boolean checkIntegerIsNull(Integer id, boolean checkHigherZero) {
		boolean flag = Boolean.FALSE;
		if (null == id) {
			flag = Boolean.TRUE;
		}

		if (!flag && checkHigherZero && id <= 0) {
			flag = Boolean.TRUE;
		}
		return flag;
	}
}
