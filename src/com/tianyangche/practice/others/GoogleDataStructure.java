package com.tianyangche.practice.others;


import java.util.*;

/**
 * Created by tianyangche on 4/24/16.
 */
public class GoogleDataStructure {
    private class Pair {
        int key;
        int value;
        public Pair(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }
    private int size;
    private Random random;
    private Map<Integer, Integer> map;
    private List<Pair> elements;

    public GoogleDataStructure() {
        this.size = 0;
        this.random = new Random();
        this.map = new HashMap<>();
        this.elements = new ArrayList<>();
    }

    public int get(int key) {
        int pos = -1;
        if (map.containsKey(key)) {
            pos = map.get(key);
        }
        if (pos == -1) {
            return -1;
        }
        return elements.get(pos).value;
    }

    public boolean set(int key, int value) {
        elements.get(map.get(key)).value = value;
        return true;
    }

    public boolean insert(int key, int value) {
        Pair pair = new Pair(key, value);
        elements.add(pair);
        map.put(key, size);
        size++;
        return true;
    }

    public boolean delete(int key) {
        Pair first = elements.get(map.get(key));
        Pair second = elements.get(size - 1);
        elements.set(map.get(key), second);
        map.put(second.key, map.get(key));
        map.remove(key);
        size--;
        return true;
    }

    public void random() {
        Pair pair = elements.get(random.nextInt(size));

    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements.get(i).key + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GoogleDataStructure ds = new GoogleDataStructure();
        ds.insert(1, 1);
        ds.insert(2, 4);
        ds.insert(3, 9);
        ds.delete(2);
        ds.print();
    }
}
