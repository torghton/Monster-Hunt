package alex.WorldExplorer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character extends alex.commonInheritance.drawableAttributes implements alex.scrollingFighter.Animateable {

    private int facing;

    public Character() {
        super();
        facing = 0;

    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public int getFacing() {
        return facing;
    }

    @Override
    public void animate(BufferedImage image, Graphics graphics) {
        graphics.drawImage(image, (int) (getLocation().getX()),(int) (getLocation().getY()),(int) (getSize().getWidth()),(int) (getSize().getHeight()), null);
    }
}
