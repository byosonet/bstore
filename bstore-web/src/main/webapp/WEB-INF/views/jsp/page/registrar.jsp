<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/model/ModelNacimiento.js"></script>
  
  <style type="text/css">
		html * {font-family: Verdana;font-size: 12;}
  </style>
  
  <script type="text/javascript">
      $(function(){
          
        $('select#dia').select2();
        $('select#anio').select2();
        $('select#mes').select2();
        $('select#actividad').select2();
          
        var status;
        $('input#masculino').click(function(){
            $('input#femenino').attr('checked',false);
        });

        $('input#femenino').click(function(){
            $('input#masculino').attr('checked',false);
        });
          
          
        $('input#nombre').focus();
        $('button#registrar').click(function(){
            
                var nombre = $('input#nombre');
                var apaterno = $('input#apaterno');
                var amaterno = $('input#amaterno');
                var login = $('input#login');
                var telefono = $('input#telefono');
                var email = $('input#email');
                var passw = $('input#password');
                var confPassword = $('input#confPassword');
                if(login.val() === ""){
                	muestraMsjSistemaError('El login es requerido.');
                    return false;
                }else if(nombre.val() === ""){
                    muestraMsjSistemaError('El nombre es requerido.');
                    return false;
                }else if(apaterno.val() === ""){
                	 muestraMsjSistemaError('El apellido paterno es requerido.');
                     return false;
                }else if(amaterno.val() === ""){
                	 muestraMsjSistemaError('El apellido materno es requerido.');
                     return false;
                }else if(telefono.val() === ""){
                	muestraMsjSistemaError('El teléfono es requerido.');
                    return false;
                }else if(email.val() === ""){
                    muestraMsjSistemaError('El email es requerido.');
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
                $('input#idEmail').val(email.val());
                $('input#idPassword').val(passw.val());
		$.blockUI();
                $.ajax({
	              type: 'POST',
	              url: '${contextpath}'+'/usuario/nuevo',
	              data: $('form#formRegistrar').serialize(),
	                  success: function (data) {
                             $.unblockUI();
                             muestraMsjSistemaSuccess(data.mensaje);
	              },
                         error: function(msj){
                             status = JSON.parse(msj.responseText);
                             $.unblockUI();
                             muestraMsjSistemaError(status.mensaje);
                          }
	        });
        });
        
        $('button#limpiar').click(function(){
              $('form#formRegistrar')[0].reset();
          });
        
        function muestraMsjSistemaError(msjStatus){
           BootstrapDialog.show({
            size: BootstrapDialog.SIZE_SMALL,
            title: 'Mensaje del Sistema:',
            closable: false,
            message: msjStatus,
            type: BootstrapDialog.TYPE_DANGER,
            cssClass: 'login-dialog',
            buttons: [{
                icon: 'glyphicon glyphicon-check',
                label: 'OK',
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
            title: 'Mensaje del Sistema:',
            closable: false,
            message: msjStatus,
            type: BootstrapDialog.TYPE_SUCCESS,
            cssClass: 'login-dialog',
            buttons: [{
                icon: 'glyphicon glyphicon-check',
                label: 'CONTINUAR',
                cssClass: 'btn-primary',
                action: function(dialog) {
                    dialog.close();
                    $.blockUI();
                    var urlAction = '${contextpath}' + '/equivira';
                    document.getElementById('ingresar').action = urlAction;
                    document.getElementById('ingresar').method = 'POST';
                    document.getElementById('ingresar').submit();
                }
            }]
        });
        }
        
        
        
      });
  </script>
    </head>
    <body>

<div class="container-fluid" style="margin-top: 30px;">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-10 col-md-offset-1 main">
        </br>
        </br>
        </br>
            <form class="form-horizontal" id="formRegistrar" method="post" action="${contextpath}/registrar">
            
            	<div class="form-group">
                    <h5 class="control-label col-sm-12" style="text-align: center;color:black;">REGISTRO DE USUARIO</h5>
                </div>
                </br>
                </br>
                
                <div class="form-group">
                	<label class="control-label col-sm-2" style="color:black;" for="login">Login:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="login" name="login" placeholder="Ingesa tu login">
                    </div>
                    
                    <label class="control-label col-sm-2" style="color:black;" for="nombre">Nombre(s):</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresa tu nombre">
                    </div>
                </div>
                    
                <div class="form-group">
                    <label class="control-label col-sm-2" style="color:black;" for="apaterno">Apellido Paterno:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="apaterno" name="apaterno" placeholder="Ingresa tu apellido paterno">
                    </div>
                    
                    <label class="control-label col-sm-2" style="color:black;" for="amaterno">Apellido Materno:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="amaterno" name="amaterno" placeholder="Ingesa tu apellido materno">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-sm-2" style="color:black;" for="telefono">Tel&eacute;fono:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Ingresa tu tel&eacute;fono">
                    </div>
                    
                    <label class="control-label col-sm-2" style="color:black;" for="email">Email:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="email" name="email" placeholder="Ingesa tu email">
                    </div>
                </div>

                <div class="form-group">
                   <label class="control-label col-sm-2" style="color:black;" for="email">Password:</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Ingesa tu password">
                    </div>
                   
                   <label class="control-label col-sm-2" style="color:black;" for="email">Confirmar password:</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="confPassword" name="confPassword" placeholder="Confirma tu password">
                    </div>
                </div>
                
                
                
                </br>
                <div class="form-group">
                    <h5 class="control-label col-sm-12" style="text-align: center;color:black;">FECHA DE NACIMIENTO</h5>
                </div>
                </br>
                
                
                <div class="form-group">
                    <label class="control-label col-sm-2" style="color:black;">Día:</label>
                    <div class="col-sm-1">
                        <select class="form-control" id="dia" name="dia" data-bind="foreach: days, visible: days().length > 0">
                        <option data-bind="value: id,text:day"></option></select>
                    </div>
                    
                    <label class="control-label col-sm-1" style="color:black;">Mes:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="mes" name="mes" data-bind="foreach: months, visible: months().length > 0">
                        <option data-bind="value: id,text:mes"></option></select>
                    </div>
                    
                    <label class="control-label col-sm-1" style="color:black;">Año:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="anio" name="anio" data-bind="foreach: years, visible: years().length > 0">
                        <option data-bind="value: year,text:year"></option></select>
                    </div>
                   
                    
                    <label class="control-label col-sm-1" style="color:black;">Actividad:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="actividad" name="actividad" data-bind="foreach: activities, visible: activities().length > 0">
                        <option data-bind="value: activity,text:activity"></option></select>
                    </div>
                </div>
                
                
                 <div class="form-group">
                    <label class="control-label col-sm-2" style="color:black;">Sexo:</label>
                    <div class="col-sm-2">
                       <div class="radio radio-info radio-inline">
                            <input type="radio" id="masculino" name="sexo" value="M">
                            <label style="color:black;"> Masculino </label>
                        </div>
                    </div>

                    <div class="col-sm-2">
                       <div class="radio radio-info radio-inline">
                            <input type="radio" id="femenino" name="sexo" value="F">
                            <label style="color:black;"> Femenino </label>
                        </div>
                    </div>
     
                    <label class="control-label col-sm-3" style="color:black;">Deseo recibir notificaciones:</label>
                    <div class="col-sm-3">
                       <div class="checkbox checkbox-primary">
                        <input type="checkbox" value="SI" name="notificar" id="notificar">
                        <label style="color:black;">
                            Sí
                        </label>
                    </div>
                    </div>
                </div>
            </form>
                
            <form id="ingresar">
                <input type="hidden" id="idEmail" name="user" value=""/>
                <input type="hidden" id="idPassword" name="password" value=""/>
            </form>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                <button id="limpiar" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> LIMPIAR</button>
                <button id="registrar" class="btn btn-primary"><span class="glyphicon glyphicon-user"></span> REGISTRAR</button>
                </div>
            </div>
        </div>
    </div>    
</div>
    
    
    </body>
</html>
