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
                 <a href="${contextpath}/publicacion/${publicacion.id}"><img class="zoom" style="border-radius:10px;margin-top:-30px;width: 100%;" src="${publicacion.portadaUrl}"></a>
                   <span style="float:left;margin-top: -10px"><b><i><c:out value="${publicacion.fuente.autor}"/></i></b></span>
				   <span style="float:left;margin-bottom: -10px">Tema: <b><c:out value="${publicacion.nombre}"/></b></span>
				   <span style="float:left;margin-bottom: -10px">ISBN: <b><c:out value="${publicacion.isbn}"/></b></span>
				   <span style="float:left;"><c:out value="${publicacion.editorial.nombre}"/></span>
				 <c:if test="${publicacion.descuento == '0.00'}">
             		<div style="padding:5px;text-align:center;margin-bottom:-30px"><b style="font-size:15">$ ${publicacion.precio} MXN</b></div>
             	 </c:if>
				 <c:if test="${publicacion.descuento != '0.00'}">
				 	<div style="padding:5px;text-align:center;margin-bottom:-30px"><b style="font-size:15;text-decoration:line-through;">$ ${publicacion.precio} MXN</b></div><br>
             		<div class="banner-title">Precio Oferta: $ <c:out value="${publicacion.descuento}"/> MXN</div>
             	 </c:if>
				 <b><a href="${contextpath}/publicacion/${publicacion.id}" style="font-size:15;width:100%;padding:5px;text-align:center;margin-bottom:-10px" class="btn btn-success">COMPRAR</a></b>
             </div>
          </c:forEach>      
     </div>
</body>
</html>