package com.tianyangche.practice.interview.uber;

import java.util.LinkedList;
import java.util.Queue;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

/**
 * Created by tianyangche on 8/27/16.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
public class SerializeAndDeserializeABinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#,";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(root.val).append(",");
        builder.append(serialize(root.left));
        builder.append(serialize(root.right));
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        Queue<String> queue = new LinkedList<>();
        for (String str : strs) {
            queue.offer(str);
        }
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String s = queue.poll();
        if (s.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(queue.poll()));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
    }
}
