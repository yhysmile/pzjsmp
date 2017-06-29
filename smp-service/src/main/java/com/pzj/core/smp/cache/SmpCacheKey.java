package com.pzj.core.smp.cache;

import com.pzj.framework.cache.core.CacheKey;

/**
 * Created by Administrator on 2017-1-9.
 */
public class SmpCacheKey extends CacheKey {
	protected final static String Writer = "smp-service";

	public SmpCacheKey(String business) {
		super(Writer, business);
	}

	public static SmpCacheKey newKey(String key) {
		return new SmpCacheKey(key);
	}

	public static SmpCacheKey[] newKeys(String... keys) {
		if (keys == null)
			return null;

		SmpCacheKey[] result = new SmpCacheKey[keys.length];
		for (int i = 0; i < keys.length; i++) {
			result[i] = newKey(keys[i]);
		}
		return result;
	}

	public static String[] kevStrings(SmpCacheKey[] smpCacheKeys){
		if (smpCacheKeys == null)
			return null;

		String[] result = new String[smpCacheKeys.length];
		for (int i = 0; i < smpCacheKeys.length; i++) {
			result[i] = smpCacheKeys[i].key();
		}
		return result;
	}

	@Override
	public String toString() {
		return key();
	}
}
