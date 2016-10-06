package Game;

import java.awt.*;
import javax.swing.*;

public class Ball {

    private Location location;
    private int width;
    private int height;

    public Ball(Location location, int width, int height) {

        this.location = location;
        this.width = width;
        this.height = height;

    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
    }
}
