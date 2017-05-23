<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <script type="text/javascript">
      $(function(){
    	  $('button#continuar').click(function(){
    		  var urlAction = '${contextpath}';
              document.location.href = urlAction;
          });
      });
  </script>
</head>
<body>  
<div class="container-fluid" style="margin-top: 30px;">
<div class="row">
<div class="col-sm-12 col-sm-offset-0 col-md-8 col-md-offset-2 main">
</br>
</br>
</br>
<div class="row">
<c:choose>
 	<c:when test="${activado}">
 		<div class="row alert alert-success">
	    	<div style="text-align: center;">
	    		<p>Has activado correctamente tu cuenta: <b>${email}</b>, ahora ya puedes iniciar sesi&oacute;n desde nuestro portal.</p>
	    		<p><button id="continuar" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> CONTINUAR</button></p>
    		</div>
		</div>
 	</c:when>
 	<c:otherwise>
 		<div class="row alert alert-danger">
	    	<div style="text-align: center;">
	    		<p>No hemos podido activar tu cuenta correctamente, te invitamos a ponerte en contacto con nuestra &aacute;rea de soporte
	    		a <b>${emailApp}</b></p>
	    		<p><button id="continuar" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> CONTINUAR</button></p>
    		</div>
    	</div>
 	</c:otherwise>
	</c:choose>
</div>
</div>
</div>
</div>      
</body>
</html>