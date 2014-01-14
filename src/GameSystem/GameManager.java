package GameSystem;

import SharedSystem.SharedConstants;

public class GameManager implements SharedConstants, Runnable {
    private Player player1;
    private Player player2;
    private GameGrid gameGrid = new GameGrid();
    
    public void createPlayer1(int type, String name) {
        switch(type) {
            case HUMAN:
                player1 = new Human(PLAYER_1, name);
                break;
            case COMPUTER:
                player1 = new Computer(PLAYER_1, name);
                break;
        }
    }
    
    public void createPlayer2(int type, String name) {
        switch(type) {
            case HUMAN:
                player2 = new Human(PLAYER_2, name);
                break;
            case COMPUTER:
                player2 = new Computer(PLAYER_2, name);
                break;
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                int move = player1.computeMove();
                gameGrid.setMark(move, player1.getID());
            } catch (InterruptedException ex) {
                
            }
        }
    }
}
