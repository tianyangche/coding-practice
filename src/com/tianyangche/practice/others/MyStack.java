package com.tianyangche.practice.others;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tianyangche on 4/6/16.
 */
public class MyStack {
    private Queue<Integer> in;
    private Queue<Integer> out;
    public MyStack() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }
    public void push(int x) {
        in.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        while (in.size() != 1) {
            out.offer(in.poll());
        }
        in.poll();
        while (out.size() != 0) {
            in.offer(out.poll());
        }
    }

    // Get the top element.
    public int top() {
        while (in.size() != 1) {
            out.offer(in.poll());
        }
        return in.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
