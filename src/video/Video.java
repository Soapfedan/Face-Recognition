package video;

import java.io.File;

import metadata.MetaDataExtractor;

public class Video {
	private int id;
	private double duration;
	private String path;
	private File file;
		//flag per capire se i thread già ci abbiano lavorato
	private boolean visitated;
	
	public Video(int cod, double duration_video) {
		id = cod;
		duration = duration_video;
		visitated = false;
	}
	
	public Video(String percorso){
		
		path = percorso;
		//It calls the method that calculate the duration of the video that has this PATH
		MetaDataExtractor.get_duration_video(path);
	}
	
	public Video(int cod, double duration_video, String percorso) {
		id = cod;
		duration = duration_video;
		visitated = false;
		path = percorso;
		file = new File (path);
	}
	
	public double getDuration() {
		return duration;
	}
	
	public String getPath() {
		return path;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDuration(double d){
		duration=d;
	}
	
}

