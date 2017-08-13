<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		
		$('body').bind('cut copy paste',function (e) {
			var navType = $("body").attr("data-browsername");
			if (navType==="MSIE"){
				window.clipboardData.setData("Text","${copyright}");
			}else{
				e.originalEvent.clipboardData.setData("Text","${copyright}");
			}
			e.preventDefault();
			return false;
		});
		$("body").on("contextmenu",function(e){
			return false;
		});

		
		
		<c:forEach items="${anexos}" var="anexo" varStatus="loop">
		$("#pub${anexo.id}").elevateZoom({
                          //easing : true,
			  //tint:true, 
                          //tintColour:'#F90', 
                          //tintOpacity:0.5,
                          zoomWindowPosition: 11,
                          zoomWindowFadeIn: 500,
                          zoomWindowFadeOut: 500
			  //lensFadeIn: 500,
			  //lensFadeOut: 500
                          //scrollZoom : true
                          //zoomWindowWidth:700,
                          //zoomWindowHeight:600
			});    
    	</c:forEach>

        var listaPublicacion = $('#listaPublicacion')
        .dataTable(
            {
            "aoColumns": [ {"bSearchable": true,"bSortable": false}, {"bSearchable": true,"bSortable": false},{"bSearchable": false,"bSortable": false}],
            "sPaginationType": "full_numbers",
            "bPaginate": true,
            "bLengthChange" : false,
            "pageLength": 1,
            "oLanguage" : {
                  "sProcessing":     "<span class='text'>Procesando...</span>",
                  "sLengthMenu":     "<span class='text'>Mostrar _MENU_ registros</span>",
                  "sZeroRecords":    "<span class='text'>No se encontraron resultados</span>",
                  "sEmptyTable":     "<span class='text'>Ningún dato disponible en esta tabla</span>",
                  "sInfo":           "<span class='text'>Mostrando Foja <b>_START_</b> de _TOTAL_.</span>",
                  "sInfoEmpty":      "<span class='text'>No existe información asociada a tu búsqueda.</span>",
                  "sInfoFiltered":   "",
                  "sInfoPostFix":    "",
                  "sSearch":         "<span class='glyphicon glyphicon-search'></span>",
                  "sUrl":            "",
                  "sInfoThousands":  ",",
                  "sLoadingRecords": "<span class='text'>Cargando...</span>",
                  "oPaginate": {
                      "sFirst":    "<span class='text'>Primero</span>",
                      "sLast":     "<span class='text'>Último</span>",
                      "sNext":     "<span class='text'>Siguiente</span>",
                      "sPrevious": "<span class='text'>Anterior</span>"
                  },
                  "oAria": {
                      "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                      "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                  }
            }
            });
	});
</script>
<style type="text/css">
.estilo{
	font-family: GaramondPremrPro!important;
    font-size: 16px!important;
    text-align:justify;
    word-wrap: break-word;
}
.cabecera{
	left: 0px;
	right: 0px;
    background-repeat: round;
}
</style>
</head>
<body>
    <div class="row" style="padding: 0.5em;">
    	<div class="cabecera" style="text-align: center;height: 7em;border-radius: 15px;background-image: url('${contextpath}/static/resources/img/cabecera_publicacion.png');">
  		<label class="alert alert-primary alert-info" style="position: absolute; z-index: 1;right:30px;top:140px"><b class="text"><c:out value="${nombrePublicacion}"/></b></label>
  		</div>
  		<br/>  
  		  <div>
          <div class="table-responsive">
            <table class="table table-hover" id="listaPublicacion">
              <thead>
                <tr>
                  <th style="text-align: center" class="text alert alert-dismissible">N° FOJA</th>
                  <th style="text-align: center" class="alert alert-dismissible"><span class="glyphicon glyphicon-list"> <b class="text">TRADUCCIÓN</b></span></th>
                  <th style="text-align: center" class="alert alert-dismissible"><span class="glyphicon glyphicon-camera"> <b class="text">ANEXO</b></span></th>
                </tr>
              </thead>
              <tbody>
               <c:forEach var="anexo" items="${anexos}">
               <tr style="background-repeat: no-repeat;" background="${contextpath}/static/resources/img/fondo_publicacion.png">
	                <td style="text-align: center;width: 10%;">
	                	<b class="text">
	                		<c:out value="${anexo.fojaTxt}"/>
	                	</b>
	                </td>
	                <td style="width: 40%;">
	                	  <div class="estilo">
	                	  	<c:out value="${anexo.traduccion.getCharacterStream()}" escapeXml="false"/>
				          </div>
	                </td>
	                <td style="text-align: center;width: 50%;">
	                	<img style="border-radius:10px;" width="65%" height="auto" id="pub${anexo.id}" src="data:image/${anexo.tipoImagen};base64,${anexo.resultImage}" data-zoom-image="data:image/${anexo.tipoImagen};base64,${anexo.resultImageZoom}"/>
	                </td>
                </tr> 
                </c:forEach>  
              </tbody>
            </table>
          </div>
    	</div>
	</div>
</body>
</html>