/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.pi.reconhecimento.facial;

import java.awt.event.KeyEvent;
import java.util.Scanner;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_core.Size;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;
import static org.bytedeco.javacpp.opencv_imgproc.resize;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;



/**
 *
 * @author paugusto
 */
public class Captura {
    
    public static void main(String arg[]) throws FrameGrabber.Exception, InterruptedException{
        
        KeyEvent tecla = null;
        OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        camera.start();
        
        CascadeClassifier detectorFace = new CascadeClassifier("src\\recursos\\haarcascade-frontalface-alt.xml");
        
        CanvasFrame cFrame = new CanvasFrame("Preview", CanvasFrame.getDefaultGamma() / camera.getGamma());
        Frame frameCapturado = null;
        Mat imagemColorida = new Mat();
        
        int numeroDeAmostras = 25;
        int amostra = 1;
        System.out.println("Digite seu Id: ");
        Scanner cadastro = new Scanner(System.in);
        int idPessoa = cadastro.nextInt();
        
        
        while((frameCapturado = camera.grab()) != null){
            
            imagemColorida = converteMat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            cvtColor(imagemColorida, imagemCinza, COLOR_BGR2GRAY);
            RectVector facesDetectadas = new RectVector();
            detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 1, 0, new Size(150,150), new Size(500,500));
            if (tecla == null){
                tecla = cFrame.waitKey(5);
            }
            for(int i = 0; i < facesDetectadas.size(); i++){
                Rect dadosFace = facesDetectadas.get(0);
                rectangle(imagemColorida, dadosFace, new Scalar(0, 0, 255,0));
                Mat faceCapturada = new Mat(imagemCinza, dadosFace);
                resize(faceCapturada, faceCapturada, new Size(160,160));
                
                  if (tecla == null){
                tecla = cFrame.waitKey(5);
            }
                if(tecla != null){
                    if(tecla.getKeyChar() == 'q'){
                        if(amostra <= numeroDeAmostras){
                            // possivel erro de importação Original(BytePointer,Mat) paliativo(String,Mat)
                          imwrite("src\\fotosTurma\\pessoas." + idPessoa + "." + amostra + ".jpg", faceCapturada);
                            System.out.println("Foto " + amostra + "Capturada\n");
                            amostra++;
                        }
                        
                    }
                    tecla = null;
                }
                
            }
              if (tecla == null){
                tecla = cFrame.waitKey(15);
            }
            if(cFrame.isVisible()){
                cFrame.showImage(frameCapturado);
            }
            
            if(amostra > numeroDeAmostras){
            break;
        }
        }
        
        
        cFrame.dispose();
        camera.stop();
        
    }
    
}
