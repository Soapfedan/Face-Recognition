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
	private double framerate;
	
		//video si costruisce con questo
	public Video(String percorso, double duration_video, double frame_rate) {
		path = percorso;
		duration = duration_video;
		framerate = frame_rate;
		visitated = false;
	}
	
	public Video(String percorso){
		
		path = percorso;
		visitated = false;
		//It calls the method that calculate the duration of the video that has this PATH
		//MetaDataExtractor.get_duration_video(path);
	}
	
	public Video(int cod, double duration_video, String percorso) {
		id = cod;
		duration = duration_video;
		visitated = false;
		path = percorso;
		file = new File (path);
	}
	
	public Video(int cod, double duration_video, String percorso, double frame_rate) {
		id = cod;
		duration = duration_video;
		visitated = false;
		path = percorso;
		file = new File (path);
		framerate = frame_rate;
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
	
	public double getFramerate() {
		return framerate;
	}
	
	public void setDuration(double d){
		duration=d;
	}
	
	public void printData() {
		System.out.println("path: " + path + " duration:" + duration + " framerate: " + framerate);
	}
	
}

