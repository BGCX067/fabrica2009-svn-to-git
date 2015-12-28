/*
 
  EJEMPLO DE AJAX
  
  function validar (valor){
  
  	try {
  		req = new XHLHTTPRequest();
  	}
  	catch(){
  		req = new ActiveXObject("Microsoft XHLHTTP");
  		
  	}
  	req.onreadyStateChange = mostrarResultado; //CALLBACK
  	req.open ("validar?usuario="+valor. "GET", true);
  	req.send(null);
  
  }
  
  function mostrarResultado(){
  	if (req.readyState == 4 && req.Status == 200){
  		respuesta = req.ResponseTxt;
  		document.getElementById('resultado').innerHTML = respuesta;
  	}
  }
  
  //EN EL SERVLET doGet()...
  
*/

function validar (elementId){
  
  	if (window.XMLHttpRequest){
  		req = new XMLHttpRequest();
  	}
  	else if (window.ActiveXObject){
  		req = new ActiveXObject("Microsoft.XMLHTTP");
  	}
  	req.label = elementId+'_label';
  	req.onreadystatechange = mostrarResultado; //CALLBACK
  	value = document.getElementById(elementId).value;
  	req.open ("GET" ,"validacion.jsp?validate="+value, true);
  	req.send(null);
  
  }
  
function mostrarResultado(){
  	if (req.readyState == 4 && req.status == 200){
  		respuesta = req.responseText.replace(/\n/g,'');
  		if (respuesta == 'false'){
  			document.getElementById(req.label).style.visibility = 'visible';
  		}
  		else{
  			document.getElementById(req.label).style.visibility = 'hidden';
  		}
  	}
}

function check(msg) {
	if ( ! msg) msg= "Seguro ?";
	var agree=confirm(msg);
	if (agree) return true ;
	else return false ;
}

function agregarMateria(){
	var tbody = document.getElementById('tbodyMaterias');
	var row = document.createElement("TR");
	var td1 = document.createElement("TD");
	var td2 = document.createElement("TD");
	
	selectMateria = document.getElementById("selectMateriasHidden");
	inputMateria = document.getElementById("textMateriasHidden");
	
	document.getElementById("cantMaterias").value = (document.getElementById("cantMaterias").value-1)+2;
	
	value = document.getElementById("cantMaterias").value;
	
	td1.innerHTML = selectMateria.innerHTML.replace(/#/g,value);
	td2.innerHTML = inputMateria.innerHTML.replace(/#/g,value);
	
	row.appendChild(td1);
	row.appendChild(td2);
	tbody.appendChild(row);
}

function quitarMateria(){
	
	var tbl = document.getElementById('tbodyMaterias');
	document.getElementById("cantMaterias").value= document.getElementById("cantMaterias").value-1;
	tbl.deleteRow(-1);
}
