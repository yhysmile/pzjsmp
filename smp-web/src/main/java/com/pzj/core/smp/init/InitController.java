package com.pzj.core.smp.init;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017-1-3.
 * 初始化页面
 * @author ZhouYuan
 */
@Controller
@RequestMapping(value = "/init")
public class InitController {
	@RequestMapping(value = { "left", "" })
	public String initLeft() {
		return "modules/framework/left";
	}

	@RequestMapping(value = { "center", "" })
	public String initCenter() {
		return "modules/framework/center";
	}

	@RequestMapping(value = { "head", "" })
	public String initHead() {
		return "modules/framework/head";
	}
}
