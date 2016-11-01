package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 * Central class to create the window and the canvas for the game.
 * Loads and paints objects on that canvas.
 */


// Extends JPanel, so as to override the paintComponent() for custom rendering codes.
public class Application extends JPanel implements MouseListener, MouseMotionListener {

    // Initialise game window
    public static JFrame frame;
    public static Application game;
    public static HomeScreen homeScreen;

    // Initialise a new ball
    private Ball ball = new Ball();

    // Initialise some blocks
    private Block block1 = new Block(ball.getWidth());

    // Animation variables
    private static final int FRAME_RATE = 30;

    // Interface variables
    private Point clickLoc;

    // Application constructor creates UI components and initialises game objects
    public Application() {

        addMouseListener(this);
        addMouseMotionListener(this);

        Thread gameThread = new Thread() {
            public void run() {
                while(true) {

                    // Execute one update step
                    ball.update();
                    ball.checkInFrame();
                    ball.checkMechIntersection(block1);

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

        // Paint the new ball
        g2.setColor(new Color(241, 172, 63));
        ball.draw(g2);

        // Paint the blocks
        g2.setColor(new Color(63, 137, 241));
        block1.draw(g2);

    }

    // Mouse control methods for interaction
    @Override
    public void mousePressed(MouseEvent e) {

        // Store location of click
        clickLoc = e.getPoint();

        if(block1.contains(clickLoc)){
            block1.setDragging(true);

            // Record the position of the block when first selected
            block1.initLoc = block1.getLocation();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(block1.getDragging()){

            // Find the distance moved
            double dx = clickLoc.getX() - e.getX();
            double dy = clickLoc.getY() - e.getY();

            int moveX = (int)(block1.initLoc.getX() -dx);
            int moveY = (int)(block1.initLoc.getY() -dy);

            // Set new location for the block as a result of interaction
            block1.setLocation(moveX, moveY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        block1.setDragging(false);
    }

    @Override
    public void mouseExited(MouseEvent e){
    }

    @Override
    public void mouseEntered(MouseEvent e){
    }

    @Override
    public void mouseMoved(MouseEvent e){
    }

    @Override
    public void mouseClicked(MouseEvent e){
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

                /*
                Doesn't work anymore... does someone have a solution?
                frame.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent ke) {  // handler
                        if(ke.getKeyCode() == ke.VK_ESCAPE) {
                            System.exit(0);
                        }
                        else {
                            System.out.println("Failed to close window.");
                        }
                    }
                });

                */
                frame.pack();
                frame.setVisible(true);

            }
        });
    }

}