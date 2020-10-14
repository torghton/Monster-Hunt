package alex.scrollingFighter;

import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CoinManager {
    public List<Coin> coinList = new CopyOnWriteArrayList<Coin>();
    public Float money = 0f;

    public void drawCoins(Graphics graphics) {
        for(Coin coin: coinList) {
            coin.drawSelf(graphics);
        }
    }

    public void removeCoin(Coin coin) { coinList.remove(coin); }

    public void addCoin(Coin coin) { coinList.add(coin); }

    public void update() { moveAllCoins(); updateCoinVelocity(); }

    private void updateCoinVelocity() {
        for(Coin coin: coinList) {
            if(!coin.isOnGround()) {
                coin.updateVelocity();
            }

        }
    }

    private void moveAllCoins() {
        for(Coin coin: coinList) {
            if(!coin.isOnGround()) {
                coin.move();
            }
        }
    }

    public void testForCollided(alex.scrollingFighter.Character character) {
        for(Coin coin: coinList) {
            // If the coins distance is less then 5 in the x direction, then it picks it up.
            if(Math.abs(character.getCenter().getX() - coin.getCenter().getX()) < character.getSize().getWidth()) {
                if(Math.sqrt(Math.pow((coin.getCenter().getX() - character.getCenter().getX()), 2) + Math.pow((coin.getCenter().getY() - character.getCenter().getY()),2)) < character.getSize().getWidth()) {
                    money += coin.getValue();
                    removeCoin(coin);
                }
            }

        }

    }
}
