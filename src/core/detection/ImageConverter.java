package core.detection;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;

import org.jcodec.common.model.Picture;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javafx.scene.image.Image;




public class ImageConverter {
	
	public static BufferedImage toBufferedImage(Picture src) {
	       BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
	
	        toBufferedImage(src, dst);
	
	        return dst;
	}
	 
	public static void toBufferedImage(Picture src, BufferedImage dst) {
	        byte[] data = ((DataBufferByte) dst.getRaster().getDataBuffer()).getData();
	        int[] srcData = src.getPlaneData(0);
	        for (int i = 0; i < data.length; i++) {
	            data[i] = (byte) srcData[i];
	       }
	}
	
	/**
	 * Convert a Mat object (OpenCV) in the corresponding Image for JavaFX
	 * 
	 * @param frame
	 *            the {@link Mat} representing the current frame
	 * @return the {@link Image} to show
	 */
	
	public static Image mat2Image(Mat frame)
		{
			// create a temporary buffer
			MatOfByte buffer = new MatOfByte();
			// encode the frame in the buffer, according to the PNG format
			Imgcodecs.imencode(".png", frame, buffer);
			// build and return an Image created from the image encoded in the
			// buffer
			return new Image(new ByteArrayInputStream(buffer.toArray()));
	}
	
	/**
	 * 
	 * Convert a bufferedImage to a Mat object (OpenCV)
	 * 
	 * @param bi
	 * @return
	 */
	
	public static Mat bufferedImageToMat(BufferedImage bi) {
			  Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
			  byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
			  mat.put(0, 0, data);
			  return mat;
	}
	
	/**  
	 * Converts/writes a Mat into a BufferedImage.  
	 *  
	 * @param matrix Mat of type CV_8UC3 or CV_8UC1  
	 * @return BufferedImage of type TYPE_3BYTE_BGR or TYPE_BYTE_GRAY  
	 */  
	public static BufferedImage matToBufferedImage(Mat matrix, BufferedImage bimg)
	{
	    if ( matrix != null ) { 
	        int cols = matrix.cols();  
	        int rows = matrix.rows();  
	        int elemSize = (int)matrix.elemSize();  
	        byte[] data = new byte[cols * rows * elemSize];  
	        int type;  
	        matrix.get(0, 0, data);  
	        switch (matrix.channels()) {  
	        case 1:  
	            type = BufferedImage.TYPE_BYTE_GRAY;  
	            break;  
	        case 3:  
	            type = BufferedImage.TYPE_3BYTE_BGR;  
	            // bgr to rgb  
	            byte b;  
	            for(int i=0; i<data.length; i=i+3) {  
	                b = data[i];  
	                data[i] = data[i+2];  
	                data[i+2] = b;  
	            }  
	            break;  
	        default:  
	            return null;  
	        }  

	        // Reuse existing BufferedImage if possible
	        if (bimg == null || bimg.getWidth() != cols || bimg.getHeight() != rows || bimg.getType() != type) {
	            bimg = new BufferedImage(cols, rows, type);
	        }        
	        bimg.getRaster().setDataElements(0, 0, cols, rows, data);
	    } else { // mat was null
	        bimg = null;
	    }
	    return bimg;  
	}   
}
