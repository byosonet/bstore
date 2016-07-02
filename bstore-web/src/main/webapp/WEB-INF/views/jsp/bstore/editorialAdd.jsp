<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<form method="post" action="${contextpath}/editorial/saveEditorial">
		<div class="form-group row">
			<label for="nombre" class="col-sm-2 form-control-label">Nombre</label>
			<div class="col-sm-10">
				<input id="nombre" type="text"  class="form-control" placeholder="Nombre" />
			</div>
		</div>
		<div class="form-group row">
			<label for="rfc" class="col-sm-2 form-control-label">RFC</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="rfc" placeholder="RFC">
			</div>
		</div>
		<div class="form-group row">
			<label for="email" class="col-sm-2 form-control-label">Email</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="email"
					placeholder="Email">
			</div>
		</div>
		<div class="form-group row">
			<label for="telefono" class="col-sm-2 form-control-label">Telefono</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="telefono"
					placeholder="Telefono">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">Activo</label>
			<div class="col-sm-10">
				<div class="checkbox">
					<label> <input type="checkbox" value="">
					</label>
				</div>
			</div>
		</div>


		<div class="form-group row" align="center">
			<button class="btn btn-primary" type="submit">Guardar</button>
			<span>&nbsp&nbsp&nbsp&nbsp&nbsp</span>


			<a href="${contextpath}/editorial/getAll"
				class="btn btn-secondary"
				style="font-size: 15; width: 10%; padding: 5px; text-align: center; align: right;">
				<i class="fa fa-folder-open-o"></i> <c:out value="Cancelar" />
			</a>
		</div>
	</form>
</body>
</html>