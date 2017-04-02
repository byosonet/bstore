<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$('ul.nav.navbar-nav').hide();
		$('#wrapper').css('padding-left','0px');
		$('a#showMenu').show();
		
		$("#publicacion1").elevateZoom({
		  zoomType	: "lens",
		  lensShape : "round",
		  lensSize  : 120,
		  borderSize: 4,
		  borderColour: "#fff"
		});
		
		$("#publicacion2").elevateZoom({
			  zoomType	: "lens",
			  lensShape : "round",
			  lensSize  : 120,
			  borderSize: 4,
			  borderColour: "#fff"
			});

        var listaPublicacion = $('#listaPublicacion')
        .dataTable(
            {
            "aoColumns": [ {"bSearchable": true}, {"bSearchable": true},{"bSearchable": false}],
            "sPaginationType": "full_numbers",
            "bPaginate": true,
            "bLengthChange" : false,
            "pageLength": 1,
            "oLanguage" : {
                  "sProcessing":     "Procesando...",
                  "sLengthMenu":     "Mostrar _MENU_ registros",
                  "sZeroRecords":    "No se encontraron resultados",
                  "sEmptyTable":     "Ningún dato disponible en esta tabla",
                  "sInfo":           "",
                  "sInfoEmpty":      "",
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
div.estilo {
    word-wrap: break-word;
}
</style>
</head>
<body>
    <div class="row" style="padding: 0.5em;">
    	<div style="text-align: center;">
  		<label class="btn btn-primary alert-info" style="position: unset; z-index: 1;right:15px;top:82px"><b><c:out value="${nombrePublicacion}"/></b></label>
  		<br/>
  		<br/>
  		</div>  
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
	                	  <div class="estilo"style="text-align:justify;">
	                	  	<c:out value="${anexo.traduccion.getCharacterStream()}" escapeXml="false"/>
				          </div>
	                </td>
	                <td style="text-align: center;width: 50%;">
	                	<img width="70%" height="auto" id="publicacion1" src="data:image/png;base64,${anexo.resultImage}" data-zoom-image="data:image/png;base64,${anexo.resultImage}"/>
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