package video;

import java.io.File;

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
	
	
}

