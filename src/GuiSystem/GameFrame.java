package GuiSystem;

import GameSystem.GameManager;
import SharedSystem.IGMListener;
import SharedSystem.SharedConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class GameFrame extends JFrame implements SharedConstants, IGMListener {
    private JButton newGameButton  = new JButton("New Game");
    private JButton exitGameButton = new JButton("Exit Game");
    private GameBoard gameBoard = new GameBoard();
    private GameManager gameManager = new GameManager();
    private Thread gameThread = new Thread(gameManager);
    private JLabel playerTurnLabel = new JLabel("");
    
    public GameFrame() {
        newGameButton.addActionListener(new ButtonNewGameListener());
        exitGameButton.addActionListener(new ButtonExitGameListener());
        
        playerTurnLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        playerTurnLabel.setForeground(Color.DARK_GRAY);
        
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 5, 10), new EtchedBorder()));
        menuPanel.add(newGameButton);
        menuPanel.add(exitGameButton);
        menuPanel.add(playerTurnLabel);
        
        JPanel boardPanel = new JPanel();
        boardPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 10, 10, 10), new EtchedBorder()));
        boardPanel.add(gameBoard);
        
        add(menuPanel,  BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
       
        pack();
    }
    
    private void initializeGame() {
        gameThread.interrupt();
        gameManager.clearGrid();
        gameManager.clearIGGListeners();
        gameManager.clearIGMListeners();
        gameManager.registerIGGListener(gameBoard);
        gameManager.registerIGMListener(this);
    }
    
    private void executeGame() {
        gameThread = new Thread(gameManager);
        gameThread.start();
    }

    @Override
    public void updateWinner(int result, String name1, String name2) {
        new ResultDialog(this, name1, name2, result);
        playerTurnLabel.setText("");
    }
    
    @Override
    public void updateTurn(String name) {
        playerTurnLabel.setText("Turn: " + name);
    }

    private class ButtonNewGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            SetupGameDialog dialog = new SetupGameDialog(GameFrame.this);
            
            if(dialog.getSelection() == SetupGameDialog.SELECTION_OK) {
                initializeGame();
                gameManager.createPlayer1(dialog.getPlayer1Type(), dialog.getPlayer1Name());
                gameManager.createPlayer2(dialog.getPlayer2Type(), dialog.getPlayer2Name());
                executeGame();
            }
        }   
    }
    
    private class ButtonExitGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
             System.exit(0);
        }   
    }
}
