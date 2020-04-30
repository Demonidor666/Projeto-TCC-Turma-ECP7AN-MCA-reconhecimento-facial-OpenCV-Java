<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,br.com.projeto.pi.gerenciador.Cadastro" %> 

<!DOCTYPE html>
<html>
<head>
        <script src="https://code.jquery.com/jquery-1.11.2.js"></script>
		<script type="text/javascript">
			jQuery(window).load(function($){
				atualizaRelogio();
			});
            </script>
    <meta charset='utf-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Relatorio de Presença</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel='stylesheet' type='text/css' media='screen' href='main.css'>
       <script src='main.js'></script>
</head>
<body>

 <%
      String nome;
     List<Cadastro> lista = (List<Cadastro>)request.getAttribute("pessoa");
     %> 
     <%  for(Cadastro pessoa : lista){
    	 nome = pessoa.getNome();
       }
         
   %>



        <style type="text/css" class="col-sm-4">
            .tg  {border-collapse:collapse;border-spacing:0;}
            .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
            .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
            .tg .tg-m7ex{font-weight:bold;font-size:15px;text-align:left;vertical-align:top}
            .tg .tg-fbrz{font-weight:bold;font-size:20px;text-align:center;vertical-align:top}
            .tg .tg-0lax{text-align:left;vertical-align:top}
            </style>            
            <table class="table table-bordered">
              <tr>
                <th class="tg-fbrz" colspan="6">Relatório de Presença - USJT 2019</th>
                    </tr>
              <tr>
                <thread class="thread-light">
                <td class="tg-m7ex">Nome</td>
                <td class="tg-m7ex">Registro do Aluno</td>
                <td class="tg-m7ex"><output id="data"></output></td>
                <td class="tg-m7ex"><output id="data2"></output></td>
                <td class="tg-m7ex"><output id="data3"></output></td>
                <td class="tg-m7ex"><output id="data4"></output></td>
                </thread>
              </tr>
              <tr>
                <td class="tg-0lax">Aparecido Jean Barreto Souza</td>
                <td class="tg-0lax">201413272</td>
                <td class="tg-0lax">  Ok</td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
              </tr>
              <tr>
                <td class="tg-0lax">Guilherme Gentil</td>
                <td class="tg-0lax">201507244</td>
                <td class="tg-0lax">  Faltou</td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
              </tr>
              <tr>
                <td class="tg-0lax">Kaio Eduardo</td>
                <td class="tg-0lax">201516380</td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
              </tr>
              <tr>
                <td class="tg-0lax">Kevin Viana</td>
                <td class="tg-0lax">816111669</td>
                <td class="tg-0lax">  Faltou</td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
              </tr>
              <tr>
                <td class="tg-0lax">Pedro Augusto dos Reis</td>
                <td class="tg-0lax">201516526</td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
              </tr>
              <tr>
                <td class="tg-0lax">Romário Farias</td>
                <td class="tg-0lax">201411655</td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
              </tr>
              <tr>
                <td class="tg-0lax">Vinicius Silveira</td>
                <td class="tg-0lax">201411004</td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
                <td class="tg-0lax"></td>
              </tr>
            </table>
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
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</html>