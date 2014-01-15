package GameSystem;

import SharedSystem.SharedConstants;

public class ComputerHard extends Player implements SharedConstants {
    private GameGrid gameGrid;
    private static final int UTILITY_LOSE = -1;
    private static final int UTILITY_DRAW = 0;
    private static final int UTILITY_WIN = 1;
    
    public ComputerHard(int id, String name, GameGrid gameGrid) {
        super(id, name);
        this.gameGrid = gameGrid;
    }
     
    @Override
    public int computeMove() throws InterruptedException {
        return minimaxDecision(gameGrid.clone());
    }
    
    private int minimaxDecision(GameGrid grid) {        
        int maximum = Integer.MIN_VALUE;
        int action = -1;
        
        for(int move : grid.getLegalMoves()) {
            int oldID = grid.getMark(move);
            grid.setMark(move, getID());
            int value = minValue(grid);
            if(value > maximum) {
                maximum = value;
                action = move;
            }
            grid.setMark(move, oldID);
        }
        
        return action;
    }
        
    private int maxValue(GameGrid grid) {
        int result = grid.getResult();
        if(terminalTest(result))
            return utility(result);
        
        int v = Integer.MIN_VALUE;
        
        for(int move : grid.getLegalMoves()) {
            int oldID = grid.getMark(move);
            grid.setMark(move, getID());
            v = Math.max(v, minValue(grid));
            grid.setMark(move, oldID);
        }
        
        return v;
    }
    
    private int minValue(GameGrid grid) {
        int result = grid.getResult();
        if(terminalTest(result))
            return utility(result);
        
        int v = Integer.MAX_VALUE;
        
        for(int move : grid.getLegalMoves()) {
            int oldID = grid.getMark(move);
            grid.setMark(move, getID());
            v = Math.min(v, maxValue(grid));
            grid.setMark(move, oldID);
        }
        
        return v;
    }
    
    private boolean terminalTest(int result) {        
        return result == RESULT_PLAYER_1_WON || 
               result == RESULT_PLAYER_2_WON || 
               result == RESULT_DRAW;
    }
    
    private int utility(int result) {        
        if(result == RESULT_PLAYER_1_WON)
            return UTILITY_LOSE;
        else if(result == RESULT_PLAYER_2_WON)
            return UTILITY_WIN;
        else
            return UTILITY_DRAW;
    }            
}
