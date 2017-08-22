<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {                               
$("#publicacion").gridalicious({
	gutter: 25,
	width:200,
    animate: true
	});
	
$(".listaPublicaciones").select2({
	theme: "classic",
	language: "es",
        width: '37%'
});

$("#listaPub").change(function(){
    window.location.href = $(this).val();
});


}          
);
</script>
</head>
<body>
<c:if test="${!empty publicaciones}">
<br/>
<div class="container">
	<div style="text-align: center;">
                <div class="form-group" style="text-align: center;">
		<a href="${contextpath}/coleccion/tema/asc/${coleccionId}"><button class="text btn btn-info">Temas A <span class="glyphicon glyphicon-triangle-top"></span></button></a>
		<a href="${contextpath}/coleccion/tema/desc/${coleccionId}"><button class="text btn btn-info">Tema Z <span class="glyphicon glyphicon-triangle-bottom"></span></button></a>
		<a href="${contextpath}/coleccion/precio/asc/${coleccionId}"><button class="text btn btn-info">Menor precio <span class="glyphicon glyphicon-triangle-bottom"></span></button></a>
		<a href="${contextpath}/coleccion/precio/desc/${coleccionId}"><button class="text btn btn-info">Mayor precio <span class="glyphicon glyphicon-triangle-top"></span></button></a>
                </div>
                <select class="listaPublicaciones" id="listaPub">
			<option value="buscar" selected>Buscar documentos</option>
			<c:forEach var="publicacion" items="${publicaciones}">
			  <c:choose>
    		 	<c:when test="${publicacion.comprada}">
    		 		<c:set var="valueUrl" value="${contextpath}/publicacion/${publicacion.id}" />
    		 		<option value="${valueUrl}">${publicacion.nombre}</option>
    		 	</c:when>
    		 	<c:otherwise>
    		 		<c:set var="valueUrl" value="${contextpath}/comprar/publicacion/${publicacion.id}" />
    		 		<option value="${valueUrl}">${publicacion.nombre}</option>
    		 	</c:otherwise>
    		 </c:choose>
		   </c:forEach>
		</select>
   </div>
