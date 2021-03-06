package com.tlglearning.playingcards;

import com.tlglearning.playingcards.model.Card;
import com.tlglearning.playingcards.model.Deck;
import com.tlglearning.playingcards.model.Suit;

import java.util.*;
import java.util.stream.Collectors;

public class CardTrick {

    private final Deque<Card> blackPile = new LinkedList<>();
    private final Deque<Card> redPile = new LinkedList<>();
    private final Comparator<Card> displayComparator = Comparator
            .comparing((Card c) -> c.getSuit().getColor())
            .thenComparing((Card::getSuit))
            .thenComparing((Card::getRank));

    public CardTrick() {
    }

    public static void main(String[] args) {
        // Create an instance of deck and shuffle it (or not).
        Deck deck = new Deck();
        deck.shuffle();

        // Draw cards from the deck, according to the instructions,
        //    placing every other card in the red deque or the black deque.
        CardTrick trick = new CardTrick();
        trick.split(deck);

        // Generate a random integer between 0 and the smaller of the two deck
        //   sizes, and swap the number of cards between the two.
        trick.swapCards();

        // count the red cards in the red deque and the black cards in the black deque and compare them.
        //   THEY MUST BE EQUAL.
        // TODO: Sort each deck by color, suit, and rank, and print them out. along with the counts from
        trick.tally();


    }

    public void split(Deck deck) {
        while (deck.getRemaining() != 0) {
            Card indicator = deck.draw();
            Card next = deck.draw();
            if (indicator.getSuit().getColor() == Suit.Color.BLACK) {
                blackPile.add(next);
            } else {
                redPile.add(next);
            }
        }
    }

    public void swapCards() {
        Random rng = new Random();
        int swapCount = rng.nextInt(1 + Math.min(redPile.size(), blackPile.size())); // n

        for (int i = 0; i < swapCount; i++) {
            redPile.add(blackPile.remove());
            blackPile.add(redPile.remove());
        }

    }

    public void tally() {
        tallyPile(blackPile, Suit.Color.BLACK);
        tallyPile(redPile, Suit.Color.RED);
    }

    private void tallyPile(Collection<Card> pile, Suit.Color color){
        long count = pile.stream().filter((c) -> c.getSuit().getColor() == color).count();
        System.out.printf("%1$s pile: cards=%2$s; count of %1$s cards=%3$s.%n", color, pile
                .stream().sorted(displayComparator).collect(Collectors.toList()), count);
    }

}
        /*
        // Kenny's Implementation
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

        System.out.println(redPile);
        System.out.println(blackPile);
        System.out.println("------------------------------------------");

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

        System.out.println(redPile);
        System.out.println(blackPile);
        System.out.println("------------------------------------------");

        // Generate a random integer between 0 and the smaller of the two deck
        //   sizes, and swap the number of cards between the two.
        int upperLimit = Math.min(redPile.size(), blackPile.size()); // u
        int swapCount = rng.nextInt(upperLimit); // n
        List<Card> headBlack = List.copyOf(blackPile.subList(0, swapCount));
        List<Card> headRed = List.copyOf(redPile.subList(0, swapCount));

        if(swapCount != 0) {
            for (int i = 0; i < swapCount; i++) {
                blackPile.set(i, headRed.get(i));
                redPile.set(i, headBlack.get(i));
            }
        }

        System.out.println(redPile);
        System.out.println(blackPile);

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

        // Sort each deck by color, suit, and rank, and print them out. along with the counts from
        //   the previous step.
         */
