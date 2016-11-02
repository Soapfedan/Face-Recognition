package core.detection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.jcodec.api.*;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.media.*;
import javax.media.Time;



public class DecomposeVideoFrames {
	
		//attributo che indica la durata del video, è statico in modo da richiamarlo ovunque,
		//non ci saranno altri attributi con questo scopo
	public static double duration_video = 0;
		//imposta la durata del video
	private void set_duration(double d) {
		duration_video = d;
	}
    /**
     * @param args the command line arguments
     */
	public static void get_duration_video() {
		File file = new File("resources/videoprova.mp4");
			//l'ho aggiunto qua come prova, andrebbe fatto un metodo proprio per questa funzione
	    	//per poter prendere i metadati devo creare un oggetto di tipo Media
	    	//e far partire il mediaPlayer (setOnReady)
	    Media ff = new Media(file.toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(ff);
	    
	    	//se non lo fo partire non si può leggere la durata
	    	//dentro run dovrebbe fare solamente una iterazione
	    mediaPlayer.setOnReady(new Runnable() {
	    	Thread currentThread = Thread.currentThread();
	    	
	        @Override
	        public void run() {
	            duration_video = ff.getDuration().toSeconds();
	            	//ferma tutto, non capisco perchè
	            interrupt();
        }
        
        public void interrupt() {
            currentThread.interrupt();
        }
        
    });
                
    System.out.println("durata video: " + duration_video);
	}
	
    public static BufferedImage decomposeVideo(int i) throws IOException, JCodecException {

        BufferedImage bi=null;
    	get_duration_video();
        File file = new File("resources/videoprova.mp4");
        	Picture frame = FrameGrab.getNativeFrame(file, i);
        	
            Transform transform = ColorUtil.getTransform(frame.getColor(), ColorSpace.RGB);
            Picture rgb = Picture.create(frame.getWidth(), frame.getHeight(), ColorSpace.RGB);
            transform.transform(frame, rgb);
            bi = ImageConverter.toBufferedImage(rgb);
            
            	
            
            //ImageIO.write(bi, "png", new File("C:/Users/Federico-PC/Desktop/frames/frame"+i+".png"));
            
       // System.out.println("Time Used:" + (System.currentTimeMillis() - time)+" Milliseconds");
		return bi;
    }
    
   
}