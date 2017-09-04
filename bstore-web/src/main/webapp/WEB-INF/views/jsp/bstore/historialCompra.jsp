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

<c:set var="count" value="0" scope="page"/>
<c:forEach var="item" items="${mapaCompras}">
<c:set var="compra" value="${item}" />
<c:set var="publicacion" value="${item.publicacion}" />
<c:set var="count" value="${count + 1}" scope="page"/>
	<div class="row col-sm-9 col-sm-offset-2 main" style="padding: 0.5em;">
  			<div class="form-group">
  				<div class="col-md-11" style="margin-top:5px;">
				  		<table id="tablaDetalleVenta" class="table table-striped" cellspacing="0" width="100%" border="5">
						<thead>
							<tr>
								<th style="text-align: center;width: 50%;" class="text alert alert-info">Publicaci&oacute;n</th>
								<th style="text-align: center;" class="text alert alert-info">ISBN</th>
								<th style="text-align: center;" class="text alert alert-info">Editorial</th>
								<th style="text-align: center;" class="text alert alert-info">Link</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="border-width: 0.5px;border: medium;;text-align: left;"><b class="text">${publicacion.nombre}</b></td>
								<td style="border-width: 0.5px;border: medium;text-align: center;" class="text">${publicacion.isbn}</td>
								<td style="border-width: 0.5px;border: medium;text-align: center;" class="text">${publicacion.editorial.nombre}</td>
                                <td style="border-width: 0.5px;border: medium;text-align: center;"><b><a href="${contextpath}/publicacion/${publicacion.id}" class="text btn btn-default"> Ver</a></b></td>
							</tr>
						</tbody>
					</table>
				</div>
  			</div>
</div>


    <div class="row col-sm-9 col-sm-offset-2 main">
        <div class="col-sm-12">
			<form class="form-horizontal">
			
				<div class="form-group">
					
                    <label class="text control-label col-sm-6">Tu pago fu&eacute; realizado con: </label>
                    <div class="col-sm-6">
                       <div class="">
                       		<c:choose>
                       			<c:when test="${compra.brandCard == 'mc'}">
                       				<label><img width="20%" height="auto" src="${contextpath}/static/resources/images/mastercard_icon.png" style="margin-top:-10px;"></label>
                       			</c:when>
                       			<c:otherwise>
                       				 <label><img width="20%" height="auto" src="${contextpath}/static/resources/images/visa_icon.png" style="margin-top:-10px;"></label>
                       			</c:otherwise>
                       		</c:choose>
                        </div>
                    </div>
                </div>
                
                
				<div class="form-group">
                    <span class="text control-label col-sm-2">Tarjeta Habiente:</span>
                    <div class="col-sm-4">
                        <label class="text control-label"><c:out value="${compra.nameCard != 'na'?compra.nameCard:'-'}"></c:out></label>
                    </div>
                    
                    <span class="text control-label col-sm-2">N&uacute;mero Tarjeta:</span>
                    <div class="col-sm-4">
                     	<label class="text control-label"><c:out value="**** **** **** ${compra.last4Card != 'na'?compra.last4Card:'****'}"/></label>
                    </div>
                </div>
			
				<div class="form-group">
                    <span class="text control-label col-sm-2">Nº Autorizaci&oacute;n:</span>
                    <div class="col-sm-4">
                        <label class="text control-label"><c:out value="${compra.authCodeCard != 'na'?compra.authCodeCard:'-'}"/></label>
                    </div>
                    
                    <span class="text control-label col-sm-2">Nº de Transacci&oacute;n</span>
                    <div class="col-sm-4">
                       	<label class="text control-label"><c:out value="${compra.idConekta}"/></label>
                    </div>
                </div>
                
                <div class="form-group">
                    <span class="text control-label col-sm-2">Monto del Cargo:</span>
                    <div class="col-sm-4">
                       	<label class="text control-label"><c:out value="$ ${compra.precioCompra}"/></label>
                    </div>
                    
                    <span class="text control-label col-sm-2">Moneda Nacional:</span>
                    <div class="col-sm-4">
                        <label class="text control-label"><c:out value="${compra.currencyCard}"/></label>
                    </div>
                </div>
                
                <div class="form-group">
                    <span class="text control-label col-sm-2">Tel&eacute;fono:</span>
                    <div class="col-sm-4">
                        <label class="text control-label"><c:out value="${compra.phoneUser}"/></label>
                    </div>
                    
                    <span class="text control-label col-sm-2">Email:</span>
                    <div class="col-sm-4">
                        <label class="text control-label"><c:out value="${compra.emailUser}"/></label>
                    </div>
                </div>
                
                <div class="form-group">
                    <span class="text control-label col-sm-2">Fecha de Compra:</span>
                    <div class="col-sm-4">
                        <label class="text control-label"><span class=""><fmt:formatDate value="${compra.fechaCompra}" pattern="dd/MM/yyyy HH:mm:ss"/></span></label>
                    </div>
                    
                    <span class="text control-label col-sm-2">Estado compra:</span>
                    <div class="col-sm-4">
                        <label class="text control-label">PAGADO</label>
                    </div>
                </div>

			 
			</form>
		</div>
	</div>
</c:forEach>	
</body>
</html>