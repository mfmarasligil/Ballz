package Game;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        Canvas canvas = new Canvas();

        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 200);
        frame.setVisible(true);
    }

}