<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
 <head>

  <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
  <meta name="generator" content="2017.1.0.379"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/model/ModelNacimiento.js"></script>
  
  <script type="text/javascript">
   // Update the 'nojs'/'js' class on the html node
document.documentElement.className = document.documentElement.className.replace(/\bnojs\b/g, 'js');

// Check that all required assets are uploaded and up-to-date
if(typeof Muse == "undefined") window.Muse = {}; window.Muse.assets = {"required":["${contextpath}/static/resources/js/museutils.js", "${contextpath}/static/resources/js/museconfig.js", "${contextpath}/static/resources/js/jquery.watch.js", "${contextpath}/static/resources/js/webpro.js", "${contextpath}/static/resources/js/musewpslideshow.js", "${contextpath}/static/resources/js/jquery.museoverlay.js", "${contextpath}/static/resources/js/touchswipe.js", "${contextpath}/static/resources/js/require.js", "${contextpath}/static/resources/css/crear-cuenta.css"], "outOfDate":[]};
</script>
  
  <title>CREAR CUENTA</title>
  <!-- CSS -->
  <link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/site_global.css?crc=444006867"/>
  <link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/master_master-login.css?crc=3830722281"/>
  <link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/crear-cuenta.css?crc=64490092" id="pagesheet"/>
  <script type="text/javascript">
            $(function () {

                $('select#dia').select2({
                	theme: "classic",
                	placeholder: 'Día',
                    allowClear: true,
                    language: {
                        noResults: function(){
                            return "No se han encontrado resultados";
                        }
                    }
                });
                $('select#anio').select2({
                	theme: "classic",
                	placeholder: 'Año',
                    allowClear: true,
                    language: {
                        noResults: function(){
                            return "No se han encontrado resultados";
                        }
                    }
                });
                $('select#mes').select2({
                	theme: "classic",
                	placeholder: 'Mes',
                    allowClear: true,
                    language: {
                        noResults: function(){
                            return "No se han encontrado resultados";
                        }
                    }
                });
                $('select#actividad').select2({
                	theme: "classic",
                	placeholder: 'Actividad',
                    allowClear: true,
                    language: {
                        noResults: function(){
                            return "No se han encontrado resultados";
                        }
                    }
                });

                var status;
                $('input#masculino').click(function () {
                    $('input#femenino').attr('checked', false);
                });

                $('input#femenino').click(function () {
                    $('input#masculino').attr('checked', false);
                });


                $('input#login').focus();
                $('button#u1995-4').click(function () {

                    var nombre = $('input#nombre');
                    var apaterno = $('input#apaterno');
                    var amaterno = $('input#amaterno');
                    var login = $('input#login');
                    var telefono = $('input#telefono');
                    var email = $('input#email');
                    var passw = $('input#password');
                    var confPassword = $('input#confPassword');
                    var dia = $('select#dia');
                    var mes = $('select#mes');
                    var anio = $('select#anio');
                    var actividad = $('select#actividad');
                    
                    if (login.val() === "") {
                        muestraMsjSistemaError("<span class='text'>El login es requerido.</span>");
                        return false;
                    } else if (nombre.val() === "") {
                        muestraMsjSistemaError("<span class='text'>El nombre es requerido.</span>");
                        return false;
                    } else if (apaterno.val() === "") {
                        muestraMsjSistemaError("<span class='text'>El apellido paterno es requerido.</span>");
                        return false;
                    } else if (amaterno.val() === "") {
                        muestraMsjSistemaError("<span class='text'>El apellido materno es requerido.</span>");
                        return false;
                    } else if (telefono.val() === "") {
                        muestraMsjSistemaError("<span class='text'>El teléfono es requerido.</span>");
                        return false;
                    } else if (email.val() === "") {
                        muestraMsjSistemaError("<span class='text'>El email es requerido.</span>");
                        return false;
                    } else if (passw.val() === "" || confPassword.val() === "") {
                        muestraMsjSistemaError("<span class='text'>El password es requerido.</span>");
                        return false;
                    } else if (passw.val() != confPassword.val()) {
                        muestraMsjSistemaError("<span class='text'>El password no coincide.</span>");
                        return false;
                    } else if(dia.val() === "" || mes.val() === "" || anio.val() === ""){
                    	muestraMsjSistemaError("<span class='text'>Debes propocionar tu fecha de nacimiento.</span>");
                    	return false;
                    } else if(actividad.val() === ""){
                    	muestraMsjSistemaError("<span class='text'>Debes elegir una actividad.</span>");
                    	return false;
                    }
                    var m = $('input#masculino').filter(":checked").val();
                    var f = $('input#femenino').filter(":checked").val();
                    var condiciones = $('input#condiciones').filter(":checked").val();
                    if (m === undefined && f === undefined) {
                        muestraMsjSistemaError("<span class='text'>El sexo es un campo requerido.</span>");
                        return false;
                    }
                    if (condiciones === undefined) {
                        muestraMsjSistemaError("<span class='text'>Debes aceptar los Términos y Condiciones.</span>");
                        return false;
                    }
                    $('input#idEmail').val(email.val());
                    $('input#idPassword').val(passw.val());
                    $.blockUI();
                    $.ajax({
                        type: 'POST',
                        url: '${contextpath}' + '/usuario/nuevo',
                        data: $('form#formRegistrar').serialize(),
                        success: function (data) {
                            $.unblockUI();
                            muestraMsjSistemaSuccess("<span class='text'>" + data.mensaje + "</span>");
                        },
                        error: function (msj) {
                            status = JSON.parse(msj.responseText);
                            $.unblockUI();
                            muestraMsjSistemaError("<span class='text'>" + status.mensaje + "</span>");
                        }
                    });
                });

                $('button#limpiar').click(function () {
                    $('form#formRegistrar')[0].reset();
                });

                function muestraMsjSistemaError(msjStatus) {
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
                                action: function (dialog) {
                                    dialog.close();
                                }
                            }]
                    });
                }

                function muestraMsjSistemaSuccess(msjStatus) {
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
                                action: function (dialog) {
                                    dialog.close();
                                    $.blockUI();
                                    var urlAction = '${contextpath}';
                                    document.location.href = urlAction;
                                }
                            }]
                    });
                }



            });
        </script>
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-99311064-1', 'auto');
            ga('send', 'pageview');

        </script>
   </head>
 <body>

  <div class="clearfix borderbox" id="page"><!-- group -->
   <div class="SlideShowWidget clearfix HeroFillFrame grpelem" id="slideshowu2671" data-visibility="changed" style="visibility:hidden" data-sizePolicy="fluidWidth" data-pintopage="page_fixedLeft"><!-- none box -->
    <div class="popup_anchor allow_click_through grpelem" data-col-pos="0" id="u2682popup">
     <div class="SlideShowContentPanel clearfix" data-col-pos="0" id="u2682"><!-- stack box -->
      <div class="SSSlide clip_frame grpelem" data-col-pos="0" id="u2874"><!-- image -->
       <img class="ImageInclude" data-col-pos="0" id="u2874_img" data-src="images/novo.jpg?crc=4078125347" src="${contextpath}/static/resources/images/blank.gif?crc=4208392903" alt="" data-width="2268" data-height="1814"/>
      </div>
     </div>
    </div>
   </div>
   
   <div class="clearfix grpelem" id="pu2123"><!-- column -->
    <a class="nonblock nontext clip_frame colelem" id="u2123" href="index.html"><!-- svg --><img class="svg svg_mar" id="u2124" src="${contextpath}/static/resources/images/logo-quivira.svg?crc=253401112" onload="this.style.height=(this.offsetWidth*276/276)+'px'" alt="" data-mu-svgfallback="images/logo-quivira_poster_.png?crc=237389168" data-heightwidthratio="1" data-image-width="276" data-image-height="276"/></a>
    <form class="form-horizontal" id="formRegistrar" method="post" action="${contextpath}/registrar">
	    <div class="clearfix colelem" id="pu1850-4"><!-- group -->
		    <div class="clearfix grpelem" id="u1850-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
		    	<p>Nombre:</p>
		    </div>
		    <div class="clearfix grpelem" id="u1865-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
		    	<p>Apellidos:</p>
		    </div>
	    </div>
	    <div class="clearfix colelem" id="pu1853"><!-- group -->
		    <div class="clearfix grpelem" id="u1853"><!-- group -->
			    <div class="clearfix grpelem" id="u1868-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>Nombre</p>
			    </div>
		    </div>
		    <div class="clearfix grpelem" id="u1862"><!-- group -->
			    <div class="clearfix grpelem" id="u1871-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>Apellidos</p>
			    </div>
		    </div>
	    </div>
	    <div class="clearfix colelem" id="pu1874-4"><!-- group -->
		    <div class="clearfix grpelem" id="u1874-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
		    	<p>Correo electrÃ³nico:</p>
		    </div>
		    <div class="clearfix grpelem" id="u1898-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
		    	<p>GÃ©nero:</p>
		    </div>
		    <div class="clearfix grpelem" id="u1928-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
		    	<p>Edad:</p>
		    </div>
	    </div>
	    <div class="clearfix colelem" id="pu1880"><!-- group -->
		    <div class="clearfix grpelem" id="u1880"><!-- group -->
			    <div class="clearfix grpelem" id="u1877-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>Correo electrÃ³nico</p>
			    </div>
		    </div>
		    <div class="clearfix grpelem" id="u1892"><!-- group -->
			    <div class="clearfix grpelem" id="u1895-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>M</p>
			    </div>
		    </div>
		    <div class="clearfix grpelem" id="u1907"><!-- group -->
			    <div class="clearfix grpelem" id="u1910-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>F</p>
			    </div>
		    </div>
		    <div class="clearfix grpelem" id="u1922"><!-- group -->
			    <div class="clearfix grpelem" id="u1925-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>Edad</p>
			    </div>
		    </div>
	    </div>
	    <div class="clearfix colelem" id="pu1964-4"><!-- group -->
		    <div class="clearfix grpelem" id="u1964-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
		    	<p>OcupaciÃ³n:</p>
		    </div>
		    <div class="clearfix grpelem" id="u1952-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
		    	<p>InstituciÃ³n:</p>
		    </div>
	    </div>
	    <div class="clearfix colelem" id="pu1958"><!-- group -->
		    <div class="clearfix grpelem" id="u1958"><!-- group -->
			    <div class="clearfix grpelem" id="u1955-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>OcupaciÃ³n</p>
			    </div>
		    </div>
		    <div class="clearfix grpelem" id="u1949"><!-- group -->
			    <div class="clearfix grpelem" id="u1961-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>InstituciÃ³n</p>
			    </div>
		    </div>
	    </div>
	    <div class="clearfix colelem" id="pu1982-4"><!-- group -->
		    <div class="clearfix grpelem" id="u1982-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
		    	<p>Nombre de usuario:</p>
		    </div>
		    <div class="clearfix grpelem" id="u2120-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
		    	<p>ContraseÃ±a:</p>
		    </div>
	    </div>
	    <div class="clearfix colelem" id="pu1976"><!-- group -->
		    <div class="clearfix grpelem" id="u1976"><!-- group -->
			    <div class="clearfix grpelem" id="u1979-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>Nombre de usuario</p>
			    </div>
		    </div>
		    <div class="clearfix grpelem" id="u2114"><!-- group -->
			    <div class="clearfix grpelem" id="u2117-4" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content -->
			    	<p>ContraseÃ±a</p>
			    </div>
		    </div>
	    </div>
	    <div class="clearfix colelem" id="pu1985"><!-- group -->
		    <div class="grpelem" id="u1985"><!-- simple frame --></div>
		    <a class="nonblock nontext clearfix grpelem" id="u1988-4" href="http://edicionesquivira.com/Novohispanorum/terminos.html" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><!-- content --><p>Acepto los tÃ©rminos y condiciones de Novohispanorum</p></a>
	    </div>
    </form>
    <form id="ingresar">
        <input type="hidden" id="idEmail" name="user" value=""/>
        <input type="hidden" id="idPassword" name="password" value=""/>
    </form>
    <div class="pointer_cursor Button rounded-corners clearfix colelem" id="buttonu1994" data-visibility="changed" style="visibility:hidden"><!-- container box -->
        <button class="nonblock nontext clearfix grpelem" id="u1995-4" href="index.html" data-muse-temp-textContainer-sizePolicy="true" data-muse-temp-textContainer-pinning="true"><span class=""></span> CREAR CUENTA</button>
    </div>
    
   </div>
  </div>
  <!-- Other scripts -->
  <script type="text/javascript">
   // Decide weather to suppress missing file error or not based on preference setting
