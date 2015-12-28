<%@ page import="java.util.ArrayList"%>
<%@ page import="fabrica.vo.ArticuloEnListadoVO"%>
<%@ page import="ar.com.fabrica.error.BasicError"%>

<jsp:include page="head.jsp" />
<body id="home" class="pageWithTooltip">
<%
	BasicError error = (BasicError) request.getAttribute("error");
	ArrayList<ArticuloEnListadoVO> articulosEnListadoVO = (ArrayList<ArticuloEnListadoVO>) request.getAttribute("articulosSolicitud");
	String action = (String) request.getAttribute("action");
%>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div id="wrapper"><!-- start page -->
<div id="page"><!-- start content -->
<div id="content_wide"><br/>
<form action="solicitudFabricacion" enctype="multipart/form-data" method="post">
	Ingrese archivo de solicitud de fabricación <input name="inputFileName" id="inputFileName" type="file"/>
	<input type="submit" value="enviar!" />
</form>
<%if (error !=null) { %>
	<br/>
	<div class="error" id="errorDiv"><%=error.getDescripcion() %></div>

<%} %>
<%if (articulosEnListadoVO != null && articulosEnListadoVO.size() > 0){ %>
<table class="listado">
	<thead>
	<tr>
		<th colspan="3" class="h">Artículos Por Fabricar</th>
	</tr>
	<tr>
		<th class="h">Referencia</th>
		<th class="h">Artículo</th>
		<th class="h">Cantidad</th>
	</tr>
	</thead>
	<tbody>
	<% for (ArticuloEnListadoVO articuloEnListadoVO : articulosEnListadoVO){ %>
	<tr>
		<td><%=articuloEnListadoVO.getReferencia() %></td>
		<td><%=articuloEnListadoVO.getNombre() %></td>
		<td><%=articuloEnListadoVO.getCantidad() %></td>
	</tr>
	</tbody>
	<%} %>
</table>
<%} else if ("post".equals(action)) {%>
<div class="message">No se han recibido materias primas. </div>
<%} %>


</div>
<!-- end content -->
<div class="bottom">&nbsp;</div>
</div>
<!-- end page -->
</div>

<!--for form-->
<div id="content_wide2" class="modal"></div>

<jsp:include page="footer.jsp" />
</body>
</html>