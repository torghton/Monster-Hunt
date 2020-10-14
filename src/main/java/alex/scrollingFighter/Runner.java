package alex.scrollingFighter;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Runner {
    private static Map<Integer, BufferedImage> images;

    public static Map<Integer, BufferedImage> getImages() {
        return images;
    }

    private Runner() {
        images = new HashMap<>();
        try {
            Object obj = new JSONParser().parse(new FileReader("JsonFiles/Imagedata/Imagedata.json"));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray elements = (JSONArray) (jsonObject.get("images"));
            Iterator itr = elements.iterator();
            while(itr.hasNext()) {
                JSONObject values = (JSONObject) itr.next();
                //images.put(values.get("id"), ImageProcessor.ProcessImage((String) values.get("directory")));
                BufferedImage image = ImageProcessor.ProcessImage((String) values.get("directory"));
                Long id = (Long) values.get("id");
                images.put(id.intValue(), image);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args) {
        // Calls the constructor to initialize all the images
        new Runner();

        // Instantiates frame
        JFrame frame = new JFrame();

        // Creates instances of the panels
        alex.WorldExplorer.Panel worldExplorePanel = new alex.WorldExplorer.Panel();
        Panel scrollFightingPanel = new Panel();

        // Saves the panels to an array for easy access
        alex.commonInheritance.Panel[] panels = {worldExplorePanel, scrollFightingPanel};

        // Adds the panels to the frame
        frame.add(scrollFightingPanel);

        // Sets the frame settings
        frame.setUndecorated(true);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setTitle("Game");
        frame.setLocation(0, 0);
        frame.setVisible(true);
        frame.setResizable(false);

        scrollFightingPanel.GameLoop(10);





    }
}