var suppressMissingFileError = false
</script>
  <script type="text/javascript">
   window.Muse.assets.check=function(d){if(!window.Muse.assets.checked){window.Muse.assets.checked=!0;var b={},c=function(a,b){if(window.getComputedStyle){var c=window.getComputedStyle(a,null);return c&&c.getPropertyValue(b)||c&&c[b]||""}if(document.documentElement.currentStyle)return(c=a.currentStyle)&&c[b]||a.style&&a.style[b]||"";return""},a=function(a){if(a.match(/^rgb/))return a=a.replace(/\s+/g,"").match(/([\d\,]+)/gi)[0].split(","),(parseInt(a[0])<<16)+(parseInt(a[1])<<8)+parseInt(a[2]);if(a.match(/^\#/))return parseInt(a.substr(1),
16);return 0},g=function(g){for(var f=document.getElementsByTagName("link"),h=0;h<f.length;h++)if("text/css"==f[h].type){var i=(f[h].href||"").match(/\/?css\/([\w\-]+\.css)\?crc=(\d+)/);if(!i||!i[1]||!i[2])break;b[i[1]]=i[2]}f=document.createElement("div");f.className="version";f.style.cssText="display:none; width:1px; height:1px;";document.getElementsByTagName("body")[0].appendChild(f);for(h=0;h<Muse.assets.required.length;){var i=Muse.assets.required[h],l=i.match(/([\w\-\.]+)\.(\w+)$/),k=l&&l[1]?
l[1]:null,l=l&&l[2]?l[2]:null;switch(l.toLowerCase()){case "css":k=k.replace(/\W/gi,"_").replace(/^([^a-z])/gi,"_$1");f.className+=" "+k;k=a(c(f,"color"));l=a(c(f,"backgroundColor"));k!=0||l!=0?(Muse.assets.required.splice(h,1),"undefined"!=typeof b[i]&&(k!=b[i]>>>24||l!=(b[i]&16777215))&&Muse.assets.outOfDate.push(i)):h++;f.className="version";break;case "js":h++;break;default:throw Error("Unsupported file type: "+l);}}d?d().jquery!="1.8.3"&&Muse.assets.outOfDate.push("jquery-1.8.3.min.js"):Muse.assets.required.push("jquery-1.8.3.min.js");
f.parentNode.removeChild(f);if(Muse.assets.outOfDate.length||Muse.assets.required.length)f="Some files on the server may be missing or incorrect. Clear browser cache and try again. If the problem persists please contact website author.",g&&Muse.assets.outOfDate.length&&(f+="\nOut of date: "+Muse.assets.outOfDate.join(",")),g&&Muse.assets.required.length&&(f+="\nMissing: "+Muse.assets.required.join(",")),suppressMissingFileError?(f+="\nUse SuppressMissingFileError key in AppPrefs.xml to show missing file error pop up.",console.log(f)):alert(f)};location&&location.search&&location.search.match&&location.search.match(/muse_debug/gi)?
setTimeout(function(){g(!0)},5E3):g()}};
var muse_init=function(){require.config({baseUrl:""});require(["jquery","museutils","whatinput","jquery.watch","webpro","musewpslideshow","jquery.museoverlay","touchswipe"],function(d){var $ = d;$(document).ready(function(){try{
window.Muse.assets.check($);/* body */
Muse.Utils.transformMarkupToFixBrowserProblemsPreInit();/* body */
Muse.Utils.prepHyperlinks(true);/* body */
Muse.Utils.resizeHeight('.popup_anchor.allow_click_through');/* resize height */
Muse.Utils.makeButtonsVisibleAfterSettingMinWidth();/* body */
Muse.Utils.initWidget('#slideshowu2671', ['#bp_infinity'], function(elem) { var widget = new WebPro.Widget.ContentSlideShow(elem, {autoPlay:true,displayInterval:3000,slideLinkStopsSlideShow:false,transitionStyle:'horizontal',lightboxEnabled_runtime:false,shuffle:false,transitionDuration:500,enableSwipe:true,elastic:'fullWidth',resumeAutoplay:false,resumeAutoplayInterval:3000,playOnce:false,autoActivate_runtime:false,isResponsive:true}); $(elem).data('widget', widget); return widget; });/* #slideshowu2671 */
Muse.Utils.showWidgetsWhenReady();/* body */
Muse.Utils.transformMarkupToFixBrowserProblems();/* body */
}catch(b){if(b&&"function"==typeof b.notify?b.notify():Muse.Assert.fail("Error calling selector function: "+b),false)throw b;}})})};

</script>
  <!-- RequireJS script -->
  <script src="${contextpath}/static/resources/js/require.js?crc=3861931657" type="text/javascript" async data-main="${contextpath}/static/resources/js/museconfig.js?crc=278381782" onload="if (requirejs) requirejs.onError = function(requireType, requireModule) { if (requireType && requireType.toString && requireType.toString().indexOf && 0 <= requireType.toString().indexOf('#scripterror')) window.Muse.assets.check(); }" onerror="window.Muse.assets.check();"></script>
  </body>
</html>
