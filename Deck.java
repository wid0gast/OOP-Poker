import java.util.*;
//import java.io.*;

public class Deck {
    static Stack<Card> deck;

    static void makeDeck() {
        deck = new Stack<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j < 15; j++) {
                int filename = i * 13 + j - 1;
                if(i == 0) {
                    deck.push(new Card(j, "Clubs", filename + ".png"));
                }
                if(i == 1) {
                    deck.push(new Card(j, "Spades", filename + ".png"));
                }
                if(i == 2) {
                    deck.push(new Card(j, "Hearts", filename + ".png"));
                }
                if(i == 3) {
                    deck.push(new Card(j, "Diamonds", filename + ".png"));
                }
            }
        }
    }
    static void deal(Player p) {
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