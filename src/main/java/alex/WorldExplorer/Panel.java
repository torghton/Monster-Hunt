package alex.WorldExplorer;

import alex.scrollingFighter.Animator;

import java.awt.*;

public class Panel extends alex.commonInheritance.Panel {

    private static Animator animator = new Animator();


    // Character
    private alex.scrollingFighter.Character character;

    //World


    public Panel() { }

    public static Animator getAnimator() {
        return animator;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawRect(10,10,100,100);
        repaint();
    }

    @Override
    public void GameLoop(int delayInMilliseconds) {

    }
}
