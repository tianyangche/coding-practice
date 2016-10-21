package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 3/19/16.
 */
public class VerifyPreorder {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length < 2) {
            return true;
        }
        int count = 0;
        for (int i = 0; i < preorder.length - 1; i++) {
            if (preorder[i] > preorder[i + 1]) {
                count++;
            } else {
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }
        return count == 0;
    }
}
