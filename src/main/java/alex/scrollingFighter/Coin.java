package alex.scrollingFighter;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;

public class Coin {
    private Velocity velocity;
    private Point location;
    private Dimension size;
    private Color coinColor;

    private float value = 0;
    private boolean onGround = false;

    public Coin(Point location, int typeOfCoin) {
        velocity = new Velocity((int) ((Math.random()*60)-30), -30);
        this.location = new Point(location);
        this.size = new Dimension(50,50);
        Panel.getCoinManager().addCoin(this);
        setValues(typeOfCoin);
    }

    private void setValues(int typeOfCoin) {
        switch (typeOfCoin) {
            // Copper Coin
            case 1:
                // Sets color to copper
                coinColor = new Color(191, 129, 36);
                // Sets coins value to 0.1
                value = 0.1f;
                break;
            case 2:
                coinColor = new Color(176, 176, 176);
                value = 1;
                break;
            case 3:
                coinColor = new Color(255, 242, 0);
                value = 10;
                break;
            default:
                try {
                    throw new UnknownTypeException("Error: The serial type of coin number inputted is not defined");
                } catch (UnknownTypeException e) {
                    e.printStackTrace();
                }

        }
    }

    public void move() {
        if(velocity.newLocation(location).getY() < 600) {
            location = velocity.newLocation(location);
            onGround = false;
        } else {
            location = new Point((int)(location.getX() + velocity.getXVelocity()), 600);
            onGround = true;
        }

    }

    public void updateVelocity() {
        velocity.affectWithForces();
    }

    public void drawSelf(Graphics graphics) {
        graphics.setColor(coinColor);
        graphics.fillOval((int) location.getX(), (int) location.getY(),(int) size.getWidth(),(int) size.getHeight());
    }

    public Dimension getSize() {
        return size;
    }

    public Point getLocation() {
        return location;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public float getValue() { return value; }

    public Point getCenter() {
        return new Point((int) (getLocation().getX() + getSize().getWidth()/2), (int) (getLocation().getY() + getSize().getHeight()/2));
    }


}
