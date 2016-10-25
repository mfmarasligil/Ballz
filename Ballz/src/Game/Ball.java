package Game;

import java.awt.*;
import java.util.Random;

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

    private static final double ACC = 1;
    private static final double COEF_REST = 0.6;


    // Default constructor
    Ball() {

        Random rand1 = new Random();
        Random rand2 = new Random();

        this.location = new Location(rand1.nextInt(250 - 0) + 0,rand2.nextInt(250 - 0) + 0);
        this.width = 40;
        this.height = 40;
        this.color = new Color(255, 0, 0);
        this.velocityX = rand1.nextInt((10 - 0) + 1) + 0;
        this.velocityY = rand2.nextInt((10 - 0) + 1) + 0;

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
        // If it equals 0 then don't change it! AGAIN SHIT HACK
        if(this.getLocation().getY() < Application.frame.getContentPane().getSize().getHeight() - this.getHeight()) {
            double v_y2 = this.getVelocityY() + this.ACC;
            this.setVelocityY(v_y2);
        }

        double v_x2 = this.getVelocityX();   // no acc in x direction

        // set new ball status
        this.setLocation(p_x2, p_y2);
        this.setVelocityX(v_x2);
    }

    // Method checks ball is in frame and applies appropriate adjustment
    void check_in_bounds(int minX, int minY, int maxX, int maxY) {

        // Check Y dimension
        if(this.getLocation().getY() < minY) {
            this.setVelocityY(-this.getVelocityY()*COEF_REST);
            this.setLocation(this.getLocation().getX(), minY);
        } else if(this.getLocation().getY() + this.getHeight() > maxY) {
            this.setVelocityY(-(this.getVelocityY())*COEF_REST);
            this.setLocation(this.getLocation().getX(), maxY - this.getHeight());
            // Method to check if velocity less than certain value and then set to zero
            // THIS FEELS LIKE A SHITTY HACK HAHA
            if(Math.abs(this.getVelocityY()) < 5) {
                this.setVelocityY(0);
            }

        }

        // Check X dimension
        if(this.getLocation().getX() < minX) {
            this.setVelocityX(-this.getVelocityX()*COEF_REST);
            this.setLocation(minX, this.getLocation().getY());
        } else if(this.getLocation().getX() + this.getWidth() > maxX) {
            this.setVelocityX(-this.getVelocityX()*COEF_REST);
            this.setLocation(maxX - this.getWidth(), this.getLocation().getY());
        }


    }



    void check_min_vel() {

        // Check Y veloctiy

    }

}
