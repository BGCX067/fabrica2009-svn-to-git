<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.TreeMap"%>
<%@ page import="java.util.Vector"%>
<%@ page import="fabrica.vo.MateriaPrimaVO"%>
<%@ page import="ar.com.fabrica.error.BasicError"%>
<%  
String tipoArticulo = (String) request.getAttribute("tipoArticulo");
Map<String, String> campos = (Map<String, String>) request.getAttribute("campos");
String key;
ArrayList<MateriaPrimaVO> materiasPrimasVO = (ArrayList<MateriaPrimaVO>) request.getAttribute("materiasPrimasVO");
BasicError error = (BasicError) request.getAttribute("error");

%>


<%@ include file="head.jsp" %>

<body id="home" class="pageWithTooltip">

<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<div id="wrapper"><!-- start page -->
<div id="page"><!-- start content -->
<div id="content_wide"><br/>
<form action="nuevoArticulo" enctype="multipart/form-data" method="post">
	Ingrese nuevo artículo <input name="inputFileName" id="inputFileName" type="file"/>
	<input type="submit" value="enviar!" />
</form>

<%if (error !=null) { %>
	<br/>
	<div class="error" id="errorDiv"><%=error.getDescripcion() %></div>

<%
}else{if (campos != null){
	Set<String> componente = campos.keySet();
	Iterator<String> nombresComponentes = componente.iterator();
%>
<form id="altaArticulo" name="altaArticulo" action="nuevoArticulo" method="post">
<table class="table_articulo">
	<tr>
		<th colspan="2">Tipo Articulo: <%=tipoArticulo %> 
		<img src="<%=(tipoArticulo.equals("Ropa")) ? "img/clothing.png" : "img/housing.png" %>"/>
		</th>
	</tr>
	<tr>
		<th colspan="2">Datos importados, completar los faltantes</th>
	</tr>
	<%	
	while (nombresComponentes.hasNext()) {
		key = nombresComponentes.next();
	%>
		<tr>
			<td>
				<%=key %>:
			</td>
			<td>
				<input name="<%=key %>" <%=(campos.get(key)!=null && campos.get(key) != "") ? "class=\"disable\" readonly=\"readonly\"" : "" %> type="text" value="<%=campos.get(key)%>"/>
			</td>
			<% if (nombresComponentes.hasNext()){
				key = nombresComponentes.next();%>
				<td>
					<%=key %>:
				</td>
				<td>
					<input name="<%=key %>" <%=(campos.get(key)!=null && campos.get(key) != "") ? "class=\"disable\" readonly=\"readonly\"" : "" %> type="text" value="<%=campos.get(key)%>"/>
				</td>
			<%} %>
		</tr>
	<% } %>
	<tr>
		<td>Tiempo:</td>
		<td colspan="2">
			<input name="Tiempo" id="Tiempo" type="text" value="" onblur="validar('Tiempo')"/>
			<label class="error" id="Tiempo_label" style="visibility: hidden;">Solo Números!</label>
		</td>
	</tr>
</table>
<input type="hidden" id="cantMaterias" name="cantMaterias" value="1">

<div class="hidden" id="selectMateriasHidden">
	<select id="selectMaterias#" name="selectMaterias#">
		<% for(MateriaPrimaVO materiaPrimaVO : materiasPrimasVO){  %>
		<option value="<%=materiaPrimaVO.getCodigo() %>" ><%=materiaPrimaVO.getNombre()%></option>
		<%} %>
	</select>
</div>
<div id="textMateriasHidden" class="hidden">
	<input type="text" id="inputMaterias#" name="inputMaterias#" onblur="validar('inputMaterias#')"/>
	<label class="error" id="inputMaterias#_label" style="visibility: hidden;">Solo Números!</label>
</div>

<table class="table_articulo wb">
	<tr>
		<th>Materia 
			<img src="img/add.png" onclick="agregarMateria()" title="Agregar M. Prima" alt="Agregar"></img>
			<img src="img/delete.png" onclick="quitarMateria()" title="Quitar M. Prima" alt="Quitar"></img>
		</th>
		<th>Cantidad</th>
	</tr>
	<tbody id="tbodyMaterias">
	<tr>
		<td>
			<select id="selectMaterias1" name="selectMaterias1">
				<% for(MateriaPrimaVO materiaPrimaVO : materiasPrimasVO){  %>
				<option value="<%=materiaPrimaVO.getCodigo() %>" ><%=materiaPrimaVO.getNombre()%></option>
				<%} %>
			</select>
		</td>
		<td>
			<input type="text" id="inputMaterias1" name="inputMaterias1" onblur="validar('inputMaterias1')"/>
			<label class="error" id="inputMaterias1_label" style="visibility: hidden;">Solo Números!</label>
		</td>
	</tr>
	</tbody>
</table>

<div id="submitForm" class="send">
	<input type="hidden" value="guardar" name="action">
	<input type="hidden" value="<%=tipoArticulo%>" name="tipoArticulo">
	<input type="submit" value="Confirmar"/>
</div>
</form>
<%}}%>

</div>
<!-- end content -->
<div class="bottom">&nbsp;</div>
</div>
<!-- end page -->
</div>
<%@ include file="footer.jsp"%>
</body>
</html>