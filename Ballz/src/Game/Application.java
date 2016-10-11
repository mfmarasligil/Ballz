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
    private Location ballLocation = new Location(0, 0);
    private Ball ball = new Ball(ballLocation, 20, 20, new Color(255, 0, 0));

    /* Framerate */
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MS_TO_WAIT = 1000 / FRAMES_PER_SECOND; // I want 30 images per 1000 milliseconds so 1000 / 30 indicates after how many milliseconds I want to refresh
    private Timer animationTimer;
    private TimerTask animationTask;

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
        ball.setLocation((frame.getWidth()/2)-(ball.getWidth()/2), (frame.getHeight()/2)-(ball.getHeight()/2));

        // Paint the ball
        g2.setColor(ball.getColor());
        g2.fillOval(ball.getLocation().getX(), ball.getLocation().getY(), ball.getWidth(), ball.getHeight());
    }

    private class AnimationUpdator extends TimerTask {

        @Override
        public void run() {

            Random r = new Random();
            int Low = 0;
            int High = 250;
            int Result = r.nextInt(High-Low) + Low;

            ball.setColor(new Color(Result, Result, Result));

            repaint();
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Ball maze");
        Application panel = new Application();
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(250, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        panel.run();
    }

}