package com.tianyangche.practice.interview.dropbox.phone.phone;

import java.io.File;
import java.util.*;

/**
 * Created by tianyangche on 6/12/16.
 */
public class FindDuplicatedFilesInAPath {
    public List<Set<String>> getSameFiles(String path) {
        File currDir = new File(path);
        Map<Long, Set<String>> fileSizeMap = new HashMap<>();
        dfs(fileSizeMap, currDir);

        Map<String, Set<String>> map = new HashMap<>();
        for (Set<String> fileSet: fileSizeMap.values()) {
            if (fileSet.size() >= 2) {
                for (String candidate : fileSet) {
                    String key = hash(candidate);
                    if (!map.containsKey(key)) {
                        map.put(key, new HashSet<>());
                    }
                    map.get(key).add(candidate);
                }
            }
        }

        List<Set<String>> res = new ArrayList<>();
        for (Set<String> set : map.values()) {
            if (set.size() >= 2) {
                res.add(set);
            }
        }

        return res;
    }
    private String hash(String candidate) {
        // 1. Hash on all the content
        // 2. Read first 1k, in the middle 1k, last 1k.
        return "";
    }

    private void dfs(Map<Long, Set<String>> fileSizeMap, File file) {
        if (!file.isDirectory()) {
            if (fileSizeMap.containsKey(file.length())) {
                fileSizeMap.put(file.length(), new HashSet<>());
                fileSizeMap.get(file.length()).add(file.getPath());
            }
        } else {
            for (File subFile : file.listFiles()) {
                dfs(fileSizeMap, subFile);
            }
        }
    }
}
