<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

        <%
            String imageInBase64 = (String)request.getAttribute("image");
            String nome =          (String)request.getAttribute("nome");
        %>


<html>
<head>
    <script src="https://code.jquery.com/jquery-1.11.2.js"></script>
		<script type="text/javascript">
			jQuery(window).load(function($){
				atualizaRelogio();
			});
            </script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Reconhecimento</title>
</head>
<body>
	<style type="text/css">
		div.all {
			text-align: center;
			}
		body, html{
		height: 100%}
		div.d {
		height: 100%;
		background-position: center;
		background-repeat: no-repeat;
		background-size: cover;
            background-color: azure;
	}
	</style>
<div class="d">
<div class="all"> 	
<div class="p-3 mb-2 bg-primary text-dark"><h2>Lista de Presença - USJT 2019</h2></div>
          
     		
			<img src="<%= imageInBase64 %>">

<div class="p-3 mb-2 bg-secondary text-white"><h4> <%= nome %>  </h4> </div>
    

    <div class="p-3 mb-2 bg-secondary text-white"> <p> DATA:  <output id="data"></output>  </p> </div>
    <div class="p-3 mb-2 bg-secondary text-white"> <p>AULA: Projeto Interdisciplinar Sala: 205D</p></div>
<a href="camera1.jsp"><button type="button" class="btn btn-dark">Back</button></a>
</div>
</div>
</body>
    
    
    
    <script>
		function atualizaRelogio(){ 
			var momentoAtual = new Date();
			
			var vhora = momentoAtual.getHours();
			var vminuto = momentoAtual.getMinutes();
			var vsegundo = momentoAtual.getSeconds();
			
			var vdia = momentoAtual.getDate();
			var vmes = momentoAtual.getMonth() + 1;
			var vano = momentoAtual.getFullYear();
			
			if (vdia < 10){ vdia = "0" + vdia;}
			if (vmes < 10){ vmes = "0" + vmes;}
			if (vhora < 10){ vhora = "0" + vhora;}
			if (vminuto < 10){ vminuto = "0" + vminuto;}
			if (vsegundo < 10){ vsegundo = "0" + vsegundo;}
 
			dataFormat = vdia + " / " + vmes + " / " + vano;
			horaFormat = vhora + " : " + vminuto + " : " + vsegundo;
 
			document.getElementById("data").innerHTML = dataFormat;
			document.getElementById("hora").innerHTML = horaFormat;
 
			setTimeout("atualizaRelogio()",1000);
		}
	</script>	
</html>