import java.util.*;
//import java.io.*;

public class Table {
    static ArrayList<Player> players = new ArrayList<>();
    static ArrayList<Player> roundPlayers = new ArrayList<>();
    static int pot = 0;
    static ArrayList<Card> communityCards = new ArrayList<>();
    static int smallBlind = 10;
    static int bigBlind = 2 * smallBlind;
    static int currentBet = bigBlind;
    static int numPlayers = roundPlayers.size();
    static int currentPlayerIndex;
    static Player currentPlayer = roundPlayers.get(currentPlayerIndex);

    Table(ArrayList<Player> p, int s) {
        players = p;
        smallBlind = s;
        roundPlayers = p;
    }

    void runGame() {
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < numPlayers; i++) {
                Deck.deal(roundPlayers.get(j));
            }
        }
        Deck.burn();
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
        }
        for(int i = 0; i < 3; i++) {
            Deck.deal(communityCards);
            printCard(communityCards.get(i));
        }
        Deck.burn();
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
        }
        Deck.deal(communityCards);
        printCard(communityCards.get(3));
        Deck.burn();
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
        }
        Deck.deal(communityCards);
        printCard(communityCards.get(4));
    }

    void printCard(Card c) {
        System.out.println(c.rank + " " + c.suit);
    }

    void promptPlayer() {
        System.out.println("Current Turn: " + currentPlayer.name);
        System.out.println("Current Chips " + currentPlayer.currentChips);
        System.out.println("Player Cards:");
        for(Card c : currentPlayer.holeCards) {
            printCard(c);
        }
        System.out.println("Community Cards:");
        for(Card c : communityCards) {
            printCard(c);
        }
        System.out.println("Pot: " + pot);
        System.out.println("Choose Option");
        System.out.println("1-call ; 2-check ; 3-raise ; 4-fold");
    }

    void recordBet() {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice) {
        case 1:
            currentPlayer.call();
            pot += currentPlayer.playerBet;
            break;
        case 2:
            try {
                currentPlayer.check();
            } 
            catch (Exception e) {
                currentPlayerIndex--;
                promptPlayer();
            }
            break;
        case 3:
            int tmp = sc.nextInt();
            try {
                currentPlayer.raise(tmp);
                pot += currentPlayer.playerBet;
            } 
            catch (Exception e) {
                currentPlayerIndex--;
                promptPlayer();
            }
            break;
        case 4:
            currentPlayer.fold();
            numPlayers--;
            break;
        default:
            System.out.println("Invalid input");
            currentPlayerIndex--;
            promptPlayer();
            break;
        }
        if(currentPlayerIndex++ >= numPlayers) {
            currentPlayerIndex = 0;
        }
        currentPlayer = roundPlayers.get(currentPlayerIndex);
        sc.close();
    }

    void revealCard(){
        for(int i = 0; i < numPlayers; i++){
            if(roundPlayers.get(i).playerBet == currentBet){
                continue;
            }
            else{
                currentPlayer = roundPlayers.get(i);
                promptPlayer();
            }
        }
        for(int i = 3; i < 5; i++){
            if (communityCards.get(i).faceUp == false) {
                communityCards.get(i).faceUp = true;
                break;
            }
        }
    }

}
