package video;

import java.io.File;
import java.util.ArrayList;

import org.opencv.core.MatOfRect;

import metadata.MetaDataExtractor;

public class Video {
	private int id;
	private double duration;
	private String path;
	private File file;
		//flag per capire se i thread già ci abbiano lavorato
	private boolean visitated;
	private double framerate;
	private ArrayList<MatOfRect> detectedFaces;
	private int category;
	
		//video si costruisce con questo
	public Video(String percorso, double duration_video, double frame_rate) {
		path = percorso;
		duration = duration_video/1000;
		framerate = frame_rate;
		setCategory();
		visitated = false;
	}
	
	public Video(String percorso){
		
		path = percorso;
		visitated = false;
		//It calls the method that calculate the duration of the video that has this PATH
		//MetaDataExtractor.get_duration_video(path);
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
	/**
	 * Add a face that has founded by the algorithm 
	 * 
	 * @param face
	 */
	public void addFace(MatOfRect face){
		detectedFaces.add(face);
	}
	
	public void setCategory(){
		double d=duration/60;
		if(d<2){//da 0 a 1.59 minuti
			category = 1;
		}else if(d<15){//da 2 a 14.59 minuti
					category = 2;
			  }else if(d<60){//da 15 a 59.99 minuti
				  		category = 3;
			  		}else if(d<120){//da 60 a 119.99 minuti
			  				category = 4;
			  			  }else{//oltre le due ore
			  				  	category = 5;
			  			  }
	}
}

