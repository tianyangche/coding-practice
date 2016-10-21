package com.tianyangche.practice.others;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tianyangche on 3/20/16.
 */
public class TreeSerializer {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr == null) {
                    sb.append('#').append(',');
                } else {
                    sb.append(curr.val).append(',');
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                }
            }
        }

        String res = sb.toString();
        return res.substring(0, res.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(",");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode root = nodeMaker(tokens[0]);
        queue.offer(root);
        if (root == null) {
            return root;
        }
        int next = 1;


        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                curr.left = nodeMaker(tokens[next++]);
                curr.right = nodeMaker(tokens[next++]);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }

        return root;
    }


    private TreeNode nodeMaker(String token) {
        if (token.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.parseInt(token));
    }

    public static void main(String[] args) {
        TreeSerializer codec = new TreeSerializer();
        TreeNode root1 = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(2);
        root1.right.left.left = new TreeNode(1);
        String token = codec.serialize(root1);
        TreeNode res = codec.deserialize(token);
        System.out.println(codec.serialize(res));
    }
}
