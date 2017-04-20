简介
===

发送短信主要有两种方式：通过RPC接口、通过MQ。


ApiDoc
======

http://10.0.18.30:8002/smp_service/index.html


示例
===

Maven配置
-------

```xml
<dependency>
    <groupId>com.pzj.core</groupId>
    <artifactId>smp-facade</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

通过RPC接口发送短信
-----------

添加Dubbo配置：

```xml
<dubbo:reference ref="shortMessageService" interface="com.pzj.core.smp.delivery.IShortMessageService"/>
```

发送短信：

```java
public class ShortMessageServiceTest {
	@Resource
	IShortMessageService shortMessageService;

	@Test
	public void sendMessage() throws IOException {
		MessageHead messageHead = new MessageHead("wlq_test_q", "A", 300L);
		MessageBean messageBean = new MessageBean(messageHead, Arrays.asList("15210147789"), "顶起");

		Result<Boolean> result = shortMessageService.sendMessage(messageBean);
		System.out.println(result.getErrorMsg());

		System.in.read();
	}
}
```


通过RocketMQ发送短信
--------------

```java
public static void main() throws Exception {
	DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
	producer.setNamesrvAddr("10.0.6.25:9876");
	producer.start();
	
	send();
	
	producer.shutdown();
}

public static void send() {
	MessageHead messageHead = new MessageHead("wlq_test_q", "A", 60000L);
	MessageBean messageBean = new MessageBean(messageHead, Arrays.asList("15210147640"), "老朋友土好和029");
	publishToMQ(messageBean);
}

private static void publishToMQ(MessageBean messageBean) {
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
```