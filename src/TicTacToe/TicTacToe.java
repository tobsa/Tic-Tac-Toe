package TicTacToe;

import GuiSystem.GameFrame;
import javax.swing.JFrame;

public class TicTacToe {
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.setTitle("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
