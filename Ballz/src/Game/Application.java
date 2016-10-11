package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * Central class to create the window and the canvas.
 * Loads and paints objects on that canvas.
 */
public class Application extends JPanel {

    public static JFrame frame;
    Application panel;
    private Location ballLocation = new Location(0, 0);
    private Ball ball = new Ball(ballLocation, 40, 40, new Color(255, 0, 0), INITIAL_X_VELOCITY, INITIAL_Y_VELOCITY);

    /* Framerate */
    private static final int FRAMES_PER_SECOND = 30;
    private static final int MS_TO_WAIT = 1000 / FRAMES_PER_SECOND; // I want 30 images per 1000 milliseconds so 1000 / 30 indicates after how many milliseconds I want to refresh
    private Timer animationTimer;
    private TimerTask animationTask;

    private static final int INITIAL_Y_VELOCITY = 10;
    private static final int INITIAL_X_VELOCITY = 5;
    private static final double ACCELERATION = 1.1;
    private static final double COEF_REST = 0.6;

    public Application() {
        animationTimer = new Timer("Ball Animation");
        animationTask = new AnimationUpdator();
    }

    public void run() {
        animationTimer.schedule(animationTask, 0, MS_TO_WAIT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear the canvas
        g.clearRect(0, 0, getWidth(), getHeight());
        // ball.setLocation((frame.getWidth()/2)-(ball.getWidth()/2), (frame.getHeight()/2)-(ball.getHeight()/2));

        // Paint the ball
        g2.setColor(ball.getColor());
        g2.fillOval(ball.getLocation().getX(), ball.getLocation().getY(), ball.getWidth(), ball.getHeight());
    }

    private class AnimationUpdator extends TimerTask {

        @Override
        public void run() {

            // Make the ball fall and accelerate
            double v2 = (ACCELERATION * 1) + ball.getVelocityY();
            ball.setVelocityY(v2);

            // Set canvas boundaries
            int minX, maxX, minY, maxY;
            minX = 0;
            minY = 0;
            maxX = (int) (frame.getWidth() - ball.getWidth());
            maxY = (int) (frame.getHeight() - ball.getHeight()-23); // 23 for the bar at the top of the window/frame

            // check frame boundaries
            if(ball.getLocation().getY() > maxY) {

                ball.setLocation(ball.getLocation().getX(), maxY);
                ball.setVelocityY(-ball.getVelocityY()*COEF_REST);

            } else if(ball.getLocation().getY() < minY) {
                ball.setVelocityY(-ball.getVelocityY()*COEF_REST);

            } else if(ball.getLocation().getX() < minX) {
                ball.setVelocityX(-ball.getVelocityX()*COEF_REST);
            } else if(ball.getLocation().getX() > maxX) {
                ball.setVelocityX(-ball.getVelocityX()*COEF_REST);
            }

            ball.update();

            repaint();
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Ball maze");
        Application panel = new Application();
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(250, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        panel.run();
    }

}