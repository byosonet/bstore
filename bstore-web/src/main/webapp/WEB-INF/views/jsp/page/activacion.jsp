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
         
      });
  </script>
</head>
<body>  
    
    <div class="container-fluid" style="margin-top: 30px;">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-3 col-md-offset-4 main">
        	</br>
        	</br>
        	</br>
            <form id="recuperar" class="form-horizontal">
            	<div class="form-group">
                    <h5 class="control-label col-sm-12" style="text-align: center;color:black;">ACTIVACION DE CUENTA</h5>
                    <c:choose>
		    		 	<c:when test="${activado}">
		    		 		 <h5 class="control-label col-sm-12" style="text-align: center;color:black;">ACTIVACION CORRECTA</h5>
		    		 	</c:when>
		    		 	<c:otherwise>
		    		 		 <h5 class="control-label col-sm-12" style="text-align: center;color:black;">ACTIVACION INCORRECTA</h5>
		    		 	</c:otherwise>
	    		 	</c:choose>
                </div>
                </br>
        		</br>
                
                <div class="form-group">
                    <label class="control-label col-sm-4" style="color:black;" for="nombre">Usuario:</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="email" name="email" placeholder="Ingresa tu email">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-4" style="color:black;" for="email">Confirmar usuario:</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="confEmail" name="confEmail" placeholder="Confirma tu email">
                    </div>
                </div>
            </form>
            <form id="regresar"></form>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                <button id="limpiar" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> LIMPIAR</button>
                <button id="enviar" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> ENVIAR</button>
                </div>
            </div>
        </div>
    </div>    
</div>
    
    
    
    
    
</body>
</html>