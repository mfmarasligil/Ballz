package Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Defines the layout of the home screen. By clicking on the "new game" button
 * a new instance of Application will be created which starts the game.
 */
public class HomeScreen extends JPanel {

    public HomeScreen() {
        // Set the layout to border Layout so that the top part doesn't resize vertically
        this.setLayout(new BorderLayout());

        // Create a JPanel for the title bar and set its colour
        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(63, 137, 241));

        JLabel title = new JLabel("BALL MAZE");
        title.setForeground(new Color(255, 255, 255));
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        title.setBorder(new EmptyBorder(12, 0, 9, 0));

        titleBar.add(title);
        titleBar.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(255, 255, 255)));
        this.add(titleBar, BorderLayout.PAGE_START);

        // Create a new game button
        JButton newGame = new JButton("START GAME");
        newGame.setOpaque(false);
        newGame.setFocusPainted(false);
        newGame.setBorderPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setForeground(new Color(255, 255, 255));
        newGame.setFont(new Font("Helvetica", Font.BOLD, 40));
        /*
        newGame.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        */

        /*
         * By clicking on the "New Game" button a new instance of the game will be created, the home screen
         * will be removed from the screen and the game starts.
        * */
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.frame.getContentPane().setPreferredSize(new Dimension(350, 500));
                Application.frame.getContentPane().remove(Application.homeScreen);
                Application.game = new Application();
                Application.frame.getContentPane().add(Application.game);
                Application.frame.pack();
                Application.frame.setLocationRelativeTo(null); // centers the window
            }

        });


        // Use a GridLayout to vertically center the button
        JPanel white = new JPanel(new GridLayout(0, 1, 0, 400));
        white.setBackground(new Color(241, 172, 63));

        white.add(newGame);

        this.add(white, BorderLayout.CENTER);
    }

}
