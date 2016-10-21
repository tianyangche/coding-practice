package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by tianyangche on 3/20/16.
 */
public class VerticalOrder {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        int low = 0;
        int high = 0;
        queue.offer(root);
        index.offer(0);
        res.add(new ArrayList<>());

        while (!queue.isEmpty()) {
            TreeNode curr = queue.peek();
            int i = index.poll();
            if (i < low) {
                low = i;
                res.add(0, new ArrayList<>());
            }
            if (i > high) {
                high = i;
                res.add(new ArrayList<>());
            }

            res.get(i - low).add(curr.val);
            if (curr.left != null) {
                queue.offer(curr.left);
                index.offer(i - 1);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                index.offer(i + 1);
            }
        }


        return res;
    }
}
