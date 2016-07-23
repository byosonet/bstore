<%@ include file="../../layout/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Historial de Compras</title>
</head>
<body>
<div class="row" style="padding: 0.5em;">
	<div class="col-md-12 alert alert-success" style="text-align: center;"><b>Mi Historial de Compras</b></div>
</div>

<c:forEach var="item" items="${mapaCompras}">
<c:set var="compra" value="${item}" />
<c:set var="publicacion" value="${item.publicacion}" />
	<div class="row" style="padding: 0.5em;">
  			<div class="form-group">
  				<div class="col-md-12" style="margin-top:5px;">
				  		<table id="tablaDetalleVenta" class="table table-striped" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th style="text-align: center;width: 30%;" class="alert alert-info">Nombre de la publicaci&oacute;n</th>
								<th style="text-align: center;" class="alert alert-info">ISBN</th>
								<th style="text-align: center;" class="alert alert-info">Nº P&aacute;ginas</th>
								<th style="text-align: center;" class="alert alert-info">Link</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="text-align: center;">${publicacion.nombre}</td>
								<td style="text-align: center;">${publicacion.isbn}</td>
								<td style="text-align: center;">${publicacion.numeroPaginas}</td>
								<td style="text-align: center;"><b><a href="${contextpath}/publicacion/${publicacion.id}" class="btn btn-success">Leer</a></b></td>
							</tr>
						</tbody>
					</table>
				</div>
  			</div>
</div>


    <div class="row">
        <div class="col-sm-12">
			<form class="form-horizontal">
			
				<div class="form-group">
					 <br>
                    <label class="control-label col-sm-6">Tu pago fu&eacute; realizado con: </label>
                    <div class="col-sm-6">
                       <div class="">
                       		<c:choose>
                       			<c:when test="${compra.brandCard == 'mc'}">
                       				<label><img width="35%" height="auto" src="${contextpath}/static/resources/images/mastercard_icon.png" style="margin-top:-25px;"></label>
                       			</c:when>
                       			<c:otherwise>
                       				 <label><img width="35%" height="auto" src="${contextpath}/static/resources/images/visa_icon.png" style="margin-top:-25px;"></label>
                       			</c:otherwise>
                       		</c:choose>
                        </div>
                    </div>
                </div>
                
                
				<div class="form-group">
                    <span class="control-label col-sm-2">Tarjeta Habiente:</span>
                    <div class="col-sm-4">
                        <label class="control-label"><c:out value="${compra.nameCard}"></c:out></label>
                    </div>
                    
                    <span class="control-label col-sm-2">N&uacute;mero Tarjeta:</span>
                    <div class="col-sm-4">
                     	<label class="control-label"><c:out value="**** **** **** ${compra.last4Card}"/></label>
                    </div>
                </div>
			
				<div class="form-group">
                    <span class="control-label col-sm-2">Nº de Autorizaci&oacute;n:</span>
                    <div class="col-sm-4">
                        <label class="control-label"><c:out value="${compra.authCodeCard}"/></label>
                    </div>
                    
                    <span class="control-label col-sm-2">Nº de Transacci&oacute;n</span>
                    <div class="col-sm-4">
                       	<label class="control-label"><c:out value="${compra.idConekta}"/></label>
                    </div>
                </div>
                
                <div class="form-group">
                    <span class="control-label col-sm-2">Monto del Cargo:</span>
                    <div class="col-sm-4">
                       	<label class="control-label"><c:out value="$ ${compra.precioCompra}"/></label>
                    </div>
                    
                    <span class="control-label col-sm-2">Moneda Nacional:</span>
                    <div class="col-sm-4">
                        <label class="control-label"><c:out value="${compra.currencyCard}"/></label>
                    </div>
                </div>
                
                <div class="form-group">
                    <span class="control-label col-sm-2">Tel&eacute;fono:</span>
                    <div class="col-sm-4">
                        <label class="control-label"><c:out value="${compra.phoneUser}"/></label>
                    </div>
                    
                    <span class="control-label col-sm-2">Correo Electr&oacute;nico:</span>
                    <div class="col-sm-4">
                        <label class="control-label"><c:out value="${compra.emailUser}"/></label>
                    </div>
                </div>
                
                <div class="form-group">
                    <span class="control-label col-sm-2">Fecha de Compra:</span>
                    <div class="col-sm-4">
                        <label class="control-label"><fmt:formatDate value="${compra.fechaCompra}" pattern="dd-MM-yyyy HH:mm:ss"/></label>
                    </div>
                    
                    <span class="control-label col-sm-2">Estado de la Compra:</span>
                    <div class="col-sm-4">
                        <label class="control-label">PAGADO</label>
                    </div>
                </div>

			 
			</form>
		</div>
	</div>
</c:forEach>	
</body>
</html>