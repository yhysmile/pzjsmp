<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>login</title>
	</head>
<body>
	<a href="${pageContext.request.contextPath}/acting/createActing.jsp">添加演艺数据</a>
	<a href="${pageContext.request.contextPath}/acting/createSeatChart.jsp">添加座位图</a>

	<%-- <shiro:guest>
		<h5>请登录后进行相关操作</h5>
		<a href="${pageContext.request.contextPath}/login.jsp">登陆</a>
	</shiro:guest>

	<shiro:authenticated>
		<h1>
			Welcome
			<shiro:principal />
		</h1>
		<h5>添加演艺时,根据供应商的名称及景区的名称，查询演艺、区域、场次的关系</h5>
		<form
			action="${pageContext.request.contextPath}/apContro/queryUserInfo"
			method="get">
			<input type="text" name="supplierName" value="供应商名称"/> 
			<input type="text" name="scienName" value="景区名称"/> 
			<input type="submit" value="create"/>
		</form>
		<br>
		<a href="${pageContext.request.contextPath}/logout">退出</a>
	</shiro:authenticated> --%>
</html>