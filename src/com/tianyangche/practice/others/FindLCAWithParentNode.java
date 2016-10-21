package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 6/26/16.
 */
public class FindLCAWithParentNode {
    TreeNodeWithParent findLCA(TreeNodeWithParent p, TreeNodeWithParent q) {
        int d1 = getDepth(p);
        int d2 = getDepth(q);
        while (d1 > d2) {
            p = p.parent;
            d1--;
        }
        while (d2 > d1) {
            q = q.parent;
            d2--;
        }
        while (p != q) {
            p = p.parent;
            q = q.parent;
        }
        return p;
    }
    private int getDepth(TreeNodeWithParent node) {
        int depth = 0;
        while (node.parent != null) {
            node = node.parent;
            depth++;
        }
        return depth;
    }
}


class TreeNodeWithParent {
    int val;
    TreeNodeWithParent left;
    TreeNodeWithParent right;
    TreeNodeWithParent parent;

}
