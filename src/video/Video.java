package video;

public class Video {
	int id;
	double duration;
	String path;
		//flag per capire se i thread già ci abbiano lavorato
	boolean visitated;
	
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
	}
	
	private double getDuration() {
		return duration;
	}
	
	private int getId() {
		return id;
	}
	
	
}

