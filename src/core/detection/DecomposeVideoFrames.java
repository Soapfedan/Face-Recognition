package core.detection;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jcodec.api.*;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.*;


public class DecomposeVideoFrames {

    /**
     * @param args the command line arguments
     */
    public static BufferedImage decomposeVideo(int i) throws IOException, JCodecException {

        BufferedImage bi=null;
        	      	
        	Picture frame = FrameGrab.getNativeFrame(new File("resources/videoprova.mp4"), i);
        	
            Transform transform = ColorUtil.getTransform(frame.getColor(), ColorSpace.RGB);
            Picture rgb = Picture.create(frame.getWidth(), frame.getHeight(), ColorSpace.RGB);
            transform.transform(frame, rgb);
            bi = toBufferedImage(rgb);
            
            //ImageIO.write(bi, "png", new File("C:/Users/Federico-PC/Desktop/frames/frame"+i+".png"));
            
       // System.out.println("Time Used:" + (System.currentTimeMillis() - time)+" Milliseconds");
		return bi;
    }
    
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
}