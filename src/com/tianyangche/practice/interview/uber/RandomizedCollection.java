package com.tianyangche.practice.interview.uber;

import java.util.*;

/**
 * Created by tianyangche on 8/27/16.
 */
public class RandomizedCollection {
    List<Integer> elements;
    Map<Integer, HashSet<Integer>> positions;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        elements = new ArrayList<>();
        positions = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean res = !positions.containsKey(val);
        elements.add(val);
        if (!positions.containsKey(val)) {
            positions.put(val, new HashSet<>());
        }
        positions.get(val).add(elements.size() - 1);
        return res;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!positions.containsKey(val)) {
            return false;
        }
        int replaceVal = elements.get(elements.size() - 1);
        if (replaceVal == val) {
            elements.remove(elements.size() - 1);
            positions.get(val).remove(elements.size());
            if (positions.get(val).size() == 0) {
                positions.remove(val);
            }
            return true;
        }
        int tempPos = positions.get(val).iterator().next();
        elements.set(tempPos, elements.get(elements.size() - 1));
        positions.get(replaceVal).remove(elements.size() - 1);
        positions.get(replaceVal).add(tempPos);
        elements.remove(elements.size() - 1);

        positions.get(val).remove(tempPos);
        if (positions.get(val).size() == 0) {
            positions.remove(val);
        }

        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return elements.get(random.nextInt(elements.size()));
    }

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        randomizedCollection.insert(1);
        randomizedCollection.insert(2);
        randomizedCollection.insert(2);
        int one = 0;
        int two = 0;
        for (int i = 0; i < 10000; i++) {
            if (randomizedCollection.getRandom() == 1) {
                one++;
            } else {
                two++;
            }
        }
        System.out.println(one + "\t" + two);


        randomizedCollection.remove(2);
        one = 0;
        two = 0;
        for (int i = 0; i < 10000; i++) {
            if (randomizedCollection.getRandom() == 1) {
                one++;
            } else {
                two++;
            }
        }
        System.out.println(one + "\t" + two);
        randomizedCollection.remove(2);
        one = 0;
        two = 0;
        for (int i = 0; i < 10000; i++) {
            if (randomizedCollection.getRandom() == 1) {
                one++;
            } else {
                two++;
            }
        }
        System.out.println(one + "\t" + two);
    }
}
