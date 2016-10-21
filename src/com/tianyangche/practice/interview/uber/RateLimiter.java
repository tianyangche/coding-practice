package com.tianyangche.practice.interview.uber;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by tianyangche on 8/28/16.
 */
public class RateLimiter {
    Map<String, Queue<String>> map;
    int timeInterval;
    int maxRequests;
    public RateLimiter(int timeInterval, int maxRequests) {
        map = new HashMap<>();
    }

}
