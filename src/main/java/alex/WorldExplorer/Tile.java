package alex.WorldExplorer;

import java.awt.*;

public class Tile {

    Dimension size;
    Point location;

    public Tile(Dimension size, Point location) {
        this.size = size;
        this.location = location;
    }

    public void move(int x, int y) {
        location.x += x;
        location.y += y;
    }


}
