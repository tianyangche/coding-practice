package com.tianyangche.oodesign.blackjack;

/**
 * Created by tianyangche on 8/28/16.
 */
public abstract class Card {
    private Suit suit;
    private int value;
    private boolean isAvailable;
    public Card(Suit s, int v) {
        suit = s;
        value = v;
        isAvailable = true;
    }

    public abstract int getValue();

    public boolean isAvailable() {
        return isAvailable;
    }
    public void markAsUnAvailable() {
        isAvailable = false;
    }
    public void markAsAvailable() {
        isAvailable = true;
    }
}
