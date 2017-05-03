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
	
	/**
	 * Search for videos in a given pathname, then print duration and framerate.
	 * @param path: pathname where the method will look in.
	 * @return: flag that indicates the state of the search:
	 * 			0: path was empty.
	 * 			1: at least one video found.
	 * 			2: path was not a directory.
	 * 			3: path was an empty directory.
	 * 			4: some exception was thrown.
	 * 			5: no video found.
	 */
	public static int loadVideos(String path) {
		
		int state = 0;
		videos = new HashMap<String,Video>();

		if (!path.isEmpty()){
			File dir = new File(path);
			if(dir.isDirectory()){
				File files[] = dir.listFiles();
				if(files.length > 0){
					try {
						int i = 0;
						for(File f : files){		
							if (extensionControl(f)) { 
								i++;
								MetaDataExtractor.extract_metada(f);
								//codice per inserire file nella cartella
								Video vid = new Video(f.getPath(), MetaDataExtractor.duration_video, MetaDataExtractor.fps_video);
								videos.put(f.getPath(), vid);
								vid.printData();
							}
						}
						if (i > 0)
							// some video was found
							state = 1;
						else
							state = 5;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						// exception
						state = 4;
					}
				}
				else
					// no files in dir
					state = 3;
			}
			else
				// dir non è una directory
				state = 2;
		}
		return state;
	}
	
		//controlla se file sia video (considerando solamente formati che terminano con avi o mp4)
	private static boolean extensionControl(File f) {
		boolean b = false;
		String tipo = null;
			//visto che viene effettuato un controllo sulla stringa (cercato il punto)
			//può venir fuori il problema della sua presenza
			//vengono analizzati solo i file nella cartella che non sono directory
		if (!f.isDirectory()) {
			Path path;
			
			try {
	             path = Paths.get(f.getAbsolutePath()); 
	             tipo = Files.probeContentType(path);
	             
	        } catch (Exception x) {
	        	  x.printStackTrace();
	        }
				//vengono selezionati solo i caratteri a partire dall'ultimo punto (l'estensione del file)

			try {
				String format = "";
				if(tipo != null){
					format = tipo.substring(0, tipo.indexOf("/"));
					if(!format.isEmpty() && format.equals("video")) {
						b = true;
					}
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
