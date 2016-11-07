package metadata;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import video.Video;

public class Directory {
	private String path;
	private static HashMap <String,Video> videos;
	
	//cerca i file video dentro la directory passata come parametro
		//forse va messo void e non boolean
	public static boolean loadVideos(String s) {
		//TODO dobbiamo fare un filtraggio della stringa 
		
		videos = new HashMap<String,Video>();
		boolean b = false;
			//dir rappresenta la cartella corrente
		File dir = new File(s);
			//check if the number of files that are contain in the directory is not zero or if the path is equal the empty string
		if(dir.listFiles().length==0 || s.equals("")){
			return false;
		}
		//creato un array di file contenuti nella cartella
		File files[]=dir.listFiles();	
			//per ogni file viene effettuato il controllo sul formato
		try {
			for( File f : files ){		
				if (extensionControl(f)) {   
					MetaDataExtractor.extract_metada(f);
					//codice per inserire file nella cartella
					Video vid = new Video(f.getPath(),MetaDataExtractor.duration_video,MetaDataExtractor.fps_video);
					videos.put(f.getPath(), vid);
					vid.printData();;
				}
			}
			b=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b=false;
		}
		return b;
	}
		//controlla se file sia video (considerando solamente formati che terminano con avi o mp4)
	private static boolean extensionControl(File f) {
		boolean b = false;
		String tipo = null;
			//visto che viene effettuato un controllo sulla stringa (cercato il punto)
			//può venir fuori il problema della sua presenza
			//vengono analizzati solo i file nella cartella che non sono directory
		if (!f.isDirectory()) {
			String s = f.getAbsolutePath();
			Path path;
			
			try {
	             path = Paths.get(f.getAbsolutePath()); 
	             tipo = Files.probeContentType(path);
	             //System.out.println("tipo: " + tipo.substring(0,tipo.indexOf("/")));
	             
	          } catch (Exception x) {
	          }
				//vengono selezionati solo i caratteri a partire dall'ultimo punto (l'estensione del file)
			
			try {
				String format = tipo.substring(0,tipo.indexOf("/"));
				if(format.equals("video")) {
					b = true;
				}
			}
			catch (Exception exc) {
				System.out.println(exc);
			}
			
		}
		return b;
	}
	
	public static HashMap<String, Video> getVideos() {
		return videos;
	}
}
