package com.tianyangche.practice.others;

import java.util.Arrays;

/**
 * Created by tianyangche on 4/2/16.
 */
public class BullsAndCows {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int n = secret.length();
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
                visited[i] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            char c = guess.charAt(i);
            for (int j = 0; j < n; j++) {
                if (visited[j]) {
                    continue;
                } else if (secret.charAt(j) == c){
                    if (!visited[j]) {
                        visited[j] = true;
                        cows++;
                    }
                }
            }
        }

        return bulls + "A" + cows + "B";
    }
}
