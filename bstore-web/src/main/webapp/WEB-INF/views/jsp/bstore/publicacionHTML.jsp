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
               <tr style="background-repeat: no-repeat;" background="${contextpath}/static/resources/img/fondo_publicacion.png">
	                <td style="text-align: center;width: 10%;"><b>F1</b></td>
	                <td style="width: 40%;">
	                	  <div class="estilo"style="text-align:justify;">
	                	  <p><span>DON JUAN VICENTE DE GUEMEZ</span></p>
				          <p>&nbsp;</p>
				          <p>Pacheco de Padilla Horcasitas y Aguayo, Conde de Revilla Gigedo,
				          Baron y Señor territorial de las Villas y Baronías de Benillova y Rivar-</span></p>
				          <p><span>roja, Caballero Comendador de Peña de Martos en la Orden de Cala-</span></p>
				          <p><span>trava, Gentil Hombre de Cámara de Su Magestad con exercicio, Teniente ge-</span></p>
				          <p><span>neral de sus Reales Exércitos, Virrey, Gobernador y Capitan general</span></p>
				          <p><span>de Nueva España, Presidente de su Real Audiencia, Superintendente</span></p>
				          <p><span>general Subdelegado de la Real Hacienda, Minas, Azogues, y Ramo</span></p>
				          <p><span>del Tabaco, Juez Conservador de éste, Presidente de su Real Junta,</span></p>
				          <p><span>y Subdelegado general de Correos en el mismo Reyno.</span></p>
				          <p>&nbsp;</p>
				          <p><span>CON la justa mira de corregir el desórden con que muchos indi-</span></p>
				          <p><span>viduos de ambos sexôs concurren á las Procesiones de Sema-</span></p>
				          <p><span>na Santa, convirtiendo en regocijo, destemplanza y desacato</span></p>
				          <p><span>la seriedad de unos dias que ofrecen ciertamente á la memoria</span></p>
				          <p><span>los mas piadosos recuerdos, tuvo á bien el Excelentisimo Señor Virey</span></p>
				          <p><span>mi antecesor remover el origen de estos abusos, que en la mayor parte oca-</span></p>
				          <p><span>sionan las vendimias de comestibles, bebidas, y juguetes, prohibiendo por</span></p>
				          <p><span>Bando de 27 de Marzo del año inmediato pasado los Puestos de chia, al-</span></p>
				          <p><span>muerzos, frutas, dulces y cosas semejantes en las calles por donde pasan</span></p>
				          <p><span>las Procesiones, y en las proxímidades de los Templos, como tambien el</span></p>
				          <p><span>que sigan á estos actos religiosos los Vendedores de matracas, pasteles, </span>
				          </div>
	                </td>
	                <td style="text-align: center;width: 50%;">
	                	<img width="70%" height="auto" id="publicacion1" src="http://127.0.0.1/images/1.png" data-zoom-image="http://127.0.0.1/images/1.png"/>
	                </td>
                </tr>
                <tr style="background-repeat: no-repeat;" background="${contextpath}/static/resources/img/fondo_publicacion.png">
	                <td style="text-align: center;width: 10%;"><b>F2</b></td>
	                <td style="width: 40%;">
	                	  <div class="estilo"style="text-align:justify;">
	                	  <p><span>DON JUAN VICENTE DE GUEMEZ</span></p>
				          <p>&nbsp;</p>
				          <p>Pacheco de Padilla Horcasitas y Aguayo, Conde de Revilla Gigedo,
				          Baron y Señor territorial de las Villas y Baronías de Benillova y Rivar-</span></p>
				          <p><span>roja, Caballero Comendador de Peña de Martos en la Orden de Cala-</span></p>
				          <p><span>trava, Gentil Hombre de Cámara de Su Magestad con exercicio, Teniente ge-</span></p>
				          <p><span>neral de sus Reales Exércitos, Virrey, Gobernador y Capitan general</span></p>
				          <p><span>de Nueva España, Presidente de su Real Audiencia, Superintendente</span></p>
				          <p><span>general Subdelegado de la Real Hacienda, Minas, Azogues, y Ramo</span></p>
				          <p><span>del Tabaco, Juez Conservador de éste, Presidente de su Real Junta,</span></p>
				          <p><span>y Subdelegado general de Correos en el mismo Reyno.</span></p>
				          <p>&nbsp;</p>
				          <p><span>CON la justa mira de corregir el desórden con que muchos indi-</span></p>
				          <p><span>viduos de ambos sexôs concurren á las Procesiones de Sema-</span></p>
				          <p><span>na Santa, convirtiendo en regocijo, destemplanza y desacato</span></p>
				          <p><span>la seriedad de unos dias que ofrecen ciertamente á la memoria</span></p>
				          <p><span>los mas piadosos recuerdos, tuvo á bien el Excelentisimo Señor Virey</span></p>
				          <p><span>mi antecesor remover el origen de estos abusos, que en la mayor parte oca-</span></p>
				          <p><span>sionan las vendimias de comestibles, bebidas, y juguetes, prohibiendo por</span></p>
				          <p><span>Bando de 27 de Marzo del año inmediato pasado los Puestos de chia, al-</span></p>
				          <p><span>muerzos, frutas, dulces y cosas semejantes en las calles por donde pasan</span></p>
				          <p><span>las Procesiones, y en las proxímidades de los Templos, como tambien el</span></p>
				          <p><span>que sigan á estos actos religiosos los Vendedores de matracas, pasteles, </span>
				          </div>
	                </td>
	                <td style="text-align: center;width: 50%;">
	                	<img width="70%" height="auto" id="publicacion2" src="http://127.0.0.1/images/1.png" data-zoom-image="http://127.0.0.1/images/1.png"/>
	                </td>
                </tr>        
              </tbody>
            </table>
          </div>
    	</div>
	</div>
</body>
</html>