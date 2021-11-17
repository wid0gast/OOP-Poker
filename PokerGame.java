import java.util.*;
//import java.io.*;

public class PokerGame {
    public static void main(String args[]) {
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
        System.out.println("Enter Small Blind amount");
        int sb = sc.nextInt();
        Table table = new Table(players, sb);
        table.runGame();

        sc.close();
    }
}