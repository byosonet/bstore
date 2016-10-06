<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$('ul.nav.navbar-nav').hide();
		$('#wrapper').css('padding-left','0px');
		$('a#showMenu').show();
	});
</script>
<style type="text/css">
</style>
</head>
<body>
    <div class="row" style="padding: 0.5em;">
  		<label class="btn btn-primary alert-info" style="position: unset; z-index: 1;right:15px;top:82px"><b><c:out value="${nombrePublicacion}"/></b></label>
  		<iframe src="${urlPublicacion}" frameborder="0" style="float:left; border:solid 0px black; padding:0em; background-color:#eee;" height="100%" width="100%"></iframe>
	</div>
</body>
</html>