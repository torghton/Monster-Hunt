package alex.scrollingFighter;

import java.util.LinkedList;

class Camera {
    private LinkedList<Panable> moveAbleObjects;

    public Camera() {
        moveAbleObjects = new LinkedList<>();
    }

    public void Pan(float amount) {
        for(Panable panObj: moveAbleObjects) {
            panObj.pan(amount);
        }
    }

    public void addToMoveAbleObjects(Panable panable) {
        moveAbleObjects.addFirst(panable);
    }


}
