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
            if(gameGrid.isWinningMove(move, getID()))
                return move;
        }
 
        for(int move : moves) {
            if(gameGrid.isWinningMove(move, opponentID))
                return move;
        }
        
        return moves.get(new Random().nextInt(moves.size()));
    }
}
