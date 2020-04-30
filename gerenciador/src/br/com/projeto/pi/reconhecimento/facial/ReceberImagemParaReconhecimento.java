package br.com.projeto.pi.reconhecimento.facial;

import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_imgproc.resize;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createEigenFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createFisherFaceRecognizer;


public class ReceberImagemParaReconhecimento {
	
	

	public ReceberImagemParaReconhecimento() {
		super();
	}

	//public static void main(String[] args) {
		
		int totalAcertos = 0;
		double percentualAcertos = 0;
		double totalConfianca = 0;
		
		
	public  String  reconhecer(){
		
		String teste = "envio";
		//String[] pessoas = {"", "PEDRO", "GUILHERME"};
		String[] pessoas = {"", "Vinicius", "Romario", "Gentil", "Kaio", "Jean"};
      //FaceRecognizer reconhecedor = createEigenFaceRecognizer();
     // reconhecedor.setThreshold(5000);
      //reconhecedor.load("src\\recursos\\classificadorEigenFaces.yml");
        
     
    // reconhecedor.load("src\\recursos\\classificadorFisherFaces.yml");
      
	  File ler = new File("c:\\reconhecer\\classificadorEigenFaces.yml");
	  String stt = ler.toString();
	  FaceRecognizer reconhecedor = createEigenFaceRecognizer();
	  //FaceRecognizer reconhecedor = createFisherFaceRecognizer();
     // FaceRecognizer reconhecedor = createLBPHFaceRecognizer();
    // reconhecedor.setThreshold(9000);
      
       reconhecedor.load(stt);
      
      
      
      File diretorio = new File("c:\\imagem\\");
      File [] arquivos = diretorio.listFiles();
      
      
      for (File imagem : arquivos) {
    	  
    	  Mat foto = imread(imagem.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
    	  int classe = Integer.parseInt(imagem.getName().split("\\.")[1]);
    	  
    	  resize(foto, foto, new opencv_core.Size(160, 160));
    	  
    	  IntPointer rotulo = new IntPointer(1);
    	  DoublePointer confianca = new DoublePointer(1);
    	  reconhecedor.predict(foto, rotulo, confianca);
    	  int predicao = rotulo.get(0);
    	  
    	  
    	  String nome;
    	  if(predicao == -1){
              nome = "Desconhecido";
              System.out.println(classe + "° Imagem foi reconhecido como " + nome );
          }else{
              nome = " " + pessoas[predicao];
              System.out.println(classe + "° Imagem foi reconhecido como " + nome );
              System.out.println("Confiança: " + confianca.get(0));
          }
    	 return nome;
      }
	return teste;

     
		}
	
	
}
