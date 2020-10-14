package alex.scrollingFighter;

import java.awt.*;
import java.awt.image.BufferedImage;

class Entity implements Moveable, Animateable {

    private int health;


    private int id;
    protected BufferedImage texture;
    private Point location;
    private Dimension size;
    private float mostRectentVelocity = 5;
    private int layer;

    public Entity(int layer, int health, Dimension size, Point location, int id) {
        this.layer = layer;
        this.id = id;
        this.texture = Runner.getImages().get(id);
        this.health = health;
        this.size = size;
        this.location = location;
    }

    public Dimension getSize() {
        return size;
    }

    public Point getLocation() {
        return location;
    }

    public Point getCenter() { return new Point((int) (getLocation().getX() + getSize().getWidth()/2), (int) (getLocation().getY() + getSize().getHeight()/2)); }

    public int getLayer() {
        return layer;
    }

    public int getHealth() { return health; }

    public int getID() { return id; }

    public void setHealth(int health) { this.health = health; }

    public void changeHealth(int amount) {
        setHealth(getHealth() + amount);
    }

    @Override
    public void move(float amount) {
        getLocation().x += amount;
        if(amount != 0) {
            mostRectentVelocity = amount;
        }
    }

    @Override
    public void animate(BufferedImage image, Graphics graphics) {
        if(mostRectentVelocity > 0) {
            graphics.drawImage(texture, (int) (getLocation().getX()), (int) (getLocation().getY()), (int) (getSize().getWidth()), (int) (getSize().getHeight()), null);
        } else {
            graphics.drawImage(texture, (int) (getLocation().getX() + getSize().getWidth()), (int) (getLocation().getY()), (int) (-getSize().getWidth()), (int) (getSize().getHeight()), null);
        }

    }
}
