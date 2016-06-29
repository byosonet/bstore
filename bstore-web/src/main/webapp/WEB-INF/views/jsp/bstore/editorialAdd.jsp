<%@ include file="../../layout/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editorial nueva</title>
</head>
<body>
	<p>Nueva editorial</p>

	<form>
		<div class="form-group row">
			<label for="nombre" class="col-sm-2 form-control-label">Nombre</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="nombre"
					placeholder="Nombre">
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
			<button class="btn btn-secondary" type="reset">Cancelar</button>
		</div>
	</form>
</body>
</html>