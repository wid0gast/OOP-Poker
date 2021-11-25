import java.util.*;
//import java.io.*;

public class PokerGame {
    public static void main(String args[]) {
        //PokerGame game = new PokerGame();
        //game.testGame();
        ArrayList<Player> players = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Number of Players?");
        int n = sc.nextInt();
        for(int i = 1; i <= n; i++) {
            System.out.println("Name of Player " + i);
            sc.nextLine();
            String tmp1 = sc.nextLine();
            System.out.println("Number of chips of Player " + i);
            int tmp2 = sc.nextInt();
            Player tmp = new Player(tmp1, tmp2);
            players.add(tmp);
        }
        Table table = new Table(players);
        table.runGame();
        System.out.printf("Another Round? %n1: Yes %n2: No");
        int x = sc.nextInt();
        while(x == 1) {
            table.runGame();
            System.out.printf("Another Round? %n1: Yes %n2: No");
            x = sc.nextInt();
        }
        System.out.println("Thank You For Playing!");
        sc.close();
    }

    public void testGame() {
        String p1 = "Shaz";
        String p2 = "Anshika";
        int x1 = 1000;
        int x2 = 1000;
        Player shaz = new Player(p1, x1);
        Player anshika = new Player(p2, x2);
        ArrayList<Player> players = new ArrayList<>();
        players.add(shaz);
        players.add(anshika);
        Table table = new Table(players);
        table.runGame();
    }
}