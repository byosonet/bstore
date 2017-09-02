<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<c:set var="fechaNacimiento">
     <fmt:formatDate pattern="ddMMyyyy" value="${user.fechaNacimiento}" />
</c:set>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/model/ModelNacimiento.js"></script>
  
  
  <script type="text/javascript">
      $(function(){
    	$('select#dia option[value=${fechaNacimiento.substring(0,2)}]').attr('selected','selected'); 
    	$('select#dia').select2({
    		placeholder: 'Día',
           
            language: {
                noResults: function(){
                    return "No se han encontrado resultados";
                }
            }
    	});
        
    	$('select#anio option[value=${fechaNacimiento.substring(4,8)}]').attr('selected','selected'); 
        $('select#anio').select2({
        	placeholder: 'Año',
            
            language: {
                noResults: function(){
                    return "No se han encontrado resultados";
                }
            }
        });
        
        $('select#mes option[value=${fechaNacimiento.substring(2,4)}]').attr('selected','selected'); 
        $('select#mes').select2({
        	placeholder: 'Mes',
            
            language: {
                noResults: function(){
                    return "No se han encontrado resultados";
                }
            }
        });
        
        $('select#actividad option[value=${user.actividad}]').attr('selected','selected');
        $('select#actividad').select2({
        	placeholder: 'Actividad',
           
            language: {
                noResults: function(){
                    return "No se han encontrado resultados";
                }
            }
        });
          
        var status;
        $('input#masculino').click(function(){
            $('input#femenino').attr('checked',false);
        });

        $('input#femenino').click(function(){
            $('input#masculino').attr('checked',false);
        });
          
        $('input#nombre').focus();
        $('button#actualizar').click(function(){
                var nombre = $('input#nombre');
                var apaterno = $('input#apaterno');
                var amaterno = $('input#amaterno');
                var email = $('input#email');
                var login = $('input#login');
                var telefono = $('input#telefono');
                var passw = $('input#pass1');
                var confPassword = $('input#pass2');
                if(nombre.val() === ""){
                    muestraMsjSistemaError('El nombre es un campo requerido.');
                    return false;
                }else if(apaterno.val() === ""){
                	muestraMsjSistemaError('El apellido paterno es requerido.');
                	return false;
                }else if(amaterno.val() === ""){
                	muestraMsjSistemaError('El apellido materno es requerido.');
                	return false;
                }else if(login.val() === ""){
                    muestraMsjSistemaError('El login es requerido.');
                    return false;
                }else if(telefono.val() === ""){
                    muestraMsjSistemaError('El teléfono es requerido.');
                    return false;
                }else if(passw.val() === "" || confPassword.val() === ""){
                    muestraMsjSistemaError('El password es requerido.');
                    return false;
                }else if(passw.val() != confPassword.val()){
                    muestraMsjSistemaError('El password no coincide.');
                    return false; 
                }
                var m = $('input#masculino').filter(":checked").val();
                var f = $('input#femenino').filter(":checked").val();
				if(m === undefined && f === undefined){
					  muestraMsjSistemaError('El sexo es un campo requerido.');
					  return false;
				}
                
				$.blockUI();
		                $.ajax({
			              type: 'POST',
			              url: '${contextpath}'+'/perfil/actualizar',
			              data: $('form#formRegistrar').serialize(),
			                  success: function (data) {
		                             $.unblockUI();
		                             if(data.mensaje === undefined){
		                            	 $.blockUI();
		     		                     var urlAction = '${contextpath}' + '/perfil';
		     		                     document.getElementById('perfil').action = urlAction;
		     		                     document.getElementById('perfil').method = 'GET';
		     		                     document.getElementById('perfil').submit();
		                             }else{
		                            	 muestraMsjSistemaSuccess("<span class='text'>"+data.mensaje+"</span>");
		                             }
		                             
			              },
		                         error: function(msj){
		                             status = JSON.parse(msj.responseText);
		                             $.unblockUI();
		                             muestraMsjSistemaError("<span class='text'>"+status.mensaje+"</span>");
		                             //window.location.href = '${contextpath}';
		                          }
			        });
		        });
        
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
		                label: "<span class='text'>OK</span>",
		                cssClass: 'btn-primary',
		                action: function(dialog) {
		                    dialog.close();
		                }
		            }]
		        });
		        }
        
		        function muestraMsjSistemaSuccess(msjStatus){
		           BootstrapDialog.show({
		            size: BootstrapDialog.SIZE_SMALL,
		            title: "<span class='text'>Mensaje de Novohispanorum</span>",
		            closable: false,
		            message: msjStatus,
		            type: BootstrapDialog.TYPE_SUCCESS,
		            cssClass: 'login-dialog',
		            buttons: [{
		                icon: '',
		                label: "<span class='text'>CONTINUAR</span>",
		                cssClass: 'btn-primary',
		                action: function(dialog) {
		                    dialog.close();
		                    $.blockUI();
		                    var urlAction = '${contextpath}' + '/perfil';
		                    document.getElementById('perfil').action = urlAction;
		                    document.getElementById('perfil').method = 'GET';
		                    document.getElementById('perfil').submit();
		                }
		            }]
		        });
		        }
		        
		        $('button#cancelar').click(function(){
		        	muestraMsjSistemaSuccessCancelar("<span class='text'>¿Estás seguro de que quieres cancelar tu cuenta?</span>");
		        });
		        
		        function muestraMsjSistemaSuccessCancelar(msjStatus){
			           BootstrapDialog.show({
			            size: BootstrapDialog.SIZE_SMALL,
			            title: "<span class='text'>Mensaje de Novohispanorum</span>",
			            closable: false,
			            message: msjStatus,
			            type: BootstrapDialog.TYPE_DANGER,
			            cssClass: 'login-dialog',
			            buttons: [{
			            	icon: '',
			            	label: "<span class='text'>CANCELAR</span>",
			            	action: function(dialog){
			            		dialog.close();}
			            	},{
			                icon: '',
			                label: "<span class='text'>CONTINUAR</span>",
			                cssClass: 'btn-primary',
			                action: function(dialog) {
			                    dialog.close();
			                    $.blockUI();
				                $.ajax({
					              type: 'POST',
					              url: '${contextpath}'+'/perfil/cancelar',
					              data: $('form#formRegistrar').serialize(),
				                  success: function (data) {
			                             $.unblockUI();
			                             if(data.mensaje === undefined){
			                            	 $.blockUI();
			     		                     var urlAction = '${contextpath}' + '/perfil';
			     		                     document.getElementById('perfil').action = urlAction;
			     		                     document.getElementById('perfil').method = 'GET';
			     		                     document.getElementById('perfil').submit();
			                             }else{
			                            	 muestraMsjSistemaSuccess("<span class='text'>"+data.mensaje+"</span>");
			                             }
				              			},
			                     error: function(msj){
			                             status = JSON.parse(msj.responseText);
			                             $.unblockUI();
			                             muestraMsjSistemaError("<span class='text'>"+status.mensaje+"</span>");
			                             window.location.href = '${contextpath}';
			                          }
					        	});
			                }
			            }]
			        });
			        }
      });
  </script>
    </head>
    <body>

