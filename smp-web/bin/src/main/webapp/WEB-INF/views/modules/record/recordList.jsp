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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>订单管理</title>
</head>
<body>
<div id="center">
    <div class="main">
        <div class="passengers">
            <form action="" name="" method="post">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr class="orderdashed">
                        <td align="right">订单号：</td>
                        <td><input  name="orderId"   type="text" class="nameinput" /></td>
                        <td align="right">产品名称：</td>
                        <td><input name="productName"  class="nameinput" /> </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="income" style="overflow-x: scroll;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr class="trbg">
                    <td >序号</td>
                    <td >订单号</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</div>
</body>
</html>