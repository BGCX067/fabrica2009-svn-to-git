<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<%@ page import="java.util.ArrayList"%>
<%@ page import="fabrica.vo.ArticuloEnListadoVO"%>
<jsp:include page="head.jsp" />

<body id="home" class="pageWithTooltip">

<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />

<div id="wrapper"><!-- start page -->
<div id="page"><!-- start content -->
<div id="content_wide">


<f:view>
	<h:outputLabel value="No se enviado artículos a los centros. " rendered="#{listadoReposicion.numRecords == 0}" styleClass="message"></h:outputLabel>
	<h:dataTable rendered="#{listadoReposicion.numRecords > 0}" headerClass="h" styleClass="listado" value="#{listadoReposicion.articulos}" var="articulo">
		<f:facet name="header">
			<h:outputText value="Artículos Enviados a los Centros de Distribución"/> 
		</f:facet>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Centro"/> 
			</f:facet>
			<h:outputText value="#{articulo.centro}"/>
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Referencia"/> 
			</f:facet>
			<h:outputText value="#{articulo.referencia}"/>
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Nombre"/> 
			</f:facet>
			<h:outputText value="#{articulo.nombre}"/>
		</h:column>
		<h:column>
			<f:facet name="header">
				<h:outputText value="Cantidad"/> 
			</f:facet>
			<h:outputText value="#{articulo.cantidad}"/>
		</h:column>
	</h:dataTable>
</f:view>

</div>
<!-- end content -->
<div class="bottom">&nbsp;</div>
</div>
<!-- end page -->
</div>
<jsp:include page="footer.jsp" />
</body>
</html>