import java.util.*;
//import java.io.*;

public class Table {
    static int numberOfPlayers;
    static ArrayList<Player> players = new ArrayList<>();
    static int pot = 0;
    static ArrayList<Card> communityCards = new ArrayList<>();
    static int smallBlind = 10;
    static int bigBlind = 2 * smallBlind;
    static int currentBet = bigBlind;
    int currentPlayerIndex;
    Player currentPlayer = players.get(currentPlayerIndex);

    Table(ArrayList<Player> p, int s) {
        players = p;
        smallBlind = s;
    }

    void promptPlayer() {
        System.out.println("Current Turn: " + currentPlayer.name);
        System.out.println("1-call ; 2-check ; 3-raise ; 4-fold");
        if(currentPlayerIndex++ >= numberOfPlayers) {
            currentPlayerIndex = 0;
        }
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
            currentPlayer.check();
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
            break;
        default:
            System.out.println("Invalid input");
            currentPlayerIndex--;
            promptPlayer();
            break;
        }
        sc.close();
    }

}