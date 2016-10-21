package com.tianyangche.practice.others;

import java.util.*;
import java.util.List;

/**
 * Created by tianyangche on 3/14/16.
 */
public class SkyLineProblem {
    class Edge {
        int pos;
        int height;
        boolean isStart;
        public Edge(int p, int h, boolean f) {
            pos = p;
            height = h;
            isStart = f;
        }
    }

    class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge e1, Edge e2) {

            if (e1.pos != e2.pos) {
                return Integer.compare(e1.pos, e2.pos);
            }


            if (e1.isStart && !e2.isStart) {
                return e1.height == e2.height ? -1 : Integer.compare(e1.height, e2.height);
            }
            if (e2.isStart && !e1.isStart) {
                return e1.height == e2.height ? 1 : Integer.compare(e1.height, e2.height);
            }

            return e1.isStart ? Integer.compare(e2.height, e1.height) : Integer.compare(e1.height, e1.height);
        }
    }

    class HeightComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return Integer.compare(i2, i1);
        }
    }


    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return res;
        }

        List<Edge> inputs = new ArrayList<>();
        // Convert input to Edge arrays;
        for (int[] building : buildings) {
            inputs.add(new Edge(building[0], building[2], true));
            inputs.add(new Edge(building[1], building[2], false));
        }

        EdgeComparator edgeComparator = new EdgeComparator();
        Collections.sort(inputs, edgeComparator);
//
        for (Edge e : inputs) {
            System.out.println(e.pos + "\t\t" + e.height + "\t\t" + e.isStart);

        }



        PriorityQueue<Integer> heights = new PriorityQueue<>(inputs.size(), new HeightComparator());


        for (int i = 0; i < inputs.size(); i++) {
            Edge edge = inputs.get(i);
//            if (heights.isEmpty()) {
//                System.out.println("Peek = " + 0);
//            } else {
//                System.out.println("Peek = " + heights.peek());
//            }

            if (edge.isStart) {
                if (heights.isEmpty() || heights.peek() < edge.height) {
                    res.add(new int[]{edge.pos, edge.height});
                }
                heights.add(edge.height);
            } else {
                int prev = heights.peek();
                heights.remove(edge.height);
                if (prev == edge.height) {
                    if (heights.isEmpty()) {
                        res.add(new int[]{edge.pos, 0});
                    } else if (heights.peek() < prev) {
                        res.add(new int[]{edge.pos, heights.peek()});
                    }
                }
            }

        }

        return res;
    }

    public static void main(String[] args) {
        SkyLineProblem skyLineProblem = new SkyLineProblem();
        int[][] buildings = {{0,5,7},{5,10,7},{5,10,12},{10,15,7},{15,20,7},{15,20,12},{20,25,7}};
        List<int[]> res = skyLineProblem.getSkyline(buildings);
        for (int[] e : res) {
            System.out.println(e[0] + "\t\t" + e[1]);

        }
    }
}
