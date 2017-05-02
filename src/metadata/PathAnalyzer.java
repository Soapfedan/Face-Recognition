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
	
	// Metodo chiamato alla premuta de bottone, vedi PathReader.fxml
	public void getPath(){
		text = directoryPath.getText();
		System.out.println(text);
		boolean b = Directory.loadVideos(text);
	}
}
