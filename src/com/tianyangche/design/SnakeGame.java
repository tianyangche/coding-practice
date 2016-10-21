package com.tianyangche.design;

import java.util.*;

/**
 * Created by tianyangche on 9/1/16.
 */
public class SnakeGame {
    private static final Map<String, int[]> DIRECTIONS = new HashMap<>();
    private static final int[] UP = {-1, 0};
    private static final int[] DOWN = {1, 0};
    private static final int[] LEFT = {0, -1};
    private static final int[] RIGHT = {0, 1};
    static {
        DIRECTIONS.put("U", UP);
        DIRECTIONS.put("D", DOWN);
        DIRECTIONS.put("L", LEFT);
        DIRECTIONS.put("R", RIGHT);
    }

    private char[][] board;
    private Deque<int[]> snake;
    private Queue<int[]> food;
    private int width;
    private int height;
    private int score;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = new LinkedList<>();
        this.snake = new LinkedList<>();
        this.board = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 'X';
            }
        }
        this.score = 0;
        int[] start = {0, 0};
        snake.offerFirst(start);
        board[0][0] = 'S';
        for (int[] f : food) {
            this.food.offer(f);
        }
        if (!this.food.isEmpty()) {
            int[] f = this.food.poll();
            this.board[f[0]][f[1]] = 'F';
        }
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        boolean shouldPollLater = true;
        if (snake.size() > 1) {
            shouldPollLater = false;
            int[] temp = snake.pollLast();
            board[temp[0]][temp[1]] = 'X';
        }
        int[] head = snake.peekFirst();
        int[] d = DIRECTIONS.get(direction);
        int[] next = {head[0] + d[0], head[1] + d[1]};
        if (next[0] < 0 || next[0] >= height || next[1] < 0 || next[1] >= width || board[next[0]][next[1]] == 'S') {
            return -1;
        }
//        System.out.println(next[0] + "\t" + next[1] + "\t" + board[next[0]][next[1]]);
        if (board[next[0]][next[1]] != 'F') {
            if (shouldPollLater) {
                int[] temp = snake.pollLast();
                board[temp[0]][temp[1]] = 'X';
            }
        } else {
            if (!food.isEmpty()) {
                int[] f = food.poll();
                board[f[0]][f[1]] = 'F';
            }
            score++;
        }
        board[next[0]][next[1]] = 'S';
        snake.offerFirst(next);
        return score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */