<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../../layout/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editorial nueva</title>

<script type="text/javascript">
	$(function() {

		$('button#saveEditorial').click(function() {
			var nombre = $('input#nombre');
			var rfc = $('input#rfc');
			var email = $('input#email');
			var telefono = $('input#telefono');
			var activo = $('input#activo');

			console.log('nombre: ' + nombre);
			console.log('rfc: ' + rfc);
			console.log('email: ' + email);
			console.log('telefono: ' + telefono);
			console.log('activo: ' + activo);

			if (nombre.val() === "") {
				muestraMsjSistemaError('El nombre es requerido.');
				return false;
			} else if (rfc.val() === "") {
				muestraMsjSistemaError('El RFC es requerido.');
				return false;
			} else if(email.val() === ""){
				muestraMsjSistemaError('El email es requerido.');
				return false;
			} else if(telefono.val() === ""){
				muestraMsjSistemaError('El telefono es requerido.');
				return false;
			} else if(activo.val() === ""){
				muestraMsjSistemaError('El campo \'activo\' es requerido.');
				return false;
			}
			

	});

		function muestraMsjSistemaError(msjStatus) {
			BootstrapDialog.show({
				size : BootstrapDialog.SIZE_SMALL,
				title : 'Mensaje del Sistema',
				closable : false,
				message : msjStatus,
				type : BootstrapDialog.TYPE_DANGER,
				cssClass : 'login-dialog',
				buttons : [ {
					icon : 'glyphicon glyphicon-check',
					label : 'OK',
					cssClass : 'btn-primary',
					action : function(dialog) {
						dialog.close();
					}
				} ]
			});
		}

		function muestraMsjSistemaSuccess(msjStatus) {
			BootstrapDialog.show({
				size : BootstrapDialog.SIZE_SMALL,
				title : 'Mensaje del Sistema',
				closable : false,
				message : msjStatus,
				type : BootstrapDialog.TYPE_SUCCESS,
				cssClass : 'login-dialog',
				buttons : [ {
					icon : 'glyphicon glyphicon-check',
					label : 'CONTINUAR',
					cssClass : 'btn-primary',
					action : function(dialog) {
						dialog.close();
						$.blockUI();
						var urlAction = '${contextpath}' + '/perfil';
						document.getElementById('perfil').action = urlAction;
						document.getElementById('perfil').method = 'GET';
						document.getElementById('perfil').submit();
					}
				} ]
			});
		}
	});
</script>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12">
				<form class="form-horizontal" id="editorialAddForm" method="post">

					<div class="form-group">
						<div class="control-label col-sm-12 alert alert-info"
							style="text-align: center;">Informaci&oacute;n de Editorial
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2"  for="nombre">Nombre</label>
						<div class="col-sm-4">
							<input id="nombre" type="text" class="form-control" placeholder="Nombre" />
						</div>
						
						<label for="rfc" class="col-sm-2 control-label">RFC</label>
						<div class="col-sm-4">
							<input id="rfc" type="text" class="form-control" placeholder="RFC">
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-4">
							<input id="email" type="email" class="form-control" placeholder="Email">
						</div>
						<label for="telefono" class="col-sm-2 control-label">Telefono</label>
						<div class="col-sm-4">
							<input id="telefono" type="text" class="form-control" placeholder="Telefono">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Activar</label>
						<div class="col-sm-4">
							<div class="checkbox checkbox-primary">
								<input id="activo" type="checkbox" value="1" name="activo">
								<label>
                            		Sí
                        		</label>
							</div>
						</div>
					</div>


					<div class="row" align="right">
							<a
								href="${contextpath}/editorial/getAll" class="btn btn-default"
								style="font-size: 15; width: 10%; padding: 5px; text-align: center; align: right;">
								<i class="glyphicon glyphicon-remove"></i> <c:out value="Cancelar" />
							</a>
							<button id="saveEditorial" class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-floppy-disk"></i> Guardar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<br>
</body>
</html>