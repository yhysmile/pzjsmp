<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-1-13
  Time: 11:00
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
    <title>新增业务线</title>
</head>
<body>
<div id="center">
    <div class="main">
        <div class="passengers">
            <form action="" id="form" method="post">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr class="orderdashed">
                        <td align="right">业务线描述：</td>
                        <td><input  name="describe"   type="text" class="nameinput"/></td>
                    </tr>
                    <tr>
                        <td align="right">业务线名称：</td>
                        <td><input  name="name"   type="text" class="nameinput"/></td>
                        <input name="state" type="hidden" value="ENABLE"/>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input name="Input" type="button" onclick="add()"   value="保存" class="libox-aninput1" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    function add(){
        var result = {};
        var fieldArray = $("#form").serializeArray();
        for (var i = 0; i < fieldArray.length; i++) {
            var field = fieldArray[i];
            if(field.value != ""){
                result[field.name] = field.value;
            }
        }
        var data = JSON.stringify(result);
        $.ajax({
            type : "POST",
            url : "<%=path%>/busin/insertBusin", //访问路径
            anyac:false,
            cache:false,
            data : {
                data
            }, //参数
            success : function(msg) {
                if(msg.errorCode = 10000){
                    alert("操作成功");
                }else{
                    alert(msg.errorMsg);
                }
                window.location.href="<%=path %>/busin/list";
            }
        });
    }
</script>
</body>
</html>