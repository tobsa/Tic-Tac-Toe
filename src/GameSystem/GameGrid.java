package GameSystem;

import SharedSystem.SharedConstants;

public class GameGrid implements SharedConstants {
    private int grid[] = new int[GRID_SIZE * GRID_SIZE];
    
    public void setMark(int index, int type) {
        if(index < 0 || index >= grid.length)
            return;
        
        grid[index] = type;
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
        for(int i = 0; i < grid.length; i++)
            grid[i] = EMPTY;
    }
    
    public int getResult() {
        int result1 = getResult(PLAYER_1);
        int result2 = getResult(PLAYER_2);
        
        if(result1 == PLAYER_1)
            return PLAYER_1;
        if(result2 == PLAYER_2)
            return PLAYER_2;
        
        return NO_OUTCOME;
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
        
        return NO_OUTCOME;
    }
}
