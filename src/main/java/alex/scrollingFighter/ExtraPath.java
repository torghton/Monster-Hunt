package alex.scrollingFighter;

import java.awt.*;
import java.awt.image.BufferedImage;

class ExtraPath implements Animateable {
    private Point location;
    private Dimension size;
    private Color color;
    private int id = 0;

    public ExtraPath(Point location, Dimension size, Color color) {
        this.location = location;
        this.size = size;
        this.color = color;
        Panel.getAnimator().addToAnimateableLinkedList(0, this, 0);
    }


    @Override
    public void animate(BufferedImage image, Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect((int) (location.getX()),(int) (location.getY()),(int) (size.getWidth()),(int) (size.getHeight()));
    }
}
