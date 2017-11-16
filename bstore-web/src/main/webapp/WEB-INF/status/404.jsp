<%@ include file="../views/layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<div style="text-align: center;"> 
  <h3><strong style="color: red">Mensaje del sistema:</strong></h3>
</div>
<div style="text-align: center">
  <img src="${contextpath}/static/resources/img/404.jpg" alt="">
</div>
</html>