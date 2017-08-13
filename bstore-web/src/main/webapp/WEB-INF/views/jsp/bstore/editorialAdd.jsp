<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../../layout/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editorial nueva</title>



</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12">
				<form:form action="${contextpath}/editorial/saveEditorial" method="POST" commandName="editorial">

					<div class="form-group">
						<div class="control-label col-sm-12 alert alert-success"
							style="text-align: center;"><b>Informaci&oacute;n de Editorial</b>
						</div>
						<br/>
						<br/>
					</div>
					<br/>
					<br/>
					
					<div class="form-group">
						<label class="control-label col-sm-1" for="nombre">Nombre</label>
						
						<div class="col-sm-3">
							<form:input path="nombre" class="form-control" placeholder="Nombre" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="nombre" />
							</span>
						</div>
						
						<label for="rfc" class="col-sm-1 control-label">RFC</label>
						<div class="col-sm-3">
							<form:input path="rfc" class="form-control" placeholder="RFC" />
						</div>	
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="rfc" />
							</span>
						</div>
					</div>
					<br/>
					<br/>
					
					
					<div class="form-group">
						<label for="email" class="col-sm-1 control-label">Email</label>
						<div class="col-sm-3">
							<form:input path="email" class="form-control" placeholder="Email" />
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
						<label class="col-sm-2 control-label">Activar</label>
						<div class="col-sm-4">
							<div class="checkbox-primary">
								<form:checkbox path="estatus" value="1"/>
								<label>
                            		Sí
                        		</label>
							</div>
						</div>
					</div>

					<div class="row" style="padding-right: 12px; padding-top: 40px">
						<div class="col-sm-8">
						</div>
						<div class="col-sm-2">
							<a
								href="${contextpath}/editorial/getAll" class="btn btn-default"
								style="font-size: 15; width: 100%; padding: 10px; text-align: center;">
								<i class="glyphicon glyphicon-remove"></i> <c:out value="Cancelar" />
							</a>
						</div>
						<div class="col-sm-2">
							<button id="saveEditorial" class="btn btn-primary" style="width: 100%; padding: 10px; text-align: center;" type="submit"><i class="glyphicon glyphicon-floppy-disk"></i> Guardar</button>
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