package alex.scrollingFighter;

import java.awt.Point;

class Velocity {

    private float XVelocity;
    private float YVelocity;


    public Velocity(float XVelocity, float YVelocity) {
        this.XVelocity = XVelocity;
        this.YVelocity = YVelocity;
    }

    public void affectWithForces() {
        // Random Set Amount for Air Drag
        XVelocity *= 0.9;
        // Gravity To Counter Y Velocity
        YVelocity += 4;
    }

    public float getXVelocity() {
        return XVelocity;
    }

    public float getYVelocity() {
        return YVelocity;
    }

    public Point newLocation(Point location) {return new Point( (int) (location.x + this.getXVelocity()),(int) (location.y + this.getYVelocity())); }
}
