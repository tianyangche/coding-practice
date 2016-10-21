package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 5/8/16.
 */

public class Trie {
    class TrieNode {
        char c;
        boolean isWord;
        TrieNode[] children;
        TrieNode(char c, boolean isWord) {
            this.c = c;
            this.isWord = isWord;
            this.children = new TrieNode[26];
        }

        public TrieNode(char c) {
            this(c, false);
        }
        public TrieNode() {
            this('#', false);
        }

        public boolean contains(char c) {
            return children[c - 'a'] != null;
        }
        public void put(char c) {
            children[c - 'a'] = new TrieNode(c);
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curr = root;
        if (word.isEmpty()) {
            return;
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.contains(c)) {
                curr.put(c);
            }
            curr = curr.children[c - 'a'];
        }

        curr.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.contains(c)) {
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return curr.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curr.contains(c)) {
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("jiba");
        System.out.println(trie.startsWith("jib"));
    }
}
