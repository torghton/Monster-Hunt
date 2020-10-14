package alex.scrollingFighter;

import javafx.util.Pair;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;

public class Animator {
    private HashMap<Integer, LinkedList<Pair<Animateable, Integer>>> animatebleLinkedListHashMap;

    public Animator() {
        animatebleLinkedListHashMap = new HashMap<>();
        animatebleLinkedListHashMap.put(0, new LinkedList<>());
        animatebleLinkedListHashMap.put(1, new LinkedList<>());
        animatebleLinkedListHashMap.put(2, new LinkedList<>());
    }

    public void animateAll(Graphics graphics) {
        for(int i = 0; i < animatebleLinkedListHashMap.size();i++) {
            for(int g = 0; g < animatebleLinkedListHashMap.get(i).size(); g++)
                animatebleLinkedListHashMap.get(i).get(g).getKey().animate(Runner.getImages().get(i), graphics);
        }
    }

    public void addToAnimateableLinkedList(Integer id, Animateable animateable, int layer) {
        animatebleLinkedListHashMap.get(layer).add(new Pair(animateable, id));
    }

    public void removeFromAnimateableLinkedList(Animateable animateable, Integer layer, int id) {
        Pair foundPair = null;
        for(Pair pair: animatebleLinkedListHashMap.get(layer)) {
            System.out.println(pair);
            if(pair.getKey().equals(animateable)) {
                foundPair = pair;
                System.out.println("Found pair");
            }
        }
        System.out.println(foundPair);

        if(foundPair != null) {
            animatebleLinkedListHashMap.get(layer).remove(foundPair);
        }
    }
}
