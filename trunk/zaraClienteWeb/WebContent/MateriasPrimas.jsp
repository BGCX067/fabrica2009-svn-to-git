<%@ page import="java.util.ArrayList"%>
<%@ page import="fabrica.vo.ArticuloEnListadoVO"%>
<%@ page import="ar.com.fabrica.error.BasicError"%>
<%@ page import="fabrica.vo.MateriaPrimaVO"%>

<jsp:include page="head.jsp" />
<body id="home" class="pageWithTooltip">
<%
	BasicError error = (BasicError) request.getAttribute("error");
	ArrayList<MateriaPrimaVO> materiasPrimasVO = (ArrayList<MateriaPrimaVO>) request.getAttribute("materiasPrimasVO");
	String action = (String) request.getAttribute("action");
%>

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div id="wrapper"><!-- start page -->
<div id="page"><!-- start content -->
<div id="content_wide"><br/>
<form action="materiasPrimas" enctype="multipart/form-data" method="post">
	Ingrese archivo de materias primas <input name="inputFileName" id="inputFileName" type="file"/>
	<input type="submit" value="enviar!" />
</form>
<%if (error !=null) { %>
	<br/>
	<div class="error" id="errorDiv"><%=error.getDescripcion() %></div>

<%} %>
<%if (materiasPrimasVO != null && materiasPrimasVO.size() > 0){ %>
<table class="listado">
	<thead>
	<tr>
		<th colspan="2" class="h">Materias Primas Recibidas</th>
	</tr>
	<tr>
		<th class="h">Art&iacute;culo</th>
		<th class="h">Cantidad</th>
	</tr>
	</thead>
	<tbody>
	<% for (MateriaPrimaVO materiaPrimaVO : materiasPrimasVO){ %>
	<tr>
		<td><%=materiaPrimaVO.getCodigo() %></td>
		<td><%=materiaPrimaVO.getStock() %></td>
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