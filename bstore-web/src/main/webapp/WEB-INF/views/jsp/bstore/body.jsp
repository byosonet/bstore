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
                    width: 600
                });

                $("#listaPub").change(function () {
                    window.location.href = $(this).val();
                });
            });
        </script>
    </head>
    <body>
        <div class="row">
            
                <div class="col-md-12" style="position: relative;">
                    <div class="col-md-12" style="text-align: right;"><img style="width: 80%;border-radius:20px;" src="${contextpath}/static/resources/img/app-background.jpg"></div>
                    <div class="col-md-12" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">
                        <c:if test="${!empty publicacionesActivas}">
                            <div style="text-align: center;">
                                <select class="listaPublicaciones" id="listaPub">
                                    <option value="buscar" selected>Buscar..</option>
                                    <c:forEach var="publicacion" items="${publicacionesActivas}">
                                        <c:choose>
                                            <c:when test="${publicacion.comprada}">
                                                <c:set var="valueUrl" value="${contextpath}/publicacion/${publicacion.id}" />
                                                <option value="${valueUrl}">${publicacion.nombre}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="valueUrl" value="${contextpath}/comprar/publicacion/${publicacion.id}" />
                                                <option value="${valueUrl}">${publicacion.nombre}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </div>
                </div>

                </br>
                </br>
                <div class="row col-md-12 alert alert-info" style="text-align: justify;word-wrap: break-word;margin-top:40px;width: 98%;margin-left: 0.5em;">
                <p class="text">Ediciones Quivira surge con el propósito de crear una colección digital de documentos novohispanos inéditos de la Inquisición del Virreinato de la Nueva España. Además de publicar algunas obras en diversos temas y géneros como literatura, filosofía, historia, vocabularios, diccionarios y manuales de consulta.</p>
                <p class="text"> En la actualidad, nuestra finalidad es encontrar la mejor manera de presentar un texto en pantalla, de manera que sea funcionales y sea posible aprovechar todas las ventajas que pueden brindar las herramientas digitales para su lectura, sin por ello alejarnos de la estructura y organización de las publicaciones impresas, considerando siempre las necesidades de nuestros lectores.</p>
                </br>
                <p class="text"> Nuestras publicaciones están planeadas para adaptarse a diferentes formatos y plataformas de equipos móviles y tabletas con sistema operativo Android o iOS. Para aquellos lectores que prefieran las ediciones impresas, en ciertos casos será posible adquirir algunos de éstos títulos en ediciones de colección. </p>
                <p class="text">En la mayoría de los casos, generamos nuestros propios contenidos, en los cuales intervienen colaboradores y especialistas que enriquecen las colecciones de nuestro acervo.</p>
                
                <div style="float: right;">
                    <p style="float: left;padding: 5px">Vis&iacute;tanos en: </p>
                    <a href="https://www.facebook.com/edicionesquivira?ref=hl" target="_blank"><img style="width: 30px;height: 30px;float: right;" src="${contextpath}/static/resources/images/facebook.png"></a>
                    <a href="https://twitter.com/equivira" target="_blank"><img style="width: 30px;height: 30px;float: right;" src="${contextpath}/static/resources/images/twitter.png"></a>
                    <a href="https://plus.google.com/+Edicionesquivira666/videos" target="_blank"><img style="width: 30px;height: 30px;float: right;" src="${contextpath}/static/resources/images/googleplus.png"></a>
                    <a href="https://www.pinterest.com/equivira/" target="_blank"><img style="width: 30px;height: 30px;float: left;" src="${contextpath}/static/resources/images/pinterest.png"></a>
                </div>
                
                </div>
           
        </div>
    </body>
</html>