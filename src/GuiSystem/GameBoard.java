package GuiSystem;

import SharedSystem.BlockQueue;
import SharedSystem.IGGListener;
import SharedSystem.SharedConstants;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements SharedConstants, IGGListener {
    private JLabel grid[] = new JLabel[GRID_SIZE * GRID_SIZE];
    private ImageIcon emptyIcon  = new ImageIcon("images/empty.png");
    private ImageIcon crossIcon  = new ImageIcon("images/cross.png");
    private ImageIcon circleIcon = new ImageIcon("images/circle.png");
    
    public GameBoard() {
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        addMouseListener(new MousePressedListener());
        
        for(int i = 0; i < grid.length; i++) {
            JLabel label = new JLabel(emptyIcon);
            grid[i] = label;
            add(label);
        }
    }

    @Override
    public void updateMove(int index, int id) {
        switch(id) {
            case BOARD_EMPTY:
                grid[index].setIcon(emptyIcon);
                break;
            case BOARD_PLAYER_1:
                grid[index].setIcon(crossIcon);
                break;
            case BOARD_PLAYER_2:
                grid[index].setIcon(circleIcon);
                break;
        }
    }
    
    private class MousePressedListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            int x = event.getX() / IMAGE_SIZE;
            int y = event.getY() / IMAGE_SIZE;
              
            BlockQueue.getInstance().add(x + y * GRID_SIZE);
        }
    }
}

