<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$('ul.nav.navbar-nav').hide();
		$('#wrapper').css('padding-left','0px');
		$('a#showMenu').show();
		
		$.ajax
		  ({
		    type: "GET",
		    url: "http://www.edicionesquivira.com/publicaciones/documento2/",
		    async: false,
		    success: function (data){
		        $("#protected_iframe").attr("src","http://www.edicionesquivira.com/publicaciones/documento2/");
		    },
		    error: function (xhr, ajaxOptions, thrownError) {
		    	if (xhr.status === 0) {
		    		muestraMsjSistemaError("¡Lo sentimos no tienes permiso para ver el contenido!")
		        }
		    }
		});
		
		$('#protected_iframe').load(function(){
			muestraMsjSistemaSuccess("¡Tu contenido esta listo, ahora disfruta de la publicación!")
		});
		
		function muestraMsjSistemaError(msjStatus){
	           BootstrapDialog.show({
	            size: BootstrapDialog.SIZE_SMALL,
	            title: 'Mensaje del Sistema',
	            closable: false,
	            message: msjStatus,
	            type: BootstrapDialog.TYPE_DANGER,
	            cssClass: 'login-dialog',
	            buttons: [{
	                icon: 'glyphicon glyphicon-check',
	                label: 'Aceptar',
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
	            title: 'Mensaje del Sistema',
	            closable: false,
	            message: msjStatus,
	            type: BootstrapDialog.TYPE_SUCCESS,
	            cssClass: 'login-dialog',
	            buttons: [{
	                icon: 'glyphicon glyphicon-check',
	                label: 'Aceptar',
	                cssClass: 'btn-primary',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            }]
	        });
	        }
		
	});
</script>
<style type="text/css">
</style>
</head>
<body>
    <div class="row" style="padding: 0.5em;">
  		<label class="btn btn-primary alert-info" style="position: unset; z-index: 1;right:15px;top:82px"><b><c:out value="${nombrePublicacion}"/></b></label>
  		<iframe id="protected_iframe" frameborder="0" style="float:left; border:solid 0px black; padding:0em; background-color:#eee;" height="100%" width="100%"></iframe>
	</div>
</body>
</html>