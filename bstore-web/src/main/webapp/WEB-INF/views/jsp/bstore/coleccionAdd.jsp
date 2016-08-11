<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../../layout/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Colección nueva</title>



</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12">
				<form:form action="${contextpath}/editorial/saveColeccion" method="POST" commandName="coleccion">

					<div class="form-group">
						<div class="control-label col-sm-12 alert alert-success"
							style="text-align: center;"><b>Informaci&oacute;n de Colección</b>
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
						
						<label for="rfc" class="col-sm-1 control-label">Nombre a mostrar</label>
						<div class="col-sm-3">
							<form:input path="nombreMostrar" class="form-control" placeholder="Nombre a mostrar" />
						</div>	
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="nombreMostrar" />
							</span>
						</div>
					</div>
					<br/>
					<br/>
					
					
					<div class="form-group">
						<label for="email" class="col-sm-1 control-label">URL de portada</label>
						<div class="col-sm-3">
							<form:input path="portadaUrl" class="form-control" placeholder="URL de portada (JPG)" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="portadaUrl" />
							</span>
						</div>
						
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
					<br/>
					<br/>

					<div class="row" align="right">
							<a
								href="${contextpath}/coleccion/getAll" class="btn btn-default"
								style="font-size: 15; width: 10%; padding: 5px; text-align: center; align: right;">
								<i class="glyphicon glyphicon-remove"></i> <c:out value="Cancelar" />
							</a>
							<button id="saveColeccion" class="btn btn-primary" type="submit"><i class="glyphicon glyphicon-floppy-disk"></i> Guardar</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br>
	<br>
</body>
</html>