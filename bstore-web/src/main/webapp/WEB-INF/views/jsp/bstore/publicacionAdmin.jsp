<%@ include file="../../layout/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$('#tablaPublicaciones').DataTable({
				"language" : {
					"lengthMenu" : "Mostrar _MENU_ registros por página",
					"zeroRecords" : "No hay registros",
					"info" : "Página _PAGE_ de _PAGES_",
					"infoEmpty" : "No hay registros",
					"infoFiltered" : "(Filtrado de _MAX_ registros)",
					"sSearch" : "Buscar:",
					"oPaginate" : {
						"sFirst" : "Primero",
						"sLast" : "Último",
						"sNext" : "Siguiente",
						"sPrevious" : "Anterior"
					},
				}
			});
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Publicaciones</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-sm-offset-0 col-md-12">
				<div class="form-group">
                    <div class="control-label col-sm-12 alert alert-success" style="text-align: center;"><b>Publicaciones</b></div>
                </div>
                <div class="form-group">
				  <br /> <br /> <br />
				</div>
				<div class="form-group">
					<a href="#"
						class="btn btn-primary pull-right"
						style="font-size: 15; width: 15%; padding: 5px; text-align: center; margin-bottom: 20px; margin-top: -20px; align: right;">
						<i class="fa fa-plus"></i> <c:out value="Agregar publicacion" />
					</a>
				</div>
				<div class="form-group">
				<table id="tablaPublicaciones"
					class="table table-striped table-bordered" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th class="alert alert-success">Id</th>
							<th class="alert alert-success">Nombre</th>
							<th class="alert alert-success">Portada</th>
							<th class="alert alert-success">Url</th>
							<th class="alert alert-success">Coleccion</th>
							<th class="alert alert-success">Fuente</th>
							<th class="alert alert-success">Editorial</th>
							<th class="alert alert-success">ISBN</th>
							<th class="alert alert-success">Precio</th>
							<th class="alert alert-success">Descuento</th>
							<th class="alert alert-success">Estatus</th>
							<th class="alert alert-success">Fecha Última Modif</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Portada</th>
							<th>Url</th>
							<th>Coleccion</th>
							<th>Fuente</th>
							<th>Editorial</th>
							<th>ISBN</th>
							<th>Precio</th>
							<th>Descuento</th>
							<th>Estatus</th>
							<th>Fecha Última Modif</th>
							<th></th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="publicacion" items="${publicaciones}">
							<tr>
								<td>${publicacion.id}</td>
								<td>${publicacion.nombre}</td>
								<td>${publicacion.portada}</td>
								<td>${publicacion.urlArchivo}</td>
								<td>${publicacion.coleccion.nombre}</td>
								<td>${publicacion.fuente.nombreBiblioteca}</td>
								<td>${publicacion.editorial.nombre}</td>
								<td>${publicacion.isbn}</td>
								<td>${publicacion.precio}</td>
								<td>${publicacion.descuento}</td>
								<td>${publicacion.estatus}</td>
								<td>${publicacion.fechaUmodif}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>