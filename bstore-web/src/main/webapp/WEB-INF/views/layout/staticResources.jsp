<%@ include file="../layout/taglibs.jsp"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>"/>
<link rel="shortcut icon" type="image/x-icon" href="${contextpath}/static/resources/img/favicon.ico"/> 
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/bootstrap-checkbox.css">
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/bootstrap-dialog.css">
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/dataTables.css">
<!-- NUEVO THEME PORTAL BSTORE -->
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/sb-admin.css">
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/plugins/morris.css">
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/font-awesome/css/font-awesome.min.css">
<!-- knob && nouislider CSS -->
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/noui/nouislider.css">
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/noui/nouislider.pips.css">
<!-- Select2 css -->
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/select2/select2.min.css">
<!-- footable css -->
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/footable/footable-0.1.css">
<!-- summernote css -->
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/summernote/summernote.css">
<!-- novedades css -->
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/novedades.css">
<!-- datatables responsice css -->
<link rel="stylesheet" type="text/css" href="${contextpath}/static/resources/css/dataTables.responsive.css">

<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/knockout-3.2.0.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/knockout.simpleGrid.3.0.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/holder.min.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/jquery/blockUI.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/jquery/dataTables2.min.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/bootstrap/bootstrap-dialog.js"></script>
<!-- JS DE MORRIS PARA PORTAL BSTORE -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/plugins/morris/raphael.min.js"></script>
<!-- footable js -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/footable/footable.js"></script>
<!-- summernote js -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/summernote/summernote.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/summernote/summernote-es-ES.js"></script>
<!-- knob && nouislider JS -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/knob/knob.js"></script>
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/noui/nouislider.js"></script>   
<!-- select2 js -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/select2/select2.min.js"></script> 
<!-- chart js -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/chart/Chart.js"></script>
<!-- csi js -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/csi.js"></script>
<!-- grid a licious js -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/jquery.grid-a-licious.js"></script>
<!-- datatables responsive js -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/dataTables.responsive.min.js"></script>
<!-- zoom for image js -->
<script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/elevatezoom.min.js"></script>
<style>
    body {
        margin-top: 100px;
        background-color: #F7F7F7;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
    }
    .wallpaper{
        background-color: #F7F7F7;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
    }
</style>
<!--link type="text/css" id="colors" rel="stylesheet" href="${contextpath}/static/resources/principal/css/green.css"-->



<!-- Se sobreescriben estilos para botones y dialogos de OK, para poner colores de Quivira (General) -->
<style type="text/css">
	.bootstrap-dialog.type-success .modal-header {
	    background-color: #412020 !important;
	}

	.btn-primary {
	    background-color: #412020;
	    border-color: #412020;
	    color: #fff;
	}
	.btn-primary:hover {
	    background-color: #a67c52;
	    border-color: #a67c52;
	    color: #fff;
	}
</style>
