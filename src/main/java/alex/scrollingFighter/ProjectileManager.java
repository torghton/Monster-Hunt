package alex.scrollingFighter;


import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class ProjectileManager {

    private List<Projectile> projectiles = new CopyOnWriteArrayList<>();

    public void moveAllProjectiles() {
        for(Projectile projectile: projectiles) {
            projectile.move();
            projectile.calculateNewVelocity();
        }
    }

    public void cleanUpAllProjectiles() {
        for(Projectile projectile: projectiles) {
           if(projectile.checkForAbleToBeDeleted()) {
               projectiles.remove(projectile);
           }
        }
    }

    public void addToProjectileList(Projectile projectile) {
        projectiles.add(projectile);
    }

    public void drawAllProjectiles(Graphics graphics) {
        for(Projectile projectile: projectiles) {
            graphics.fillOval((int) projectile.getLocation().getX(), (int) projectile.getLocation().getY(), projectile.getSize(), projectile.getSize());
        }

    }

    public void removeProjectile(Object object) {
        projectiles.remove(object);
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }


}
