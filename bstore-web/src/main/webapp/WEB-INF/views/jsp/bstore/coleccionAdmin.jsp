<%@ include file="../../layout/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$('#tablaColecciones').DataTable({
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
<title>Colecciones</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-sm-offset-0 col-md-12">
				<div class="form-group">
                    <div class="control-label col-sm-12 alert alert-success" style="text-align: center;"><b>Colecciones</b></div>
                </div>
                <div class="form-group">
				  <br /> <br /> <br />
				</div>
				<div class="form-group">
					<a href="#"
						class="btn btn-primary pull-right"
						style="font-size: 15; width: 15%; padding: 5px; text-align: center; margin-bottom: 20px; margin-top: -20px; align: right;">
						<i class="fa fa-plus"></i> <c:out value="Agregar coleccion" />
					</a>
				</div>
				<div class="form-group">
				<table id="tablaColecciones"
					class="table table-striped table-bordered" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th class="alert alert-success">Id</th>
							<th class="alert alert-success">Nombre</th>
							<th class="alert alert-success">Nombre a mostrar</th>
							<th class="alert alert-success">Estatus</th>
							<th class="alert alert-success">URL Portada</th>
							<th class="alert alert-success">Ranking</th>
							<th class="alert alert-success">Usuario última modif</th>
							<th class="alert alert-success">Fecha última modif</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Nombre a mostrar</th>
							<th>Estatus</th>
							<th>URL Portada</th>
							<th>Ranking</th>
							<th>Usuario última modif</th>
							<th>Fecha última modif</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="coleccion" items="${colecciones}">
							<tr>
								<td>${coleccion.id}</td>
								<td>${coleccion.nombre}</td>
								<td>${coleccion.nombreMostrar}</td>
								<td>${coleccion.estatus}</td>
								<td>${coleccion.portadaUrl}</td>
								<td>${coleccion.ranking}</td>
								<td>${coleccion.idUsuarioUmodif}</td>
								<td>${coleccion.fechaUmodif}</td>
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