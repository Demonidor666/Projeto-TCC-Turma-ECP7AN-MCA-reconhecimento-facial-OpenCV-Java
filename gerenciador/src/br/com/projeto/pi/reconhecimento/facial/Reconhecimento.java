
package br.com.projeto.pi.reconhecimento.facial;

import java.awt.event.KeyEvent;
import java.util.Scanner;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;
import static org.bytedeco.javacpp.opencv_core.FONT_HERSHEY_PLAIN;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createEigenFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createFisherFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.putText;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;
import static org.bytedeco.javacpp.opencv_imgproc.resize;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;


public class Reconhecimento {
    public static void main(String arg[]) throws FrameGrabber.Exception, InterruptedException{
        
       
        OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        String[] pessoas = {"", "Vinicius", "Romario", "Gentil", "Kaio", "Jean"};
        camera.start();
        
        opencv_objdetect.CascadeClassifier detectorFace = new opencv_objdetect.CascadeClassifier("src\\recursos\\haarcascade-frontalface-alt.xml");
        
//        FaceRecognizer reconhecedor = createEigenFaceRecognizer();
//        reconhecedor.setThreshold(1000);
//        reconhecedor.load("src\\recursos\\classificadorEigenFaces.yml");
          
      //  FaceRecognizer reconhecedor = createFisherFaceRecognizer();
       //  reconhecedor.load("src\\recursosTurma\\classificadorFisherFaces.yml");
        
        FaceRecognizer reconhecedor = createLBPHFaceRecognizer();
        // pasta pra reconhecer o nego
        reconhecedor.load("src\\recursosTurma\\classificadorLBPH.yml");
       // reconhecedor.setThreshold(72);
       
        CanvasFrame cFrame = new CanvasFrame("Preview", CanvasFrame.getDefaultGamma() / camera.getGamma());
        Frame frameCapturado = null;
        opencv_core.Mat imagemColorida = new opencv_core.Mat();
         
        int contador = 0;
        double produto = 0;
        
        while((frameCapturado = camera.grab()) != null){
            
            imagemColorida = converteMat.convert(frameCapturado);
            opencv_core.Mat imagemCinza = new opencv_core.Mat();
            cvtColor(imagemColorida, imagemCinza, COLOR_BGR2GRAY);
            opencv_core.RectVector facesDetectadas = new opencv_core.RectVector();
            detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 1, 0, new opencv_core.Size(150,150), new opencv_core.Size(500,500));
          
            for(int i = 0; i < facesDetectadas.size(); i++){
                opencv_core.Rect dadosFace = facesDetectadas.get(0);
                rectangle(imagemColorida, dadosFace, new opencv_core.Scalar(0, 0, 255,0));
                opencv_core.Mat faceCapturada = new opencv_core.Mat(imagemCinza, dadosFace);
                resize(faceCapturada, faceCapturada, new opencv_core.Size(160,160));
                
                IntPointer rotulo = new IntPointer(1);
                DoublePointer confianca = new DoublePointer(1);
                reconhecedor.predict(faceCapturada, rotulo, confianca);
                int predicao = rotulo.get(0);
                String nome;
                
                // PREDICAO = -1 PQ Nao ENCOTROU A FACE
                if(predicao == -1){
                    nome = "Desconhecido";
                }else{
                    nome = " " + pessoas[predicao] + " - " + confianca.get(0);
                }
                
                int x = Math.max(dadosFace.tl().x() - 10, 0);
                int y = Math.max(dadosFace.tl().y() - 10, 0);
                putText(imagemColorida, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0,0, 255,5));
                
//                contador++;
//                    if(contador < 50){
//                        
//                      produto = produto + confianca.get(0);                
//                    }else{
//                        camera.stop();
//                    }
                           
            }
            if (contador == 50){
            System.out.println(" Numero de confianção Media: " + produto/contador); 
            }
                    if(cFrame.isVisible()){
                cFrame.showImage(frameCapturado);
            }
     
        }
        
        
        cFrame.dispose();
        camera.stop();
        
    }
    
    
}
