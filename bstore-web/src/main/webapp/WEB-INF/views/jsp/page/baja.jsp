<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <style type="text/css">
		html * {font-family: Verdana;font-size: 12;}
  </style>
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
 	<c:when test="${baja}">
 		<div class="row alert alert-success">
	    	<div style="text-align: center;">
	    		<p>Hemos dado de baja correctamente tu cuenta: <b>${email}</b>, esperamos que vuelvas pronto.</p>
	    		<p><button id="continuar" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> CONTINUAR</button></p>
    		</div>
		</div>
 	</c:when>
 	<c:otherwise>
 		<div class="row alert alert-danger">
	    	<div style="text-align: center;">
	    		<p>No hemos podido desactivar tu cuenta correctamente, te invitamos a ponerte en contacto con nuestra &aacute;rea de soporte
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