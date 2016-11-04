package metadata;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MetaDataExtractor {
	
		//attributo che indica la durata del video, è statico in modo da richiamarlo ovunque,
		//non ci saranno altri attributi con questo scopo
	public static double duration_video = 0;
	//imposta la durata del video
	public static void set_duration(double d) {
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
	   
	    	Runnable durationRun = 	new Runnable() {
	    	Thread currentThread = Thread.currentThread();
	    	
	        @Override
	        public void run() {
	        	duration_video = ff.getDuration().toSeconds();
	            	//ferma tutto, non capisco perchè
	            
	            //interrupt();
	    }
	    
	    public void interrupt() {
	        currentThread.interrupt();
	    }
	    
	};
	 Thread threadMediaData = new Thread (durationRun);
	 threadMediaData.start();
	 try {
		threadMediaData.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 mediaPlayer.setOnReady(durationRun);
	
	}
	
	public static void get_duration_video(String path) {
		
		File file = new File(path);
			//l'ho aggiunto qua come prova, andrebbe fatto un metodo proprio per questa funzione
	    	//per poter prendere i metadati devo creare un oggetto di tipo Media
	    	//e far partire il mediaPlayer (setOnReady)
	    Media ff = new Media(file.toURI().toString());
	    MediaPlayer mediaPlayer = new MediaPlayer(ff);
	    
	    	//se non lo fo partire non si può leggere la durata
	    	//dentro run dovrebbe fare solamente una iterazione
	   
	    mediaPlayer.setOnReady(new Runnable() {
	    		    	
	        @Override
	        public void run() {
	        	duration_video = ff.getDuration().toSeconds();
	            Directory.getVideos().get(path).setDuration(duration_video);
	            System.out.println("video "+path+" durata "+duration_video);
	        }  
	    });
	
	
	
	}
}
