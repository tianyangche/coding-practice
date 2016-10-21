package com.tianyangche.practice.others;

import java.util.Iterator;
import java.util.List;

/**
 * Created by tianyangche on 4/9/16.
 */
public class VectorIterator {
    public class Vector2D implements Iterator<Integer> {
        private List<List<Integer>> elements;
        private int index;
        private Iterator<Integer> iterator;
        public Vector2D(List<List<Integer>> vec2d) {
            elements = vec2d;
            index = 0;
            iterator = vec2d.get(0).iterator();
        }

        @Override
        public Integer next() {
            Integer res = iterator.next();
            if (!iterator.hasNext()) {
                index++;
                iterator = elements.get(index).iterator();
            }
            return res;
        }

        @Override
        public boolean hasNext() {
            return !(index == elements.size() && !iterator.hasNext());
        }
    }
}
