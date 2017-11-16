<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script src="http://listjs.com/assets/javascripts/list.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var opciones = { valueNames: [ 'tema', 'autor', 'isbn', 'fecha' ] };
	var lista = new List('publicaciones', opciones);
});
</script>
</head>
<body>
<div id="publicaciones">
<input class="search" placeholder="Buscar Tema" />
<button class="sort" data-sort="tema">Ordenar por Tema</button>
<ul class="list">
	<c:forEach var="publicacion" items="${publicaciones}">
		 <div class="item banner-wrapper">
			   <a href="#" data-toggle="modal" data-target="#modalPublicacion${publicacion.id}"><img class="" style="border-radius:10px;width: 7%;" src="${publicacion.portadaUrl}"></a>
               <span class="text" style="float:center;margin-left:40px;"><b>Autor/Fuente</b></span>
               <span class="autor" style="float:center;"><c:out value="${publicacion.fuente.autor}"/></span>
			   <span class="tema" style="float:left;margin-bottom: -10px">Tema: <c:out value="${publicacion.nombre}"/></span>
			   <span class="isbn" style="float:left;margin-bottom: -10px">ISBN: <b><c:out value="${publicacion.isbn}"/></b></span>
			   <span class="fecha" style="float:left;"><c:out value="${publicacion.editorial.nombre}"/></span>
			 <c:if test="${publicacion.descuento == '0.00'}">
            	<div class="text" style="padding:5px;text-align:left;margin-bottom:-30px"><b style="font-size:15">$ ${publicacion.precio} MXN</b></div>
             </c:if>
			 <c:if test="${publicacion.descuento != '0.00'}">
			 	<div class="text" style="padding:5px;text-align:left;margin-bottom:-30px"><b style="font-size:15;text-decoration:line-through;">$ ${publicacion.precio} MXN</b></div><br>
             </c:if>
			 <b><a href="${valueUrl}" style="font-size:15;width:100%;padding:5px;text-align:center;margin-bottom:-10px" class="${valueColor}"><span class="${valueIcon}"></span> <c:out value="${valueName}"/></a></b>
		 </div>
	</c:forEach>
</ul>
</div>
</body>
</html>