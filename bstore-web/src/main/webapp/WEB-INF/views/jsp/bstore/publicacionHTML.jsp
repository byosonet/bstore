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

		$('ul.nav.navbar-nav').hide();
		$('#wrapper').css('padding-left','0px');
		$('a#showMenu').show();
		
		<c:forEach items="${anexos}" var="anexo" varStatus="loop">
		$("#pub${anexo.id}").elevateZoom({
			  zoomType	: "lens",
			  lensShape : "round",
			  lensSize  : 120,
			  borderSize: 4,
			  borderColour: "#fff"
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
                  "sProcessing":     "Procesando...",
                  "sLengthMenu":     "Mostrar _MENU_ registros",
                  "sZeroRecords":    "No se encontraron resultados",
                  "sEmptyTable":     "Ningún dato disponible en esta tabla",
                  "sInfo":           "Mostrando Foja <b>_START_</b> de _TOTAL_.",
                  "sInfoEmpty":      "No existe información asociada a tu búsqueda.",
                  "sInfoFiltered":   "",
                  "sInfoPostFix":    "",
                  "sSearch":         "<span class='glyphicon glyphicon-search'></span>",
                  "sUrl":            "",
                  "sInfoThousands":  ",",
                  "sLoadingRecords": "Cargando...",
                  "oPaginate": {
                      "sFirst":    "Primero",
                      "sLast":     "Último",
                      "sNext":     "Siguiente",
                      "sPrevious": "Anterior"
                  },
                  "oAria": {
                      "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                      "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                  }
            }
            });
	});
</script>
<style>
.estilo *{
	font-family: cursive, geneva;
	font-size: 14px!important;
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
  		<label class="alert alert-primary alert-info" style="position: absolute; z-index: 1;right:30px;top:100px"><b><c:out value="${nombrePublicacion}"/></b></label>
  		</div>
  		<br/>  
  		  <div>
          <div class="table-responsive">
            <table class="table table-hover" id="listaPublicacion">
              <thead>
                <tr>
                  <th style="text-align: center" class="alert alert-dismissible">N° FOJA</th>
                  <th style="text-align: center" class="alert alert-dismissible"><span class="glyphicon glyphicon-list"> <b>TRADUCCIÓN</b></span></th>
                  <th style="text-align: center" class="alert alert-dismissible"><span class="glyphicon glyphicon-camera"> <b>ANEXO</b></span></th>
                </tr>
              </thead>
              <tbody>
               <c:forEach var="anexo" items="${anexos}">
               <tr style="background-repeat: no-repeat;" background="${contextpath}/static/resources/img/fondo_publicacion.png">
	                <td style="text-align: center;width: 10%;">
	                	<b>
	                		F<c:out value="${anexo.foja}"/>
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