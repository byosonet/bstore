<%@ include file="../../layout/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript" src="https://conektaapi.s3.amazonaws.com/v0.5.0/js/conekta.js"></script>
<script type="text/javascript">
Conekta.setPublicKey('key_Niwr5ccGztUVzNHPpFxWsGA');
	$(document).ready(function() {
		$(document).ready(function() {
			$('#tablaDetalleVenta').DataTable({
				"bPaginate": false,
		        "bFilter": false,
		        "bInfo": false
			});
		});
		
			function validation(){
				var nombre = $('input#nombre');
	            var numeroTarjeta = $('input#numeroTarjeta');
	            var cvv = $('input#cvv');
	            var fechaExpiracionMes = $('input#fechaExpiracionMes');
	            var fechaExpiracionAnio = $('input#fechaExpiracionAnio');
	            var calle = $('input#calle');
	            var colonia = $('input#colonia');
	            var ciudad = $('input#ciudad');
	            var estado = $('input#estado');
	            var codigo = $('input#codigo');
	            var pais = $('input#pais');
	            
	            if(nombre.val() === ""){
	                muestraMsjSistemaError("<span class='text'>El nombre es un campo requerido.</span>");
	                return false;
	            }else if(numeroTarjeta.val() === ""){
	            	muestraMsjSistemaError("<span class='text'>El numero de tarjeta es requerido.</span>");
	            	return false;
	            }else if(cvv.val() === ""){
	            	muestraMsjSistemaError("<span class='text'>El cvv es requerido.</span>");
	            	return false;
	            }
	            else if(fechaExpiracionMes.val() === ""){
	                muestraMsjSistemaError("<span class='text'>El mes es requerido.</span>");
	                return false;
	            }else if(fechaExpiracionAnio.val() === ""){
	                muestraMsjSistemaError("<span class='text'>El año es requerido.</span>");
	                return false;
	            }else if(calle.val() === ""){
	                muestraMsjSistemaError("<span class='text'>La calle es requerida.</span>");
	                return false; 
	            }else if(colonia.val() === ""){
	                muestraMsjSistemaError("<span class='text'>La colonia es requerida.</span>");
	                return false; 
	            }else if(ciudad.val() === ""){
	                muestraMsjSistemaError("<span class='text'>La ciudad es requerida.</span>");
	                return false; 
	            }else if(estado.val() === ""){
	                muestraMsjSistemaError("<span class='text'>El estado es requerido.</span>");
	                return false; 
	            }else if(codigo.val() === ""){
	                muestraMsjSistemaError("<span class='text'>El código postal es requerido.</span>");
	                return false; 
	            }else if(pais.val() === ""){
	                muestraMsjSistemaError("<span class='text'>País es requerido.</span>");
	                return false; 
	            }

	            var m = $('input#visa').filter(":checked").val();
	            var f = $('input#mastercard').filter(":checked").val();
				if(m === undefined && f === undefined){
					  muestraMsjSistemaError("<span class='text'>Debes seleccionar una forma de pago, Visa/Mastercard.</span>");
					  return false;
				}
				return true;
			}
    
	        function muestraMsjSistemaError(msjStatus){
	           BootstrapDialog.show({
	            size: BootstrapDialog.SIZE_SMALL,
	            title: "<span class='text'>Mensaje de Novohispanorum</span>",
	            closable: false,
	            message: msjStatus,
	            type: BootstrapDialog.TYPE_DANGER,
	            cssClass: 'login-dialog',
	            buttons: [{
	                icon: '',
	                label: "<span class='text'>ACEPTAR</span>",
	                cssClass: 'btn-primary',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
	        }
    
	        function procesarPago(token){
                $.blockUI();
                var urlAction = '${contextpath}' + '/pagar/publicacion/'+${publicacion.id};
                $('input#key').val(token);
                document.getElementById('card-form').action = urlAction;
                document.getElementById('card-form').method = 'POST';
                document.getElementById('card-form').submit();
	        }
                
                $('#pagarPublicacion').click(function(){
                    $.blockUI();
                    var urlAction = '${contextpath}' + '/pagar/publicacion/gratis/'+${publicacion.id};
                    document.getElementById('formPagarPublicacion').action = urlAction;
                    document.getElementById('formPagarPublicacion').method = 'POST';
                    document.getElementById('formPagarPublicacion').submit();
                });
		
		$('input#visa').click(function(){
            $('input#mastercard').attr('checked',false);
        });

        $('input#mastercard').click(function(){
            $('input#visa').attr('checked',false);
        });
		
		var success = function(token) {
			procesarPago(token.id);
		};
			
		var error = function(messages) {
			console.log('--message_to_purchaser: '+messages.message_to_purchaser);
			muestraMsjSistemaError(messages.message_to_purchaser);
		};
		     
   	   $("#card-form").submit(function(event) {
			var result = validation();
			if(result){
   			    var $form = $(this);
   			    $form.find("#pagar").prop("disabled", true);
   			    Conekta.Token.create($form, success, error);
   			    return false;
			}
			return false;
   		      
	   });
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editoriales</title>
</head>
<body>
	<div class="${publicacion.precio != '0.00'? 'row col-sm-6':'col-sm-8 col-sm-offset-2 main'}">
		<c:if test="${errorMessage}">
			<div class="col-md-12 alert alert-danger" style="text-align: center">
				<p><b class="text"><c:out value="${messageError}"/></b></p>
				<p><b class="text"><c:out value="${messageErrorConekta}"/></b></p>
			</div>
		</c:if>
		
            <div class="col-md-12 alert alert-info" style="text-align: center"><b class="text">1.- DETALLE DE TU COMPRA</b></div>
  			<div class="form-group">
  				<div class="col-md-12 alert alert-success">
  				<span class="text"><b>Resumen:</b> ${publicacion.resumen}</span></div>
  				<div class="col-md-2" style="padding: 0.5em;text-align: center;margin-top: -10px">
  					<img style="width: 100%;height:auto;border-radius:5px;" src="${publicacion.portadaUrl}">
  				</div>
  				
  				<div class="col-md-10" style="margin-top:30px;">
				  		<table id="tablaDetalleVenta" class="table table-striped" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th style="text-align: center;width: 30%;" class="text alert alert-success">Publicaci&oacute;n</th>
								<th style="text-align: center;" class="text alert alert-success">ISBN</th>
								<th style="text-align: center;" class="text alert alert-success">Descuento</th>
								<th style="text-align: center;" class="text alert alert-success">Precio</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th></th>
								<th></th>
								<th class="text alert alert-warning" style="text-align: center;">Total a Pagar </th>
								<th class="text alert alert-warning" style="text-align: center;">$ ${publicacion.precio - publicacion.descuento} MXN</th>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td class="text" style="text-align: center;">${publicacion.nombre}</td>
								<td class="text" style="text-align: center;">${publicacion.isbn}</td>
								<td class="text" style="text-align: center;">$ ${publicacion.descuento}</td>
								<c:if test="${publicacion.descuento != '0.00'}">
									<td class="text" style="text-align: center;text-decoration:line-through;">$ ${publicacion.precio}</td>
								</c:if>
								<c:if test="${publicacion.descuento == '0.00'}">
									<td class="text" style="text-align: center;">$ ${publicacion.precio}</td>
								</c:if>
							</tr>
						</tbody>
					</table>
				</div>
  			</div>
                        <div class="" style="float: right;${publicacion.precio != '0.00'? 'display: none;':''}">
                            <form class="form-horizontal" action="" method="POST" id="formPagarPublicacion">
                            </form>
                            <button id="pagarPublicacion" class="text btn btn-primary">COMPRAR</button>
                        </div>
                                                                
                        
</div>


<div class="row col-sm-6" style="margin-left:30px;${publicacion.precio == '0.00'? 'display: none;':''}">
    <div class="">
        <div class="col-sm-12">
			<form class="form-horizontal" action="" method="POST" id="card-form">
				<div class="col-md-12 alert alert-info" style="text-align: center;${publicacion.precio == '0.00'? 'display: none;':''}"><b class="text">2.- ELIGE TU FORMA DE PAGO</b></div>
				<div class="form-group">
                    <label class="control-label col-sm-4" ></label>
                    <div class="col-sm-3">
                       <div class="radio radio-info radio-inline">
                           <input type="radio" id="visa" name="visa" value="visa">
                           <label><img width="35%" height="auto" src="${contextpath}/static/resources/images/visa_icon.png" style="margin-top:-10px;"></label>
                           
                        </div>
                    </div>

                    <div class="col-sm-3">
                       <div class="radio radio-info radio-inline">
                       		<input type="radio" id="mastercard" name="mastercard" value="mastercard">
                       		<label><img width="35%" height="auto" src="${contextpath}/static/resources/images/mastercard_icon.png" style="margin-top:-10px;"></label>
                       		
                        </div>
                    </div>
                </div>
                
                
				<div class="form-group">
                    <div class="col-sm-6">
                        <input data-conekta="card[name]" type="text" class="text form-control" id="nombre" maxlength="50" name="nombre" placeholder="Nombre del cuenta habiente" value="" >
                    </div>
                    
                    
                    <div class="col-sm-6">
                        <input data-conekta="card[number]" type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57' class="text form-control" id="numeroTarjeta" maxlength="16" name="numeroTarjeta" placeholder="N&uacute;mero de tarjeta" value="">
                    </div>
                </div>
			
				<div class="form-group">

                    <div class="col-sm-4">
                        <input data-conekta="card[cvc]" type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57' class="text form-control" id="cvv" maxlength="4" name="cvv" placeholder="CVC/CVV" value="" >
                    </div>
                    
                   
                    <div class="col-sm-4">
                        <input data-conekta="card[exp_month]" type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57' class="text form-control" id="fechaExpiracionMes" maxlength="2" name="fechaExpiracionMes" placeholder="MM" value="">
                    </div>
                    <div class="col-sm-4">
                    	<input data-conekta="card[exp_year]" type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57' class="text form-control" id="fechaExpiracionAnio" maxlength="4" name="fechaExpiracionAnio" placeholder="YYYY" value="">
                    </div>
                </div>
			
				<div class="form-group">

                    <div class="col-sm-6">
                        <input data-conekta="card[address][street1]" type="text" class="text form-control" id="calle" maxlength="40" name="calle" placeholder="Calle" value="" >
                    </div>
                    

                    <div class="col-sm-6">
                        <input data-conekta="card[address][street2]" type="text" class="text form-control" id="colonia" maxlength="30" name="colonia" placeholder="Colonia" value="">
                    </div>
                </div>
                
                <div class="form-group">

                    <div class="col-sm-6">
                        <input data-conekta="card[address][city]" type="text" class="text form-control" id="ciudad" maxlength="30" name="ciudad" placeholder="Ciudad" value="" >
                    </div>
                    

                    <div class="col-sm-6">
                        <input data-conekta="card[address][state]" type="text" class="text form-control" id="estado" maxlength="30" name="estado" placeholder="Estado" value="">
                    </div>
                </div>
                
                <div class="form-group">

                    <div class="col-sm-6">
                        <input data-conekta="card[address][zip]" type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57' class="text form-control" id="codigo" maxlength="5" name="codigo" placeholder="C&oacute;digo postal" value="" >
                    </div>
                    

                    <div class="col-sm-6">
                        <input data-conekta="card[address][country]" type="text" class="text form-control" id="pais" maxlength="25" name="pais" placeholder="Pa&iacute;s" value="">
                    </div>
                </div>

			  <div class="row">
			  	<input id="key" name="key" type="hidden" />
                <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                 <button type="reset" id="reset" class="text btn btn-default"><span class=""></span> LIMPIAR</button>
                <button type="submit" id="pagar" class="text btn btn-primary"><span class=""></span> COMPRAR</button>
                </div>
            </div>
			</form>
		</div>
	</div>
</div>

<div class="row col-md-12" style="padding:1em;${publicacion.precio == '0.00'? 'display: none;':''}">
		<div class="alert alert-info" style="text-align: center">
                    <p class="text">En <b>Tarjetas de Cr&eacute;dito:</b> Aceptamos cualquier forma de pago con <b>VISA</b> y <b>MASTERCARD</b>.</p>
			<p class="text">y en <b>Tarjetas de D&eacute;bito:</b> &uacute;nicamente Banamex, HSBC, Inbursa y Santander.</p>
		</div>
</div>	
</body>
</html>