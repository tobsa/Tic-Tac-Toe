package GameSystem;

import SharedSystem.IGGListener;
import SharedSystem.SharedConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerEasy extends Player implements SharedConstants, IGGListener {
    private int grid[] = new int[GRID_SIZE * GRID_SIZE];
    
    public ComputerEasy(int id, String name) {
        super(id, name);
    }
     
    @Override
    public int computeMove() throws InterruptedException {
        Thread.sleep(500);
        
        List<Integer> moves = getMoves();
        return moves.get(new Random().nextInt(moves.size()));
    }
    
    private List<Integer> getMoves() {
        List<Integer> list = new ArrayList();
        
        for(int i = 0; i < grid.length; i++)
            if(grid[i] == PLAYER_EMPTY)
                list.add(i);
        
        return list;
    }

    @Override
    public void updateMove(int index, int id) {
        grid[index] = id;
    }
}
