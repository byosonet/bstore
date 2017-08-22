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
                    width: '50%'
                });

                $("#listaPub").change(function () {
                    window.location.href = $(this).val();
                });
                
                $(document).ready(function () {
                    $("#coleccion").gridalicious({
                        gutter: 25,
                        width: 280,
                        animate: true
                    });
                }
                );
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
                                    <option value="buscar" selected>Buscar documentos</option>
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
            
            <br/>
            
			<div id="coleccion">
			<c:forEach var="coleccion" items="${colecciones}">
			<div class="item">
			    <a href="${contextpath}/coleccion/${coleccion.id}"><img class="zoom" style="border-radius:10px;margin-top:-20px;width: 100%;" src="${coleccion.portadaUrl}"></a>
			<div class="text" style="word-wrap: break-word;text-align: justify;"><c:out value=""/></div>
			<b><a href="${contextpath}/coleccion/${coleccion.id}" class="text btn btn-primary" style="width:100%;padding:5px;text-align:center;margin-bottom:-10px;margin-top:-50px;">
			        <i class=""></i> <c:out value="${empty coleccion.nombreMostrar?coleccion.nombre:coleccion.nombreMostrar}"/>
			        </a></b>
			
			</div> 
			</c:forEach>         
			</div>
           

        </div>
    </body>
</html>