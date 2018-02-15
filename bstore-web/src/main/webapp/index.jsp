<%@ include file="./WEB-INF/views/layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Novohispanorum</title>
        <jsp:include page="./WEB-INF/views/layout/staticResources.jsp"></jsp:include>


            <script type="text/javascript">
                $(function () {
                    var status;
                    $('input#login').focus();

                    $("input#password").keypress(function (event) {
                        if (event.which == 13) {
                            event.preventDefault();
                            login();
                        }
                    });

                    $("input#login").keypress(function (event) {
                        if (event.which == 13) {
                            event.preventDefault();
                            login();
                        }
                    });


                    $('button#acceder').click(function () {
                        login();
                    });

                    function login() {
                        var email = $('input#login');
                        var password = $('input#password');
                        if (email.val() === "") {
                            muestraMsjSistemaError("<span class='text'>El login es requerido.</span>");
                            return false;
                        } else if (password.val() === "") {
                            muestraMsjSistemaError("<span class='text'>El password es requerido.</span>");
                            return false;
                        }

                        $.blockUI();
                        $.ajax({
                            type: 'POST',
                            url: '${contextpath}' + '/validar/usuario',
                            data: $('form#ingresar').serialize(),
                            success: function (data) {
                                $.unblockUI();
                                //muestraMsjSistemaSuccess(data.mensaje);
                                var urlAction = '${contextpath}' + '/equivira';
                                document.getElementById('ingresar').action = urlAction;
                                document.getElementById('ingresar').method = 'POST';
                                document.getElementById('ingresar').submit();
                            },
                            error: function (msj) {
                                status = JSON.parse(msj.responseText);
                                $.unblockUI();
                                muestraMsjSistemaError("<span class='text'>" + status.mensaje + "</span>");
                            }
                        });
                    }

                    $('button#registrar').click(function () {
                        $.blockUI();
                        var urlAction = '${contextpath}' + '/registrar/usuario';
                        document.location.href = urlAction;
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
    <body background="${contextpath}/static/resources/imagesv2/novo.jpg">

        <div class="container-fluid" style="margin-top:30px;">
            <div class="row">
                <div class="col-sm-12 col-sm-offset-0 col-md-2 col-md-offset-5 main">
                    <form id="ingresar" class="form-horizontal" method="POST" action="${contextpath}/equivira">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div style="text-align:center;color:black"><img width="200px" height="200px" src="${contextpath}/static/resources/imagesv2/logo-quivira.svg">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="text" class="text form-control" id="login" name="user" placeholder="Cuenta">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="password" class="text form-control" id="password" name="password" placeholder="Contrase&ncaron;a">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="text col-sm-12" style="color:black;text-align: center;"> 
                            	<a class="text" href="${contextpath}/password/recuperar" style="color: #A67C52;">Recuperar contraseña</a>
                            </label>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-sm-12" style="text-align: center;">
                            <button id="acceder" class="text btn btn-primary" style="background-color:#412020;border-color:#412020;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ENTRAR&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                        </div>
                    </div>
                    <br>
                    <!-- div class="row">
                    	<div class="col-sm-12" style="text-align: center;">
                        	<button id="registrar" class="text btn btn-default">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CREAR CUENTA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                        </div>
                    </div -->
                </div>
            </div>
            
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            
            <div class="row">
            	<div align="center">
            		<!-- a href="#" class="text" data-toggle="modal" data-target="#modalSobreNosotros" style="color: #A67C52;">Sobre nosotros</a>
            		&nbsp;|&nbsp;
            		-->
            		<a href="#" class="text" data-toggle="modal" data-target="#modalTerminosCondiciones" style="color: #A67C52;">T&eacute;rminos y condiciones</a>
            		&nbsp;|&nbsp;
            		<a href="#" class="text" data-toggle="modal" data-target="#modalAvisoPrivacidad" style="color: #A67C52;">Aviso de privacidad</a>
            		<!-- 
            		&nbsp;|&nbsp;
            		<a href="#" class="text" data-toggle="modal" data-target="#modalContacto" style="color: #A67C52;">Contacto</a>
            		-->
            	</div>
            </div>
            <br>
            <div class="row">
            	<div align="center">
            		<label class="control-label" style="color:#A67C52;">Ediciones Quivira, S.A. de C.V.</label>
            	</div>
            </div>
            <div class="row">
            	<div align="center">
            		<label class="control-label" style="color:#A67C52;">© Derechos reservados 2017</label>
            	</div>
            </div>
            
            
            
            
            
            <!-- Inician dialogos -->
            <div class="container">
			  <div class="modal fade" id="modalTerminosCondiciones" role="dialog">
                  <div class="modal-dialog modal-lg">
                      <div class="modal-content">
                          <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title"><b class="text"><c:out value="TÉRMINOS Y CONDICIONES DE SERVICIO Y USO DE NOVOHISPANORUM"/></b></h4>
                          </div>
                          <div class="modal-body">
                              <div class="row">
                                  <table>
                                      <tbody>
                                          <tr>
                                              <td width="50"></td>
                                              <td width="800">
                                                  <h5 class="text">ESTE ACUERDO LEGAL ENTRE USTED ("USUARIO") Y EDICIONES QUIVIRA, S.A.
                                                      DE C.V. ("QUIVIRA") RIGE EL SERVICIO Y LAS REGLAS DE USO DE LA 
                                                      APLICACIÓN NOVOHISPANORUM Y DE SUS CONTENIDOS.</h5>
                                                  <p class="text" style="color: gray;">Fecha de actualizaci&oacute;n: 6 de febrero de 2017.</p>
                                                  <br>
                                                  <p>
                                                      <a class="text" href="#servicio" style="color: #A67C52;">1. Servicio</a><br>
                                                      <a class="text" href="#responsabilidad" style="color: #A67C52;">1.1 Limitación de responsabilidad</a><br>
                                                      <a class="text" href="#privacidad" style="color: #A67C52;">2. Privacidad</a><br>
                                                      <a class="text" href="#cuenta" style="color: #A67C52;">2.1 Su cuenta</a><br>
                                                      <a class="text" href="#seguimiento" style="color: #A67C52;">2.2 Seguimiento</a><br>
                                                      <a class="text" href="#contenido" style="color: #A67C52;">3. Contenido</a><br>
                                                      <a class="text" href="#disponibilidad" style="color: #A67C52;">3.1 Disponibilidad</a><br>
                                                      <a class="text" href="#reglas" style="color: #A67C52;">3.2 Reglas de uso</a><br>
                                                      <a class="text" href="#propiedad" style="color: #A67C52;">3.3 Propiedad intelectual</a><br>
                                                      <a class="text" href="#desarrollador" style="color: #A67C52;">4. Sobre el desarrollador</a><br>
                                                      <br>
                                                      <br>
                                                  </p><h4 id="servicio" class="text">1. El servicio</h4>
                                                  <br>
                                                  <p class="text" style="text-align: justify; color: gray;">
                                                      Quivira es una editorial establecida conforme las leyes mexicanas que le concede 
                                                      un servicio de visualizaci&oacute;n, acceso y consulta del contenido digital de su colecci&oacute;n 
                                                      Novohispanorum ("publicaciones"), de acuerdo al programa editorial y las gestiones de 
                                                      derechos de reproducci&oacute;n como sigue:
                                                      <br><br>
                                                      (i) Un software gratuito que funciona para la organizaci&oacute;n, gesti&oacute;n y medio de 
                                                      visualizaci&oacute;n de las "publicaciones" de acuerdo con la tem&aacute;tica a la que pertenezcan, 
                                                      el cual estar&aacute; ligado a su cuenta personal.
                                                      <br><br>
                                                      (ii) "Publicaciones" que contendr&aacute;n la transcripci&oacute;n de un documento de archivo o 
                                                      biblioteca, una reproducci&oacute;n digital del documento original del cual se hizo la transcripci&oacute;n, 
                                                      un &iacute;ndice onom&aacute;stico y uno topon&iacute;mico, notas explicativas que hacen referencias a 
                                                      t&eacute;rminos o arca&iacute;smos del español antiguo u otro idioma. La inclusi&oacute;n de estos &uacute;ltimos 
                                                      en las "publicaciones" est&aacute; sujeta al contenido mismo del documento, por lo que no se asegura que todas 
                                                      las "publicaciones" tengan uno propio.
                                                      <br><br>
                                                      (iii) La mayor&iacute;a de las "publicaciones" requieren de un pago para su descarga y consulta; aunque algunas 
                                                      de ellas podr&aacute;n descargarse de manera gratuita. El uso de ambas queda sujeto a las reglas de uso y derechos 
                                                      de la propiedad intelectual de estos t&eacute;rminos y condiciones.
                                                      <br>
                                                      <br>
                                                  </p>
                                                  <h4 id="responsabilidad" class="text">1.1 Limitaci&oacute;n de responsabilidad</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text"> 
                                                      Quivira no garantiza ni declara que el uso del servicio estar&aacute; libre de error y sea ininterrumpido, lo cual 
                                                      depende de su conexi&oacute;n a internet, de la capacidad de su computadora y de los conocimientos que usted tenga 
                                                      sobre el uso de la misma. Quivira tampoco asegura que las “publicaciones” est&eacute;n libres de p&eacute;rdida, 
                                                      corrupci&oacute;n, ataque o intervenci&oacute;n de hackers, virus o intrusi&oacute;n de seguridad, por lo que Quivira 
                                                      renuncia a cualquier responsabilidad relacionada con las mencionadas anteriormente.
                                                      <br>
                                                      En ning&uacute;n caso Quivira y cualquiera de sus gerentes, empleados o distribuidor ser&aacute;n responsables por 
                                                      cualquier daño indirecto, incidental o emergente derivado del uso que usted haga de las "publicaciones". De la misma 
                                                      forma, Quivira renuncia a cualquier responsabilidad sobre cualquier error u omisi&oacute;n en el contenido de los 
                                                      documentos, los cuales son reproducidos de forma fiel y publicados bajo estrictos lineamientos editoriales para 
                                                      mantener un alto est&aacute;ndar de calidad en los mismos.
                                                      <br>
                                                      Quivira se deslinda de cualquier uso pol&iacute;tico, religioso, social, educativo, acad&eacute;mico o de cualquier 
                                                      otro tipo que el "usuario" haga del contenido e interpretaci&oacute;n de las "publicaciones". Los contenidos y las 
                                                      tem&aacute;ticas de las "publicaciones" de ninguna manera reflejan alguna postura ideol&oacute;gica, sea pol&iacute;tica, 
                                                      religiosa, acad&eacute;mica o de cualquier otra &iacute;ndole de Quivira ni de alguno de sus gerentes o empleados.
                                                      <br>
                                                      Usted acepta expresamente el uso de todas las "publicaciones" tal y como se presentan, sean gratuitas o de pago, de 
                                                      acuerdo con su disponibilidad, diseño, funcionalidades y contenido, sin garant&iacute;as de ninguna clase. Cualquier 
                                                      falla en las publicaciones ser&aacute; actualizada y reparada de acuerdo al plan editorial de actualizaci&oacute;n 
                                                      constante de las "publicaciones" de Quivira.
                                                      <br>
                                                      Estos T&eacute;rminos y condiciones de servicio y uso ser&aacute;n actualizados constantemente. Al usuario se le 
                                                      notificar&aacute; en el apartado de mensajes de su cuenta en Novohispanorum, por lo que es su responsabilidad revisar y 
                                                      leer los cambios y modificaciones que se hagan en ellos.
                                                      <br>
                                                      Usted libera a Quivira y cualquiera de sus gerentes, empleados y distribuidor por el mal uso de los contenidos y 
                                                      materiales que contengan las "publicaciones", lo que derive en infracciones de la propiedad intelectual, derechos de 
                                                      reproducci&oacute;n del contenido y derechos de autor, de acuerdo a las leyes vigentes del pa&iacute;s.
                                                      <br>
                                                      Quivira tiene autorizaci&oacute;n de reproducci&oacute;n y publicaci&oacute;n de las im&aacute;genes digitales de los 
                                                      documentos en las "publicaciones", si usted hace mal uso o reproduce alguna de las im&aacute;genes, libera a Quivira de 
                                                      cualquier responsabilidad por su reproducci&oacute;n no autorizada y mal uso. Usted acepta, en la medida m&aacute;xima 
                                                      permitida por la ley, defender a Quivira, sus gerentes, cualquiera de sus empleados, distribuidor e inclusive terceros de 
                                                      cualquier tipo de reclamo, demanda, procedimiento, p&eacute;rdidas, responsabilidades, daños, costos y gastos que devengan 
                                                      y est&eacute;n relacionados del mal uso que el "usuario" haga del material.
                                                      <br>
                                                      <br>
                                                  </p>
                                                  <h4 id="privacidad" class="text">2. Privacidad</h4>
                                                  <br>
                                                  <h4 id="cuenta" class="text">2.1 Su cuenta</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      El pago, uso y acceso a las "publicaciones" es a trav&eacute;s de nuestra propia plataforma y est&aacute; relacionada con 
                                                      una cuenta de correo electr&oacute;nico, por lo que usted necesita un correo electr&oacute;nico activo para poder crear 
                                                      una cuenta en Novohispanorum. No revele la informaci&oacute;n de su cuenta a nadie. Usted es el &uacute;nico responsable 
                                                      de mantener la informaci&oacute;n de su cuenta de manera confidencial y segura, as&iacute; como de todas las acciones y 
                                                      actividades realizadas a trav&eacute;s de ella; usted acepta informar inmediatamente a los administradores de ella sobre 
                                                      cualquier anormalidad o fallo en la seguridad de su cuenta, lo que est&aacute; regulado por los t&eacute;rminos y 
                                                      condiciones de cada uno de los servicios de correo electr&oacute;nico. Cualquier mal uso que surja de las "publicaciones" 
                                                      a trav&eacute;s de su cuenta ser&aacute; &uacute;nica y exclusivamente su responsabilidad.
                                                      <br>
                                                      Queda bajo su responsabilidad proporcionar informaci&oacute;n ver&iacute;dica en su cuenta de correo electr&oacute;nico y 
                                                      actualizarla constantemente, mantener datos precisos y exactos facilitar&aacute; hacer los cargos y recibos de compra de 
                                                      las "publicaciones" a su cuenta.
                                                      <br>
                                                      Quivira, sus gerentes o empleados no est&aacute;n autorizados a recibir pagos, en dinero o en especie ni de ninguna otra 
                                                      forma por el acceso a las "publicaciones" que difiera de las establecidas por los sistemas de cobro establecidos en 
                                                      nuestro sitio.
                                                      <br>
                                                      <br>
                                                  </p> 
                                                  <h4 id="seguimiento" class="text">2.2 Seguimiento</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      Las "publicaciones" emplean la herramienta de seguimiento de uso de Google Analytics, la cual permite medir la forma en que 
                                                      el "usuario" interact&uacute;a con el contenido de la aplicaci&oacute;n. Google Analytics emplea cookies para "recordar" lo 
                                                      que el “usuario” ha hecho en el sitio anteriormente, proporcionando etiquetas JavaScript para su registro. NINGUNA 
                                                      INFORMACI&Oacute;N PERSONAL, REFERENCIAL, DESCRIPTIVA, PUBLICITARIA, DE UBICACI&Oacute;N O IDENTIDAD del "usuario" es 
                                                      recopilada por esta herramienta.
                                                      <br>
                                                      La herramienta proporciona datos estad&iacute;sticos del uso del sitio y su interacci&oacute;n con el “usuario”, para mejorar 
                                                      la calidad, contenido, disposición, funcionalidad y experiencia que el "usuario" tiene con el sitio.
                                                      <br>
                                                      Para saber sobre esta herramienta puede consultar el sitio de Google Analytics: <a style="color: #A67C52;" class="text" 
                                                      href="http://www.google.com.mx/intl/es_ALL/analytics/index.html" target="_blank">
                                                      http://www.google.com.mx/intl/es_ALL/analytics/index.html</a>
                                                      <br>
                                                  </p>
                                                  <br>
                                                  <h4 id="contenido" class="text">3. Contenido</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      Quivira se compromete a publicar contenido fiable, ver&iacute;dico siguiendo est&aacute;ndares de alta calidad editorial que 
                                                      constituyan una referencia, ayuda y soporte al desarrollo de actividades culturales, sociales y acad&eacute;micas del 
                                                      "usuario" en las "publicaciones". As&iacute; mismo, Quivira se compromete tambi&eacute;n a mantener las "publicaciones" libres 
                                                      de materiales textuales o im&aacute;genes de terceros que puedan ser considerados como publicidad.
                                                      <br>
                                                      La actualizaci&oacute;n de las "publicaciones", tanto en el software como en los contenidos ser&aacute; constante, lo que 
                                                      constituir&aacute; una mejora en la funcionalidad y en la experiencia del "usuario". Cualquier actualizaci&oacute;n en el 
                                                      contenido o software ser&aacute; notificada al "usuario" para que sea descargada.
                                                      <br> 
                                                  </p>
                                                  <br>
                                                  <h4 id="disponibilidad" class="text">3.1 Disponibilidad del contenido</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      Quivira se reserva el derecho de cambiar el diseño, las opciones de contenido, las funcionalidades de las "publicaciones" 
                                                      durante sus actualizaciones sin previo aviso, de acuerdo con su programa editorial y mejoramiento de las “publicaciones”. 
                                                      Estos cambios no afectar&aacute;n el acceso a las "publicaciones" que el "usuario" ha descargado, por lo cual no se ver&aacute; 
                                                      obligado a hacer un nuevo cargo una vez que haya cumplido con los requisitos de descarga, pues s&oacute;lo modificar&aacute; 
                                                      el aspecto y la funcionalidad en cantidad m&iacute;nima.
                                                      <br>
                                                  </p>
                                                  <br>
                                                  <h4 id="reglas" class="text">3.2 Reglas de uso</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      Usted acepta que el uso de las "publicaciones" est&aacute;n determinadas por las Reglas de Uso que establezca Quivira de acuerdo 
                                                      con las leyes en materia de Derechos de Autor y Propiedad Intelectual vigentes. Cualquier otro uso puede constituir una 
                                                      violaci&oacute;n de los derechos de autor. Quivira se reserva el derecho de modificar las reglas de uso en cualquier momento, 
                                                      dando aviso al “usuario” cuando esto ocurra.
                                                      <br>
                                                      Usted acepta no violar, eludir, realizar ingenier&iacute;a inversa, decodificar, desmontar, alterar con cualquier tipo de 
                                                      tecnolog&iacute;a la seguridad y la integridad de las "publicaciones" por ninguna raz&oacute;n, ni ayudar o asesorar a ninguna 
                                                      persona para hacerlo. Usted acepta no acceder a las "publicaciones" por otro medio que no sea el sitio de Novohispanorum. Usted 
                                                      acepta no modificar el software, la imagen visual y gr&aacute;fica, la interfaz y ninguno de los contenidos para ning&uacute;n 
                                                      prop&oacute;sito.
                                                      <br>
                                                      Quivira puede controlar, supervisar e involucrarse en el cumplimiento de las Reglas de Uso y se reserva el derecho de hacerlas 
                                                      cumplir sin notificarlo previamente. Quivira se reserva, a su criterio, negar, limitar o cancelar el uso de las "publicaciones" al 
                                                      "usuario" si no cumple y viola las Reglas de Uso, si se detecta que el uso de la cuenta tiene alguna finalidad maliciosa que atente 
                                                      en contra de los derechos de autor de las publicaciones o tenga finalidades de copia, reproducci&oacute;n ilegal de las 
                                                      publicaciones o atente contra la integridad y seguridad del sitio y de los contenidos de Novohispanorum o Quivira, sin que haya 
                                                      responsabilidad alguna frente al mismo ni haya reembolso alguno por la compra anterior de cualquiera de las "publicaciones".
                                                      <br>
                                                      Cualquier reproducci&oacute;n del contenido, imagen visual y gr&aacute;fica, la interfaz, logotipos, nombres, im&aacute;genes de 
                                                      los documentos, etc&eacute;tera, deber&aacute; constar por escrito, con firma adjunta y aut&oacute;grafa del representante legal 
                                                      de Quivira y de uno de sus gerentes.
                                                      <br>
                                                      (i) Usted acepta utilizar las "publicaciones" s&oacute;lo para uso personal, sin exclusividad alguna.
                                                      <br>
                                                      (ii) Su cuenta es intransferible a otra persona o usuario, no se puede revender, rentar o comercializar el acceso y consulta a 
                                                      terceros por medio de suscripciones o venta directa para cualquier uso comercial.
                                                      <br>
                                                      (iii) La compra de las "publicaciones" no constituye una cesi&oacute;n, renuncia o limitaci&oacute;n de cualquiera de los derechos 
                                                      de autor del propietario de los derechos de las “publicaciones”.
                                                      <br>
                                                      (iv) Usted no estar&aacute; autorizado a utilizar de forma comercial cualquier contenido de las "publicaciones", &iacute;ntegra o 
                                                      parcialmente, como las transcripciones, los &iacute;ndices, las notas explicativas, la imagen visual y gr&aacute;fica, la interfaz, 
                                                      los logotipos, los nombres de la colecci&oacute;n o Quivira y las im&aacute;genes de los documentos.
                                                      <br>
                                                      (v) La descarga y el pago por cualquiera de las "publicaciones" no lo autoriza a usted a reproducir y distribuir de cualquier forma 
                                                      los contenidos de las "publicaciones" ni sus contenidos &iacute;ntegra o parcialmente, como las transcripciones, los &iacute;ndices, 
                                                      las notas explicativas, la imagen visual y gr&aacute;fica, la interfaz, los logotipos, los nombres de la colecci&oacute;n o Quivira 
                                                      y las im&aacute;genes de los documentos.
                                                      <br>
                                                      (vi) Usted podr&aacute; consultar las "publicaciones" en cualquier equipo a trav&eacute;s de su cuenta personal de Novohispanorum. 
                                                      Cualquier mal uso de las "publicaciones" a trav&eacute;s de su cuenta son &uacute;nica y exclusivamente su responsabilidad. 
                                                      Ver 2.1 SU CUENTA.
                                                      <br>
                                                      (vii) La reproducci&oacute;n no autorizada y el mal uso de las im&aacute;genes de los documentos antiguos que se incluyen en las 
                                                      "publicaciones" constituyen una infracci&oacute;n de acuerdo con las leyes. Quivira no est&aacute; facultada para autorizar ni su 
                                                      reproducci&oacute;n ni su uso. Cualquier violaci&oacute;n o reproducci&oacute;n no autorizada y mal uso pueden resultar en 
                                                      responsabilidad civil o penal en contra del "usuario".
                                                      <br>
                                                      (viii) Ning&uacute;n documento, imagen, transcripci&oacute;n, &iacute;ndice, comentario, descripci&oacute;n o cualquier elemento del 
                                                      sitio electr&oacute;nico o de cualquier publicaci&oacute;n, &iacute;ntegro o en partes, podr&aacute; ser reproducido, duplicado, 
                                                      copiado, vendido, revendido, descargado para ser explotado de alguna forma, comercializado para fines propios o de terceros.
                                                      <br>
                                                      (ix) Queda prohibida tambi&eacute;n cualquier reproducci&oacute;n o copia de los logotipos, im&aacute;genes o cualquier otro elemento 
                                                      del sitio de Novohispanorum o Ediciones Quivira sin consentimiento expreso y por escrito del representante legal, como se señala en 
                                                      estos t&eacute;rminos y condiciones.
                                                      <br>
                                                      Las violaciones del sistema, la seguridad, su modificaci&oacute;n, alteraci&oacute;n en cualquier forma, así como la reproducci&oacute;n 
                                                      &iacute;ntegra o parcial, distribuci&oacute;n, comercializaci&oacute;n de las transcripciones, los &iacute;ndices, las notas 
                                                      explicativas, la imagen visual y gráfica, la interfaz, los logotipos, los nombres de la colecci&oacute;n o Quivira y las im&aacute;genes 
                                                      de los documentos y la violaci&oacute;n de alguna de estas Reglas de Uso pueden resultar en responsabilidad civil o penal en contra del 
                                                      "usuario".
                                                      <br>
                                                  </p>
                                                  <br>
                                                  <h4 id="propiedad" class="text">3.3 Propiedad intelectual</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      Usted acepta que las "publicaciones" y todo su contenido editorial, gr&aacute;fico, visual, transcripciones, &iacute;ndices, notas 
                                                      explicativas, interfaz, logotipos, nombres de la colecci&oacute;n o Quivira utilizados en las "publicaciones" es propiedad exclusiva de 
                                                      Quivira, que est&acute; protegido por las leyes aplicables de propiedad intelectual y derechos de autor vigentes. Usted acepta que no 
                                                      utilizar&aacute; este contenido o material de ninguna otra forma m&aacute;s que de la permitida por este contrato. Ninguna parte del 
                                                      contenido fragmentada o &iacute;ntegra podr&aacute; reproducirse por ning&uacute;n medio impreso o digital con excepci&oacute;n de la 
                                                      previa autorizaci&oacute;n, de acuerdo con las Reglas de Uso de este contrato. Usted acepta no modificar, rentar, arrendar, vender, 
                                                      distribuir, comerciar de ninguna forma posible, ni crear trabajos derivados en las "publicaciones" de cualquier manera no autorizada, 
                                                      sea enunciativa pero no limitativa mediante cualquier medio electr&oacute;nico o impreso.
                                                      <br>
                                                      Quivira se reserva el derecho de suspender o desactivar el servicio de las "publicaciones" al "usuario" cuando determine que se ha hecho 
                                                      mal uso o se han violado las especificaciones de las reglas de Uso de este contrato, sin que haya, en cualquier caso, responsabilidad 
                                                      alguna.
                                                      <br>
                                                      Quivira, Novohispanorum, sus logos, imagen visual y gr&aacute;fica son marcas comerciales registradas de Ediciones Quivira, S.A. de C.V. 
                                                      Otras marcas, servicios, gr&aacute;ficos, interfaz y logotipos, por la cual se distribuyen las "publicaciones" son marcas comerciales de 
                                                      sus respectivos dueños. No se le cede a usted ning&uacute;n derecho o licencia de uso de ning&uacute;n tipo con respecto a ninguna de 
                                                      las marcas comerciales mencionadas.
                                                      <br>
                                                  </p>
                                                  <br>
                                                  <h4 id="desarrollador" class="text">4. Sobre el desarrollador</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      Quivira es una editorial constituida conforme las leyes mexicanas, la cual tiene como objeto la publicaci&oacute;n y distribuci&oacute;n 
                                                      de publicaciones a trav&eacute;s de medios electr&oacute;nicos e impresos sobre diversos temas.
                                                      <br>
                                                      Si desea descargar una versi&oacute;n impresa de los t&eacute;rminos y condiciones puede acceder a la p&aacute;gina: 
                                                      <a style="color: #A67C52;" class="text" href="http://edicionesquivira.com/Novohispanorum/termsnovohispanorum.pdf" target="blank">T&eacute;rminos y 
                                                      condiciones</a>
                                                      <br>
                                                      <br>
                                                      Tambi&eacute;n puede escribir a la direcci&oacute;n salve@novohispanorum.mx por cualquier aclaraci&oacute;n.
                                                      <br>
                                                      <br>
                                                      Contacto:<br>
                                                      Al&iacute; Albarr&aacute;n<br>
                                                      ali@edicionesquivira.com<br>
                                                      Ediciones Quivira, S.A. de C.V.<br>
                                                      Excelsior 239, colonia Guadalupe Insurgentes<br>
                                                      C. P. 07870<br>
                                                      M&eacute;xico, D. F. <br>
                                                  </p>
                                              </td>
                                              <td width="20">
                                              </td>
                                          </tr>
                                      </tbody></table>
                              </div>
                              <div class="modal-footer">
                                  <div class="col-md-12">
                                      <button type="button" class="text btn btn-default" data-dismiss="modal">Aceptar</button>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
              
              
              
              <div class="modal fade" id="modalAvisoPrivacidad" role="dialog">
                  <div class="modal-dialog modal-lg">
                      <div class="modal-content">
                          <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title"><b class="text"><c:out value="AVISO DE PRIVACIDAD"/></b></h4>
                          </div>
                          <div class="modal-body">
                              <div class="row">
                                  <table>
                                      <tbody>
                                          <tr>
                                              <td width="50"></td>
                                              <td width="800">
                                                  <h4 class="text">Identidad del responsable</h4>
                                                  <br>
                                                  <p class="text" style="text-align: justify; color: gray;">
                                                      Ediciones Quivira, S.A. de C.V. (Quivira), con domicilio en Pet&eacute;n 108, colonia Narvarte, Delegaci&oacute;n 
                                                      Benito Ju&aacute;rez, C.P. 03020 en la Ciudad de M&eacute;xico, es el responsable del uso y protecci&oacute;n de 
                                                      sus datos personales, y al respecto publica el presente aviso de privacidad con motivo de los datos personales 
                                                      que recabe o llegue a recabar por las actividades que realiza. Estos datos pueden ser en algunos casos: nombre, 
                                                      correo electr&oacute;nico, fecha de nacimiento, edad, ocupaci&oacute;n, nivel acad&eacute;mico, fotograf&iacute;a 
                                                      del perfil, correo electr&oacute;nico, domicilio personal.
                                                      <br>
                                                      <br>
                                                  </p>
                                                  <h4 class="text">Datos personales sensibles</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text"> 
                                                      Los datos personales sensibles son los que afecten de forma m&aacute;s &iacute;ntima a su titular o cuya 
                                                      utilizaci&oacute;n indebida puede dar origen a discriminaci&oacute;n o acarrear un riesgo grave para el mismo. 
                                                      Nosotros no solicitaremos datos personales sensibles.
                                                      <br>
                                                      <br>
                                                  </p>
                                                  <h4 class="text">¿Para qu&eacute; fines utilizaremos sus datos personales?</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      1) Creaci&oacute;n de una cuenta de usuario.  <br />
													  2) Verificar la identidad del usuario. <br />
													  3) Venta de acceso de publicaciones en l&iacute;nea.
                                                      <br>
                                                      <br>
                                                  </p> 
                                                  <h4 class="text">Finalidades secundarias</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      De manera adicional, utilizaremos su informaci&oacute;n personal para las siguientes finalidades secundarias que no 
                                                      son necesarias para el servicio solicitado, pero que nos permiten y facilitan brindarle una mejor atenci&oacute;n 
                                                      y servicio a los usuarios:
                                                      <br>
                                                      <br>
                                                      1) Env&iacute;o de novedades y noticias a trav&eacute;s de un bolet&iacute;n por correo electr&oacute;nico. <br />
													  2) Uso de un c&oacute;digo de seguimiento para generar estad&iacute;sticas de uso de la plataforma. <br />
													  3) Env&iacute;o de bolet&iacute;n de noticias de publicaciones. <br />
													  4) Mercadotecnia o publicitaria. <br />
													  5) Prospecci&oacute;n comercial.
													  <br>
													  <br>
													  En caso de que no desee que sus datos personales sean tratados para las finalidades secundarias, usted puede comunicarlo 
													  al siguiente correo electr&oacute;nico avisodeprivacidad@novohispanorum.mx, las cuales no impedir&aacute;n la 
													  relaci&oacute;n y uso de este sitio. Si no se manifiesta esta negativa en los siguientes 10 d&iacute;as naturales, se 
													  entender&aacute; que ha otorgado su consentimiento para el tratamiento de sus datos para las finalidades citadas. En caso 
													  contrario debe mandar un mensaje con el texto: <i>No consiento que mis datos personales sean utilizados para las finalidades 
													  secundarias establecidas en este aviso</i>.
                                                  </p>
                                                  <br>
                                                  <h4 class="text">Medios para limitar el uso o divulgaci&oacute;n de datos personales</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      En caso de que desee limitar total o parcialmente sus datos personales para los fines secundarios, puede hacerlo de manera 
                                                      gratuita, mediante el medio al que hacen referencia en los incisos siguientes: 
													  <br />
													  a) Deber&aacute; enviar su solicitud a la direcci&oacute;n de correo electr&oacute;nico avisodeprivacidad@novohispanorum.mx 
													  con la siguiente informaci&oacute;n: 
													  <br />
													  1) Asunto: “Limitaci&oacute;n de uso de datos para fines secundarios”. <br />
													  2) Nombre, domicilio y un medio para comunicarse con usted (correo electr&oacute;nico y n&uacute;mero de tel&eacute;fono). <br />
													  3) Documentos que acrediten la identidad o, en su caso, la representaci&oacute;n legal del titular.<br />
													  4) Cualquier otro elemento o documento que facilite la localizaci&oacute;n de los datos personales.<br />
													  <br />
													  No consiento que mis datos personales se utilicen para los siguientes fines (marcar con una x):
													  <br />
													  [  ] Env&iacute;o de novedades y noticias a trav&eacute;s de un bolet&iacute;n por correo electr&oacute;nico. <br />
													  [  ] Uso de un c&oacute;digo de seguimiento para generar estad&iacute;sticas de uso de la plataforma. <br />
													  [  ] Env&iacute;o de bolet&iacute;n de noticias de publicaciones. <br />
													  [  ] Mercadotecnia o publicitaria. <br />
													  [  ] Prospecci&oacute;n comercial. <br />
													  <br />
													  Para mayor informaci&oacute;n tambi&eacute;n puede comunicarse con nuestro Oficial de Privacidad al (55)72611709 o al correo 
													  electr&oacute;nico avisodeprivacidad@novohispanorum.mx 
													  <br />
													  b) El Oficial de Privacidad deber&aacute; responder a su solicitud dentro de los 20 (veinte) d&iacute;as h&aacute;biles 
													  posteriores a la fecha en que reciba el correo electr&oacute;nico con la informaci&oacute;n requerida.<br />
													  c) De proceder la limitaci&oacute;n usted ser&aacute; incluido en nuestro listado de exclusi&oacute;n y no trataremos sus datos 
													  personales para las finalidades secundarias, salvo que usted nos lo solicite por escrito. <br />
													  d) El medio para respuesta a su solicitud ser&aacute; a trav&eacute;s de correo electr&oacute;nico. <br />
													<br>
													La negativa para el uso de sus datos personales para estas finalidades no podr&aacute; ser un motivo para que le neguemos 
													los servicios y productos que solicita o contrata con nosotros.
													<br />
                                                  </p>
                                                  <br>
                                                  <h4 class="text">Transferencia de datos</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      Los datos personales que se recaben en el sitio no ser&aacute;n transferidos, divulgados ni compartidos de forma alguna con 
                                                      terceros, salvo por lo indispensable para el cumplimiento de obligaciones legales.
                                                      <br>
                                                  </p>
                                                  <br>
                                                  <h4 class="text">Medio por el cual se comunicar&aacute;n cambios en este Aviso de Privacidad</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      Cualquier cambio al presente Aviso de Privacidad ser&aacute; publicado en el momento en que se efect&uacute;e en nuestro sitio web 
                                                      en la siguiente direcci&oacute;n: www.edicionesquivira.mx
                                                      <br>
                                                  </p>
                                                  <br>
                                                  <h4 class="text">Informaci&oacute;n sobre el uso de cookies y web beacons.</h4>
                                                  <br>
                                                  <p style="text-align: justify; color: gray;" class="text">
                                                      Las cookies son archivos de texto que son descargados autom&aacute;ticamente y almacenados en disco duro del equipo de c&oacute;mputo 
                                                      del usuario al navegar en una p&aacute;gina de Internet espec&iacute;fica, que permiten recordar al servidor de Internet algunos datos 
                                                      sobre este usuario, entre ellos, preferencias para la visualizaci&oacute;n de las p&aacute;ginas de ese servidor, nombre y contraseña.
													  Por su parte, las web beacons son im&aacute;genes insertadas en una p&aacute;gina de internet o correo electr&oacute;nico, que puede 
													  ser utilizado para monitorear el comportamiento de un visitante, como almacenar informaci&oacute;n sobre la direcci&oacute;n IP del 
													  usuario, duraci&oacute;n del tiempo de interacci&oacute;n en dicha p&aacute;gina y el tipo de navegador utilizado entre otros.
													  Le informamos que utilizamos cookies y web beacons para obtener informaci&oacute;n personal de usted como la siguiente: tipo de navegador 
													  y sistema operativo; las p&aacute;ginas web que visita; los v&iacute;nculos que sigue; direcci&oacute;n IP; sitio web que visit&oacute; 
													  antes del nuestro. Estas cookies y otras tecnolog&iacute;as pueden ser deshabilitadas. Para conocer c&oacute;mo hacerlo, consulte el 
													  manual de su navegador o comun&iacute;quese con su proveedor de servicio.
                                                      <br>
                                                      <br />
													  Atentamente,<br />
													  Ediciones Quivira, S.A. de C.V. <br />
													  &Uacute;ltima fecha de actualizaci&oacute;n: 26 de noviembre de 2017. 
													  <br /><br />
                                                  </p>
                                              </td>
                                              <td width="20">
                                              </td>
                                          </tr>
                                      </tbody></table>
                              </div>
                              <div class="modal-footer">
                                  <div class="col-md-12">
                                      <button type="button" class="text btn btn-default" data-dismiss="modal">Aceptar</button>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
              
              
              
              <div class="modal fade" id="modalContacto" role="dialog">
                  <div class="modal-dialog modal-lg">
                      <div class="modal-content">
                          <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 class="modal-title"><b class="text"><c:out value="CONTACTO"/></b></h4>
                          </div>
                          <div class="modal-body">
                              <div class="row">
                                  <div class="col-sm-12 col-sm-offset-0">
				                    <form id="contacto" class="form-horizontal" method="POST" action="${contextpath}/equivira">
				                    	<div class="form-group">
				                        	<span class="col-md-1 col-md-offset-1 text-center"><i class="fa fa-user bigicon"></i></span>
				                            <div class="col-md-9">
				                                <input type="text" class="text form-control" id="nombreContacto" name="nombreContacto" placeholder="Escriba su nombre...">
				                            </div>
				                        </div>
				                        <div class="form-group">
				                            <div class="col-sm-10">
				                                <input type="text" class="text form-control" id="correoContacto" name="correoContacto" placeholder="Correo">
				                            </div>
				                        </div>
				                        <div class="form-group">
				                            <div class="col-sm-10">
				                                <input type="text" class="text form-control" id="nombreContacto" name="nombreContacto" placeholder="Nombre">
				                            </div>
				                        </div>
				                        <div class="form-group">
				                            <div class="col-sm-10">
				                                <input type="text" class="text form-control" id="nombreContacto" name="nombreContacto" placeholder="Nombre">
				                            </div>
				                        </div>
				                    </form>
				                </div>
                              </div>
                              <div class="modal-footer">
                                  <div class="col-md-12">
                                      <button type="button" class="text btn btn-default" data-dismiss="modal">Aceptar</button>
                                      <button type="button" class="text btn btn-default" data-dismiss="modal">Cancelar</button>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
              
              
			</div>
			<!-- Terminan dialogos -->
			
			
			
			
        </div>
    </body>
</html>
