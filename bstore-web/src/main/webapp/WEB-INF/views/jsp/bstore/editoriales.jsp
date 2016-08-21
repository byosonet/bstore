<%@ include file="../../layout/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
			$('#tablaEditoriales').DataTable({
				responsive: true,
				"lengthMenu": [5, 10, 15, 20, 25],
				"language" : {
					"lengthMenu" : "Nº registros a Mostrar: _MENU_",
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
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editoriales</title>
</head>
<body>
<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-sm-offset-0 col-md-12">
				<div class="form-group">
                    <div class="control-label col-sm-12 alert alert-success" style="text-align: center;"><b>Cat&aacute;logo de Editoriales</b></div>
                </div>
                <div class="form-group">
				  <br /> <br /> <br />
				</div>
				<div class="form-group">
				
				<table id="tablaEditoriales"
					class="display table dt-responsive nowrap" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th style="text-align: center;" class="alert alert-info">#</th>
							<th style="text-align: center;" class="alert alert-info">Nombre</th>
							<th style="text-align: center;" class="alert alert-info">RFC</th>
							<th style="text-align: center;" class="alert alert-info">Tel&eacute;fono</th>
							<th style="text-align: center;" class="alert alert-info">Email</th>
							<th style="text-align: center;" class="alert alert-info">Editado por Usuario</th>
							<th style="text-align: center;" class="alert alert-info">Fecha Actualizaci&oacute;n</th>
							<th style="text-align: center;" class="alert alert-info">Estatus</th>
							<th style="text-align: center;" class="alert alert-info">Acciones</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th style="text-align: center;" class="alert alert-info">#</th>
							<th style="text-align: center;" class="alert alert-info">Nombre</th>
							<th style="text-align: center;" class="alert alert-info">RFC</th>
							<th style="text-align: center;" class="alert alert-info">Tel&eacute;fono</th>
							<th style="text-align: center;" class="alert alert-info">Email</th>
							<th style="text-align: center;" class="alert alert-info">Editado por Usuario</th>
							<th style="text-align: center;" class="alert alert-info">Fecha Actualizaci&oacute;n</th>
							<th style="text-align: center;" class="alert alert-info">Estatus</th>
							<th style="text-align: center;" class="alert alert-info">Acciones</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="editorial" items="${editoriales}">
							<tr>
								<td class="text" style="text-align: center;">${editorial.id}</td>
								<td class="text">${editorial.nombre}</td>
								<td class="text">${editorial.rfc}</td>
								<td class="text">${editorial.telefono}</td>
								<td class="text">${editorial.email}</td>
								<td class="text">${editorial.usuario}</td>
								<td class="text" style="text-align: center;"><fmt:formatDate value="${editorial.fechaUmodif}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
								
								<td class="text" style="text-align: center;">
								<c:if test="${editorial.estatus eq 1}">
								    <label style="padding: 7px; margin-top: -5px;margin-bottom: -5px;" class="text alert btn-info">
										<c:out value="Activado"></c:out>
									</label>
								</c:if>
								<c:if test="${editorial.estatus eq 0}">
									<label style="padding: 7px; margin-top: -5px;margin-bottom: -5px;" class="text alert btn-warning">
										<c:out value="Desactivado"></c:out>
									</label>
								</c:if>
								</td>
								<td class="text" style="text-align: center;">
									<a href="#${editorial.id}" style="padding: 7px; margin-top: 5px;margin-bottom: 0px;" class="btn btn-success">
									 <c:out value="Modificar" />
									</a>
									<a href="#${editorial.id}" style="padding: 7px; margin-top: 5px;margin-bottom: 0px;" class="btn btn-danger">
									 <c:out value="Eliminar" />
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				</div>
				<div class="form-group" style="float:right;">
					<a href="${contextpath}/editorial/add"
						class="btn btn-primary">
						<i class="fa fa-plus"></i> Nueva Editorial
					</a>
				</div>
			</div>
		</div>
</div>
</body>
</html>