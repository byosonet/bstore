<%@ include file="../views/layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" /><%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<div style="text-align: center;"> 
  <h3><strong style="color: red">Mensaje del sistema:</strong> <br>Sitio no disponible por el momento.</h3>
</div>
<div style="text-align: center">
  <img src="${contextpath}/static/resources/img/503.png" alt="">
</div>
</html>