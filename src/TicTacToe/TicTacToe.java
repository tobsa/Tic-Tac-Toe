package TicTacToe;

import GameSystem.GameGrid;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        GameGrid grid = new GameGrid();
        
        while(true) {
            for(int i = 0; i < grid.getGridSize(); i++) {
                System.out.print(grid.getType(i) + " ");
                if(i == 2 || i == 5)
                    System.out.println("");
            }
            
            if(grid.getResult() == 1 || grid.getResult() == 2)
                System.out.println("WINNER!");
            
            System.out.print("\nInput: ");
            int input = new Scanner(System.in).nextInt();
            grid.setMark(input, 1);
        }
    }
}
