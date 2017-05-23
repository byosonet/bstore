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
<div class="row">
    <div class="alert alert-danger">
        <p style="text-align: center;"><b>${mensajeError}</b></p>
    </div>
</div>
</body>
</html>
