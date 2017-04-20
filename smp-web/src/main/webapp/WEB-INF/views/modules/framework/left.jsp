<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-1-3
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<%@ include file="/WEB-INF/views/include/base.jsp"%>
<html>
<script type="text/javascript">
    function tragerMenu(src){
        var srcpath = "<%=path%>/"+src;
        window.parent.document.getElementById("frame_center").src=srcpath;
    }
</script>
<head>
    <title>菜单</title>
</head>
<body>
<div class="leftbox">
    <dl>
        <dt><a href="javascript:void(0);" onclick="tragerMenu('/record/list')">短信管理</a></dt>
    </dl>
    <dl>
        <dt><a href="javascript:void(0);" onclick="tragerMenu('/busin/list')">业务管理</a></dt>
    </dl>

</div>


</body>
</html>
