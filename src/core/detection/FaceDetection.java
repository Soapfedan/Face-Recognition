package core.detection;

import java.awt.event.WindowEvent;

import org.opencv.core.Core;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * The main class for a JavaFX application. It creates and handle the main
 * window with its resources (style, graphics, etc.).
 * 
 * This application handles a video stream and try to find any possible human
 * face in a frame. It can use the Haar or the LBP classifier.
 * 
 *
 * 
 */
public class FaceDetection extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			// load the FXML resource
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FaceDetection.fxml"));
			BorderPane root = (BorderPane) loader.load();
			//primaryStage.setOnCloseRequest(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSED));
           
     
			// set a whitesmoke background
			root.setStyle("-fx-background-color: whitesmoke;");
			// create and style a scene
			Scene scene = new Scene(root, 800, 600);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// create the stage with the given title and the previously created
			// scene
			primaryStage.setTitle("Face Detection and Tracking");
			primaryStage.setScene(scene);
			// show the GUI
			primaryStage.show();
			
			// init the controller
			FaceDetectionController controller = loader.getController();
			controller.init();
			
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		System.load("C:/OpenCV/opencv/build/java/x64/opencv_java310.dll");
		
		// load the native OpenCV library
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		launch(args);
	}
}
