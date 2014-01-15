package GameSystem;

import SharedSystem.BlockQueue;
import SharedSystem.IGGListener;
import SharedSystem.IGMListener;
import SharedSystem.SharedConstants;
import java.util.ArrayList;
import java.util.List;

public class GameManager implements SharedConstants, Runnable {
    private Player player1;
    private Player player2;
    private GameGrid gameGrid = new GameGrid();
    private List<IGMListener> listeners = new ArrayList();
    
    public void createPlayer1(int type, String name) {
        switch(type) {
            case PLAYER_HUMAN:
                player1 = new Human(BOARD_PLAYER_1, name);
                break;
            case PLAYER_COMPUTER:
                player1 = new Computer(BOARD_PLAYER_1, name);
                break;
        }
    }
    
    public void createPlayer2(int type, String name) {
        switch(type) {
            case PLAYER_HUMAN:
                player2 = new Human(BOARD_PLAYER_2, name);
                break;
            case PLAYER_COMPUTER:
                player2 = new Computer(BOARD_PLAYER_2, name);
                break;
        }
    }
    
    public void clearGrid() {
        gameGrid.clear();
    }
    
    public void registerIGGListener(IGGListener listener) {
        gameGrid.registerListener(listener);
    }
    
    public void registerIGMListener(IGMListener listener) {
        listeners.add(listener);
    }
    
    public void clearIGGListeners() {
        gameGrid.clearListeners();
    }
    
    public void clearIGMListeners() {
        listeners.clear();
    }
    
    private boolean makeMove(Player player, int winner) throws InterruptedException {
        for(IGMListener listener : listeners)
            listener.updateTurn(player.getName());
        
        BlockQueue.getInstance().clear();
        
        gameGrid.setMark(player.computeMove(), player.getID());
        
        int result = gameGrid.getResult();        
        if(result == winner || result == RESULT_DRAW) {
            for(IGMListener listener : listeners)
                listener.updateWinner(result, player1.getName(), player2.getName());
            
            return true;
        }
        
        return false;
    }

    @Override
    public void run() {
        while(true) {
            try {
                if(makeMove(player1, RESULT_PLAYER_1_WON))
                    break;
                if(makeMove(player2, RESULT_PLAYER_2_WON))
                    break;
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
