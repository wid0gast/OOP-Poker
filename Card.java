//import java.util.*;
//import java.io.*;

public class Card implements Comparable<Card> {
    final int rank;
    final String suit;
    final String imgPath;

    Card(int n, String s, String x) {
        rank = n;
        suit = s;
        imgPath = "images" + "/" + x;
    }

    @Override
    public int compareTo(Card c) {
        return this.rank - c.rank;
    }
}
