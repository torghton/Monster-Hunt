package alex.scrollingFighter;

import java.awt.*;
import java.awt.Panel;
import java.awt.image.BufferedImage;

class Path implements Animateable, Panable {
    private Point location;
    private Dimension size;
    private BufferedImage texture;
    public static int id = 3;

    public Path(Point location, Dimension size) {
        this.location = location;
        this.size = size;
        this.texture = Runner.getImages().get(id);
        alex.scrollingFighter.Panel.getAnimator().addToAnimateableLinkedList(id, this, 0);
        alex.scrollingFighter.Panel.getCamera().addToMoveAbleObjects(this);
    }

    public Dimension getSize() { return size; }

    public Point getLocation() { return location; }

    public BufferedImage getTexture() {
        return texture;
    }

    @Override
    public void animate(BufferedImage image, Graphics graphics) {
        graphics.drawImage(texture,(int) (location.getX()),(int) (location.getY()), (int) (size.getWidth()), (int) (size.getHeight()), null);
    }

    @Override
    public void pan(float amount) {
        location.x += amount;
    }
}
