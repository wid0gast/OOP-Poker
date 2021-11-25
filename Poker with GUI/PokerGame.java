import java.util.*;
//import java.io.*;

public class PokerGame {
    public static String input1;
    public static String input2;
    public static String input3;
    
    public static void main(String args[]) {
        //PokerGame game = new PokerGame();
        //game.testGame();
        ArrayList<Player> players = new ArrayList<>();
        PokerGUI.Prompt.setText("Number of Players?");
        int n = Integer.parseInt(input3);
        for(int i = 1; i <= n; i++) {
            PokerGUI.Prompt.setText("Name of Player " + i);
            while(n == Integer.parseInt(input3)) {
                continue;
            }
            String tmp1 = input3;
            PokerGUI.Prompt.setText("Number of chips of Player " + i);
            while(tmp1 == input3) {
                continue;
            }
            int tmp2 = Integer.parseInt(input3);
            Player tmp = new Player(tmp1, tmp2);
            players.add(tmp);
            while(tmp2 == Integer.parseInt(input3));
        }
        Table table = new Table(players);
        table.runGame();
        PokerGUI.Prompt.setText("Another Round? %n1: Yes%n0: No");
        while(PokerGame.input3 == "1") {
            table.runGame();
            PokerGUI.Prompt.setText("Another Round? %n1: Yes%n0: No");
        }
        System.out.println("Thank You For Playing!");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PokerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PokerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PokerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PokerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PokerGUI().setVisible(true);
            }
        });
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