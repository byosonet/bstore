<%@ include file="../../layout/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		console.log('--load editoriales');
		$(document).ready(function() {
			$('#tablaEditoriales').DataTable();
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editoriales</title>
</head>
<body>
	<p>Editoriales</p>

	<b>
		<a href="${contextpath}/editorial/add" class="btn btn-primary" style="font-size: 15; width: 10%; padding: 5px; text-align: center; margin-bottom: -10px; margin-top: -20px; align:right;">
			<i class="fa fa-folder-open-o"></i> 
			<c:out value="Agregar editorial" />
		</a>
	</b>

	<table id="tablaEditoriales" class="table table-striped table-bordered"
		cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Estatus</th>
				<th>RFC</th>
				<th>Telefono</th>
				<th>E-mail</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Estatus</th>
				<th>RFC</th>
				<th>Telefono</th>
				<th>E-mail</th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach var="editorial" items="${editoriales}">
				<tr>
					<td>${editorial.id}</td>
					<td>${editorial.nombre}</td>
					<td>${editorial.estatus}</td>
					<td>${editorial.rfc}</td>
					<td>${editorial.telefono}</td>
					<td>${editorial.email}</td>
				</tr>
			</c:forEach>
		</tbody>
</body>
</html>