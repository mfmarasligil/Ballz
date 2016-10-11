package Game;

import java.awt.*;
import javax.swing.*;

public class Ball {

    private Location location;
    private int width;
    private int height;
    private Color color;

    private double velocityX;
    private double velocityY;

    public Ball(Location location, int width, int height, Color color, double velocityX, double velocityY) {

        this.location = location;
        this.width = width;
        this.height = height;
        this.color = color;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

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

    public void setVelocityX(double newVelocity) {
        this.velocityX = newVelocity;
    }

    public void setVelocityY(double newVelocity) {
        this.velocityY = newVelocity;
    }

    public double getVelocityY() {
        return this.velocityY;
    }

    public double getVelocityX() {
        return this.velocityX;
    }

    public void update() {
        this.setLocation((int)(this.getLocation().getX()+velocityX), (int)(this.getLocation().getY()+velocityY));
    }

}
