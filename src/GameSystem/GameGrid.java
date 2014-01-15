package GameSystem;

import SharedSystem.IGGListener;
import SharedSystem.SharedConstants;
import java.util.ArrayList;
import java.util.List;

public class GameGrid implements SharedConstants {
    private int grid[] = new int[GRID_SIZE * GRID_SIZE];
    private List<IGGListener> listeners = new ArrayList();
    
    public boolean setMark(int index, int id) {
        if(index < 0 || index >= grid.length || grid[index] != PLAYER_EMPTY)
            return false;
                
        for(IGGListener listener : listeners)
            listener.updateMove(index, id);
        
        grid[index] = id;
        
        return true;
    }
    
    @Override
    public GameGrid clone() {
        GameGrid gameGrid = new GameGrid();
        
        for(int i = 0; i < grid.length; i++)
            gameGrid.setMark(i, grid[i]);
        
        return gameGrid;        
    }
    
    public int getMark(int index) {
        return grid[index];
    }
    
    public int getGridSize() {
        return grid.length;
    }
        
    public void clear() {
        for(int i = 0; i < grid.length; i++) {
            grid[i] = PLAYER_EMPTY;
            
            for(IGGListener listener : listeners)
                listener.updateMove(i, PLAYER_EMPTY);
        }
    }
    
    public List<Integer> getLegalMoves() {
        List<Integer> list = new ArrayList();
        
        for(int i = 0; i < grid.length; i++)
            if(grid[i] == PLAYER_EMPTY)
                list.add(i);
        
        return list;
    }
    
    public boolean isWinningMove(int index, int id) {
        if(index < 0 || index >= grid.length)
            return false;
        
        boolean result = false;
        int oldID = grid[index];
        grid[index] = id;
        
        switch(index) {
            case 0: 
                result = (grid[0] == id && grid[1] == id && grid[2] == id) ||
                         (grid[0] == id && grid[3] == id && grid[6] == id) ||
                         (grid[0] == id && grid[4] == id && grid[8] == id);
                break;
            case 1:
                result = (grid[0] == id && grid[1] == id && grid[2] == id) ||
                         (grid[1] == id && grid[4] == id && grid[7] == id);
                break;
            case 2:
                result = (grid[0] == id && grid[1] == id && grid[2] == id) ||
                         (grid[2] == id && grid[5] == id && grid[8] == id) ||
                         (grid[2] == id && grid[4] == id && grid[6] == id);
                break;
            case 3:
                result = (grid[3] == id && grid[4] == id && grid[5] == id) ||
                         (grid[0] == id && grid[3] == id && grid[6] == id);
                break;
            case 4:
                result = (grid[1] == id && grid[4] == id && grid[7] == id) ||
                         (grid[3] == id && grid[4] == id && grid[5] == id) ||
                         (grid[0] == id && grid[4] == id && grid[8] == id) ||
                         (grid[2] == id && grid[4] == id && grid[6] == id);
                break;
            case 5:
                result = (grid[3] == id && grid[4] == id && grid[5] == id) ||
                         (grid[2] == id && grid[5] == id && grid[8] == id);
                break;
            case 6:
                result = (grid[6] == id && grid[7] == id && grid[8] == id) ||
                         (grid[0] == id && grid[3] == id && grid[6] == id) ||
                         (grid[6] == id && grid[4] == id && grid[2] == id);
                break;
            case 7:
                result = (grid[1] == id && grid[4] == id && grid[7] == id) ||
                         (grid[6] == id && grid[7] == id && grid[8] == id);
                break;
            case 8:
                result = (grid[2] == id && grid[5] == id && grid[8] == id) ||
                         (grid[6] == id && grid[7] == id && grid[8] == id) ||
                         (grid[8] == id && grid[4] == id && grid[0] == id); 
                break;
        }
        
        grid[index] = oldID;
        
        return result;
    }
    
    public int getResult() {
        int result1 = getResult(PLAYER_1);
        int result2 = getResult(PLAYER_2);
        
        if(result1 == PLAYER_1)
            return RESULT_PLAYER_1_WON;
        if(result2 == PLAYER_2)
            return RESULT_PLAYER_2_WON;
        if(isFull())
            return RESULT_DRAW;
        
        return RESULT_NO_OUTCOME;
    }
    
    private int getResult(int id) {
        if((grid[0] == id && grid[3] == id && grid[6] == id) || 
           (grid[1] == id && grid[4] == id && grid[7] == id) ||
           (grid[2] == id && grid[5] == id && grid[8] == id) ||
           (grid[0] == id && grid[1] == id && grid[2] == id) ||
           (grid[3] == id && grid[4] == id && grid[5] == id) ||
           (grid[6] == id && grid[7] == id && grid[8] == id) ||
           (grid[0] == id && grid[4] == id && grid[8] == id) ||
           (grid[2] == id && grid[4] == id && grid[6] == id))
            return id;
        
        return RESULT_NO_OUTCOME;
    }
    
    private boolean isFull() {
        for(int i = 0; i < grid.length; i++)
            if(grid[i] == PLAYER_EMPTY)
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
