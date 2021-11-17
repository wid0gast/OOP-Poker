import java.util.*;
//import java.io.*;

public class Deck {
    static Stack<Card> deck;

    static void makeDeck() {
        deck = new Stack<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j < 15; j++) {
                if(i == 0) {
                    deck.push(new Card(j, "Spades"));
                }
                if(i == 1) {
                    deck.push(new Card(j, "Diamonds"));
                }
                if(i == 2) {
                    deck.push(new Card(j, "Clubs"));
                }
                if(i == 3) {
                    deck.push(new Card(j, "Hearts"));
                }
            }
        }
        System.out.println(deck.size());
    }
    static int here = 0;
    static void deal(Player p) {
        System.out.println(here++);
        p.holeCards.add(deck.pop());
    }

    static void deal(ArrayList<Card> com) {
        com.add(deck.pop());
    }

    static void shuffle() {
        Collections.shuffle(deck);
    }

    static void burn() {
        deck.pop();
    }
}