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
                            title: "<span class='text'>Mensaje de Novohispano</span>",
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
                            title: "<span class='text'>Mensaje de Novohispano</span>",
                            closable: false,
                            message: msjStatus,
                            type: BootstrapDialog.TYPE_SUCCESS,
                            cssClass: 'login-dialog',
                            buttons: [{
                                    icon: 'glyphicon glyphicon-ok',
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
    <body style="">
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                </div>
                <div style="height: 1px;" aria-expanded="false" class="navbar-collapse collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a style="color:black;" aria-expanded="true" aria-haspopup="true" role="button" data-toggle="dropdown" class="navbar-brand dropdown-toggle" href="#"></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid" style="margin-top: 30px;">
            <div class="row">
                <div class="col-sm-12 col-sm-offset-0 col-md-2 col-md-offset-5 main">
                    <form id="ingresar" class="form-horizontal" method="POST" action="${contextpath}/equivira">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <div style="text-align:center;color:black"><h5><b class="text"></b></h5><br><img width="150px" height="150px" src="${contextpath}/static/resources/img/favicon.ico">
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
                            	<a class="text" href="${contextpath}/password/recuperar">¿Olvidaste la cuenta?</a>
                            </label>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-sm-12" style="text-align: center;">
                            <button id="acceder" class="text btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ENTRAR&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                    	<div class="col-sm-12" style="text-align: center;">
                        	<button id="registrar" class="text btn btn-default">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CREAR CUENTA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                        </div>
                    </div>
                </div>
            </div>    
        </div>
    </body>
</html>
