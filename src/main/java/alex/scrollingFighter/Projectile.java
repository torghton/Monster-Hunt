package alex.scrollingFighter;

import java.awt.Point;

class Projectile {

    private Point location;

    private int size = 15;
    private int angle = 0;
    private float speed = 0;
    private Velocity velocity;

    /**
     *
     * @param angle The angle in which the projectile is flying
     * @param speed How far the projectile travels with each call of the loop
     * @param itemHeld The item that the Character is currently holding
     *
     *  Creates a Projectile that  stars at the center of the item, and makes it fly strait out when the method is called/
     */
    public Projectile(int angle, float speed, Weapon itemHeld) {
        this.angle = angle;
        this.speed = speed;
        this.location = new Point(itemHeld.getCenter());
        this.velocity = new Velocity((float) Math.toDegrees(Math.sin(Math.toRadians(-angle+90)) * speed), (float) Math.toDegrees(Math.cos(Math.toRadians(-angle+90)) * speed));
    }

    public void move(){
        location = velocity.newLocation(location);
    }

    public boolean checkForAbleToBeDeleted() {
        if(location.getY() > 800) {
            return true;
        }
        return false;
    }

    public void calculateNewVelocity() {
        velocity.affectWithForces();
    }

    public Point getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }
}