<div class="container-fluid">
    <div class="row col-sm-8 col-sm-offset-2 main">
        <div class="">
            <form class="form-horizontal" id="formRegistrar">
            	<input type="hidden" name="idUsuario" value="${user.id}">
                </br>
                <div class="form-group">
                    
                    <div class="col-sm-6">
                        <input type="text" class="text form-control" id="nombre" name="nombre" placeholder="Ingresa tu nombre" value="${user.nombre}" >
                    </div>
                    
                    
                    <div class="col-sm-6">
                        <input type="text" class="text form-control" id="apaterno" name="apaterno" placeholder="Ingresa tu apellido paterno" value="${user.APaterno}">
                    </div>
                    
                </div>
                
                 <div class="form-group">
                    
                    <div class="col-sm-6">
                        <input type="text" class="text form-control" id="amaterno" name="amaterno" placeholder="Ingresa tu apellido materno" value="${user.AMaterno}">
                    </div>
                    
                   
                    <div class="col-sm-6">
                        <input type="text" class="text form-control" id="email" name="email" placeholder="Ingesa tu email" value="${user.email}" readonly>
                    </div>
                </div>
                
                <div class="form-group">
                	
                    <div class="col-sm-6">
                        <input type="text" class="text form-control" id="login" name="login" placeholder="Ingesa tu login" value="${user.login}">
                    </div>
                    
                    
                    <div class="col-sm-6">
                        <input type="text" class="text form-control" id="telefono" name="telefono" placeholder="Ingesa tu tel&eacute;fono" value="${user.telefono}">
                    </div>
                </div>

                <div class="form-group">
                   
                    <div class="col-sm-6">
                        <input type="password" class="text form-control" id="pass1" name="pass1" placeholder="Ingesa tu password" value="${user.password}">
                    </div>
                   
                   
                    <div class="col-sm-6">
                        <input type="password" class="text form-control" id="pass2" name="pass2" placeholder="Confirma tu password" value="${user.password}">
                    </div>
                </div>
                
                
                </br>
                </br>
                <div class="row col-sm-12" style="margin-left: 140px;">
                <div class="form-group">
                   
                    <div class="col-sm-2">
                        <select class="form-control" id="dia" name="dia" data-bind="foreach: days, visible: days().length > 0">
                        
                        <option data-bind="value: id,text:day"></option></select>
                    </div>
                    
                    
                    <div class="col-sm-2">
                        <select class="form-control" id="mes" name="mes" data-bind="foreach: months, visible: months().length > 0">
                        
                        <option data-bind="value: id,text:mes"></option></select>
                    </div>
                    
                    
                    <div class="col-sm-2">
                        <select class="form-control" id="anio" name="anio" data-bind="foreach: years, visible: years().length > 0">
                        
                        <option data-bind="value: year,text:year"></option></select>
                    </div>
                   
                    
                    
                    <div class="col-sm-2">
                        <select class="form-control" id="actividad" name="actividad" data-bind="foreach: activities, visible: activities().length > 0">
                        
                        <option data-bind="value: activity,text:activity"></option></select>
                    </div>
                </div>
                </div>
                
                
                 <div class="form-group">
                    <label class="text control-label col-sm-2" >Sexo:</label>
                    <div class="col-sm-2">
                       <div class="radio radio-info radio-inline">
                            <c:if test="${user.sexo == 'M'}">
                            	<input type="radio" id="masculino" name="sexo" value="M" checked>
                            </c:if>
                            <c:if test="${user.sexo == 'F'}">
                            	<input type="radio" id="masculino" name="sexo" value="M">
                            </c:if>
                            
                            <label class="text"> Masculino </label>
                        </div>
                    </div>

                    <div class="col-sm-2">
                       <div class="radio radio-info radio-inline">
                       		<c:if test="${user.sexo == 'F'}">
                       			<input type="radio" id="femenino" name="sexo" value="F" checked>
                       		</c:if>
                       		<c:if test="${user.sexo == 'M'}">
                       			<input type="radio" id="femenino" name="sexo" value="F">
                       		</c:if>
                            <label class="text"> Femenino </label>
                        </div>
                    </div>
     
                    <label class="text control-label col-sm-3" >Deseo recibir notificaciones:</label>
                    <div class="col-sm-3">
                       <div class="checkbox checkbox-primary">
                       <c:if test="${user.notificaciones == 'SI'}">
                       		<input type="checkbox" value="SI" name="notificar" id="notificar" checked>
                       </c:if>
                       <c:if test="${user.notificaciones == 'NO'}">
                       		<input type="checkbox" value="SI" name="notificar" id="notificar">
                       </c:if>
                        <label class="text">
                            Sí
                        </label>
                    </div>
                    </div>
                </div>
            </form>
            <form id="perfil"></form>
            <div class="">
                <div class="" style="float: right;">
                <button id="cancelar" class="text btn btn-danger"><span class=""></span> CANCELAR CUENTA</button>
                <button id="actualizar" class="text btn btn-primary"><span class=""></span> ACTUALIZAR DATOS</button>
                </div>
            </div>
        </div>
    </div>    
</div>
<br>
<br>
<br>
</body>
</html>
