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
                $('button#continuar').click(function () {
                    var urlAction = '${contextpath}';
                    document.location.href = urlAction;
                });
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
                <div class="col-sm-12 col-sm-offset-0 col-md-8 col-md-offset-2 main">
                    </br>
                    </br>
                    </br>
                    <div class="row">
                        <c:choose>
                            <c:when test="${baja}">
                                <div class="row alert alert-success">
                                    <div style="text-align: center;">
                                        <p>Hemos dado de baja correctamente tu cuenta: <b>${email}</b>, esperamos que vuelvas pronto.</p>
                                        <p><button id="continuar" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> CONTINUAR</button></p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row alert alert-danger">
                                    <div style="text-align: center;">
                                        <p>No hemos podido desactivar tu cuenta correctamente, te invitamos a ponerte en contacto con nuestra &aacute;rea de soporte
                                            a <b>${emailApp}</b></p>
                                        <p><button id="continuar" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> CONTINUAR</button></p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>      
    </body>
</html>