</div>
</c:if>    
<br>
    <div id="publicacion">
    	<c:forEach var="publicacion" items="${publicaciones}">
    		 <c:choose>
    		 	<c:when test="${publicacion.comprada}">
    		 		<c:set var="valueName" value="Ver" />
    		 		<c:set var="valueUrl" value="${contextpath}/publicacion/${publicacion.id}" />
    		 		<c:set var="valueIcon" value="" />
    		 		<c:set var="valueColor" value="btn btn-info" />
    		 	</c:when>
    		 	<c:otherwise>
    		 		<c:set var="valueName" value="Comprar" />
    		 		<c:set var="valueUrl" value="${contextpath}/comprar/publicacion/${publicacion.id}" />
    		 		<c:set var="valueIcon" value="" />
    		 		<c:set var="valueColor" value="btn btn-primary" />
    		 	</c:otherwise>
    		 </c:choose>
             <div class="item banner-wrapper" style="${publicacion.comprada ? 'background-color:':''}">
                 <a href="#" data-toggle="modal" data-target="#modalPublicacion${publicacion.id}"><img class="zoom" style="border-radius:10px;margin-top:-30px;width: 100%;" src="${publicacion.portadaUrl}"></a>
                 <!--<span class="text" style="float:left;margin-top: -20px;margin-left:20px;color:orange;font-size: 16px;"><b class=""><span class="glyphicon glyphicon-star"></span>68</b> <span class="label label-warning">Me gusta</span></span>-->
                   <span class="text" style="float:left;margin-top: -10px;margin-left:40px;"><b class="">Autor/Fuente</b></span>
                   <span class="text" style="float:left;margin-top: -10px"><c:out value="${publicacion.fuente.autor}"/></span>
				   <span class="text" style="float:left;margin-bottom: -10px">Tema: <c:out value="${publicacion.nombre}"/></span>
				   <span class="text" style="float:left;margin-bottom: -10px">ISBN: <b><c:out value="${publicacion.isbn}"/></b></span>
				   <span class="text" style="float:left;margin-top: 10px;">Editorial: <c:out value="${publicacion.editorial.nombre}"/></span>
				 <c:if test="${publicacion.descuento == '0.00' && valueName != 'Leer'}">
             		<div class="text" style="padding:5px;text-align:center;margin-bottom:-30px"><b>$ ${publicacion.precio} MXN</b></div>
             	 </c:if>
				 <c:if test="${publicacion.descuento != '0.00' && valueName != 'Leer'}">
				 	<div class="text" style="padding:5px;text-align:center;margin-bottom:-30px"><b style="text-decoration:line-through;">$ ${publicacion.precio} MXN</b></div><br>
             		<div class="text banner-title"> <b class="text">Oferta: $ <c:out value="${publicacion.precio - publicacion.descuento}"/> MXN</b></div>
             	 </c:if>
			  <c:if test="${publicacion.comprada}">
			 	<span class="text label label-danger" style="float:left;margin-top: -10px;margin-left:-4px;">Comprado <fmt:formatDate value="${publicacion.fechaCompraTemporal}" pattern="dd/MM/yyyy"/></span>
                         </c:if>	 
                        <b><a href="${valueUrl}" style="width:100%;padding:5px;text-align:center;margin-bottom:-10px" class="text ${valueColor}"><span class="${valueIcon}"></span> <c:out value="${valueName}"/></a></b>
             </div>
             <div class="container">
				<div class="modal fade" id="modalPublicacion${publicacion.id}" role="dialog">
                                    <div class="modal-dialog">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title"><b class="text"><c:out value="${publicacion.nombre}"/></b></h4>
				        </div>
				        <div class="modal-body">
				        
				        <div class="row">
				        	<div class="col-md-6">
				        		<div style="text-align: center;">
				          			<p><img style="border-radius:10px;width: 70%;" src="${publicacion.portadaUrl}"></p>
				          		</div>
				        	</div>
				        	<div class="col-md-6">
				        		<h5 style="text-align: center;"><b class="text">Sinopsis</b></h5>
				          		<p class="text" style="text-indent: 2em;text-align: justify;"><c:out value="${publicacion.resumen}"/></p>
				        	</div>
				        </div>
                  <br/><!--h5 style="text-align: center;" class="alert alert-info text">Ultima fecha de actualizaci&oacute;n: <b class="text"><fmt:formatDate value="${publicacion.fechaUmodif}" pattern="dd-MM-yyyy HH:mm:ss"/></b></h5-->
					         <div class="row">
							      <div class="col-md-6">
							        <b class="text">Autor/Fuente</b>
							      </div>
							      <div class="col-md-6">
							        <b class="text">Tema</b>
							      </div>
						     </div>
						     <div class="row">
							      <div class="text col-md-6">
							        <c:out value="${publicacion.fuente.autor}"/>
							      </div>
							      <div class="text col-md-6">
							        <c:out value="${publicacion.nombre}"/>
							      </div>
						     </div>
						     <br>
						     <div class="row">
							      <div class="col-md-6">
                                                                  <b class="text">ISBN</b>
							      </div>
							      <div class="col-md-6">
							        <b class="text">P&aacute;ginas</b>
							      </div>
						     </div>
						     <div class="row">
							      <div class="text col-md-6">
							        <c:out value="${publicacion.isbn}"/>
							      </div>
							      <div class="text col-md-6">
							        <c:out value="${publicacion.numeroPaginas}"/>
							      </div>
						     </div>
						     <br>
						     <div class="row">
							      <div class="col-md-6">
                                                                  <b class="text">Precio p&uacute;blico</b>
							      </div>
							      <div class="col-md-6">
							        <b class="text">Descuento disponible</b>
							      </div>
						     </div>
						     <div class="row">
							      <div class="text col-md-6">
							        $ <c:out value="${publicacion.precio}"/> MXN
							      </div>
							      <div class="text col-md-6">
							        $ <c:out value="${publicacion.descuento}"/> MXN
							      </div>
						     </div>
				        </div>
				        <div class="modal-footer">
					      <div class="col-md-12">
					      	<button type="button" class="text btn btn-default" data-dismiss="modal"><span class=""></span> Cerrar</button>
					        <b><a href="${valueUrl}" class="text ${valueColor}"><span class="${valueIcon}"></span> <c:out value="${valueName}"/></a></b>
					      </div>
				        </div>
				      </div>
				    </div>
				  </div>
			</div>
          </c:forEach>
          <c:if test="${empty publicaciones}">
		 	<div class="row alert alert-danger">
                            <p style="text-align: center;"><b class="text">Aun no existen publicaciones asociadas a esta colecci&oacute;n, intente m&aacute;s tarde.</b></p>
		 	</div>
		 </c:if>          
     </div>
     
</body>
</html>