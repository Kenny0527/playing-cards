package com.tlglearning.playingcards;

import com.tlglearning.playingcards.model.Card;
import com.tlglearning.playingcards.model.Deck;
import com.tlglearning.playingcards.model.Suit;

import java.util.*;

public class CardTrick {
    public static void main(String[] args) {
        // empty deque sequences for face down piles.
        List<Card> redPile = new ArrayList<>();
        List<Card> blackPile = new ArrayList<>();
        Random rng = new Random();
        int redCount = 0;
        int blackCount = 0;

        // Create an instance of deck and shuffle it (or not).
        Deck deck = new Deck();
        deck.shuffle();

        // Draw cards from the deck, according to the instructions, placing every other card in the
        //    red deque or the black deque.
        while (deck.getRemaining() != 0) {
            Card indicator = deck.draw();
            Card nextCard = deck.draw();
            if(indicator.getSuit() == Suit.SPADES || indicator.getSuit() == Suit.CLUBS){
                blackPile.add(nextCard);
            }
            else {
                redPile.add(nextCard);
            }
        }

        // (Optional: shuffle the red deque and black deque)
        for(int i = redPile.size() - 1; i > 0; i--){
            int j = rng.nextInt(i);
            Card temp = redPile.get(i);
            redPile.set(i, redPile.get(j));
            redPile.set(j, temp);
        }

        for(int i = blackPile.size() - 1; i > 0; i--){
            int j = rng.nextInt(i);
            Card temp = blackPile.get(i);
            blackPile.set(i, blackPile.get(j));
            blackPile.set(j, temp);
        }

        // Generate a random integer between 0 and the smaller of the two deck
        //   sizes, and swap the number of cards between the two.
        int upperLimit = Math.min(redPile.size(), blackPile.size()); // u
        int swapCount = rng.nextInt(upperLimit); // n
        List<Card> headBlack = List.copyOf(blackPile.subList(0, swapCount));
        List<Card> headRed = List.copyOf(redPile.subList(0, swapCount));

        if(swapCount != 0){
            for (int i = 0; i < swapCount; i++){
                blackPile.set(i, headRed.get(i));
            }

            System.out.println("This is the head of black after swapping pile " + headBlack);
            for (int i = 0; i < swapCount; i++){
                redPile.set(i, headBlack.get(i));
            }
        }

        // count the red cards in the red deque and the black cards in the black deque and compare them.
        //   THEY MUST BE EQUAL.
        for(Card c : blackPile){
            if(c.getSuit() == Suit.SPADES || c.getSuit() == Suit.CLUBS){
                blackCount++;
            }

        }
        for(Card c : redPile){
            if(c.getSuit() == Suit.DIAMONDS || c.getSuit() == Suit.HEARTS){
                redCount++;
            }
        }

        if(redCount == blackCount){
            System.out.println("Trick was done correctly");
        }
        else{
            System.out.println("FAILED RUN");
        }

        System.out.println(deck);
        deck.sort();
        System.out.println(deck);
        // TODO: Sort each deck by color, suit, and rank, and print them out. along with the counts from
        //   the previous step.


    }
}
