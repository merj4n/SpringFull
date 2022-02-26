// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function iniciarSesion() {
	let datos = {};
	datos.email = document.getElementById('txtEmail').value;
	datos.password = document.getElementById('txtPassword').value;
	
	
  const request = await fetch('api/login', {
    method: 'POST',
    headers: getHeaders(),
    body: JSON.stringify(datos)
  });  
  const respuesta = await request.text();
  if (respuesta != 'NOK'){
	localStorage.token = respuesta;
	localStorage.email = datos.email;
	window.location.href = 'usuarios.html'
}else{
	alert('Las credenciales son incorrectas! Intentelo de nuevo.')
}
}

function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
     'Authorization': localStorage.token
   };
}