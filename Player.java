import java.util.*;
//import java.io.*;

public class Player {
    String name;
    int currentChips;
    ArrayList<Card> holeCards = new ArrayList<>();
    int playerBet = Table.currentBet;
    boolean isAllIn = false;

    Player(String n, int i) {
        name = n;
        currentChips = i;
    }

    void call() {
        if(currentChips < Table.currentBet) {
            System.out.println("Going All In!");
            playerBet = currentChips;
            isAllIn = true;
        }
        currentChips -= playerBet;
    }

    void check() {
        for(int i = 0; i < table.currentPlayerIndex; i++){
            if(Table.players.get(i).playerBet != 0){
                throw new ForbiddenCheckException("CANNOT CHECK");
            }
        }
        playerBet = 0; 
    }

    void raise(int n) throws NotEnoughChipsException {
        if(currentChips < Table.currentBet) {
            throw new NotEnoughChipsException("NOT ENOUGH CHIPS!");
        }
        currentChips -= playerBet;
        Table.currentBet = playerBet;
        if(currentChips == 0){
            isAllIn = true;
        } 
    }

    void fold() {
        Table.players.remove(this);
        table.currentPlayerIndex--;
    }
}
