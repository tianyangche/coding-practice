package com.tianyangche.practice.others;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by tianyangche on 3/20/16.
 */
public class ClosestKNodes {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        Queue<Integer> queue = new LinkedList<Integer>();
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
//            System.out.println(curr.val);
            if (queue.size() < k) {
                System.out.println("yes");
                queue.offer(root.val);
            } else {
                if (Math.abs(queue.peek() - target) > Math.abs(curr.val - target)) {
                    System.out.println(queue.peek());
                    queue.poll();
                    queue.offer(curr.val);
                }
            }
            curr = curr.right;
        }

        return (List<Integer>) queue;
    }

    public static void main(String[] args) {
        ClosestKNodes closestKNodes = new ClosestKNodes();
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(14);

        List<Integer> list = closestKNodes.closestKValues(root, -15.00, 1);
        System.out.println(list);
    }
}
