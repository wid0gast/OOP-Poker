import java.util.*;
//import java.io.*;

public class Table {
    static ArrayList<Player> players;
    static ArrayList<Player> roundPlayers;
    static int pot = 0;
    static ArrayList<Card> communityCards;
    static int smallBlind = 10;
    static int bigBlind = 2 * smallBlind;
    static int currentBet = bigBlind;
    static int numPlayers;
    static int currentPlayerIndex;
    static Player currentPlayer;
    static Scanner sc = new Scanner(System.in);
    static boolean gameOver = false;

    Table(ArrayList<Player> p) {
        players = new ArrayList<>(p);
        roundPlayers = new ArrayList<>(p);
        communityCards = new ArrayList<>();
        numPlayers = roundPlayers.size();
        currentPlayer = roundPlayers.get(currentPlayerIndex);
        Deck.makeDeck();

    }

    void runGame() {
        Deck.shuffle();
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < numPlayers; j++) {
                Deck.deal(roundPlayers.get(j));
            }
        }
        Deck.burn();
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
            if(gameOver) {
                return;
            }
        }
        System.out.println("Revealing Flop: ");
        revealCard(3);
        Deck.burn();
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
            if(gameOver) {
                return;
            }
        }
        revealCard(1);
        Deck.burn();
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
            if(gameOver) {
                return;
            }
        }
        revealCard(1);
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
            if(gameOver) {
                return;
            }
        }
        revealCard(0);
        declareWinner();
    }

    static void printCard(Card c) {
        System.out.println(c.rank + " " + c.suit);
    }

    static void promptPlayer() {
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
        System.out.println("Current Bet: " + currentBet);
        System.out.println("Choose Option");
        System.out.println("1-call ; 2-check ; 3-raise ; 4-fold");
    }

    static void recordBet() {
        int choice = Integer.parseInt(sc.nextLine());
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
            System.out.println("Enter Bet Amount");
            int tmp = Integer.parseInt(sc.nextLine());

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
            if(numPlayers == 1) {
                declareWinner(roundPlayers.get(0));
            }
            break;
        default:
            System.out.println("Invalid input");
            currentPlayerIndex--;
            promptPlayer();
            break;
        }
        if(++currentPlayerIndex >= numPlayers) {
            currentPlayerIndex = 0;
        }
        currentPlayer = roundPlayers.get(currentPlayerIndex);
        //sc.close();
    }

    static void revealCard(int n){
        for(int i = 0; i < numPlayers; i++){
            if(roundPlayers.get(i).playerBet == currentBet){
                continue;
            }
            else{
                currentPlayer = roundPlayers.get(i);
                promptPlayer();
            }
        }
        for(int i = 0; i < n; i++) {
            Deck.deal(communityCards);
            printCard(communityCards.get(communityCards.size() - 1));
        }
    }

    static void declareWinner() {
        for(Player p : roundPlayers) {
            ArrayList<Card> tmp = new ArrayList<>(p.holeCards);
            tmp.addAll(communityCards);
            p.hand = new Hand(tmp);
        }
        Collections.sort(roundPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.hand.compareTo(o2.hand);
            }
        });
        int numWinners = 1;
        for (int  i = 1; i < roundPlayers.size(); i++){
            if(roundPlayers.get(0).hand.worth != roundPlayers.get(i).hand.worth){
                break;
            }
            else{
                numWinners++;
            }
        }
        int roundPrize = pot/numWinners;
        for(int i = 0; i < numWinners; i++){
            roundPlayers.get(i).currentChips += roundPrize;
        }
        if(numWinners == 1) {
            System.out.println("Winner is: " + roundPlayers.get(0).name + "!!");
            System.out.println("You Win: " + roundPrize + " Chips!");
        }
        else {
            System.out.println("Winners are: ");
            for(int i = 0; i < numWinners; i++) {
                System.out.println(roundPlayers.get(i) + "!! You Win: " + roundPrize + "Chips!");
            }
        }
    }

    static void declareWinner(Player p) {
        System.out.println("Winner is: " + p.name + "!!");
        System.out.println("You Win: " + pot + " Chips!");
        gameOver = true;
    }

}
