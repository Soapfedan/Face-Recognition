package metadata;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import it.sauronsoftware.jave.*;

public class MetaDataExtractor {
	
		//attributo che indica la durata del video, è statico in modo da richiamarlo ovunque,
		//non ci saranno altri attributi con questo scopo
	public static double duration_video = 0;
	
	public static double fps_video = 0;

/**
 * @param args the command line arguments
 */
	
		//estrae fps e durata video
	public static void extract_metada(File file) {
		//oggetto appartenente a Jave, serve per leggere i metadati (try catch necessario)
	    Encoder encoder = new Encoder();
	    try {
	    		//crea oggetto MultimediaInfo, cui si passa il file da analizzare
            MultimediaInfo info = encoder.getInfo(file);
            VideoInfo vInfo = info.getVideo();
 
            duration_video = info.getDuration();
            fps_video = vInfo.getFrameRate();
 
        } catch (EncoderException e) {
            e.printStackTrace();
        }
	}
	
		//dovrebbe diventare get_metadata_video, dentro trova sia framerate che duration
	public static void get_duration_video(String path) {
		
		File file = new File(path);
			//l'ho aggiunto qua come prova, andrebbe fatto un metodo proprio per questa funzione
	    	//per poter prendere i metadati devo creare un oggetto di tipo Media
	    	//e far partire il mediaPlayer (setOnReady)
//	    Media ff = new Media(file.toURI().toString());
//	    MediaPlayer mediaPlayer = new MediaPlayer(ff);
	    
	    	//oggetto appartenente a Jave, serve per leggere i metadati (try catch necessario)
	    Encoder encoder = new Encoder();
	    try {
	    		//crea oggetto MultimediaInfo, cui si passa il file da analizzare
            MultimediaInfo info = encoder.getInfo(file);
            VideoInfo vInfo = info.getVideo();
 
            duration_video = info.getDuration();
            fps_video = vInfo.getFrameRate();
 
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    
	/*    
	    mediaPlayer.setOnReady(new Runnable() {
	    		    	
	        @Override
	        public void run() {
	        	//calculate the duration of the video and it has putted on the
	        	//Pair that has as key path and as value Video(path) 
	        	duration_video = ff.getDuration().toSeconds();
	        	//get the element and set his duration
	            Directory.getVideos().get(path).setDuration(duration_video);
	            System.out.println("video "+path+" durata "+duration_video);
	        }  
	    });
*/	
	
	
	}
}


