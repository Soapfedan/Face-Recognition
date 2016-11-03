package video;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.jcodec.api.*;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.*;

import utility.ImageConverter;



public class DecomposeVideoFrames {
		
    public static BufferedImage decomposeVideo(int i) throws IOException, JCodecException {

        BufferedImage bi=null;
        
        File file = new File("resources/videoprova.mp4");
        	Picture frame = FrameGrab.getNativeFrame(file, i);
        	
            Transform transform = ColorUtil.getTransform(frame.getColor(), ColorSpace.RGB);
            Picture rgb = Picture.create(frame.getWidth(), frame.getHeight(), ColorSpace.RGB);
            transform.transform(frame, rgb);
            bi = ImageConverter.toBufferedImage(rgb);
            
            	
            
            //ImageIO.write(bi, "png", new File("C:/Users/Federico-PC/Desktop/frames/frame"+i+".png"));
            
       // System.out.println("Time Used:" + (System.currentTimeMillis() - time)+" Milliseconds");
		return bi;
    }
    
   
}