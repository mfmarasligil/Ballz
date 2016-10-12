package Game;

import java.awt.*;

/**
 * Ball class, sets the details for the ball and how it should update.
 */

class Ball {

    // Ball variables
    private Location location;
    private int width;
    private int height;
    private Color color;

    private double velocityX;
    private double velocityY;

    private static final double ACC = 0;
    private static final double COEF_REST = 1;

    // Default constructor
    Ball() {
        this.location = new Location(0,0);
        this.width = 40;
        this.height = 40;
        this.color = new Color(255, 0, 0);
        this.velocityX = 5;
        this.velocityY = 5;

    }

    // Detailed constructor
    Ball(Location location, int width, int height, Color color, double velocityX, double velocityY) {
        this.location = location;
        this.width = width;
        this.height = height;
        this.color = color;
        this.velocityX = velocityX;
        this.velocityY = velocityY;

    }

    Location getLocation() {
        return this.location;
    }

    void setLocation(int x, int y) {
        this.location.setX(x);
        this.location.setY(y);
    }

    int getWidth() {
        return this.width;
    }

    int getHeight() {
        return this.height;
    }

    Color getColor() {
        return this.color;
    }

    void setColor(Color color) {
        this.color = color;
    }

    void setVelocityX(double newVelocity) {
        this.velocityX = newVelocity;
    }

    void setVelocityY(double newVelocity) {
        this.velocityY = newVelocity;
    }

    double getVelocityY() {
        return this.velocityY;
    }

    double getVelocityX() {
        return this.velocityX;
    }


    // update the ball component
    void update() {

        // calculate new position: (x2 = x1 + v*dt + 0.5*a*t^2)
        int p_x2 = this.getLocation().getX() + (int)this.getVelocityX(); // no acceleration in x-direction
        int p_y2 = this.getLocation().getY() + (int)this.getVelocityY() + (int)(0.5*this.ACC);

        // calculate new velocity: (v2 = v1 + a*dt)
        double v_y2 = this.getVelocityY() + this.ACC*this.getVelocityY();
        double v_x2 = this.getVelocityX();   // no acc in x direction

        // set new ball status
        this.setLocation(p_x2, p_y2);
        this.setVelocityY(v_y2);
        this.setVelocityX(v_x2);
    }

    // Method checks ball is in frame and applies appropriate adjustment
    void check_in_bounds(int minX, int minY, int maxX, int maxY) {

        // Check Y dimension
        if(this.getLocation().getY() < minY) {
            this.setVelocityY(-this.getVelocityY()*COEF_REST);
            this.setLocation(this.getLocation().getX(), minY);
        } else if(this.getLocation().getY() + this.getHeight() > maxY) {
            this.setVelocityY(-(this.getVelocityY()*COEF_REST));
            this.setLocation(this.getLocation().getX(), maxY - this.getHeight());
        }

        // Check X dimension
        if(this.getLocation().getX() < minX) {
            this.setVelocityX(-this.getVelocityX());
            this.setLocation(minX, this.getLocation().getY());
        } else if(this.getLocation().getX() + this.getWidth() > maxX) {
            this.setVelocityX(-this.getVelocityX()*COEF_REST);
            this.setLocation(maxX - this.getWidth(), this.getLocation().getY());
        }


    }

}
