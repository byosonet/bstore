<%@ include file="../views/layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<div style="text-align: center;"> 
  <h3><strong style="color: red">Mensaje del sistema:</strong> <br>Ocurrio un error en el sistema.</h3>
</div>
<div style="text-align: center">
  <img src="${contextpath}/static/resources/img/500.jpg" alt="">
</div>
</html>