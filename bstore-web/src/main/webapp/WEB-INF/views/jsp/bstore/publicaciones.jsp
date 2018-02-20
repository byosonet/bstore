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
	placeholder: 'Buscar documentos',
    allowClear: true,
    language: {
        noResults: function(){
            return "No se han encontrado resultados";
        }
    }
});

$(".ordenarPublicaciones").select2({
	theme: "classic",
	width: '200',
	placeholder: 'Filtros de ordenamiento',
    allowClear: true,
    language: {
        noResults: function(){
            return "No se han encontrado resultados";
        }
    }
});

$("#listaPub").change(function(){
    window.location.href = $(this).val();
});

$("#ordernarPub").change(function(){
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

      <div style="float: right;">
        <select class="ordenarPublicaciones" id="ordernarPub">
        	<option></option>        	        
        	<!-- option value="${contextpath}/coleccion/precio/desc/${coleccionId}">Mayor precio</option>
        	<option value="${contextpath}/coleccion/precio/asc/${coleccionId}">Menor precio</option>
        	<option value="${contextpath}/coleccion/comprados/${coleccionId}">Mis compras</option>        	
        	<option value="${contextpath}/coleccion/porcomprar/${coleccionId}">Publicaciones por comprar</option -->
        	<option value="${contextpath}/coleccion/tema/asc/${coleccionId}">Tema ascendente</option>
        	<option value="${contextpath}/coleccion/tema/desc/${coleccionId}">Tema descendente</option>
        </select>
       </div>
       <div style="float:right;top: 135px;margin-right:10px;">
            <select class="listaPublicaciones" id="listaPub">
			<option></option>
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
    		 		<c:set var="valueColor" value="btn btn-primary" />
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
                   <span class="text" style="float:left;margin-top: -10px"><c:out value="${publicacion.fuente.autor}"/></span>
                   <!-- span class="text" style="float:left;margin-top: 5px;margin-left:45px;"><b class="">Publicaci&oacute;n</b></span -->
                   <span class="text" style="float:left;margin-bottom: -10px"><c:out value="${publicacion.nombre}"/></span>
				   <span class="text" style="float:left;margin-bottom: -10px">ISBN: <b><c:out value="${publicacion.isbn}"/></b></span>
				   <!-- span class="text" style="float:left;margin-top: 10px;">Editorial: <c:out value="${publicacion.editorial.nombre}"/></span -->
				   	 
              <b><a href="${valueUrl}" style="width:100%;padding:5px;text-align:center;margin-top:-50px" class="text ${valueColor}"><span class="${valueIcon}"></span> <c:out value="${valueName}"/></a></b>
             </div>
             <div class="container">
				<div class="modal fade" id="modalPublicacion${publicacion.id}" role="dialog">
                    <div class="modal-dialog" style="width: 550px;">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title"><b class="text"><c:out value="${publicacion.nombre}"/></b></h4>
				        </div>
				        <div class="modal-body">
				        
				        <div class="row">
				        	<div class="col-sm-6">
				        		<div style="text-align: center;">
				          			<p><img style="border-radius:10px;width: 60%;" src="${publicacion.portadaUrl}"></p>
				          		</div>
				        	</div>
				        	<div class="col-sm-6">
				        		
				          		<p class="text" style="text-align: justify;"><c:out value="${publicacion.resumen}"/></p>
				        	</div>
				        </div>
                  <!--h5 style="text-align: center;" class="alert alert-info text">Ultima fecha de actualizaci&oacute;n: <b class="text"><fmt:formatDate value="${publicacion.fechaUmodif}" pattern="dd-MM-yyyy HH:mm:ss"/></b></h5-->
					         <div class="row">
							      <div class="col-sm-6">
							        <b class="text"></b><c:out value="${publicacion.fuente.autor}"/>
							      </div>
							      <div class="col-sm-6">
							        <b class="text">
							        	
							        </b>
							        <!--  c:out value="${publicacion.editorial.nombre}"/ -->
							      </div>
						     </div>
						     <br>
						     <div class="row">
							      <div class="col-sm-6">
                                    <b class="text">ISBN: </b><c:out value=" ${publicacion.isbn}"/>
							      </div>
							      <div class="col-sm-6">
							        <b class="text">P&aacute;ginas: </b><c:out value=" ${publicacion.numeroPaginas}"/>
							      </div>
						     </div>
						     <br>
						     <!-- div class="row">
							      <div class="col-sm-6">
                                    <b class="text">Descuento: </b>$ <c:out value=" ${publicacion.descuento}"/> MXN
							      </div>
							      <div class="col-sm-6">							        
							        <b class="text">Precio: </b>$ <c:out value=" ${publicacion.precio}"/> MXN
							      </div>
						     </div -->
				        </div>
				        <div class="modal-footer">
					      <div class="col-sm-12">
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
		 	<div class="row col-sm-5 col-sm-offset-4 main alert alert-success">
                 <p style="text-align: center;"><b class="text">Aun no existen publicaciones asociadas a esta colecci&oacute;n, intente m&aacute;s tarde.</b>
                 	<a href="${contextpath}/equivira" class="text btn btn-default">Continuar</a>
                 </p>
		 	</div>
		 </c:if>          
     </div>
     
</body>
</html>