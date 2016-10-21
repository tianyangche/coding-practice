package com.tianyangche.oodesign.blackjack;

/**
 * Created by tianyangche on 8/28/16.
 */
public class BlackjackCard extends Card {
    public BlackjackCard(Suit s, int v) {
        super(s, v);
    }

    //TODO: get better idea of getting value
    @Override
    public int getValue() {
        return 0;
    }
}
