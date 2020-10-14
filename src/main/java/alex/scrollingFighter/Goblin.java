package alex.scrollingFighter;

import java.awt.*;

public class Goblin extends Enemy {
    private float coinsDropped = .5f;

    public Goblin(int layer, int health, Dimension size, Point location) {
        super(layer, health, size, location, 1000, 2);
        Panel.getEntityManager().addToEntityList(this);
        Panel.getEntityManager().addToAnimatable(this, getLayer(), getID());
        super.coinsDropped = this.coinsDropped;
    }



}
