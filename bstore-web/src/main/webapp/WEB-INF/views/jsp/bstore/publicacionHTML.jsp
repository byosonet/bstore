<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
</head>
<body>
    <div class="row" style="padding: 0.5em;">
  		<div class="col-md-12 alert alert-info" style="text-align: center"><b><c:out value="${nombrePublicacion}"/></b></div>
  		<iframe src="${urlPublicacion}" width=100% height=100%></iframe>
	</div>
</body>
</html>