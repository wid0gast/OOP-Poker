//import java.util.*;
//import java.io.*;

public class Card implements Comparable<Card> {
    final int rank;
    final String suit;

    Card(int n, String s) {
        rank = n;
        suit = s;
    }

    @Override
    public int compareTo(Card c) {
        return this.rank - c.rank;
    }
}
