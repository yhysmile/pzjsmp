<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-1-3
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%-- <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<%@ include file="/WEB-INF/views/include/base.jsp"%>
<script type="text/javascript" src="<%=path%>/static/js/pagination.js"></script>
<script type="text/javascript" src="<%=path%>/static/My97DatePicker/WdatePicker.js"></script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>短信记录</title>
</head>
<body>
<div id="center">
    <div class="main">
        <div class="passengers">
            <form action="" id="form" method="post">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr class="orderdashed">
                        <td align="right">发送手机号：</td>
                        <td><input  name="sendPhone"   type="text" class="nameinput" value="${recordVO.sendPhone}"/></td>
                        <td align="right">发送状态：</td>
                        <td>
                            <select name="sendState">
                                <option value="">全部</option>
                                <option value="SEND_SUCCESS" <c:if test="${recordVO.sendState== 'SEND_SUCCESS'}">selected="selected"</c:if>>发送成功</option>
                                <option value="SEND_ERROR" <c:if test="${recordVO.sendState== 'SEND_ERROR'}">selected="selected"</c:if>>发送失败</option>
                            </select>
                        </td>
                        <td align="right">发送时间：</td>
                        <td>
                            <input type="text" name="sendTime" class="index_searchhome"
                                    value="<fmt:formatDate value='${recordVO.sendTime}' pattern='yyyy-MM-dd'/>" onClick="WdatePicker()"/></td>
                        <td><input name="Input" type="button" onclick="sel()"   value="查询" class="libox-aninput1" /></td>
                        <input type="hidden" name="currentPage" value="${roResult.data.currentPage }"/>
                    </tr>
                </table>
            </form>
        </div>
        <div class="income">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr class="trbg">
                    <td width="15%">发送手机号</td>
                    <td width="30%">短信内容</td>
                    <td width="10%">发送状态</td>
                    <td width="20%">发送通道</td>
                    <td width="25%">发送时间</td>
                </tr>
                <c:if test="${ not empty roResult}">
                    <c:forEach var="rb" items="${roResult.data.records}" varStatus="i">
                    <tr>
                        <td>${rb.sendPhone}</td>
                        <td>${rb.sendContent}</td>
                        <td>
                            <c:if test="${rb.sendState == 'SEND_SUCCESS'}">发送成功</c:if>
                            <c:if test="${rb.sendState == 'SEND_ERROR'}">
                                <a style="color: #1d7cd0;" href="<%=path %>/error/record/selectByRecordId?data={sendRecordId:${rb.sendId}}" >发送失败 </a>
                                </c:if>
                        </td>
                        <td>${rb.channelName}</td>
                        <td>${rb.sendTime}</td>
                    </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
        <div class="pagination-wrapper policy_product_page" style="display:inline"></div>
    </div>
</div>
</div>
<script type="text/javascript">
    $(function() {
        var wrap2 = $('.main');
        wrap2.find('.policy_product_page').pagination({
            items: "${roResult.data.total}",       // 总记录条数
            currentPage:"${roResult.data.currentPage}",
            onPageClick: function(pageNumber) {
                $("input[name='currentPage']").val(pageNumber);
                  sel();
            }
        });
    })
    function sel(){
        var result = {};
        var fieldArray = $("#form").serializeArray();
        for (var i = 0; i < fieldArray.length; i++) {
            var field = fieldArray[i];
            if(field.value != ""){
                result[field.name] = field.value;
            }
        }
        var data = JSON.stringify(result);
        window.location.href="<%=path%>/record/list?data="+data;
    };
</script>
</body>
</html>