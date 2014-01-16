package GameSystem;

import SharedSystem.SharedConstants;
import java.util.List;
import java.util.Random;

public class ComputerNormal extends Player implements SharedConstants {
    private GameGrid gameGrid;
    
    public ComputerNormal(int id, String name, GameGrid gameGrid) {
        super(id, name);
        this.gameGrid = gameGrid;
    }
     
    @Override
    public int computeMove() throws InterruptedException {
        Thread.sleep(500);
        
        List<Integer> moves = gameGrid.getLegalMoves();
        
        int opponentID = getID() == PLAYER_1 ? PLAYER_2 : PLAYER_1;
        
        for(int move : moves) {
            if(isWinningMove(move, getID()))
                return move;
        }
 
        for(int move : moves) {
            if(isWinningMove(move, opponentID))
                return move;
        }
        
        return moves.get(new Random().nextInt(moves.size()));
    }
    
    private boolean isWinningMove(int index, int id) {
        int oldID = gameGrid.getID(index);
        gameGrid.replaceID(index, id);
        int result = gameGrid.getResult();
        gameGrid.replaceID(index, oldID);
        
        if(result == RESULT_PLAYER_1_WON || result == RESULT_PLAYER_2_WON)
            return true;
        
        return false;        
    }
}
