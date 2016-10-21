package com.tianyangche.practice.interview.uber;

import java.util.*;

/**
 * Created by tianyangche on 8/17/16.
 */
public class RandomizedSet {
    List<Integer> elements;
    Map<Integer, Integer> positions;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        elements = new ArrayList<>();
        positions = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (positions.containsKey(val)) {
            return false;
        }
        elements.add(val);
        positions.put(val, elements.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!elements.contains(val)) {
            return false;
        }
        int replaceVal = elements.get(elements.size() - 1);
        int temp = elements.get(positions.get(val));
        elements.set(positions.get(val), elements.get(elements.size() - 1));
        elements.set(elements.size() - 1, temp);
        positions.put(replaceVal, positions.get(val));
        elements.remove(elements.size() - 1);
        positions.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random random = new Random();
        return elements.get(random.nextInt(elements.size()));
    }

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        System.out.println(obj.insert(1));
        System.out.println(obj.remove(2));
        System.out.println(obj.insert(2));
        System.out.println(obj.getRandom());
        System.out.println(obj.remove(1));
        System.out.println(obj.insert(2));
        System.out.println(obj.getRandom());
    }
}
