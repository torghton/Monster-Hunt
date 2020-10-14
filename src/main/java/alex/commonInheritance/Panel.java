package alex.commonInheritance;

import alex.scrollingFighter.Animator;

import javax.swing.*;
import java.awt.*;

public abstract class Panel extends JPanel {

    private static Animator animator;

    public Panel() { animator = new Animator(); }

    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Dimension getPreferredSize() {
        return new Dimension(screenSize);
    }

    public static Animator getAnimator() { return animator; }

    public abstract void GameLoop(int delayInMilliseconds);
}
