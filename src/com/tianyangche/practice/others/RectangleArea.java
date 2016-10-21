package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 4/2/16.
 */
public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        return getArea(A, B, C, D) + getArea(E, F, G, H) - getCommonArea(A, B, C, D, E, F, G, H);
    }

    private int getArea(int A, int B, int C, int D) {
        return (B - A) * (D - C);
    }

    private int getCommonArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        return getCommonEdge(A, C, E, G) * getCommonEdge(B, D, F, H);
    }

    private int getCommonEdge(int A, int B, int C, int D) {
        if (A <= C && B >= D) {
            return D - C;
        }
        if (C <= A && D >= B) {
            return B - A;
        }
        if (C >= B || A >= D) {
            return 0;
        }
        return Math.min(D, B) - Math.max(A, C);
    }
}
