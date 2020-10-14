package alex.scrollingFighter;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Character implements Moveable, Animateable, Panable {

    private Weapon itemHeld;
    private Point location;
    private Dimension size;
    private float moveVelocity;
    private int health;

    public Character()
    {
        health = 100;
        moveVelocity = 0;
        itemHeld = new Bow(Runner.getImages().get(Bow.id));
        size = new Dimension(100, 100);
        location = new Point((int) ((Panel.screenSize.getWidth()/2) - size.getWidth()/2), (int) ((Panel.screenSize.getHeight()/2) - size.getHeight()/2));
        Panel.getCamera().addToMoveAbleObjects(this);
    }

    public Weapon getItemHeld() {
        return itemHeld;
    }

    public float getMoveVelocity() { return moveVelocity; }

    public void setMoveVelocity(float newMoveVelocity) { moveVelocity = newMoveVelocity; }

    public Point getLocation() { return location; }

    public Dimension getSize() { return size; };

    public Point getCenter() { return new Point((int) (getLocation().getX() + getSize().getWidth()/2), (int) (getLocation().getY() + getSize().getHeight()/2)); }

    public void drawSelf(Graphics g, Color c) {
        g.setColor(c);
        g.fillRect((int) location.getX(),(int) location.getY(),(int) size.getWidth(),(int) size.getHeight());
        g.fillRect((int) (location.getX() + (size.getWidth()/6)), (int) (location.getY() - ((location.getY()*3)/20)), (int) ((size.getWidth()*2)/3), (int) ((size.getWidth()*2)/3));
    }

    public void changeByHealth(int amount) { health += amount; }

    public int getHealth() { return health; }

    @Override
    public void move(float amount) {
        location.x += amount;
        itemHeld.move(amount);
    }

    @Override
    public void animate(BufferedImage image, Graphics g) {

    }

    @Override
    public void pan(float amount) {
        if(getMoveVelocity() == 0) {
            move(amount);
        }
    }
}
