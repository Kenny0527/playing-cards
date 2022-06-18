package com.tlglearning.playingcards;

import com.tlglearning.playingcards.model.Card;
import com.tlglearning.playingcards.model.Deck;

public class CardTrick {
    public static void main(String[] args) {
        // Create an instance of deck and shuffle it (or not).
        Deck deck = new Deck();
        deck.shuffle();

        // TODO: Draw cards from the deck, according to the instructions, placing every other card in the
        //    red deck or the black deck.
        while (deck.getRemaining() != 0) {
            Card p = deck.draw();
            System.out.println(p);
        }

        // TODO: (Optional: shuffle the red deck and black deck)
        // TODO: Generate a random integer between 0 and the smaller of the two deck
        //   sizes, and swap the number of cards between the two.
        // TODO: count the red cards in the red deck and the black cards in the black deck and compare them.
        //   THEY MUST BE EQUAL.
        // TODO: Sort each deck by color, suit, and rank, and print them out. along with the counts from
        //   the previous step.

    }
}
