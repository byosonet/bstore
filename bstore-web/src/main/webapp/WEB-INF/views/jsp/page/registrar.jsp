<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
    <head>
        <title>Novohispanorum</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/model/ModelNacimiento.js"></script>

        <script type="text/javascript">
            $(function () {

                $('select#dia').select2({
                	placeholder: 'Día',
                    allowClear: true,
                    language: {
                        noResults: function(){
                            return "No se han encontrado resultados";
                        }
                    }
                });
                $('select#anio').select2({
                	placeholder: 'Año',
                    allowClear: true,
                    language: {
                        noResults: function(){
                            return "No se han encontrado resultados";
                        }
                    }
                });
                $('select#mes').select2({
                	placeholder: 'Mes',
                    allowClear: true,
                    language: {
                        noResults: function(){
                            return "No se han encontrado resultados";
                        }
                    }
                });
                $('select#actividad').select2({
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
                $('button#registrar').click(function () {

                    var nombre = $('input#nombre');
                    var apaterno = $('input#apaterno');
                    var amaterno = $('input#amaterno');
                    var login = $('input#login');
                    var telefono = $('input#telefono');
                    var email = $('input#email');
                    var passw = $('input#password');
                    var confPassword = $('input#confPassword');
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

        <div class="container-fluid" style="margin-top: 30px;">
            <div class="row">
                <div class="col-sm-9 col-sm-offset-1 main">
                    
                    <form class="form-horizontal" id="formRegistrar" method="post" action="${contextpath}/registrar">

                        <div class="form-group">
                            <h3 class="control-label col-sm-12" style="text-align: center;color:black;"><b class="text"><span class="label label-info"></span></b></h3>
                        </div>
                        </br>
                        </br>

                        <div class="form-group">
                            <label class="text control-label col-sm-2" style="color:black;" for="login"></label>
                            <div class="col-sm-4">
                                <input type="text" class="text form-control" id="login" name="login" placeholder="Login">
                            </div>

                            <label class="text control-label col-sm-2" style="color:black;" for="nombre"></label>
                            <div class="col-sm-4">
                                <input type="text" class="text form-control" id="nombre" name="nombre" placeholder="Nombre">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="text control-label col-sm-2" style="color:black;" for="apaterno"></label>
                            <div class="col-sm-4">
                                <input type="text" class="text form-control" id="apaterno" name="apaterno" placeholder="Apellido paterno">
                            </div>

                            <label class="text control-label col-sm-2" style="color:black;" for="amaterno"></label>
                            <div class="col-sm-4">
                                <input type="text" class="text form-control" id="amaterno" name="amaterno" placeholder="Apellido materno">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="text control-label col-sm-2" style="color:black;" for="telefono"></label>
                            <div class="col-sm-4">
                                <input type="text" class="text form-control" id="telefono" name="telefono" placeholder="Tel&eacute;fono">
                            </div>

                            <label class="text control-label col-sm-2" style="color:black;" for="email"></label>
                            <div class="col-sm-4">
                                <input type="text" class="text form-control" id="email" name="email" placeholder="Email">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="text control-label col-sm-2" style="color:black;" for="email"></label>
                            <div class="col-sm-4">
                                <input type="password" class="text form-control" id="password" name="password" placeholder="Password">
                            </div>

                            <label class="text control-label col-sm-2" style="color:black;" for="email"></label>
                            <div class="col-sm-4">
                                <input type="password" class="text form-control" id="confPassword" name="confPassword" placeholder="Confirmar password">
                            </div>
                        </div>
                        </br>
                        <div class="row col-sm-12" style="margin-left: 250px;">
                        <div class="form-group">
                           
                            <div class="col-sm-2">
                                <select class="form-control" id="dia" name="dia" data-bind="foreach: days, visible: days().length > 0">
                                    <option></option>
                                    <option data-bind="value: id,text:day"></option></select>
                            </div>

                            
                            <div class="col-sm-2">
                                <select class="form-control" id="mes" name="mes" data-bind="foreach: months, visible: months().length > 0">
                                    <option></option>
                                    <option data-bind="value: id,text:mes"></option></select>
                            </div>

                            
                            <div class="col-sm-2">
                                <select class="form-control" id="anio" name="anio" data-bind="foreach: years, visible: years().length > 0">
                                    <option></option>
                                    <option data-bind="value: year,text:year"></option></select>
                            </div>


                            
                            <div class="col-sm-2">
                                <select class="form-control" id="actividad" name="actividad" data-bind="foreach: activities, visible: activities().length > 0">
                                    <option></option>
                                    <option data-bind="value: activity,text:activity"></option></select>
                            </div>
                        </div>
                        </div>


                        <div class="form-group">
                            <label class="text control-label col-sm-2" style="color:black;"></label>
                            <div class="col-sm-2">
                                <div class="radio radio-info radio-inline">
                                    <input type="radio" id="masculino" name="sexo" value="M">
                                    <label class="text" style="color:black;"> Masculino </label>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <div class="radio radio-info radio-inline">
                                    <input type="radio" id="femenino" name="sexo" value="F">
                                    <label class="text" style="color:black;"> Femenino </label>
                                </div>
                            </div>

                            <label class="text control-label col-sm-2" style="color:black;">Deseo recibir notificaciones:</label>
                            <div class="col-sm-1">
                                <div class="checkbox checkbox-primary">
                                    <input type="checkbox" value="SI" name="notificar" id="notificar">
                                    <label class="text" style="color:black;">
                                        Sí
                                    </label>
                                </div>
                            </div>

                            <label class="control-label col-sm-2" style="color:black;"><a href="#" class="text" data-toggle="modal" data-target="#modalCondiciones">Acepto T&eacute;rminos y Condiciones</a></label>
                            <div class="col-sm-1">
                                <div class="checkbox checkbox-primary">
                                    <input type="checkbox" value="SI" name="condiciones" id="condiciones">
                                    <label class="text" style="color:black;">
                                        
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
                            <button id="limpiar" class="text btn btn-default"><span class=""></span> LIMPIAR</button>
                            <button id="registrar" class="text btn btn-primary"><span class=""></span> CREAR CUENTA</button>
                        </div>
                    </div>

                    <div class="container">
                        <div class="modal fade" id="modalCondiciones" role="dialog">
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
                                                            <p>Febrero 15 de 2015.</p>
                                                            <br>
                                                            <p>
                                                                <a class="text" href="#servicio">1. Servicio</a><br>
                                                                <a class="text" href="#responsabilidad">1.1 Limitación de responsabilidad</a><br>
                                                                <a class="text" href="#privacidad">2. Privacidad</a><br>
                                                                <a class="text" href="#cuenta">2.1 Su cuenta</a><br>
                                                                <a class="text" href="#seguimiento">2.2 Seguimiento</a><br>
                                                                <a class="text" href="#contenido">3. Contenido</a><br>
                                                                <a class="text" href="#disponibilidad">3.1 Disponibilidad</a><br>
                                                                <a class="text" href="#reglas">3.2 Reglas de uso</a><br>
                                                                <a class="text" href="#propiedad">3.3 Propiedad intelectual</a><br>
                                                                <a class="text" href="#desarrollador">4. Sobre el desarrollador</a><br>
                                                                <br>
                                                                <br>
                                                            </p><h4 id="servicio" class="text">1. El servicio</h4>
                                                            <br>
                                                            <p class="text" style="text-align: justify">
                                                                Quivira es una editorial que le permite comprar y descargar contenido 
                                                                digital de su colección Novohispanorum ("Publicaciones"), a través de 
                                                                Chrome Store, para uso personal exclusivamente bajo los términos y las 
                                                                condiciones establecidas en este Contrato.
                                                                <br>
                                                                Lo que usted descarga como "publicaciones" son transcripciones y, en su 
                                                                mayoría, reproducciones digitales de documentos que incluyen, según el 
                                                                programa editorial y las gestiones de derechos de reproducción de 
                                                                imágenes, lo siguiente:
                                                                <br> 
                                                                (i) Un librero virtual de forma gratuita para la organización de las 
                                                                "publicaciones" de acuerdo con la temática a la que pertenezcan.
                                                                <br>
                                                                (ii) "Publicaciones" que contendrán la transcripción de un documento de 
                                                                archivo o biblioteca, una reproducción digital del documento original 
                                                                del cual se hizo la transcripción, un índice onomástico y uno 
                                                                toponímico, notas explicativas que hacen referencias a términos o 
                                                                arcaísmos del español. La inclusión de estos últimos en las 
                                                                "publicaciones" está sujeta al contenido mismo del documento, por lo que
                                                                no se asegura que todas las "publicaciones" tengan uno propio.  
                                                                <br> 
                                                                (iii) La mayoría de las "publicaciones" requieren de un pago para su 
                                                                descarga y uso; algunas podrán descargarse de manera gratuita. El uso de
                                                                ambas queda sujeto a las reglas de uso y derechos de la propiedad 
                                                                intelectual de estos términos y condiciones.
                                                                <br>
                                                                <br>
                                                            </p>
                                                            <h4 id="responsabilidad" class="text">1.1 Limitación de responsabilidad</h4>
                                                            <br>
                                                            <p style="text-align: justify" class="text"> 
                                                                Quivira no garantiza ni declara que el uso del servicio estará libre de 
                                                                error y sea ininterrumpido, lo cual depende de su conexión a internet, 
                                                                de la capacidad de su computadora y de los conocimientos que usted tenga
                                                                sobre el uso de la misma. Quivira tampoco asegura que las 
                                                                "publicaciones" estén libres de pérdida, corrupción, ataque o 
                                                                intervención de hackers, virus o intrusión de seguridad, por lo que 
                                                                Quivira renuncia a cualquier responsabilidad relacionada con las 
                                                                mencionadas anteriormente.
                                                                <br>
                                                                En ningún caso Quivira y cualquiera de sus gerentes, empleados o 
                                                                distribuidor serán responsables por cualquier daño indirecto, incidental
                                                                o emergente derivado del uso que usted haga de las "publicaciones". De 
                                                                la misma forma, Quivira renuncia a cualquier responsabilidad sobre 
                                                                cualquier error u omisión en el contenido de los documentos, los cuales 
                                                                son reproducidos de forma fiel y publicados bajo estrictos lineamientos 
                                                                editoriales para mantener un alto estándar de calidad en los mismos.
                                                                <br>
                                                                Quivira se deslinda de cualquier uso político, religioso, social, 
                                                                educativo, académico o de cualquier otro tipo que el "usuario" haga del 
                                                                contenido e interpretación de las "publicaciones". Los contenidos y las 
                                                                temáticas de las "publicaciones" de ninguna manera reflejan alguna 
                                                                postura ideológica, sea política, religiosa, académica o de cualquier 
                                                                otra índole de Quivira ni de alguno de sus gerentes o empleados.
                                                                <br>
                                                                Usted acepta expresamente el uso de todas las "publicaciones" tal y como
                                                                se presentan, sean gratuitas o de pago, de acuerdo con su 
                                                                disponibilidad, diseño, funcionalidades y contenido, sin garantías de 
                                                                ninguna clase. Cualquier falla en las publicaciones será mejorada y 
                                                                reparada de acuerdo al plan editorial de actualización constante de las 
                                                                "publicaciones" de Quivira.
                                                                <br>
                                                                Usted libera a Quivira y cualquiera de sus gerentes, empleados y 
                                                                distribuidor por el mal uso de los contenidos y materiales que contengan
                                                                las "publicaciones", lo que derive en infracciones de la propiedad 
                                                                intelectual, derechos de reproducción del contenido y derechos de autor,
                                                                de acuerdo a las leyes vigentes del país.
                                                                <br>
                                                                Quivira tiene autorización de reproducción y publicación de las imágenes
                                                                digitales de los documentos en las "publicaciones", si usted hace mal 
                                                                uso o reproduce alguna de las imágenes, libera a Quivira de cualquier 
                                                                responsabilidad por su reproducción no autorizada y mal uso. Usted 
                                                                acepta, en la medida máxima permitida por la ley, defender a Quivira, 
                                                                sus gerentes, cualquiera de sus empleados, distribuidor e inclusive 
                                                                terceros de cualquier tipo de reclamo, demanda, procedimiento, pérdidas,
                                                                responsabilidades, daños, costos y gastos que devengan y estén 
                                                                relacionados del mal uso que el "usuario" haga del material.
                                                                <br>
                                                                <br>
                                                            </p>
                                                            <h4 id="privacidad" class="text">2. Privacidad</h4>
                                                            <br>
                                                            <h4 id="cuenta" class="text">2.1 Su cuenta</h4>
                                                            <br>
                                                            <p style="text-align: justify" class="text">
                                                                La compra, el uso y acceso a las "publicaciones" es a través de Google 
                                                                Chrome Store y está relacionado con una cuenta de Google, por lo que 
                                                                usted necesita una cuenta activa para poder comprar las "publicaciones".
                                                                No revele la información de su cuenta a nadie. Usted es el único 
                                                                responsable de mantener la información de su cuenta de manera 
                                                                confidencial y segura, así como de todas las acciones y actividades 
                                                                realizadas a través de ella; usted acepta informar inmediatamente a los 
                                                                administradores de ella sobre cualquier anormalidad o fallo en la 
                                                                seguridad de su cuenta, lo que está regulado por los términos y 
                                                                condiciones de las cuentas Google. Cualquier mal uso que surja de las 
                                                                "publicaciones" a través de su cuenta será única y exclusivamente su 
                                                                responsabilidad.
                                                                <br>
                                                                Queda bajo su responsabilidad proporcionar información verídica en su 
                                                                cuenta Google y actualizarla constantemente, mantener datos precisos y 
                                                                exactos facilitará hacer los cargos y recibos de compra de las 
                                                                "publicaciones" a su cuenta, lo que queda a cargo del distribuidor.
                                                                <br> 
                                                                Quivira, sus gerentes o empleados no están autorizados a recibir pagos, 
                                                                en dinero o en especie ni de ninguna otra forma por el acceso a las 
                                                                "publicaciones" que difiera de las establecidas por Chrome Store y sus 
                                                                sistemas de cobro.
                                                                <br>
                                                                <br> 
                                                            </p> 
                                                            <h4 id="seguimiento" class="text">2.2 Seguimiento</h4>
                                                            <br>
                                                            <p style="text-align: justify" class="text">
                                                                Las "publicaciones" emplean la herramienta de seguimiento de uso de 
                                                                Google Analytics, la cual  permite medir la forma en que el "usuario" 
                                                                interactúa con el contenido de la aplicación. Google Analytics emplea 
                                                                cookies para "recordar" lo que el "usuario" ha hecho en la aplicación 
                                                                anteriormente, proporcionando etiquetas JavaScript para su registro. 
                                                                NINGUNA INFORMACIÓN PERSONAL, REFERENCIAL, DESCRIPTIVA, PUBLICITARIA, DE
                                                                UBICACIÓN O IDENTIDAD del "usuario" es recopilada por esta herramienta.
                                                                <br> 
                                                                La herramienta proporciona datos estadísticos del uso de la aplicación y
                                                                su interacción con el "usuario", para mejorar la calidad, contenido, 
                                                                disposición, funcionalidad y experiencia que el "usuario" tiene con la 
                                                                aplicación.
                                                                <br> 
                                                                Para saber sobre esta herramienta puede consultar el sitio de Google Analytics: <a class="text" href="http://www.google.com.mx/intl/es_ALL/analytics/index.html" target="_blank">http://www.google.com.mx/intl/es_ALL/analytics/index.html</a>
                                                                <br>
                                                            </p> 
                                                            <h4 id="contenido" class="text">3. Contenido</h4>
                                                            <br>
                                                            <p style="text-align: justify" class="text">
                                                                Quivira se compromete a publicar contenido fiable, verídico, con 
                                                                contenidos de alta calidad editorial que constituyan una referencia, 
                                                                ayuda y soporte al desarrollo de actividades culturales, sociales y 
                                                                académicas del "usuario" en las "publicaciones". Así mismo, Quivira se 
                                                                compromete también a mantener las "publicaciones" libres de materiales 
                                                                textuales o imágenes de terceros que puedan ser considerados como 
                                                                publicidad.
                                                                <br> 
                                                                La actualización de las "publicaciones", tanto en el software como en 
                                                                los contenidos será constante, lo que constituirá una mejora en la 
                                                                funcionalidad y en la experiencia del "usuario". Cualquier actualización
                                                                en el contenido o software será notificada al "usuario" para que sea 
                                                                descargada.
                                                                <br> 
                                                            </p>
                                                            <h4 id="disponibilidad" class="text">3.1 Disponibilidad del contenido</h4>
                                                            <br>
                                                            <p style="text-align: justify" class="text">
                                                                Quivira se reserva el derecho de cambiar el diseño, las opciones de 
                                                                contenido, las funcionalidades de las "publicaciones" durante sus 
                                                                actualizaciones sin previo aviso, de acuerdo con su programa editorial y
                                                                mejoramiento de dichas "publicaciones". Estos cambios no afectarán el 
                                                                acceso a las "publicaciones" que el "usuario" ha descargado, por lo cual
                                                                no se verá obligado a hacer  un nuevo cargo una vez que haya cumplido 
                                                                con los requisitos de descarga, pues sólo modificará el aspecto y la 
                                                                funcionalidad en cantidad mínima.
                                                                <br>
                                                            </p>
                                                            <h4 id="reglas" class="text">3.2 Reglas de uso</h4>
                                                            <br>
                                                            <p style="text-align: justify" class="text">
                                                                Usted acepta que el uso de las "publicaciones" están determinadas por 
                                                                las Reglas de Uso que establezca Quivira de acuerdo con las leyes en 
                                                                materia de Derechos de Autor y Propiedad Intelectual vigentes. Cualquier
                                                                otro uso puede constituir una violación de los derechos de autor. 
                                                                Quivira se reserva el derecho de modificar las reglas de uso en 
                                                                cualquier momento, dando aviso al "usuario" cuando esto ocurra.
                                                                <br> 
                                                                Usted acepta no violar, eludir, realizar ingeniería inversa, 
                                                                decodificar, desmontar, alterar con cualquier tipo de tecnología la 
                                                                seguridad y la integridad de las "publicaciones" por ninguna razón, ni 
                                                                ayudar o asesorar a ninguna persona para hacerlo. Usted acepta no 
                                                                acceder a las "publicaciones" por otro medio que no sea la aplicación 
                                                                alojada en Chrome Store. Usted acepta no modificar el software, la 
                                                                imagen visual y gráfica, la interfaz y ninguno de los contenidos para 
                                                                ningún propósito.
                                                                <br>
                                                                Quivira puede controlar, supervisar e involucrarse en el cumplimiento de
                                                                las Reglas de Uso y se reserva el derecho de hacerlas cumplir sin 
                                                                notificarlo previamente. Quivira se reserva, a su criterio, negar o 
                                                                limitar el uso de las "publicaciones" al "usuario" si no cumple y viola 
                                                                las Reglas de Uso, sin que haya responsabilidad alguna frente al mismo 
                                                                ni haya reembolso alguno por la compra anterior de cualquiera de las 
                                                                "publicaciones".
                                                                <br> 
                                                                Cualquier reproducción del contenido, imagen visual y gráfica, la 
                                                                interfaz, logotipos, nombres, imágenes de los documentos, etcétera, 
                                                                deberá constar por escrito, con firma adjunta del representante legal de
                                                                Quivira.
                                                                <br> 
                                                                (i) Usted acepta utilizar las "publicaciones" sólo para uso personal.
                                                                <br> 
                                                                (ii) La compra de las "publicaciones" no constituye una cesión, renuncia
                                                                o limitación de cualquiera de los derechos de autor del propietario.
                                                                <br> 
                                                                (iii) Usted no estará autorizado a utilizar de forma comercial cualquier
                                                                contenido de las "publicaciones", íntegra o parcialmente, como las 
                                                                transcripciones, los índices, las notas explicativas, la imagen visual y
                                                                gráfica, la interfaz, los logotipos, los nombres de la colección o 
                                                                Quivira y las imágenes de los documentos.
                                                                <br> 
                                                                (iv) La descarga y el pago por cualquiera de las "publicaciones" no lo 
                                                                autoriza a usted a reproducir y distribuir de cualquier forma los 
                                                                contenidos de las "publicaciones" ni sus contenidos íntegra o 
                                                                parcialmente, como las transcripciones, los índices, las notas 
                                                                explicativas, la imagen visual y gráfica, la interfaz, los logotipos, 
                                                                los nombres de la colección o Quivira y las imágenes de los documentos.
                                                                <br> 
                                                                (v) Usted podrá consultar las "publicaciones" en cualquier equipo con su
                                                                cuenta Google a través de Chrome Store. Cualquier mal uso de las 
                                                                "publicaciones" a través de su cuenta son única y exclusivamente su 
                                                                responsabilidad. Ver 2.1 SU CUENTA.
                                                                <br> 
                                                                (vi) La compra y descarga de las "publicaciones" no es transferible a otro "usuario".
                                                                <br> 
                                                                (vii) La reproducción no autorizada y el mal uso de las imágenes de los 
                                                                documentos antiguos que se incluyen en las "publicaciones" constituyen 
                                                                una infracción de acuerdo con las leyes. Quivira no está facultada para 
                                                                autorizar ni su reproducción ni su uso.
                                                                <br> 
                                                                Las violaciones del sistema, la seguridad, su modificación, alteración 
                                                                en cualquier forma, así como la reproducción íntegra o parcial, 
                                                                distribución, comercialización de las transcripciones, los índices, las 
                                                                notas explicativas, la imagen visual y gráfica, la interfaz, los 
                                                                logotipos, los nombres de la colección o Quivira y las imágenes de los 
                                                                documentos y la violación de alguna de estas Reglas de Uso  pueden 
                                                                resultar en responsabilidad civil o penal en contra del "usuario".
                                                                <br>
                                                            </p> 
                                                            <h4 id="propiedad" class="text">3.3 Propiedad intelectual</h4>
                                                            <br>
                                                            <p style="text-align: justify" class="text">
                                                                Usted acepta que las "publicaciones" y todo su contenido editorial, 
                                                                gráfico, visual, transcripciones, índices, notas explicativas, interfaz,
                                                                logotipos, nombres de la colección o Quivira utilizados en las 
                                                                "publicaciones" es propiedad exclusiva de Quivira, que está protegido 
                                                                por las leyes aplicables de propiedad intelectual y derechos de autor 
                                                                vigentes. Usted acepta que no utilizará este contenido o material de 
                                                                ninguna otra forma más que de la permitida por este contrato. Ninguna 
                                                                parte del contenido fragmentada o íntegra podrá reproducirse por ningún 
                                                                medio impreso o digital con excepción de la previa autorización, de 
                                                                acuerdo con las Reglas de Uso de este contrato. Usted acepta no 
                                                                modificar, rentar, arrendar, vender, distribuir, comerciar de ninguna 
                                                                forma posible, ni crear trabajos derivados en las "publicaciones" de 
                                                                cualquier manera  no autorizada, sea enunciativa pero no limitativa 
                                                                mediante cualquier medio electrónico o impreso.
                                                                <br>
                                                                Quivira se reserva el derecho de suspender o desactivar el servicio de 
                                                                las "publicaciones" al "usuario" cuando determine que se ha hecho mal 
                                                                uso o se han violado las especificaciones de las reglas de Uso de este 
                                                                contrato, sin que haya, en cualquier caso, responsabilidad alguna.
                                                                <br> 
                                                                Quivira, Novohispanorum, sus logos, imagen visual y gráfica son marcas 
                                                                comerciales registradas, propiedad de Ediciones Quivira, S.A. de C.V. 
                                                                Otras marcas, servicios, gráficos, interfaz y logotipos, por la cual se 
                                                                distribuyen las "publicaciones" son marcas comerciales de sus 
                                                                respectivos dueños. No se le cede a usted ningún derecho o licencia de 
                                                                uso de ningún tipo con respecto a ninguna de las marcas comerciales 
                                                                mencionadas.
                                                                <br>
                                                            </p> 
                                                            <h4 id="desarrollador" class="text">4. Sobre el desarrollador</h4>
                                                            <br>
                                                            <p style="text-align: justify" class="text"> 
                                                                Quivira es una editorial constituida conforme las leyes mexicanas, la 
                                                                cual tiene como objeto la publicación y distribución de publicaciones a 
                                                                través de medios electrónicos e impresos sobre diversos temas.
                                                                <br> 
                                                                Si desea descargar una versión impresa de los términos y condiciones puede acceder a la página:  <a class="text" href="http://novohispanorum.mx/Novohispanorum/termsnovohipanorum.pdf">Términos y condiciones</a>
                                                                <br>
                                                                <br>
                                                                También puede escribir a la dirección quivira@edicionesquivira.com para cualquier aclaración o comentario.
                                                                <br> 
                                                                <br>
                                                                Contacto:<br> 
                                                                Alí Albarrán<br> 
                                                                ali@edicionesquivira.com<br> 
                                                                Ediciones Quivira, S.A. de C.V.<br> 
                                                                Excelsior 239, colonia Guadalupe Insurgentes<br> 
                                                                C. P. 07870<br> 
                                                                México, D. F. <br> 
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
                    </div>
                </div>
            </div>    
        </div>


    </body>
</html>
