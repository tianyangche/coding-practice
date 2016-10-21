package com.tianyangche.practice.interview.dropbox.phone.phone;

import java.util.*;

/**
 * Created by tianyangche on 6/11/16.
 */
public class PhoneNumber {
    private static final String[] KEYBOARD = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> findWordsInAPhoneNumber(String phoneNumber) {
        List<String> res = new LinkedList<>();
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return res;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        for (int i = 0; i < phoneNumber.length(); i++) {
            String button = KEYBOARD[phoneNumber.charAt(i) - '0'];
            if (button.isEmpty()) {
                continue;
            }
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String curr = queue.poll();
                for (int k = 0; k < button.length(); k++) {
                    String candidate = curr + button.charAt(k);
                    if (i == 3) {
                        if (isValid(candidate) || isValid(candidate.substring(0, 3))) {
                            queue.offer(candidate);
                        }
                    } else if (i == phoneNumber.length() - 1) {

                        if (isValid(candidate) || (isValid(candidate.substring(0, 3)) && isValid(candidate.substring(3))
                                || (isValid(candidate.substring(0, 4)) && isValid(candidate.substring(4)))
                        )) {
                            queue.offer(candidate);
                        }
                    } else {
                        queue.offer(candidate);
                    }
                }
            }
        }
        return (List<String>) queue;
    }

    private boolean isValid(String input) {
        Set<String> set = new HashSet<>();
        set.add("adgj");
        set.add("mst");
        set.add("adgjmsu");
        return set.contains(input);
    }

    public static void main(String[] args) {
        String phoneNumber = "2345678";
        PhoneNumber solution = new PhoneNumber();
        System.out.println(solution.findWordsInAPhoneNumber(phoneNumber));
    }
}
