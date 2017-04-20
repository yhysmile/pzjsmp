package com.pzj.core.smp.scheduling;

/**
 * Created by Administrator on 2017-1-3.
 */
interface QueueService<T> {
	void push(T queueName, QueueData data);

	QueueData poll(int timeout, T... queueNames);
}
