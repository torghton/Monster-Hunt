package alex.scrollingFighter;

import java.awt.*;
import java.awt.image.BufferedImage;

class Weapon implements Useable, Moveable {

    int width = 50, height = 50;
    int rotationAmount = 0;
    protected BufferedImage texture;
    Point location;

    public Weapon(BufferedImage image) {
        texture = image;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public int getRotationAmount(Point Center,Point pointToRotateTowards) {
        rotationAmount = (int) Math.toDegrees(Math.atan2(pointToRotateTowards.getY() - Center.getY(), pointToRotateTowards.getX() - Center.getX()));
        return rotationAmount;

    }

    protected void setLocation() {
        location = new Point((int) (Panel.screenSize.getWidth()/2) - width/2, (int) ((Panel.screenSize.getHeight()/2) - height/2));
    }

    public Point getLocation() {
        return location;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getCenter() {
        return new Point((int) location.x + width/2, (int) location.y + height/2);
    }

    @Override
    public void use() {

    }

    @Override
    public void move(float amount) {
        location.x += amount;
    }
}
