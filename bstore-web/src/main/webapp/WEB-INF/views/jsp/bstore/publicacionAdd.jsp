<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../../layout/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Publicacion nueva</title>



</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12">
				<form:form action="${contextpath}/publicacion/savePublicacion" method="POST" commandName="publicacion">

					<div class="form-group">
						<div class="control-label col-sm-12 alert alert-success"
							style="text-align: center;"><b>Informaci&oacute;n de Publicacion</b>
						</div>
						<br/>
						<br/>
					</div>
					<br/>
					<br/>
					
					<div class="form-group">
						<label class="control-label col-sm-1" for="nombreBiblioteca">Nombre</label>
						
						<div class="col-sm-3">
							<form:input path="nombre" class="form-control" placeholder="Nombre" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="nombre" />
							</span>
						</div>
						
						<label for="rfc" class="col-sm-1 control-label">Numero de paginas</label>
						<div class="col-sm-3">
							<form:input path="numeroPaginas" class="form-control" placeholder="Numero de paginas" />
						</div>	
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="numeroPaginas" />
							</span>
						</div>
					</div>
					<br/>
					<br/>
					
					
					<div class="form-group">
						<label for="portada" class="col-sm-1 control-label">Portada</label>
						<div class="col-sm-3">
							<form:input path="portada" class="form-control" placeholder="Portada" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="portada" />
							</span>
						</div>
						
						<label for="isbn" class="col-sm-1 control-label">ISBN</label>
						<div class="col-sm-3">
							<form:input path="isbn" class="form-control" placeholder="isbn" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="isbn" />
							</span>
						</div>
						
						
					</div>
					<br/>
					<br/>
					
					<div class="form-group">
						<label for="precio" class="col-sm-1 control-label">Precio</label>
						<div class="col-sm-3">
							<form:input path="precio" class="form-control" placeholder="precio" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="precio" />
							</span>
						</div>
						
						<label for="urlArchivo" class="col-sm-1 control-label">URL Archivo</label>
						<div class="col-sm-3">
							<form:input path="urlArchivo" class="form-control" placeholder="urlArchivo" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="urlArchivo" />
							</span>
						</div>
						
					</div>
					<br/>
					<br/>
					
					<div class="form-group">
						<label for="portadaUrl" class="col-sm-1 control-label">URL de portada</label>
						<div class="col-sm-3">
							<form:input path="portadaUrl" class="form-control" placeholder="portadaUrl" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="portadaUrl" />
							</span>
						</div>
						
						<label for="resumen" class="col-sm-1 control-label">Resumen</label>
						<div class="col-sm-3">
							<form:input path="resumen" class="form-control" placeholder="resumen" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="resumen" />
							</span>
						</div>
						
					</div>
					<br/>
					<br/>
					
					
					
					<!-- 
						private Coleccion coleccion;
	private Usuario usuario;
	private Fuente fuente;
	private Editorial editorial;
					-->
					
					
					<div class="form-group">
						<label for="descuento" class="col-sm-1 control-label">Descuento</label>
						<div class="col-sm-3">
							<form:input path="descuento" class="form-control" placeholder="descuento" />
						</div>
						<div class="col-sm-2">
							<span style="color:#FE2E2E;">
								<form:errors path="descuento" />
							</span>
						</div>
						
						<label class="col-sm-1 control-label">Activar</label>
						<div class="col-sm-5">
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

					<div class="row" style="padding-right: 12px; padding-top: 40px">
						<div class="col-sm-8">
						</div>
						<div class="col-sm-2">
            				<a style="font-size: 15; width: 100%; padding: 10px; text-align: center;" class="btn btn-default" href="/bstore/publicacion/getAll">
								<i class="glyphicon glyphicon-remove"></i> Cancelar
							</a>
            			</div>
            			<div class="col-sm-2">
							<button type="submit" class="btn btn-primary" style="font-size: 15; width: 100%; padding: 10px; text-align: center;" id="savePublicacion"><i class="glyphicon glyphicon-floppy-disk"></i> Guardar</button>
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