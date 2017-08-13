<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
    <head>
        <script type="text/javascript">
            $(document).ready(function () {

                $(".listaPublicaciones").select2({
                    theme: "classic",
                    language: "es",
                    width: '100%'
                });

                $("#listaPub").change(function () {
                    window.location.href = $(this).val();
                });
            });
        </script>
        <style>
            .cabecera{
                left: 0px;
                right: 0px;
                background-repeat: round;
            }
        </style>
    </head>
    <body>
        <div id="globalWrapper" class="localscroll">
            <br/>
            <section class="clearfix bgFullScreen" id="homeApp">
                <div class="container">
                    <div class="">
                        <c:if test="${!empty publicacionesActivas}">
                            <div style="text-align: center;">
                                <select class="listaPublicaciones" id="listaPub">
                                    <option value="buscar" selected>Documentos Novohispanos</option>
                                    <c:forEach var="publicacion" items="${publicacionesActivas}">
                                        <c:choose>
                                            <c:when test="${publicacion.purchased}">
                                                <c:set var="valueUrl" value="${contextpath}/publicacion/${publicacion.id}" />
                                                <option value="${valueUrl}">${publicacion.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="valueUrl" value="${contextpath}/comprar/publicacion/${publicacion.id}" />
                                                <option value="${valueUrl}">${publicacion.name}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>
                </div>
            </section>
            
            <section class="clearfix bgFullScreen" id="homeApp">
                <div class="container">
                    <div class="cabecera" style="margin-top:15px ;text-align: center;height: 16em;border-radius: 15px;background-image: url('${contextpath}/static/resources/img/head_novohispanorum.jpg');">
                    </div>
                </div>
            </section>
            <br/>
            <section class="noPaddingBottom" id="editorial">
                <div class="container">
                    <div class="">
                        <div class="span12">
                            <span class="label label-default"><b class="text">Editorial</b></span>
                        </div>
                        <div class="span6" style="text-align: justify">
                            <h2></h2>
                            <p class="text">Ediciones Quivira surge con el propósito de crear una colección digital de documentos novohispanos inéditos de la Inquisición del Virreinato de la Nueva España. Además de publicar algunas obras en diversos temas y géneros como literatura, filosofía, historia, vocabularios, diccionarios y manuales de consulta.</p>
                            <p class="text"> En la actualidad, nuestra finalidad es encontrar la mejor manera de presentar un texto en pantalla, de manera que sea funcionales y sea posible aprovechar todas las ventajas que pueden brindar las herramientas digitales para su lectura, sin por ello alejarnos de la estructura y organización de las publicaciones impresas, considerando siempre las necesidades de nuestros lectores.</p>
                            <br/><br/>
                        </div>
                    </div>
                </div>
            </section>
            <section class="noPaddingBottom">
                <div class="container">
                    <div class="">
                        <div class="span6 clearfix" style="text-align: justify">
                            <span class="label label-default"><b class="text">En busca de la edición perfecta</b></span><br/>
                            <div class="one_half">
                                <h3 class="text">Ediciones flexibles</h3>
                                <p class="text"> Nuestras publicaciones están planeadas para adaptarse a diferentes formatos y plataformas de equipos móviles y tabletas con sistema operativo Android o iOS. Para aquellos lectores que prefieran las ediciones impresas, en ciertos casos será posible adquirir algunos de éstos títulos en ediciones de colección. </p>
                            </div>
                            <div class="one_half last">
                                <h3 class="text">Contenidos propios</h3>
                                <p class="text">En la mayoría de los casos, generamos nuestros propios contenidos, en los cuales intervienen colaboradores y especialistas que enriquecen las colecciones de nuestro acervo.</p>
                            </div>
                        </div>
                    </div>
                    </br>
                    <div style="float: right;">
                        <p style="float: left;padding: 5px" class="text"><b>Vis&iacute;tanos en: </b></p>
                        <a href="https://www.facebook.com/edicionesquivira?ref=hl" target="_blank"><img style="width: 30px;height: 30px;float: right;" src="${contextpath}/static/resources/images/facebook.png"></a>
                        <a href="https://twitter.com/equivira" target="_blank"><img style="width: 30px;height: 30px;float: right;" src="${contextpath}/static/resources/images/twitter.png"></a>
                        <a href="https://plus.google.com/+Edicionesquivira666/videos" target="_blank"><img style="width: 30px;height: 30px;float: right;" src="${contextpath}/static/resources/images/googleplus.png"></a>
                        <a href="https://www.pinterest.com/equivira/" target="_blank"><img style="width: 30px;height: 30px;float: left;" src="${contextpath}/static/resources/images/pinterest.png"></a>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>