package metadata;

import java.io.*;
import java.util.HashMap;
import video.Video;

public class Directory {
	private String path;
	private HashMap <String,Video> videos;
	
		//cerca i file video dentro la directory passata come parametro
		//forse va messo void e non boolean
	public static boolean isVideos(String s) {
		
		boolean b = false;
			//dir rappresenta la cartella corrente
		File dir = new File(s);
			//creato un array di file contenuti nella cartella
		File files[]=dir.listFiles();	
			//per ogni file viene effettuato il controllo sul formato
		for( File f : files ){		
			if (extensionControl(f)) {      
				//codice per inserire file nella cartella
			}
		}
		return b;
	}
		//controlla se file sia avi oppure mp4
	private static boolean extensionControl(File f) {
		boolean b = false;
			//visto che viene effettuato un controllo sulla stringa (cercato il punto)
			//può venir fuori il problema della sua presenza
			//vengono analizzati solo i file nella cartella che non sono directory
		if (!f.isDirectory()) {
			String s = f.getAbsolutePath();
				//vengono selezionati solo i caratteri a partire dall'ultimo punto (l'estensione del file)
			String format = s.substring(s.lastIndexOf("."));
			if(format.equals(".avi") || format.equals(".mp4") || format.equals(".MP4")) {
				b = true;
			}
		}
		return b;
	}
}
