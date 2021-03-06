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
        PokerGUI.Prompt.setText("Round 1");
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
            if(gameOver) {
                return;
            }
        }
        PokerGUI.Prompt.setText("Revealing Flop: ");
        revealCard(3);

        Deck.burn();
        PokerGUI.Prompt.setText("Round 2");
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
            if(gameOver) {
                return;
            }
        }
        catchChecks();
        PokerGUI.Prompt.setText("Revealing Turn: ");
        revealCard(1);
        Deck.burn();
        PokerGUI.Prompt.setText("Round 3");
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
            if(gameOver) {
                return;
            }
        }
        catchChecks();
        PokerGUI.Prompt.setText("Revealing River: ");
        revealCard(1);
        PokerGUI.Prompt.setText("Round 4");
        for(int i = 0; i < numPlayers; i++) {
            promptPlayer();
            recordBet();
            if(gameOver) {
                return;
            }
        }
        catchChecks();
        System.out.printf("%n#######################################################%n%n");
        declareWinner();
    }

    static void printCard(Card c) {
        switch (c.rank) {
            case 11:
                System.out.println("J " + c.suit);
                break;
            case 12:
                System.out.println("Q " + c.suit);
                break;
            case 13:
                System.out.println("K " + c.suit);
                break;
            case 14:
                System.out.println("A " + c.suit);
                break;
            default:
                System.out.println(c.rank + " " + c.suit);
                break;
        }
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
            catch (ForbiddenCheckException e) {
                System.out.println(e.getMessage());
                System.out.println("ERROR");
                currentPlayerIndex--;
                promptPlayer();
                recordBet();
            }
            break;
        case 3:
            System.out.println("Enter Bet Amount");
            int tmp = Integer.parseInt(sc.nextLine());

            try {
                currentPlayer.raise(tmp);
                pot += currentPlayer.playerBet;
            } 
            catch (NotEnoughChipsException e) {
                System.out.println("Not enough chips to raise");
                currentPlayerIndex--;
                promptPlayer();
                recordBet();
            }
            catch(RaisingTooLittleException e) {
                System.out.println("Raise more than the current bet");
                currentPlayerIndex--;
                promptPlayer();
                recordBet();
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
            recordBet();
            break;
        }
        if(++currentPlayerIndex >= numPlayers) {
            currentPlayerIndex = 0;
        }
        currentPlayer = roundPlayers.get(currentPlayerIndex);
        System.out.printf("%n#######################################################%n%n");
        //sc.close();
    }

    static void catchChecks() {
        for(int i = 0; i < roundPlayers.size() - 1; i++){
            if(roundPlayers.get(i).isAllIn == false && roundPlayers.get(i + 1).isAllIn == false){
                if(roundPlayers.get(i).playerBet != roundPlayers.get(i + 1).playerBet){
                    currentPlayer = roundPlayers.get(i);
                    promptPlayer();
                    recordBet();
                }
            }
            else{
                continue;
            }
        }
    }

    static void revealCard(int n){
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
                return o2.hand.compareTo(o1.hand);
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
                System.out.println(roundPlayers.get(i).name + "!! You Win: " + roundPrize + " Chips!");
            }
        }
    }

    static void declareWinner(Player p) {
        System.out.println("Winner is: " + p.name + "!!");
        System.out.println("You Win: " + pot + " Chips!");
        gameOver = true;
    }

}
