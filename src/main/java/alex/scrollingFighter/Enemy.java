package alex.scrollingFighter;

import java.awt.Dimension;
import java.awt.Point;

public abstract class Enemy extends Entity {
    protected float coinsDropped = 0;
    private long lastAttackTime = 0;
    private float minTimeBetweenAttacks;

    public Enemy(int layer, int health, Dimension size, Point location, float timeBetweenAttacks, int id) {
        super(layer, health, size, location, id);
        this.minTimeBetweenAttacks = timeBetweenAttacks;
    }

    public void attack(alex.scrollingFighter.Character characterToAttack) {
        if(System.currentTimeMillis() - lastAttackTime > minTimeBetweenAttacks) {
            characterToAttack.changeByHealth(-1);
            lastAttackTime = System.currentTimeMillis();
        }
    }

    public float getCoinsDropped() {
        return coinsDropped;
    }
}
