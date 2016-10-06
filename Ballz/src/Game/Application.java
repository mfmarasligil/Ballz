package Game;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public JPanel panel;

    public void init() {
        Container cp = getContentPane();
        this.setTitle("Ball Maze");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();
        cp.add(panel);
    }

    public void paint(Graphics g) {
        super.paintComponents(g);
        g = this.panel.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        Location ballLocation = new Location(0, 0);

        Ball ball = new Ball(ballLocation, 20, 20);

        g2.setColor(new Color(255, 0, 0));
        g2.fillOval(ball.getLocation().getX(), ball.getLocation().getY(), 10, 10);

    }

    public static void main(String[] args) {
        Application application = new Application();
        application.init();
        application.setSize(250, 200);
        application.setVisible(true);
        application.repaint();
    }

}