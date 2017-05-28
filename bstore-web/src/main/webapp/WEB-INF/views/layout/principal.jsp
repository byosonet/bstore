<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<c:set var="urlhref"><spring:message code="url.menu.href"/></c:set>
    <html>
        <head>
            <title>Novohispanorum</title>
            <meta name="author" content="Ediciones Quivira : Anneli Daniela Torres, Diseño ; Pudenciano Espinobarros Peralta, Sistemas ; Arturo Sauza, Programación; Alí Albarrán, edición; Jesús Alfaro, Contenidos y paleografía">
            <meta name="description" content=" Novohispanorum tiene como objetivo crear publicaciones digitales de documentos novohispanos inéditos del siglo XVI, XVII y XVIII del Oficio de la Santa Inquisición del Virreinato de la Nueva España.">
            <meta name="keywords" content="virreinato de méxico, reino de portugal, tribunal santa inquisición, historia de méxico, juicio inquisitorial, estudios novohispanos">
            <meta name="geo.placename" content="Mexico" />
            <meta name="geo.position" content="x;x" />
            <meta name="geo.region" content="MX" />
            <meta name="ICBM" content="x,x" />
            <link rel="alternate" hreflang="es-mx" href="www.novohispanorum.com">
            <meta name="googlebot" content="..., ..." />
            <link rel="schema.DC" href="http://purl.org/dc/elements/1.1/">
            <meta name="DC.Title" content="Novohispanorum">
            <meta name="DC.Creator" content="Anneli Daniela Torres, Diseño ; Pudenciano Espinobarros Peralta, Sistemas ; Arturo Sauza, Programación; Alí Albarrán, edición; Jesús Alfaro, Contenidos y paleografía ">
            <meta name="DC.Subject" content="Librero Novohispanorum">
            <meta name="DC.Description" content="Novohispanorum tiene como objetivo crear publicaciones digitales de documentos novohispanos inéditos del siglo XVI y XVII del Oficio de la Santa Inquisición del Virreinato de la Nueva España. ">
            <meta name="DC.Publisher" content="Ediciones Quivira, S.A. de C.V.">
            <meta name="DC.Contributor" content=" Anneli Daniela Torres, Diseño ; Pudenciano Espinobarros Peralta, Sistemas ; Gustavo Ulises Trejo Armenta, Programación; Arturo Sauza, Programación; Alí Albarrán, edición; Jesús Alfaro, Contenidos y paleografía ">
            <meta name="DC.Date" content="2017-05-15">
            <meta name="DC.Type" content="txt">
            <meta name="DC.Type" content="image">
            <meta name="DC.Format" content="txt/html">
            <link rel="DC.Identifier" href="http://www.novohispanorum.mx/index.html">
            <meta name="DC.Source" content="Novohispanorum">
            <meta name="DC.Language" content="ES">
            <meta name="DC.Relation" content="Digital editions">
            <meta name="DC.Coverage" content="México">
            <meta name="DC.Coverage" content="Nueva España">
            <meta name="DC.Rights" content="Copyright 2010-2017, Ediciones Quivira, S.A. de C.V. All rights reserved.">
        <jsp:include page="staticResources.jsp"></jsp:include>
            <script type="text/javascript">
                
                $('ul.nav.navbar-nav').hide();
                $('#wrapper').css('padding-left', '0px');
                $('a#showMenu').show();

                $(document).ready(function () {

                    $('ul.nav.navbar-nav').hide();
                    $('#wrapper').css('padding-left', '0px');
                    $('a#showMenu').show();

                    //$('a#showMenu').hide();
                    $('a#exit').click(function () {
                        $.ajax({
                            type: 'POST',
                            url: '${contextpath}' + '/sistema/salir',
                            data: $('form#regresar').serialize(),
                            success: function (data) {
                                muestraMsjSistemaSuccessIndex("<span class='text'>Gracias por tu visita "+"<b>${sessionScope.usuario.nombre}</b>"+", vuelve pronto.</span>");
                            }
                        });
                    });

                    $('a#showMenu').click(function () {
                        $('ul.nav.navbar-nav').show();
                        $('#wrapper').css('padding-left', '');
                        $('a#showMenu').hide();
                    });

                    function muestraMsjSistemaSuccessIndex(msjStatus) {
                        BootstrapDialog.show({
                            size: BootstrapDialog.SIZE_SMALL,
                            title: "<span class='text'>Mensaje de Novohispano</span>",
                            closable: false,
                            message: msjStatus,
                            type: BootstrapDialog.TYPE_SUCCESS,
                            cssClass: 'login-dialog',
                            buttons: [{
                                    icon: 'glyphicon glyphicon-ok',
                                    label: "<span class='text'>ACEPTAR</span>",
                                    cssClass: 'btn-primary',
                                    action: function (dialog) {
                                        dialog.close();
                                        $.blockUI();
                                        var urlAction = '${urlhref}';
                                        document.getElementById('index').action = urlAction;
                                        document.getElementById('index').method = 'GET';
                                        document.getElementById('index').submit();
                                    }
                                }]
                        });
                    }
                }
                );
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
        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <a id="showMenu" class="navbar-brand" href="#" style="color: white;"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> Men&uacute;</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <li class="dropdown">
                        <a class="text" href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;"><span class="text">Hola </span> <i class="fa fa-user"></i> <c:out value="${sessionScope.usuario.nombre}"/> <c:out value="${sessionScope.usuario.APaterno}"/> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a class="text" href="${contextpath}/perfil"><i class="fa fa-fw fa-user"></i> Mi perfil</a>
                            </li>
                            <c:if test="${sessionScope.usuario.perfil.nombre == 'LECTOR'}">
                                <c:if test="${not empty ultimasCompras}">
                                    <li>
                                        <a class="text" href="${contextpath}/historial/compras"><i class="fa fa-fw fa-shopping-cart"></i> Mi historial</a>
                                    </li>
                                </c:if>
                            </c:if>
                            <li class="divider"></li>
                            <li>
                                <a class="text" href="#" id="exit"><i class="fa fa-fw fa-power-off"></i> Salir</a>
                            </li>
                        </ul>
                    </li>

                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li class="active">
                            <a class="text" href="${contextpath}/equivira"><i class="fa fa-fw fa-home"></i> Inicio</a>
                        </li>
                        <li class="active">
                            <a class="text" href="${contextpath}/colecciones"><i class="fa fa-fw fa-star"></i> Colecciones</a>
                        </li>
                        <c:forEach var="entry" items="${menu}">
                            <li class="active">
                                <a class="text" href="#" data-toggle="collapse" data-target="#${entry.idCol}"><i class="fa fa-fw fa-floppy-o"></i> <c:out value="${entry.nombreCol}"/> </a>
                                <ul id="${entry.idCol}" class="">
                                    <c:forEach var="publicacion" items="${entry.pubs}">
                                        <li>
                                            <a class="text" style="color: white;" href="${contextpath}/publicacion/${publicacion.idPub}"><i class="fa fa-fw fa-book"></i> <c:out value="${publicacion.nombrePub}"/></a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>

                        <!-- Menu del Admin -->
                        <c:if test="${sessionScope.usuario.perfil.nombre == 'ADMIN'}">
                            <li class="active">
                                <a class="text" href="#" data-toggle="collapse" data-target="#admin"><i class="fa fa-fw fa-user"></i> Cat&aacute;logos</a>
                                <ul id="admin" class="">
                                    <li>
                                        <a class="text" style="color: white;" href="${contextpath}/coleccionAdmin"><i class="fa fa-fw fa-folder-open"></i> Colecciones</a>
                                    </li>
                                    <li>
                                        <a class="text" style="color: white;" href="${contextpath}/editorial/getAll"><i class="fa fa-fw fa-folder-open"></i> Editoriales</a>
                                    </li>
                                    <li>
                                        <a class="text" style="color: white;" href="${contextpath}/fuente/getAll"><i class="fa fa-fw fa-folder-open"></i> Fuentes</a>
                                    </li>
                                    <li>
                                        <a class="text" style="color: white;" href="${contextpath}/publicacion/getAll"><i class="fa fa-fw fa-folder-open"></i> Publicaciones</a>
                                    </li>
                                </ul>
                            </li>
                        </c:if>

                    </ul>
                </div>
            </nav>
            <div id="page-wrapper">
                <div class="container-fluid wallpaper">
                    <div id="contenido">
                        <tiles:insertAttribute name="contenido" />
                    </div>
                </div>
            </div>
        </div>
        <form id="regresar"><input type="hidden" id="cifrar" name="cifrar" value="${cifrar}"></form>
        <form id="index"></form>
    </body>
</html>
