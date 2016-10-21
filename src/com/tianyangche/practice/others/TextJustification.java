package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 5/19/16.
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        int i = 0;
        int j = 0;
        while (j < words.length) {
            int sum = 0;
            while (j < words.length && sum + words[j].length() + j - i <= maxWidth) {

                sum += words[j].length();
                j++;
            }
            StringBuilder builder = new StringBuilder();
            if (i == j - 1) {
                builder.append(words[i]);
                for (int k = sum + 1; k <= maxWidth; k++) {
                    builder.append(' ');
                }
                res.add(builder.toString());
                i = j;
            } else {
                int count = j - i;

                int spaces = 0;
                if (j == words.length) {
                    while (i < j) {
                        builder.append(words[i++]);
                        if (sum + spaces < maxWidth) {
                            builder.append(" ");
                            spaces++;
                        }
                    }
                    while (sum + spaces < maxWidth) {
                        builder.append(" ");
                        spaces++;
                    }
                } else {
                    builder.append(words[i++]);
                    count--;
                    while (i < j) {
                        if (i == j - 1) {
                            for (int k = 1; k <= maxWidth - sum - spaces; k++) {
                                builder.append(' ');
                            }
                        } else {
                            if ((maxWidth - sum - spaces) % (count) != 0) {
                                for (int k = 1; k <= (maxWidth - sum - spaces) / (count ) + 1; k++) {
                                    builder.append(' ');
                                }
                                spaces += (maxWidth - sum - spaces) / (count) + 1;
                            } else {
                                for (int k = 1; k <= (maxWidth - sum - spaces) / (count ); k++) {
                                    builder.append(' ');
                                }
                                spaces += (maxWidth - sum - spaces) / (count);
                            }
                        }
                        builder.append(words[i++]);
                        count--;
                    }
                }
                res.add(builder.toString());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"Don't","go","around","saying","the","world","owes","you","a","living;","the","world","owes","you","nothing;","it","was","here","first."};
        TextJustification textJustification = new TextJustification();
        System.out.println(textJustification.fullJustify(words, 30));
    }
}
