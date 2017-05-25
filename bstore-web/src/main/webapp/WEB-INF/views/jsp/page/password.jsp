<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
    <head>
        <title>Novohispanorum</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <script type="text/javascript">
            $(function () {
                var status;
                $('input#email').focus();
                $('button#enviar').click(function () {

                    var email = $('input#email');
                    var confEmail = $('input#confEmail');
                    if (email.val() === "") {
                        muestraMsjSistemaError("<span class='text'>El email es requerido.</span>");
                        return false;
                    } else if (confEmail.val() === "") {
                        muestraMsjSistemaError("<span class='text'>El email es requerido.</span>");
                        return false;
                    } else if (email.val() !== confEmail.val()) {
                        muestraMsjSistemaError("<span class='text'>El email no coincide.</span>");
                        return false;
                    }

                    $.blockUI();
                    $.ajax({
                        type: 'POST',
                        url: '${contextpath}' + '/password/enviar/email',
                        data: $('form#recuperar').serialize(),
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
                    $('form#recuperar')[0].reset();
                });

                function muestraMsjSistemaError(msjStatus) {
                    BootstrapDialog.show({
                        size: BootstrapDialog.SIZE_SMALL,
                        title: 'Mensaje del Sistema:',
                        closable: false,
                        message: msjStatus,
                        type: BootstrapDialog.TYPE_DANGER,
                        cssClass: 'login-dialog',
                        buttons: [{
                                icon: 'glyphicon glyphicon-check',
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
                        title: 'Mensaje del Sistema:',
                        closable: false,
                        message: msjStatus,
                        type: BootstrapDialog.TYPE_SUCCESS,
                        cssClass: 'login-dialog',
                        buttons: [{
                                icon: 'glyphicon glyphicon-check',
                                label: "<span class='text'>CONTINUAR</span>",
                                cssClass: 'btn-primary',
                                action: function (dialog) {
                                    dialog.close();
                                    $.blockUI();
                                    var urlAction = '${contextpath}/';
                                    document.getElementById('regresar').action = urlAction;
                                    document.getElementById('regresar').method = 'GET';
                                    document.getElementById('regresar').submit();
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
                <div class="col-sm-12 col-sm-offset-0 col-md-3 col-md-offset-4 main">
                    </br>
                    </br>
                    </br>
                    <form id="recuperar" class="form-horizontal">
                        <div class="form-group">
                            <h5 class="control-label col-sm-12" style="text-align: center;color:black;"><b class="text">RECUPERAR CUENTA</b></h5>
                        </div>
                        </br>
                        </br>

                        <div class="form-group">
                            <label class="text control-label col-sm-4" style="color:black;" for="nombre">Email:</label>
                            <div class="col-sm-8">
                                <input type="text" class="text form-control" id="email" name="email" placeholder="Ingresa tu email">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="text control-label col-sm-4" style="color:black;" for="email">Confirmar Email:</label>
                            <div class="col-sm-8">
                                <input type="text" class="text form-control" id="confEmail" name="confEmail" placeholder="Confirma tu email">
                            </div>
                        </div>
                    </form>
                    <form id="regresar"></form>
                    <div class="row">
                        <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                            <button id="limpiar" class="text btn btn-default"><span class="glyphicon glyphicon-remove"></span> LIMPIAR</button>
                            <button id="enviar" class="text btn btn-primary"><span class="glyphicon glyphicon-ok"></span> ENVIAR</button>
                        </div>
                    </div>
                </div>
            </div>    
        </div>
    </body>
</html>