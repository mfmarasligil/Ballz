package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Central class to create the window and the canvas for the game.
 * Loads and paints objects on that canvas.
 */

// Extends JPanel, so as to override the paintComponent() for custom rendering codes.
public class Application extends JPanel implements MouseMotionListener {

    // Initialise game window
    public static JFrame frame;
    public static Application game;
    public static HomeScreen homeScreen;

    // Initialise a new ball
    private Ball ball = new Ball();

    // List of blocks
    private ArrayList<Block> blockList;
    Block block1 = new Block(ball.getWidth());
    Block block2 = new Block(ball.getWidth());
    Block block3 = new Block(ball.getWidth());
    RubberBlock block4 = new RubberBlock(ball.getWidth());

    // Animation variables
    private static final int FRAME_RATE = 30;

    /**
     * Application constructor creates UI components and initialises game objects
     */
    public Application() {
        // Add the mouse motion listener as defined in the function below
        this.addMouseMotionListener(this);

        Thread gameThread = new Thread() {
            public void run() {
                while(true) {

                    // Execute one update step
                    ball.update();
                    ball.checkInFrame();

                    ball.checkMechIntersection(block1);
                    ball.checkMechIntersection(block2);
                    ball.checkMechIntersection(block3);
                    ball.checkMechIntersection(block4);

                    // Repaint all the components on the frame
                    repaint();

                    // Delay for timing control and give other threads a chance
                    try {
                        Thread.sleep(1000 / FRAME_RATE);  // milliseconds
                    } catch (InterruptedException ex) {
                    }

                }
            }
        };

        gameThread.start(); // Callback run
    }


    /**
     * Overriding paint method to paint all the components
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear the canvas
        g.clearRect(0, 0, getWidth(), getHeight());

        // Paint the new ball
        g2.setColor(new Color(241, 172, 63));
        ball.draw(g2);

        // Paint the blocks
        g2.setColor(new Color(63, 137, 241));
        block1.draw(g2);
        block2.draw(g2);
        block3.draw(g2);

        block4.draw(g2);
    }

    /**
     * Adds points to the points ArrayList in order for the eraser block to work
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        block4.addPoint(e.getPoint());
    }

    // Not used
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    // Main method
    public static void main(String[] args) {

        // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                // Create JFrame and attach application
                frame = new JFrame("Ball maze");
                frame.getContentPane().setPreferredSize(new Dimension(500, 350));
                frame.pack();
                frame.setLocationRelativeTo(null); // centers the window

                // Show the home screen
                homeScreen = new HomeScreen();
                frame.getContentPane().add(homeScreen);

                // Run house keeping on frame
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}