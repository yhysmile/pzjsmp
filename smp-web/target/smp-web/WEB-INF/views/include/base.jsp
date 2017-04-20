<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-1-3
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    if("/".equals(path))
        path = "";
%>
<link href="<%=path%>/static/css/css.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="<%=path%>/static/js/mf.jquery-ui.min.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/static/js/mf.jquery.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/pagination.js"></script>
