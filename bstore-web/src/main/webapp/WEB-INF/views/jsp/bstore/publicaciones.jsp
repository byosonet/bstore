<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {                               
console.log('--load novedades');
$("#publicacion").gridalicious({
	gutter: 25,
	width:200,
    animate: true
	});
}          
);
</script>
</head>
<body>
    <div id="publicacion">
    	<c:forEach var="publicacion" items="${publicaciones}">
             <div class="item banner-wrapper">
                 <a href="#" data-toggle="modal" data-target="#modalPublicacion${publicacion.id}"><img class="zoom" style="border-radius:10px;margin-top:-30px;width: 100%;" src="${publicacion.portadaUrl}"></a>
                   <span style="float:left;margin-top: -10px"><b><i><c:out value="${publicacion.fuente.autor}"/></i></b></span>
				   <span style="float:left;margin-bottom: -10px">Tema: <b><c:out value="${publicacion.nombre}"/></b></span>
				   <span style="float:left;margin-bottom: -10px">ISBN: <b><c:out value="${publicacion.isbn}"/></b></span>
				   <span style="float:left;"><c:out value="${publicacion.editorial.nombre}"/></span>
				 <c:if test="${publicacion.descuento == '0.00'}">
             		<div style="padding:5px;text-align:center;margin-bottom:-30px"><b style="font-size:15">$ ${publicacion.precio} MXN</b></div>
             	 </c:if>
				 <c:if test="${publicacion.descuento != '0.00'}">
				 	<div style="padding:5px;text-align:center;margin-bottom:-30px"><b style="font-size:15;text-decoration:line-through;">$ ${publicacion.precio} MXN</b></div><br>
             		<div class="banner-title">Precio Oferta: $ <c:out value="${publicacion.precio - publicacion.descuento}"/> MXN</div>
             	 </c:if>
				 <b><a href="${contextpath}/publicacion/${publicacion.id}" style="font-size:15;width:100%;padding:5px;text-align:center;margin-bottom:-10px" class="btn btn-primary">Comprar</a></b>
             </div>
             <div class="container">
				<div class="modal fade" id="modalPublicacion${publicacion.id}" role="dialog">
				  <div class="modal-dialog">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title"><b><c:out value="${publicacion.nombre}"/></b></h4>
				        </div>
				        <div class="modal-body">
				        
				        <div class="row">
				        	<div class="col-md-6">
				        		<div style="text-align: center;">
				          			<p><img style="border-radius:10px;width: 70%;" src="${publicacion.portadaUrl}"></p>
				          		</div>
				        	</div>
				        	<div class="col-md-6">
				        		<h5 style="text-align: center;"><b>Sinopsis</b></h5>
				          		<p style="text-indent: 2em;text-align: justify;"><c:out value="${publicacion.resumen}"/></p>
				        	</div>
				        </div>
				        	 <h5 style="text-align: center;" class="alert alert-info">Informaci&oacute;n Adicional</h5>
					         <div class="row">
							      <div class="col-md-6">
							        <b>Autor</b>
							      </div>
							      <div class="col-md-6">
							        <b>Tema</b>
							      </div>
						     </div>
						     <div class="row">
							      <div class="col-md-6">
							        <c:out value="${publicacion.fuente.autor}"/>
							      </div>
							      <div class="col-md-6">
							        <c:out value="${publicacion.nombre}"/>
							      </div>
						     </div>
						     <br>
						     <div class="row">
							      <div class="col-md-6">
							        <b>ISBN</b>
							      </div>
							      <div class="col-md-6">
							        <b>P&aacute;ginas</b>
							      </div>
						     </div>
						     <div class="row">
							      <div class="col-md-6">
							        <c:out value="${publicacion.isbn}"/>
							      </div>
							      <div class="col-md-6">
							        <c:out value="${publicacion.numeroPaginas}"/>
							      </div>
						     </div>
						     <br>
						     <div class="row">
							      <div class="col-md-6">
							        <b>Precio p&uacute;blico</b>
							      </div>
							      <div class="col-md-6">
							        <b>Descuento disponible</b>
							      </div>
						     </div>
						     <div class="row">
							      <div class="col-md-6">
							        $ <c:out value="${publicacion.precio}"/> MXN
							      </div>
							      <div class="col-md-6">
							        $ <c:out value="${publicacion.descuento}"/> MXN
							      </div>
						     </div>
				        </div>
				        <div class="modal-footer">
				          <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
				        </div>
				      </div>
				    </div>
				  </div>
			</div>
          </c:forEach>      
     </div>
     
</body>
</html>