package metadata;

import detection.FaceDetection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PathAnalyzer {
	
	//the text extracted from the textfield and it contains the path of the selected directory
	
	private String directoryPath;
	
	public void init(){
		//System.out.println("utilita");
	}
	
	public void getPath(String text){
		directoryPath = text;
		FaceDetection.getTextArea().append("PATH = " + directoryPath + '\n');
		boolean b = Directory.loadVideos(directoryPath);
	}
}
