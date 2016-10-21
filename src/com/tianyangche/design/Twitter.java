package com.tianyangche.design;

import java.util.*;

/**
 * Created by tianyangche on 6/26/16.
 */

public class Twitter {

    int timestamp = 0;
    /** Initialize your data structure here. */
    Map<Integer, List<Tweet>> tweets;
    Map<Integer, Set<Integer>> users;
    public Twitter() {
        tweets = new HashMap<>();
        users = new HashMap<>();
        timestamp = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!users.containsKey(userId)) {
            users.put(userId, new HashSet<>());
            tweets.put(userId, new ArrayList<>());
        }
        tweets.get(userId).add(0, new Tweet(tweetId, timestamp++));
    }

    class Tweet {
        int tweetId;
        int timestamp;
        public Tweet(int tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }
    class TweetNode {
        List<Tweet> tweetList;
        int pos;
        public TweetNode(List<Tweet> tweetList) {
            this.tweetList = tweetList;
            this.pos = 0;
        }
        public boolean isValid() {
            if (tweetList == null) {
                return false;
            }
            if (tweetList.size() <= pos) {
                return false;
            }
            return true;
        }
        public Tweet get() {
            return tweetList.get(pos);
        }
    }

    class TweetComparator implements Comparator<TweetNode> {
        public int compare(TweetNode node1, TweetNode node2) {
            return node2.get().timestamp - node1.get().timestamp;
        }
    }
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */

    public List<Integer> getNewsFeed(int userId) {

        List<Integer> res = new ArrayList<Integer>();
        if (!users.containsKey(userId)) {
            return res;
        }
        PriorityQueue<TweetNode> heap = new PriorityQueue<TweetNode>(new TweetComparator());
        Set<Integer> follows = users.get(userId);
        for (int f : follows) {
            if (tweets.get(f).size() > 0) {
                TweetNode node = new TweetNode(tweets.get(f));
                if (node.isValid()) {
                    heap.offer(node);
                }
            }
        }
        if (tweets.get(userId).size() > 0) {
            TweetNode node = new TweetNode(tweets.get(userId));
            heap.offer(node);
        }
        while (!heap.isEmpty() && res.size() < 10) {
            TweetNode node = heap.poll();
            res.add(node.get().tweetId);
            node.pos++;
            if (node.isValid()) {
                heap.offer(node);
            }
        }

        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!users.containsKey(followerId) || !users.containsKey(followeeId)) {
            if (!users.containsKey(followerId)) {
                users.put(followerId, new HashSet<>());
                tweets.put(followerId, new ArrayList<>());
            }
            if (!users.containsKey(followeeId)) {
                users.put(followeeId, new HashSet<>());
                tweets.put(followeeId, new ArrayList<>());
            }
        }

        if (followerId == followeeId) {
            return;
        }
        users.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!users.containsKey(followerId) || !users.containsKey(followeeId)) {
            if (!users.containsKey(followerId)) {
                users.put(followerId, new HashSet<>());
                tweets.put(followerId, new ArrayList<>());
            }
            if (!users.containsKey(followeeId)) {
                users.put(followeeId, new HashSet<>());
                tweets.put(followeeId, new ArrayList<>());
            }
        }
        if (followerId == followeeId) {
            return;
        }
        users.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */