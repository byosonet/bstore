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
			$('#tablaDetalleVenta').DataTable({
				"bPaginate": false,
		        "bFilter": false,
		        "bInfo": false
			});
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editoriales</title>
</head>
<body>
	<div class="row" style="padding: 0.5em;">
  		<div class="col-md-12 alert alert-info" style="text-align: center"><b>1.- Valida el detalle de tu compra</b></div>
  		<div class="col-md-12" style="padding: 0.5em;text-align: center;margin-top: -20px">
  			<img style="width: 20%;height:auto;border-radius:5px;" src="${publicacion.portadaUrl}">
  		</div>
  		<div class="col-md-12">
	  		<table id="tablaDetalleVenta" class="table table-striped" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th style="text-align: center;width: 30%;" class="alert alert-success">Nombre</th>
					<th style="text-align: center;" class="alert alert-success">ISBN</th>
					<th style="text-align: center;" class="alert alert-success">Descuento</th>
					<th style="text-align: center;" class="alert alert-success">Precio Venta</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th></th>
					<th></th>
					<th class="alert alert-warning" style="text-align: center;">Total a Pagar </th>
					<th class="alert alert-warning" style="text-align: center;">$ ${publicacion.precio - publicacion.descuento} MXN</th>
				</tr>
			</tfoot>
			<tbody>
				<tr>
					<td style="text-align: center;">${publicacion.nombre}</td>
					<td style="text-align: center;">${publicacion.isbn}</td>
					<td style="text-align: center;">$ ${publicacion.descuento}</td>
					<c:if test="${publicacion.descuento != '0.00'}">
						<td style="text-align: center;text-decoration:line-through;">$ ${publicacion.precio}</td>
					</c:if>
					<c:if test="${publicacion.descuento == '0.00'}">
						<td style="text-align: center;">$ ${publicacion.precio}</td>
					</c:if>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<br>
<div class="col-md-12 alert alert-info" style="text-align: center"><b>2.- Selecciona tu forma de pago:</b>
	<img style="margin-top:-5px;" width="140px" height="35px" src="${contextpath}/static/resources/images/visa.png">
</div>

</body>
</html>