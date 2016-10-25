package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Central class to create the window and the canvas.
 * Loads and paints objects on that canvas.
 */

// Extends JPanel, so as to override the paintComponent() for custom rendering codes.
public class Application extends JPanel {

    // Initialise game window
    public static JFrame frame;

    // Initialise a default ball for testing purposes
    private Ball ball = new Ball();

    // Animation variables
    private static final int FRAME_RATE = 30;


    // Application constructor creates UI components and initialises game objects
    public Application() {
        frame.getContentPane().setPreferredSize(new Dimension(250, 500));
        frame.pack();
        frame.setLocationRelativeTo(null); // centers the window

        Thread gameThread = new Thread() {
            public void run() {
                while (true) { // Execute one update step

                    // Check if ball out of frame
                    ball.check_in_bounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight());

                    // Each component should call it's update method
                    ball.update();

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

    //
    // Overriding paint method to paint all the components
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear the canvas
        g.clearRect(0, 0, getWidth(), getHeight());

        // Paint the ball
        g2.setColor(ball.getColor());
        g2.fillOval(ball.getLocation().getX(), ball.getLocation().getY(), ball.getWidth(), ball.getHeight());
    }


    // Main method
    public static void main(String[] args) {

        // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create JFrame and attach application
                frame = new JFrame("Ball maze");
                Application game = new Application();
                frame.getContentPane().add(game);
                frame.pack();
                // Run house keeping on frame
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke) {  // handler
                        if(ke.getKeyCode() == ke.VK_ESCAPE) {
                            System.out.println("escaped ?");
                            System.exit(0);
                        }
                        else {
                            System.out.println("not escaped");
                        }
                    }
                });

            }
        });
    }

}