<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-1-12
  Time: 11:50
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
    <title>业务线列表</title>
</head>
<body>
<div id="center">
    <div class="main">
        <div class="passengers">
            <form action="" id="form" method="post">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr class="orderdashed">
                        <td align="right">业务线名称：</td>
                        <td><input  name="name"   type="text" class="nameinput" value="${businVo.name}"/></td>
                        <td align="right">业务线状态：</td>
                        <td>
                            <select name="state">
                                <option value="">全部</option>
                                <option value="ENABLE" <c:if test="${businVo.state== 'ENABLE'}">selected="selected"</c:if>>启用</option>
                                <option value="DISABLE" <c:if test="${businVo.state== 'DISABLE'}">selected="selected"</c:if>>禁用</option>
                            </select>
                        </td>
                        <td><input name="Input" type="button" onclick="sel()"   value="查询" class="libox-aninput1" /></td>
                        <td><input name="Input" type="button" onclick="window.location.href='<%=path%>/busin/addBusin'"   value="新增" class="libox-aninput1" /></td>
                        <input type="hidden" name="currentPage" value="${buResult.data.currentPage }"/>
                    </tr>
                </table>
            </form>
        </div>
        <div class="income">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr class="trbg">
                    <td>业务线名称</td>
                    <td >业务线描述</td>
                    <td >业务线状态</td>
                    <td >创建时间</td>
                    <td >更新时间</td>
                </tr>
                <c:if test="${ not empty buResult}">
                    <c:forEach var="bu" items="${buResult.data.records}" varStatus="i">
                    <tr>
                        <td>${bu.name}</td>
                        <td>${bu.describe}</td>
                        <td>
                        <c:if test="${bu.state == 'ENABLE'}">启用</c:if>
                        <c:if test="${bu.state == 'DISABLE'}">禁用</c:if>
                        <c:if test="${bu.state == 'ENABLE'}"><a style="color: #1d7cd0;" href="javascript:void(0);" onclick="updateState('${bu.id}','DISABLE')">禁用 </a></c:if>
                        <c:if test="${bu.state == 'DISABLE'}"><a style="color: #1d7cd0;" href="javascript:void(0);" onclick="updateState('${bu.id}','ENABLE')">启用 </a></c:if>
                        </td>
                        <td>${bu.createDate}</td>
                        <td>${bu.editDate}</td>
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
            items: "${buResult.data.total}",       // 总记录条数
            currentPage:"${buResult.data.currentPage}",
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
        window.location.href="<%=path%>/busin/list?data="+data;
    }


    function updateState(id,state){
        if(!confirm('确认此操作?')){
            return false;
        }
        var data = JSON.stringify({"id":id,"state":state});
        $.ajax({
            type : "POST",
            url : "<%=path%>/busin/updateBusinState", //访问路径
            anyac:false,
            cache:false,
            data : {
                    data
            }, //参数
            success : function(msg) {
                if(msg.errorCode == 10000){
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