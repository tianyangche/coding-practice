package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/12/16.
 */
public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        dfs(res, tempList, s, 0);
        return res;
    }

    private void dfs(List<String> res, List<String> curr, String str, int index) {
        if (index == 4) {
            if (str.isEmpty()) {
                res.add(generateString(curr));
                return;
            }
        }
        if (str.length() > (4 - index) * 3 || str.length() < 4 - index) {
            return;
        }

        for (int i = 0; i < Math.min(3, str.length() - i); i++) {
            String sub = str.substring(0, i + 1);
            if (!isGoodSub(sub)) {
                return;
            }
            curr.add(sub);
            dfs(res, curr, str.substring(i + 1), index + 1);
            curr.remove(curr.size() - 1);
        }
    }

    private boolean isGoodSub(String s) {
        int value = Integer.valueOf(s);
        if (value > 255) {
            return false;
        }
        if (value == 0 && s.length() > 1 || value != 0 && s.charAt(0) == '0') {
            return false;
        }
        return true;
    }

    private String generateString(List<String> curr) {
        System.out.println();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            builder.append(curr.get(i)).append(".");
        }
        return builder.append(curr.get(3)).toString();
    }

    public static void main(String[] args) {
        RestoreIPAddress restoreIPAddress = new RestoreIPAddress();
        String input = "0000";
        System.out.println(restoreIPAddress.restoreIpAddresses(input));
    }
}
