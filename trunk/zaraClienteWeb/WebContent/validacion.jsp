<%
String resultado ="";
String validate = request.getParameter("validate");
try{
if (Integer.parseInt(validate) > 0){
	
	resultado = "true";
}}catch(Exception ex){
	resultado = "false";
}
%>
<%=resultado%>