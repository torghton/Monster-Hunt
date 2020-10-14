package alex.scrollingFighter;

import java.awt.*;
import java.awt.Panel;
import java.util.LinkedList;

class PathManager {
    LinkedList<Path> pathList = new LinkedList<>();

    public PathManager(Path path) {
        pathList.add(path);
        ExtraPath extraPath = new ExtraPath(new Point((int) (pathList.getFirst().getLocation().getX()),(int) (pathList.getFirst().getLocation().getY() + pathList.getFirst().getSize().getHeight())), new Dimension( alex.scrollingFighter.Panel.screenSize.width, (int) ( alex.scrollingFighter.Panel.screenSize.getHeight()-(pathList.getFirst().getLocation().getY() + pathList.getFirst().getSize().height)))
                ,new Color(pathList.getFirst().getTexture().getRGB(1,1) - 1500));
    }

    private void addToLastPathList(Path path) { pathList.addLast(path); }

    private void removeFromPathList(Path path) { pathList.remove(path); }

    private Path getLastPathInList() { return pathList.getLast(); }

    private void pathIsEligibleForDeletion() {
        // If the location is less then the size - 0 delete it
        if(pathList.get(0).getLocation().getX() < 0 - pathList.get(0).getSize().getWidth()) {
            removeFromPathList(pathList.getFirst());

        }
    }

    private void pathIsEligibleToBeAdded() {
        // While there is space for the path to be seen and added
        if(pathList.size() != 0) {
            if(pathList.getLast().getLocation().getX() <= alex.scrollingFighter.Panel.screenSize.getWidth()) {
                do {
                    // adds to the last path with the same width and height but its further out then the last one
                    addToLastPathList(new Path(new Point((int) (getLastPathInList().getLocation().getX() + getLastPathInList().getSize().getWidth()),(int) (pathList.getLast().getLocation().getY())), pathList.getLast().getSize()));
                } while(pathList.getLast().getLocation().getX() <=  alex.scrollingFighter.Panel.screenSize.getWidth());
            }
        }


    }

    public void UpdatePaths() {
        pathIsEligibleToBeAdded();
        pathIsEligibleForDeletion();
    }
}
