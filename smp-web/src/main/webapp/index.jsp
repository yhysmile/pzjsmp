<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-1-3
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<%@ include file="/WEB-INF/views/include/base.jsp"%>
<html>
<head>
	<meta name="renderer" content="ie-stand"/>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>票之家</title>
	<script type="text/javascript">
		//iframe自适应高度
		function reinitIframe(){
			var mainiframe = document.getElementById("frame_center");
			var iframe2 = document.getElementById("frame_left");
			try{
				var bHeight = mainiframe.contentWindow.document.body.scrollHeight;
				var dHeight = mainiframe.contentWindow.document.documentElement.scrollHeight;
				//var height = bHeight;
				var height = Math.max(bHeight, dHeight);
				var leftbHeight = iframe2.contentWindow.document.body.scrollHeight;
				var leftcHeight = iframe2.contentWindow.document.documentElement.scrollHeight;
				var leftheight = Math.max(leftbHeight, leftcHeight);
				iframe2.height =  leftheight;
				if(height<leftheight){
					height = leftheight;
					mainiframe.height =  height;
				}else{
					mainiframe.height =  height;
				}
			}catch (ex){}
		}
		function showDivBox(id,width,height){
			return mf.msg.showDivBox(id,width,height);
		}
		window.setInterval("reinitIframe()", 200);
	</script>

	<style>
		.qrcode_icon{
			width:80px;
			height:80px;
			position:fixed;
			bottom:50px;

			right:50px;
		}
		.qrcode_icon>img{
			width:70px;
			hegiht:70px;
			margin:5px 5px;
		}
		.qrcode_icon>div{text-align: center}
	</style>
</head>
<body>
<iframe id="frame_top" src="<%=path%>/init/head" width="100%" height="80px" scrolling="no" frameborder="0" v-align="center"></iframe>

<div id="center">

	<div class="leftbox">
		<iframe id="frame_left" src="<%=path%>/init/left"  width="215px"
				scrolling="no" frameborder="0"></iframe>
	</div>
	<div class="main">
		<iframe id="frame_center" src="<%=path%>/init/center"
				width="1007px" scrolling="no" frameborder="0">
		</iframe>
	</div>
</div>
</body>
</html></html>