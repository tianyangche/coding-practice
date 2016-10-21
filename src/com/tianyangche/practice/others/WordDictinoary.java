package com.tianyangche.practice.others;

/**
 * Created by tianyangche on 5/8/16.
 */
public class WordDictinoary {
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


    class Trie {
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
            if (word.isEmpty()) {
                return false;
            }
            return search(root, word);
        }


        private boolean search(TrieNode trieNode, String word) {
            TrieNode curr = trieNode;
            if (word.isEmpty()) {
                return trieNode.isWord;
            }

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c != '.') {
                    if (!curr.contains(c)) {
                        return false;
                    }
                    curr = curr.children[c - 'a'];
                } else {
                    for (char d = 'a'; d <= 'z'; d++) {
                        if (curr.contains(d) && search(curr.children[d - 'a'], word.substring(i + 1))) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return curr.isWord;
        }
    }

    Trie root = new Trie();

    // Adds a word into the data structure.
    public void addWord(String word) {
        root.insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return root.search(word);
    }


    public static void main(String[] args) {
        WordDictinoary wd = new WordDictinoary();
        wd.addWord("aa");
        System.out.println(wd.search("."));
    }

}
