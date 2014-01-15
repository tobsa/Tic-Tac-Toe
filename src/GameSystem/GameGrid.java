package GameSystem;

import SharedSystem.IGGListener;
import SharedSystem.SharedConstants;
import java.util.ArrayList;
import java.util.List;

public class GameGrid implements SharedConstants {
    private int grid[] = new int[GRID_SIZE * GRID_SIZE];
    private List<IGGListener> listeners = new ArrayList();
    
    public void setMark(int index, int id) {
        if(index < 0 || index >= grid.length)
            return;
        
        for(IGGListener listener : listeners)
            listener.updateMove(index, id);
        
        grid[index] = id;
    }
    
    public int getGridSize() {
        return grid.length;
    }
    
    public int getType(int index) {
        if(index < 0 || index >= grid.length)
            return INVALID_INDEX;
        
        return grid[index];
    }
    
    public void clear() {
        for(int i = 0; i < grid.length; i++) {
            grid[i] = BOARD_EMPTY;
            
            for(IGGListener listener : listeners)
                listener.updateMove(i, BOARD_EMPTY);
        }
    }
    
    public int getResult() {
        int result1 = getResult(BOARD_PLAYER_1);
        int result2 = getResult(BOARD_PLAYER_2);
        
        if(result1 == BOARD_PLAYER_1)
            return RESULT_PLAYER_1_WON;
        if(result2 == BOARD_PLAYER_2)
            return RESULT_PLAYER_2_WON;
        if(isFull())
            return RESULT_DRAW;
        
        return RESULT_NO_OUTCOME;
    }
    
    private int getResult(int player) {
        if((grid[0] == player && grid[3] == player && grid[6] == player) || 
           (grid[1] == player && grid[4] == player && grid[7] == player) ||
           (grid[2] == player && grid[5] == player && grid[8] == player) ||
           (grid[0] == player && grid[1] == player && grid[2] == player) ||
           (grid[3] == player && grid[4] == player && grid[5] == player) ||
           (grid[6] == player && grid[7] == player && grid[8] == player) ||
           (grid[0] == player && grid[4] == player && grid[8] == player) ||
           (grid[2] == player && grid[4] == player && grid[6] == player))
            return player;
        
        return RESULT_NO_OUTCOME;
    }
    
    private boolean isFull() {
        for(int i = 0; i < grid.length; i++)
            if(grid[i] == BOARD_EMPTY)
                return false;
        
        return true;
    }
    
    public void registerListener(IGGListener listener) {
        listeners.add(listener);
    }
    
    public void clearListeners() {
        listeners.clear();
    }
}
