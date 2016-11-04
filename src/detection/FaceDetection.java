package detection;


import javafx.application.Application;
import javafx.stage.Stage;
import metadata.MetaDataExtractor;
import metadata.PathAnalyzer;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
	private static FXMLLoader applicationLoader;
	private static FXMLLoader pathLoader;
	private static BorderPane applicationPane;
	private static GridPane pathPane;
	private Scene applicationScene;
	private Scene pathScene;
	private Stage stage;
	
	@Override
	public void start(Stage primaryStage)
	{
		stage = new Stage();
		primaryStage = stage;
		try
		{
			// load the FXML resource for the Main application
			
			applicationLoader = new FXMLLoader(getClass().getResource("FaceDetection.fxml"));
			applicationPane = (BorderPane) applicationLoader.load();
			
			// create and style a scene
			applicationScene = new Scene(applicationPane, 800, 600);
			
			
			// load the FXML resource for the path reader
			pathLoader = new FXMLLoader(getClass().getResource("PathReader.fxml"));
			pathPane = (GridPane) pathLoader.load();
			
			// create and style a scene
			pathScene = new Scene(pathPane,800,600);

			
			// create the stage with the given title and the previously created
			// scene
			primaryStage.setTitle("Face Detection and Tracking");
			primaryStage.setScene(pathScene);
			// show the GUI
			primaryStage.show();
			
			MetaDataExtractor.get_duration_video();
			
			PathAnalyzer analyzer = new PathAnalyzer();
			analyzer.init();
			
	
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

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		System.exit(0);
	}
	
	public void initProcessing(){
		
		stae
		stage.setScene(applicationScene);
		// init the controller
		FaceDetectionController controller = applicationLoader.getController();
		controller.init();
	}
}
