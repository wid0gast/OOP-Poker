import java.util.*;
//import java.io.*;

public class Table {
    static ArrayList<Player> players = new ArrayList<>();
    static int pot = 0;
    static ArrayList<Card> communityCards = new ArrayList<>();
    static int currentBet = 0;
    static int smallBlind = 10;
    static int bigBlind = 2 * smallBlind;
    
    Table(ArrayList<Player> p, int s) {
        players = p;
        smallBlind = s;
        
    }
}