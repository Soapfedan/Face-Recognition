package core.detection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
            bi = ImageConverter.toBufferedImage(rgb);
            
            //ImageIO.write(bi, "png", new File("C:/Users/Federico-PC/Desktop/frames/frame"+i+".png"));
            
       // System.out.println("Time Used:" + (System.currentTimeMillis() - time)+" Milliseconds");
		return bi;
    }
    
   
}