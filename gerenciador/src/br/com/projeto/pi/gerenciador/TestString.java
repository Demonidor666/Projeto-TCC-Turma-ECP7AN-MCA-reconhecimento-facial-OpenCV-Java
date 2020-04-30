package br.com.projeto.pi.gerenciador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import br.com.projeto.pi.reconhecimento.facial.ReceberImagemParaReconhecimento;
import javafx.scene.control.TableView.ResizeFeatures;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

// parte do reconhecimento facial
/**
 * Servlet implementation class TestString
 */
@WebServlet("/TestString")
public class TestString extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	public TestString() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String arquivo = request.getParameter("arquivo");
		// System.out.println("chegou " +arquivo );
		/*
		 * File file = new File(
		 * "C:\\Workspace PI\\gerenciador\\WebContent\\pessoas.1.1.png" );
		 * file.delete(); file.canExecute();
		 */
		
		String stt = request.getParameter("arquivo");
		
		
		
		
		
		try {
			
					
			//System.out.println("Arquivo deletado com sucesso!!");

			// String parts[] = stt.split(",");
			// String imgPart = parts[1];

			BufferedImage image = null;

			byte[] imageByte;
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(stt);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();

			BufferedImage Resized = new BufferedImage(320, 240, image.getType());
			Graphics2D g2 = Resized.createGraphics();
			g2.drawImage(image, 0, 0, 320, 240, null);
			g2.dispose();

			File outputfile = new File("c:\\imagem\\pessoas.1.1.png");
			//File outputfile = new File("C:\\Workspace PI\\gerenciador\\WebContent\\pessoas.1.1.png");
			ImageIO.write(Resized, "PNG", outputfile);
            System.out.println("Arquivo salvo com sucesso!!");
			 
			
		
	       

		} catch (Exception e) {
			e.printStackTrace();
		}

		 String nomereconhecido = null;
		
		 nomereconhecido = reconhecimento();
		 
		
			
		 
		File fileRead = new File("c:\\imagem\\pessoas.1.1.png"); 
		
		
		 
	            String encodedfile = null;
	            try {
	                FileInputStream fileInputStreamReader = new FileInputStream(fileRead);
	                byte[] bytes = new byte[(int)fileRead.length()];
	                fileInputStreamReader.read(bytes);
	                BASE64Encoder encoder = new BASE64Encoder();
	                encodedfile = encoder.encode(bytes);
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }

	   
	          
	  		  
	      			
				RequestDispatcher rd = request.getRequestDispatcher("/aluno.jsp");
				request.setAttribute("image","data:image/png;base64,"+encodedfile);
				request.setAttribute("nome", nomereconhecido);
						
				rd.forward(request, response);
			        	  
	  		   
	  		 // request.setAttribute("nome", nomereconhecido);
	  		  
	          
		/*
		 * File fileRead = new File("c:\\imagem\\pessoas.1.1.jpg"); byte[] image; image
		 * = new byte[(int)fileRead.length()];
		 * 
		 * response.setContentType("image/jpeg");
		 * 
		 * response.getOutputStream().write(image);
		 */
		 
		//RequestDispatcher rd = request.getRequestDispatcher("/aluno.jsp");
		// rd.forward(request, response);

		// reconhecimento();

	}
	
	
    
	//@Override
	public String  reconhecimento() {
		
		 String nome;
		 ReceberImagemParaReconhecimento recebido = new  ReceberImagemParaReconhecimento(); 
	     nome =  recebido.reconhecer();
	     
	     Cadastro cadastro = new Cadastro();
	     cadastro.setNome(nome);
	     
	     Banco banco = new Banco();
	     banco.adiciona(cadastro);
	     
	     if(nome.equals("Desconhecido")) {
	    	 return "Aluno " + nome + " por gentileza solicitar ao adm seu Cadastro!";
	    	 
	     }
	    
		return "Aluno " + nome + " sua presença foi registrada com sucesso!";		
	}
	
}


