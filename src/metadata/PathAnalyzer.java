package metadata;

import detection.FaceDetection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PathAnalyzer {
	
	@FXML
	private TextField directoryPath;
	
	public void init(){
		System.out.println("utilita");
	}
	public void getPath(){
		System.out.println("eccomi");
		String text = directoryPath.getText();
		System.out.println(text);
		FaceDetection.initProcessing();
	}
}
