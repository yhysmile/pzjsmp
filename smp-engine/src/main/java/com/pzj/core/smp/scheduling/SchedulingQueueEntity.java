package com.pzj.core.smp.scheduling;

import com.pzj.core.smp.common.exception.SmpException;
import com.pzj.core.smp.common.exception.SmpExceptionCode;

/**
 * Created by Administrator on 2016-12-29.
 */
public class SchedulingQueueEntity {
	private String name;

	public SchedulingQueueEntity(String name) {
		setName(name);
	}

	public String name() {
		return name;
	}

	private void setName(String name) {
		if (name == null || name.isEmpty())
			throw new SmpException(SmpExceptionCode.SCHEDULING_NAME_NULL);
		this.name = name;
	}
}
