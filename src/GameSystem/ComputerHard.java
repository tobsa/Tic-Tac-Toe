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
        int bestValue = Integer.MIN_VALUE;
        int bestMove = -1;
        
        for(int move : grid.getLegalMoves()) {
            int oldID = grid.getID(move);
            grid.replaceID(move, getID());
            int value = minValue(grid);
            if(value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
            grid.replaceID(move, oldID);
        }
        
        return bestMove;
    }
        
    private int maxValue(GameGrid grid) {
        int result = grid.getResult();
        if(terminalTest(result))
            return utility(result);
        
        int v = Integer.MIN_VALUE;
        
        for(int move : grid.getLegalMoves()) {
            int oldID = grid.getID(move);
            grid.replaceID(move, getID());
            v = Math.max(v, minValue(grid));
            grid.replaceID(move, oldID);
        }
        
        return v;
    }
    
    private int minValue(GameGrid grid) {
        int result = grid.getResult();
        if(terminalTest(result))
            return utility(result);
        
        int v = Integer.MAX_VALUE;
        
        for(int move : grid.getLegalMoves()) {
            int oldID = grid.getID(move);
            grid.replaceID(move, getID() == PLAYER_1 ? PLAYER_2 : PLAYER_1);
            v = Math.min(v, maxValue(grid));
            grid.replaceID(move, oldID);
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
            return getID() == PLAYER_1 ? UTILITY_WIN : UTILITY_LOSE;
        else if(result == RESULT_PLAYER_2_WON)
            return getID() == PLAYER_2 ? UTILITY_WIN : UTILITY_LOSE;
        else
            return UTILITY_DRAW;
    }
}
