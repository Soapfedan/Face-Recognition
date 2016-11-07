package metadata;

import detection.FaceDetection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PathAnalyzer {
	
	//the text extracted from the textfield and it contains the path of the selected directory
	private static String text;
	@FXML
	private TextField directoryPath;
	
	public void init(){
		//System.out.println("utilita");
	}
	
	public void getPath(){
		//System.out.println("eccomi");
		text = directoryPath.getText();
		System.out.println(text);
		//FaceDetection.initProcessing();
		boolean b = Directory.loadVideos(text);
	}
}
