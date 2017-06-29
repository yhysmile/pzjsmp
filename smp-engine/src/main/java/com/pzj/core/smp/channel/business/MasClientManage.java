package com.pzj.core.smp.channel.business;

import org.springframework.stereotype.Component;

@Component("masClientManage")
public class MasClientManage {
	//	private final Logger logger = LoggerFactory.getLogger(MasClientManage.class);
	//
	//	private MasClientManage instance;
	//
	//	private Client masClient;
	//
	//	public MasClientManage getInstance() {
	//		if (null == instance) {
	//			instance = MasClientInstanceHolder.instance;
	//		}
	//		return instance;
	//	}
	//
	//	public Client getMasClient(MasAccountParam masAccountParam) {
	//		logger.debug("getMasClient start masAccountParam:{}", JSONConverter.toJson(masAccountParam));
	//		if (null == masClient) {
	//			masClient = loginMas(masAccountParam);
	//		}
	//		logger.debug("getMasClient return masClient:{}", JSONConverter.toJson(masClient));
	//		return masClient;
	//	}
	//
	//	private MasClientManage() {
	//	}
	//
	//	static class MasClientInstanceHolder {
	//		private static final MasClientManage instance = new MasClientManage();
	//
	//	}
	//
	//	/**
	//	 * 登录mas平台，获取客户端对象
	//	 * @param masAccountParam
	//	 * @return
	//	 */
	//	private Client loginMas(MasAccountParam masAccountParam) {
	//		logger.info("loginMas get client start ...");
	//		logger.info("loginMas Client:{}", Client.getInstance());
	//		Client client = Client.getInstance();
	//		logger.info("loginMas masAccountParam:{},get client:{}", JSONConverter.toJson(masAccountParam),
	//				JSONConverter.toJson(client));
	//		Boolean loginFlag = client.login(masAccountParam.getUrl(), masAccountParam.getUserAccount(),
	//				masAccountParam.getPassword(), masAccountParam.getEcname());
	//		logger.info("loginMas loginFlag:{}", loginFlag);
	//		if (loginFlag) {
	//			return client;
	//		}
	//		return null;
	//	}
}
