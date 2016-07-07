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
</head>
<body>
	<p>Nueva editorial</p>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-sm-offset-0 col-md-12">
				<form class="form-horizontal" id="editorialAddForm" method="post"
					action="${contextpath}/editorial/saveEditorial">

					<div class="form-group">
						<div class="control-label col-sm-12 alert alert-info"
							style="text-align: center;">Informaci&oacute;n de Editorial
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2"  for="nombre">Nombre:</label>
						<div class="col-sm-4">
							<input id="nombre" type="text" class="form-control" placeholder="Nombre" />
						</div>
						
						<label for="rfc" class="col-sm-2 form-control-label">RFC</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="rfc" placeholder="RFC">
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 form-control-label">Email</label>
						<div class="col-sm-4">
							<input type="email" class="form-control" id="email" placeholder="Email">
						</div>
						<label for="telefono" class="col-sm-2 form-control-label">Telefono</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="telefono" placeholder="Telefono">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2">Activo</label>
						<div class="col-sm-4">
							<div class="checkbox">
								<label> <input type="checkbox" value=""></label>
							</div>
						</div>
						<div class="col-sm-6">
								<label/>
						</div>
					</div>


					<div class="form-group" align="center">
						<div class="col-sm-6">
							<button class="btn btn-primary" type="submit">Guardar</button>
						</div>
						<div class="col-sm-6">
							<span>&nbsp&nbsp&nbsp&nbsp&nbsp</span> <a
								href="${contextpath}/editorial/getAll" class="btn btn-secondary"
								style="font-size: 15; width: 10%; padding: 5px; text-align: center; align: right;">
								<i class="fa fa-folder-open-o"></i> <c:out value="Cancelar" />
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>