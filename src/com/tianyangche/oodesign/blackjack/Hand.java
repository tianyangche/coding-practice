package com.tianyangche.oodesign.blackjack;

import java.util.List;

/**
 * Created by tianyangche on 8/28/16.
 */
public class Hand <T extends Card>{
    protected List<T> cards;

    // TODO: calculate score
    public int score() {
        return 0;
    }

    public void addCard(T card) {
        cards.add(card);
    }
}
