package com.pzj.core.smp.delivery;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.rocketmq.client.exception.MQClientException;

/**
 * Created by Administrator on 2017-1-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring.xml" })
public class ShortMessageManyTHreadTest {
    @Resource
    IShortMessageService shortMessageService;

    @Test
    public void test() throws MQClientException, IOException {
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
                    send(bussinessName, priority, phoneNums, sec);
                }
            }
        }

        System.in.read();
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

    public void send(String busniessName, String priority, List<String> phoneNums, int sec) {

        MessageHead messageHead = new MessageHead(busniessName, priority, 300L);
        MessageBean messageBean = new MessageBean(messageHead, phoneNums, busniessName + "-" + priority + "-" + sec);

        shortMessageService.sendMessage(messageBean);
    }

}
