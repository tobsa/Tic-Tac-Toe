package GameSystem;

import java.util.Random;

public class Computer extends Player {

    public Computer(int id, String name) {
        super(id, name);
    }
     
    @Override
    public int computeMove() {
        return new Random().nextInt(9);
    }
}
