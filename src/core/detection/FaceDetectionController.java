package core.detection;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The controller associated with the only view of our application. The
 * application logic is implemented here. It handles the button for
 * starting/stopping the camera, the acquired video stream, the relative
 * controls and the face detection/tracking.
 * 
 * 
 * 		
 */
public class FaceDetectionController
{
	// FXML buttons

	@FXML
	private ImageView originalFrame;
	// checkboxes for enabling/disabling a classifier
	// a timer for acquiring the video stream
	private ScheduledExecutorService timer;
	// the OpenCV object that performs the video capture
	private VideoCapture capture;
	// a flag to change the button behavior
	private boolean cameraActive;
	private int founded=0;
	private int total=0;
	
	// face cascade classifier
	private CascadeClassifier faceCascade;
	private int absoluteFaceSize;
	private int frames = 0;
	
	/**
	 * Init the controller, at start time
	 */
	protected void init()
	{
		capture = new VideoCapture();
		faceCascade = new CascadeClassifier();
		absoluteFaceSize = 0;
		loadFaceLibrary("resources/lbpcascades/lbpcascade_frontalface.xml");
		startAnalysis();
	}
	
	/**
	 * The action triggered by pushing the button on the GUI
	 */
	@FXML
	protected void startAnalysis()
	{
		// set a fixed width for the frame
		originalFrame.setFitWidth(600);
		// preserve image ratio
		originalFrame.setPreserveRatio(true);
		
		/*
		if (!this.cameraActive)
		{
						
			// start the video capture
			this.capture.open(0);
			
			// is the video stream available?
			if (this.capture.isOpened())
			{
				this.cameraActive = true;
				*/
				// grab a frame every 33 ms (30 frames/sec)
				Runnable frameGrabber = new Runnable() {
					
					@Override
					public void run()
					{
						Image imageToShow = grabFrame(frames);
						frames+=30;
						originalFrame.setImage(imageToShow);
						//System.out.println("Trovate "+founded+" Totali "+total);
					}
				};
				
				timer = Executors.newSingleThreadScheduledExecutor();
				timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
/*
			}
			else
			{
				// log the error
				System.err.println("Failed to open the camera connection...");
			}
		}
		else
		{
			// the camera is not active at this point
			cameraActive = false;
			
			// stop the timer
			try
			{
				timer.shutdown();
				timer.awaitTermination(33, TimeUnit.MILLISECONDS);
			}
			catch (InterruptedException e)
			{
				// log the exception
				System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
			}
			
			// release the camera
			capture.release();
			// clean the frame
			originalFrame.setImage(null);*/
		}
	
	
	/**
	 * Get a frame from the opened video stream (if any)
	 * 
	 * @return the {@link Image} to show
	 */
	private Image grabFrame(int fr)
	{
		// init everything
		Image imageToShow = null;
		Mat frame = new Mat();
		BufferedImage img = null;
		/*
		// check if the capture is open
		if (this.capture.isOpened())
		{*/
			try
			{
				// read the current frame
				//this.capture.read(frame);
				img = DecomposeVideoFrames.decomposeVideo(fr);
				frame = bufferedImageToMat(img);
				// if the frame is not empty, process it
				if (!frame.empty())
				{
					// face detection
					this.detectAndDisplay(frame);
					
					// convert the Mat object (OpenCV) to Image (JavaFX)
					imageToShow = mat2Image(frame);
				}
				
			}
			catch (Exception e)
			{
				// log the (full) error
				System.err.println("ERROR: " + e);
			}
		
		
		return imageToShow;
	}
	
	/**
	 * Method for face detection and tracking
	 * 
	 * @param frame
	 *            it looks for faces in this frame
	 */
	private void detectAndDisplay(Mat frame)
	{
		MatOfRect faces = new MatOfRect();
		Mat grayFrame = new Mat();
		
		// convert the frame in gray scale
		Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
		// equalize the frame histogram to improve the result
		Imgproc.equalizeHist(grayFrame, grayFrame);
		
		// compute minimum face size (20% of the frame height, in our case)
		if (this.absoluteFaceSize == 0)
		{
			int height = grayFrame.rows();
			if (Math.round(height * 0.2f) > 0)
			{
				this.absoluteFaceSize = Math.round(height * 0.2f);
			}
		}
		/*
		 * The detectMultiScale function detects objects of different sizes in the input image. The detected objects are returned as a list of rectangles.
		 *  The parameters are:

			image: 			 Matrix of the type CV_8U containing an image where objects are detected.
			objects: 	 	 Vector of rectangles where each rectangle contains the detected object.
			scaleFactor:	 Parameter specifying how much the image size is reduced at each image scale.
			minNeighbors:	 Parameter specifying how many neighbors each candidate rectangle should have to retain it.
			flags: 			 Parameter with the same meaning for an old cascade as in the function cvHaarDetectObjects. It is not used for a new cascade.
			minSize: 	 	 Minimum possible object size. Objects smaller than that are ignored.
			maxSize:		 Maximum possible object size. Objects larger than that are ignored.*/
		// detect faces
		faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
				new Size(this.absoluteFaceSize, this.absoluteFaceSize), new Size());
				
		// each rectangle in faces is a face: draw them!
		Rect[] facesArray = faces.toArray();
		if(facesArray.length!=0){
			founded++;
		}
		total++;
		for (int i = 0; i < facesArray.length; i++)
			Imgproc.rectangle(frame, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0), 3);
			
	}
	
	
	/**
	 * Method for loading a classifier trained set from disk
	 * 
	 * @param classifierPath
	 *            the path on disk where a classifier trained set is located
	 */
	private void loadFaceLibrary(String classifierPath)
	{
		// load the classifier(s)
		faceCascade.load(classifierPath);
		
	}
	
	/**
	 * Convert a Mat object (OpenCV) in the corresponding Image for JavaFX
	 * 
	 * @param frame
	 *            the {@link Mat} representing the current frame
	 * @return the {@link Image} to show
	 */
	private Image mat2Image(Mat frame)
	{
		// create a temporary buffer
		MatOfByte buffer = new MatOfByte();
		// encode the frame in the buffer, according to the PNG format
		Imgcodecs.imencode(".png", frame, buffer);
		// build and return an Image created from the image encoded in the
		// buffer
		return new Image(new ByteArrayInputStream(buffer.toArray()));
	}
	
	public static Mat bufferedImageToMat(BufferedImage bi) {
		  Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
		  byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
		  mat.put(0, 0, data);
		  return mat;
		}
}
