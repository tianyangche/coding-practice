package com.tianyangche.practice.others;

import java.util.Stack;

/**
 * Created by tianyangche on 4/10/16.
 */
public class VerifyPreorderSerializationOfBinaryTree {
    public boolean isValidSerialization(String preorder) {
        Stack<Character> nodes = new Stack<>();
        Stack<Integer> counts=  new Stack<>();
        for (int i = 0; i < preorder.length(); i++) {
            char c = preorder.charAt(i);
            // 1. comma, continue
            if (c == ',') {
                continue;
            }

            // 2. #, update peek value
            if (c == '#') {
                if (nodes.isEmpty()) {
                    return false;
                } else {
                    counts.push(counts.pop() + 1);
                }
            }

            if (c >= '0' && c <= '9') {
                if (nodes.isEmpty()) {
                    nodes.push(c);
                    counts.push(0);
                } else {
                    nodes.push(c);
                    counts.push(counts.pop() + 1);
                }
            }


            if (counts.peek() == 2) {
                nodes.pop();
                counts.pop();
            }
        }

        return nodes.isEmpty();
    }
}
