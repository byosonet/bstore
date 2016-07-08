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
    		 <c:choose>
    		 	<c:when test="${publicacion.comprada}">
    		 		<c:set var="valueName" value="Leer" />
    		 		<c:set var="valueUrl" value="${contextpath}/publicacion/${publicacion.id}" />
    		 		<c:set var="valueIcon" value="glyphicon glyphicon-book" />
    		 		<c:set var="valueColor" value="btn btn-success" />
    		 	</c:when>
    		 	<c:otherwise>
    		 		<c:set var="valueName" value="Comprar" />
    		 		<c:set var="valueUrl" value="${contextpath}/comprar/publicacion/${publicacion.id}" />
    		 		<c:set var="valueIcon" value="glyphicon glyphicon-shopping-cart" />
    		 		<c:set var="valueColor" value="btn btn-primary" />
    		 	</c:otherwise>
    		 </c:choose>
             <div class="item banner-wrapper">
                 <a href="#" data-toggle="modal" data-target="#modalPublicacion${publicacion.id}"><img class="zoom" style="border-radius:10px;margin-top:-30px;width: 100%;" src="${publicacion.portadaUrl}"></a>
                   <span style="float:left;margin-top: -10px;margin-left:40px;"><b>Autor/Fuente</b></span>
                   <span style="float:left;margin-top: -10px"><c:out value="${publicacion.fuente.autor}"/></span>
				   <span style="float:left;margin-bottom: -10px">Tema: <c:out value="${publicacion.nombre}"/></span>
				   <span style="float:left;margin-bottom: -10px">ISBN: <b><c:out value="${publicacion.isbn}"/></b></span>
				   <span style="float:left;"><c:out value="${publicacion.editorial.nombre}"/></span>
				 <c:if test="${publicacion.descuento == '0.00'}">
             		<div style="padding:5px;text-align:center;margin-bottom:-30px"><b style="font-size:15">$ ${publicacion.precio} MXN</b></div>
             	 </c:if>
				 <c:if test="${publicacion.descuento != '0.00'}">
				 	<div style="padding:5px;text-align:center;margin-bottom:-30px"><b style="font-size:15;text-decoration:line-through;">$ ${publicacion.precio} MXN</b></div><br>
             		<div class="banner-title">Precio Oferta: $ <c:out value="${publicacion.precio - publicacion.descuento}"/> MXN</div>
             	 </c:if>
				 <b><a href="${valueUrl}" style="font-size:15;width:100%;padding:5px;text-align:center;margin-bottom:-10px" class="${valueColor}"><span class="${valueIcon}"></span> <c:out value="${valueName}"/></a></b>
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
				        	 <h5 style="text-align: center;" class="alert alert-info">Ultima fecha de actualizaci&oacute;n: <b><fmt:formatDate value="${publicacion.fechaUmodif}" pattern="dd-MM-yyyy HH:mm:ss"/></b></h5>
					         <div class="row">
							      <div class="col-md-6">
							        <b>Autor/Fuente</b>
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
					      <div class="col-md-12">
					      	<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cerrar</button>
					        <b><a href="${valueUrl}" class="${valueColor}"><span class="${valueIcon}"></span> <c:out value="${valueName}"/></a></b>
					      </div>
				        </div>
				      </div>
				    </div>
				  </div>
			</div>
          </c:forEach>
          <c:if test="${empty publicaciones}">
		 	<div class="row alert alert-danger">
		 	    <p style="text-align: center;">Aun no existen publicaciones asociadas a esta colecci&oacute;n, intente m&aacute;s tarde.</p>
		 	</div>
		 </c:if>          
     </div>
     
</body>
</html>