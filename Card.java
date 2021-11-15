//import java.util.*;
//import java.io.*;

public class Card implements Comparable<Card> {
    final int rank;
    final String suit;
    boolean faceUp = false;

    Card(int n, String s) {
        rank = n;
        suit = s;
    }

    void flip() {
        if(faceUp == false) {
            faceUp = true;
        }
        else {
            faceUp = false;
        }
    }

    @Override
    public int compareTo(Card c) {
        return this.rank - c.rank;
    }
}