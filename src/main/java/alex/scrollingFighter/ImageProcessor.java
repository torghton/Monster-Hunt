package alex.scrollingFighter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageProcessor {
    public static BufferedImage ProcessImage(String fileLocation) {
        try{
            return ImageIO.read(new File(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<Integer, BufferedImage> ProcessImages(String[] fileLocations) {
        HashMap<Integer, BufferedImage> images = new HashMap<>();
        for(int i = 0; i < fileLocations.length; i++) {
            images.put(i, ProcessImage(fileLocations[i]));
        }
        return images;

    }
}
