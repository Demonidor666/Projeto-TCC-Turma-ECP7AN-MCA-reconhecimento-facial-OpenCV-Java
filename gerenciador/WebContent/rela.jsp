<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List,br.com.projeto.pi.gerenciador.Cadastro" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

    <script src="https://code.jquery.com/jquery-1.11.2.js"></script>
		<script type="text/javascript">
			jQuery(window).load(function($){
				atualizaRelogio();
			});
            </script> 
<title>Relatorio de Presença</title>
</head>
<body>
<h1>Relatorio de Registros Capturados </h1>
<ul>

<%
      List<Cadastro> lista = (List<Cadastro>)request.getAttribute("pessoa");
      for(Cadastro pessoa : lista){
  %>
      <li><%= pessoa.getNome() %><output id="hora"></output></li>
      
  <%      	  
    	  
      }

%>


</ul>
 	
</body>
<script>
		function atualizaRelogio(){ 
			var momentoAtual = new Date();
			
			var vhora = momentoAtual.getHours();
			var vminuto = momentoAtual.getMinutes();
			var vsegundo = momentoAtual.getSeconds();
			
			var vdia = momentoAtual.getDate();
            var vdia2 = momentoAtual.getDate() + 1;
            var vdia3 = momentoAtual.getDate() + 2;
            var vdia4 = momentoAtual.getDate() + 3;
			var vmes = momentoAtual.getMonth() + 1;
			var vano = momentoAtual.getFullYear();
			
             
			if (vdia < 10){ vdia = "0" + vdia;}
            if (vdia2 < 10){ vdia2 = "0" + vdia2;}
            if (vdia3 < 10){ vdia3 = "0" + vdia3;}
            if (vdia4 < 10){ vdia4 = "0" + vdia4;}
			if (vmes < 10){ vmes = "0" + vmes;}
			if (vhora < 10){ vhora = "0" + vhora;}
			if (vminuto < 10){ vminuto = "0" + vminuto;}
			if (vsegundo < 10){ vsegundo = "0" + vsegundo;}
            
            
			dataFormat = vdia + "/" + vmes + "/" + vano;
            dataFormat2 = vdia2 + "/" + vmes + "/" + vano;
            dataFormat3 = vdia3 + "/" + vmes + "/" + vano;
            dataFormat4 = vdia4 + "/" + vmes + "/" + vano;
			horaFormat = vhora + " : " + vminuto + " : " + vsegundo;
            
 
			document.getElementById("data").innerHTML = dataFormat;
            document.getElementById("data2").innerHTML = dataFormat2;
            document.getElementById("data3").innerHTML = dataFormat3;
            document.getElementById("data4").innerHTML = dataFormat4;
			document.getElementById("hora").innerHTML = horaFormat;
 
			setTimeout("atualizaRelogio()",1000);
		}
	</script>
</html>