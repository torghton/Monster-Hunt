package alex.scrollingFighter;

import java.awt.Point;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EntityManager {
    public  List<Entity> entityList = new CopyOnWriteArrayList<>();

    private void moveAllEntities(Point characterLocation) {
        for(Entity entity : entityList) {
            if(entity instanceof Enemy) {
                // if the enemy is next to the player
                if(Math.abs(characterLocation.getX() - entity.getCenter().getX()) < entity.getSize().getWidth()) {
                    // Do Nothing
                // if the enemy is to the left of the player, move left.
                } else if(characterLocation.getX() < entity.getLocation().getX()) {
                    entity.move(-4);
                // if the enemy is to the right of the player, move right.
                } else if(characterLocation.getX() > entity.getLocation().getX()) {
                    entity.move(4);
                }

            }
        }
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public void attackCharacter(alex.scrollingFighter.Character character) {
        for(Entity entity: entityList) {
            if(entity instanceof Enemy) {
                if(Math.abs(character.getCenter().getX() - (entity.getSize().getWidth() + entity.getCenter().getX())) < entity.getSize().getWidth()/2) {
                    ((Enemy) entity).attack(character);
                }

            }
        }
    }

    public void updateAll(alex.scrollingFighter.Character character) {
        moveAllEntities(character.getCenter());
        attackCharacter(character);
    }

    public void addToEntityList(Entity entity) {
        entityList.add(entity);
    }

    public void addToAnimatable(Animateable animateable, int layer, int id) {
        Panel.getAnimator().addToAnimateableLinkedList(layer, animateable, id);
    }

    public void testForHit() {
        for(Entity entity: entityList) {
            for (Projectile projectile: Panel.getProjectileManager().getProjectiles()) {
                if(checkForCollision((int) entity.getLocation().getX(),(int) entity.getLocation().getY(),
                        (int) projectile.getLocation().getX(), (int) projectile.getLocation().getY(),
                        entity.getSize().width, entity.getSize().height)) {

                    entity.changeHealth(-1);

                    if(entity.getHealth() <= 0) entityDeath(entity);

                    Panel.getProjectileManager().removeProjectile(projectile);
                }
            }
        }
    }

    private void entityDeath(Entity entity) {
        if(entity instanceof Enemy) {
            dropCoins(entity);
        }
        Panel.getAnimator().removeFromAnimateableLinkedList(entity, (Integer) entity.getLayer(), entity.getID());
        entityList.remove(entity);
    }

    private boolean checkForCollision(int x1, int y1, int x2, int y2, int width1, int height1) {
        if(x1 <= x2 && x2 <= width1 + x1) {
            if(y1 <= y2 && y2 < height1 + y1) {
                return true;
            }
        }
        return false;
    }

    private void dropCoins(Entity entity) {
        float coinsDropped = ((Enemy) entity).getCoinsDropped();
        while(coinsDropped >= 10) {
            coinsDropped -= 10;
            new Coin(entity.getCenter(), 3);
        }
        while(coinsDropped >= 1) {
            coinsDropped -= 1;
            new Coin(entity.getCenter(), 2);
        }
        while(coinsDropped > 0) {
            coinsDropped -= 0.1;
            new Coin(entity.getCenter(), 1);
        }
    }

}
