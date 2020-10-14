package alex.scrollingFighter;

import java.awt.image.BufferedImage;

class Bow extends Weapon implements Shootable {

    private float projectileSpeed = 1;
    private long startDrawTime = 0;
    private long  endDrawTime = 0;
    public static int id = 1;

    public Bow(BufferedImage image) {
        super(image);
        width = 60;
        height = 100;
        setLocation();

    }

    @Override
    public void use() {
        endDrawTime = System.currentTimeMillis();
        float power = ((endDrawTime - startDrawTime)/1000.0f)*2.0f + .4f;
        if(power > 2f) {
            power = 2f;
        }
        Projectile projectile = new Projectile(rotationAmount, projectileSpeed * power, this);
        Panel.getProjectileManager().addToProjectileList(projectile);
    }

    @Override
    public void charge() {
        startDrawTime = System.currentTimeMillis();
    }
}
