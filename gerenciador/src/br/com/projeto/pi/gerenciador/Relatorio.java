package br.com.projeto.pi.gerenciador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/relatorio")
public class Relatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Relatorio() {
        super();
       
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Banco banco = new Banco();
		List<Cadastro> lista = banco.getPessoa();
		
		
		request.setAttribute("pessoa", lista);
		
	
		RequestDispatcher rd = request.getRequestDispatcher("relatorio.jsp");
		rd.forward(request, response);
		
	}

}
