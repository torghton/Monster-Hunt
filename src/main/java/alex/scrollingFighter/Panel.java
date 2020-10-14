package alex.scrollingFighter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Panel extends alex.commonInheritance.Panel implements MouseListener, KeyListener  {

    private static EntityManager entityManager = new EntityManager();
    private static ProjectileManager projectileManager = new ProjectileManager();
    private static CoinManager coinManager = new CoinManager();
    private static PathManager pathManager;
    private static Camera camera;
    private static alex.scrollingFighter.Character character;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    private Point mousePosition = new Point(getSize().width, getSize().height);
    private int rotationAmount = 0;
    private float CameraPanSpeed = -1;

    public Panel() {
        super();
        setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);
        setBackground(new Color(0, 245, 237));
        camera = new Camera();
        character = new alex.scrollingFighter.Character();
        pathManager = new PathManager(new Path(new Point(0,600), new Dimension(150, 75)));
    }

    static Camera getCamera() { return camera; }

    static EntityManager getEntityManager() {
        return entityManager;
    }

    static ProjectileManager getProjectileManager() {
        return projectileManager;
    }

    static CoinManager getCoinManager() {
        return coinManager;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // TODO: Make a neater way to draw the amount of cash you have
        Font font = new Font("Courier", Font.ROMAN_BASELINE, 50);
        g.setFont(font);
        getAnimator().animateAll(g);
        // Rounds down
        g.drawString("Coins: " + getCoinManager().money.intValue(), 100,100);

        Weapon itemHeld = character.getItemHeld();

        getCoinManager().drawCoins(g);

        // Draws The character to a blue color
        character.drawSelf(g, Color.GRAY);

        // Draws all entities, so far there only has goblin
        g.setColor(Color.GREEN);
        //getEntityManager().drawEntities(g);

        // Draws all projectiles, only from the character for now though.
        g.setColor(Color.GRAY);
        getProjectileManager().drawAllProjectiles(g);

        // Rotates and draws the characters item held.
        rotateAndDrawImage(g, itemHeld.getCenter(), rotationAmount);

        repaint();
    }

    private void rotateAndDrawImage(Graphics g, Point center, int rotationAmount) {
        Graphics2D g2d=(Graphics2D)g;
        Weapon itemHeld = character.getItemHeld();
        g2d.translate(center.getX(), center.getY());
        g2d.rotate(rotationAmount * Math.PI/180);
        g2d.drawImage(itemHeld.getTexture(), -itemHeld.getWidth()/2,-itemHeld.getHeight()/2, itemHeld.getWidth(), itemHeld.getHeight(),null);
        g2d.dispose();
    }




    // Runs a while loop after the JFrame and JPanel is initialized, then starts a look that has a delay of n seconds
    public void GameLoop(int delayInMilliseconds) {
        while (true) {
            try {
                Thread.sleep(delayInMilliseconds);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // Grabs the mouses Position
            mousePosition = MouseInfo.getPointerInfo().getLocation();
            rotationAmount = character.getItemHeld().getRotationAmount(character.getItemHeld().getCenter(),mousePosition);
            character.move(character.getMoveVelocity());
            camera.Pan(CameraPanSpeed);

            pathManager.UpdatePaths();
            getCoinManager().update();
            getCoinManager().testForCollided(character);

            if(System.nanoTime() % 10 == 0) {
                getProjectileManager().moveAllProjectiles();
            }

            if(System.currentTimeMillis()%1000 < 10) {
                new Goblin(2,2,new Dimension(150,150), new Point((int) (Math.random()*screenSize.width),500));
            }
            getProjectileManager().cleanUpAllProjectiles();
            getEntityManager().updateAll(character);
            getEntityManager().testForHit();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getButton() == 1) {
            if (character.getItemHeld() instanceof Shootable) {
                ((Bow) character.getItemHeld()).charge();
            } else if (character.getItemHeld() != null) {
                character.getItemHeld().use();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == 1) {
            if(character.getItemHeld() instanceof Shootable) {
                character.getItemHeld().use();
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(character.getMoveVelocity() == 0) {
            // Moves the character right
            if(e.getKeyCode() == 39 || e.getKeyCode() == 68) {
                character.setMoveVelocity(5);
            }
            if(e.getKeyCode() == 37 || e.getKeyCode() == 65) {
                character.setMoveVelocity(-5);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(character.getMoveVelocity() >= 5 && (e.getKeyCode() == 39 || e.getKeyCode() == 68)) {
            character.setMoveVelocity(0);
        } else if(character.getMoveVelocity() <= -5 && (e.getKeyCode() == 37 || e.getKeyCode() == 65)) {
            character.setMoveVelocity(0);
        }

    }
}
