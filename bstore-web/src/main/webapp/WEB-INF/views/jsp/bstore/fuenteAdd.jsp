<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../../layout/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fuente nueva</title>



</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12">
				<form:form action="${contextpath}/fuente/saveFuente" method="POST" commandName="fuente">

					<div class="form-group">
						<div class="control-label col-sm-12 alert alert-success"
							style="text-align: center;"><b>Informaci&oacute;n de Fuente</b>
						</div>
						<br/>
						<br/>
					</div>
					<br/>
					<br/>
					
					<div class="form-group">
						<label class="control-label col-sm-1" for="nombreBiblioteca">Nombre Biblioteca</label>
						
						<div class="col-sm-3">
							<form:input path="nombreBiblioteca" class="form-control" placeholder="Nombre de Biblioteca" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="nombreBiblioteca" />
							</span>
						</div>
						
						<label for="rfc" class="col-sm-1 control-label">Página web</label>
						<div class="col-sm-3">
							<form:input path="paginaWeb" class="form-control" placeholder="Página web" />
						</div>	
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="paginaWeb" />
							</span>
						</div>
					</div>
					<br/>
					<br/>
					
					
					<div class="form-group">
						<label for="email" class="col-sm-1 control-label">Email</label>
						<div class="col-sm-3">
							<form:input path="email" class="form-control" placeholder="Correo" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="email" />
							</span>
						</div>
						
						<label for="telefono" class="col-sm-1 control-label">Telefono</label>
						<div class="col-sm-3">
							<form:input path="telefono" class="form-control" placeholder="Telefono" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="telefono" />
							</span>
						</div>
						
						
					</div>
					<br/>
					<br/>
					
					<div class="form-group">
						<label for="rfc" class="col-sm-1 control-label">RFC</label>
						<div class="col-sm-3">
							<form:input path="rfc" class="form-control" placeholder="rfc" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="rfc" />
							</span>
						</div>
						
						<label for="autor" class="col-sm-1 control-label">Autor</label>
						<div class="col-sm-3">
							<form:input path="autor" class="form-control" placeholder="Autor" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="autor" />
							</span>
						</div>
						
						
					</div>

					<div class="row" style="padding-right: 12px; padding-top: 40px">
						<div class="col-sm-8">
						</div>
						<div class="col-sm-2">
							<a
								href="${contextpath}/fuente/getAll" class="btn btn-default"
								style="font-size: 15; width: 100%; padding: 10px; text-align: center;" >
								<i class="glyphicon glyphicon-remove"></i> <c:out value="Cancelar" />
							</a>
						</div>
						<div class="col-sm-2">
							<button id="saveColeccion" class="btn btn-primary" style="font-size: 15; width: 100%; padding: 10px; text-align: center;" type="submit"><i class="glyphicon glyphicon-floppy-disk"></i> Guardar</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br>
	<br>
</body>
</html>