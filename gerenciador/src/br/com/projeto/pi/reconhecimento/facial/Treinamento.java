package br.com.projeto.pi.reconhecimento.facial;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_face;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createEigenFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createFisherFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.resize;


public class Treinamento {
    public static void main(String args[]){
    	
        File diretorio = new File("src\\fotosTurma");
        FilenameFilter filtroImagem = new FilenameFilter() {   
            @Override
            public boolean accept(File dir, String nome) {
                return nome.endsWith(".jpg") || nome.endsWith(".gif") || nome.endsWith(".png");
            }
        };
        
        File[] arquivos = diretorio.listFiles(filtroImagem);
        MatVector fotos = new MatVector(arquivos.length);
        Mat rotulos = new Mat(arquivos.length, 1, CV_32SC1);
        IntBuffer rotulosBuffer = rotulos.createBuffer();
        
        int contador = 0;
        
        for(File imagem: arquivos){
        	
            //possivel erro de importacao original(ByterPointer) paliativo(String,int) biblioteca javacpp.jar
            Mat foto = imread(imagem.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
            int classe =Integer.parseInt(imagem.getName().split("\\.")[1]);
            //System.out.println(classe);
            
            resize(foto, foto, new Size(160,160));
            fotos.put(contador, foto);
            rotulosBuffer.put(contador, classe);
            contador++;
        }
    
    
    FaceRecognizer eigenFaces = createEigenFaceRecognizer();
    FaceRecognizer fisherfaces = createFisherFaceRecognizer();
    FaceRecognizer lbph = createLBPHFaceRecognizer();
    
    eigenFaces.train(fotos, rotulos);
    eigenFaces.save("src\\recursosTurma\\classificadorEigenFaces.yml");
    
    fisherfaces.train(fotos, rotulos);
    fisherfaces.save("src\\recursosTurma\\classificadorFisherFaces.yml");
    
    lbph.train(fotos, rotulos);
    lbph.save("src\\recursosTurma\\classificadorLBPH.yml");
    
    
    
    }
}
