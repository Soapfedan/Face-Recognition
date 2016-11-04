package metadata;

import detection.FaceDetection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PathAnalyzer {
	
	private static String text;
	@FXML
	private TextField directoryPath;
	
	public void init(){
		System.out.println("utilita");
	}
	
	public void getPath(){
		System.out.println("eccomi");
		text = directoryPath.getText();
		System.out.println(text);
		FaceDetection.initProcessing();
		boolean b = Directory.isVideos(text);
	}
}
