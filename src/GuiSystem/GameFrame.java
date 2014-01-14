package GuiSystem;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class GameFrame extends JFrame {
    private JButton newGameButton  = new JButton("New Game");
    private JButton exitGameButton = new JButton("Exit Game");
    
    public GameFrame() {
        initPanels();
        initListeners();
       
        pack();
    }

    private void initPanels() {
        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
        menuPanel.add(newGameButton);
        menuPanel.add(exitGameButton);
        
        add(menuPanel, BorderLayout.NORTH);
    }

    private void initListeners() {
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 System.exit(0);
            }
        });
    }
}
