package GameSystem;

import SharedSystem.SharedConstants;
import java.util.List;
import java.util.Random;

public class ComputerEasy extends Player implements SharedConstants {
    private GameGrid gameGrid;
    
    public ComputerEasy(int id, String name, GameGrid gameGrid) {
        super(id, name);
        this.gameGrid = gameGrid;
    }
     
    @Override
    public int computeMove() throws InterruptedException {
        Thread.sleep(500);
        
        List<Integer> moves = gameGrid.getLegalMoves();
        return moves.get(new Random().nextInt(moves.size()));
    }
}
