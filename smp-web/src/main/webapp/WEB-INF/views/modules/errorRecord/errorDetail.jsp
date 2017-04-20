<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-1-10
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%-- <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<%@ include file="/WEB-INF/views/include/base.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>发送错误详情</title>
</head>
<body>
  <div class="income">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="13%">错误单号:</td>
            <td width="37%">${errorRecordVO.sendRecordId }</td>
            <td width="13%">错误月份:</td>
            <td>${errorRecordVO.sendNum }</td>
        </tr>
        <tr>
            <td width="13%">错误详情:</td>
            <td width="37%">${errorRecordVO.sendErrDetail }</td>
            <td width="13%">创建时间:</td>
            <td>${errorRecordVO.sendTime }</td>
        </tr>
    </table>
  </div>
</body>
</html>