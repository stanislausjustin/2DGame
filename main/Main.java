package main;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        GamePanel gamePanel = new GamePanel();
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /*close thw window ad x button*/
        window.setResizable(false); /*cannot resize window*/
        window.setTitle("meowbob");
        window.add(gamePanel);
        window.pack(); //so the windowwwwwww is sized to fit the preferred size
        window.setLocationRelativeTo(null); /*cuz null jdny center of the screen - it's not specified*/
        window.setVisible(true); /*spya bs diliat*/

        gamePanel.setupGame();

        gamePanel.startGameThread();

    }
}