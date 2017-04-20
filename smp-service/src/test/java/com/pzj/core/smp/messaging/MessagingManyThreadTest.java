package com.pzj.core.smp.messaging;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.pzj.core.smp.delivery.MessageBean;
import com.pzj.core.smp.delivery.MessageHead;

/**
 * Created by Administrator on 2017-1-17.
 */
public class MessagingManyThreadTest {
    static DefaultMQProducer producer;
    static ExecutorService executorService = null;

    public static void main(String[] args) throws MQClientException, IOException {
        start();

        List<String> bussinessNames = bussinessNames();
        List<String> prioritys = prioritys();
        final List<String> phoneNums = phoneNums();

        Iterator<String> bussinessNamesIterator = bussinessNames.iterator();

        int num = 100;

        while (bussinessNamesIterator.hasNext()){
            final String bussinessName = bussinessNamesIterator.next();

            for (int x = 0; x < prioritys.size(); x++) {
                final String priority = prioritys.get(x);
                
                for (int i = 0; i < num; i++){
                    final int sec = i;
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            send(bussinessName, priority, phoneNums, sec);
                        }
                    });
                }
            }
        }

        System.in.read();

        shutdown();
    }

    public static void start() throws MQClientException {
        producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("10.0.6.25:9876");
        producer.start();

        executorService = Executors.newCachedThreadPool();
    }

    public static List<String> bussinessNames(){
        return Arrays.asList("wlq_test_q", "wlq_test_p", "YWXCSA");
    }

    public static List<String> prioritys(){
        return Arrays.asList("A", "B", "C", "D");
    }

    public static List<String> phoneNums(){
        return Arrays.asList("15210147640");
    }

    public static void send(String busniessName, String priority, List<String> phoneNums, int sec) {

        MessageHead messageHead = new MessageHead(busniessName, priority, 300L);
        MessageBean messageBean1 = new MessageBean(messageHead, phoneNums, busniessName + "-" + priority + "-" + sec);

        publishMessage(messageBean1);
    }

    public static void publishMessage(MessageBean messageBean) {
        String jsonString = JSON.toJSONString(messageBean);
        // 构建消息
        Message msg = new Message("aafefe", jsonString.getBytes());
        // 发送消息
        SendResult sendResult = null;

        try {
            sendResult = producer.send(msg);
            System.out.println(sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() throws IOException {
        producer.shutdown();
    }
}
