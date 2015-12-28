<%@ page import="java.util.ArrayList"%>
<%@ page import="fabrica.vo.ArticuloEnListadoVO"%>

<jsp:include page="head.jsp" />

<body id="home" class="pageWithTooltip">

<%  
	ArrayList<ArticuloEnListadoVO> articulosComenzados = (ArrayList<ArticuloEnListadoVO>) request.getAttribute("articulosComenzados");
	ArrayList<ArticuloEnListadoVO> articulosPendientes = (ArrayList<ArticuloEnListadoVO>) request.getAttribute("articulosPendientes");
%>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div id="wrapper"><!-- start page -->
<div id="page"><!-- start content -->
<div id="content_wide">

<% if (articulosComenzados != null && articulosComenzados.size() > 0){ %>
<table class="listado">
	<thead>
		<tr>
			<th class="h" colspan="3">Artículos comenzados a fabricarse</th>
		</tr>
		<tr>
			<th class="h">Referencia</th>
			<th class="h">Artículo</th>
			<th class="h">Cantidad</th>
		</tr>
	</thead>
	<tbody>
	<% for (ArticuloEnListadoVO articulo : articulosComenzados){ %>
	<tr>
		<td><%=articulo.getReferencia() %></td>
		<td><%=articulo.getNombre() %></td>
		<td><%=articulo.getCantidad() %></td>
	</tr>
	<%} %>
	</tbody>
</table>
<%} else { %>
<span class="message">No han comenzado a fabricarse artículos.</span>
<%} %>

<% if (articulosPendientes != null && articulosPendientes.size() > 0){ %>
<table class="listado">
	<thead>
		<tr>
			<th colspan="3" class="h">Artículos Pendientes (se há realizado un pedido de materias por estos artículos)</th>
		</tr>
		<tr>
			<th class="h">Referencia</th>
			<th class="h">Artículo</th>
			<th class="h">Cantidad</th>
		</tr>
	</thead>
	<tbody>
	<% for (ArticuloEnListadoVO articulo : articulosPendientes){ %>
	<tr>
		<td><%=articulo.getReferencia() %></td>
		<td><%=articulo.getNombre() %></td>
		<td><%=articulo.getCantidad() %></td>
	</tr>
	<%} %>
	</tbody>
</table>
<%} else { %>
<div class="message">No hay artículos pendientes de fabricación. </div>
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