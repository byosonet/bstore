<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {                               
});
</script>
</head>
<body>
<br/>
<div class="row col-sm-4 col-sm-offset-4 main alert alert-success">
        <p style="text-align: center;" class="text"><b>${mensajeError}</b>
        	<a href="${contextpath}/equivira" class="text btn btn-default">Continuar</a>
        </p>
</div>
</body>
</html>
