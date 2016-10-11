package Game;

import java.awt.*;
import javax.swing.*;

public class Ball {

    private Location location;
    private int width;
    private int height;
    private Color color;

    public Ball(Location location, int width, int height, Color color) {

        this.location = location;
        this.width = width;
        this.height = height;
        this.color = color;

    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
