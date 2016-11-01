package Game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Testing out some of the java.awt.shape methods to see if they will be useful
 */

public class Ball extends Ellipse2D.Double{

    // Ball properties
    private Color color;
    private double x_vel;
    private double y_vel;

    // Movement properties
    private static final double ACC = 1;
    private static final double COEF_REST = 0.6;
    private static final double COEF_FRIC = 0.8;


    // Default constructor
    Ball() {

        // Initialise the ball position randomly in top 10th of application frame
        double x_pos = Math.random()*Application.frame.getWidth();
        double y_pos = Math.random()*Application.frame.getHeight()*0.1;

        this.x = x_pos;
        this.y = y_pos;

        this.width = 40;
        this.height = 40;
        this.color = new Color(241, 172, 63);

        // Initialise random velocity between 0 and 10
        this.x_vel = 10 * Math.random();
        this.y_vel = 10 * Math.random();

    }


    // Update method to move the ball
    void update(){

        // Calculate the new position
        this.x = this.x + this.x_vel;
        this.y = this.y + this.y_vel;

        // Calculate the new velocity
        this.y_vel = this.y_vel + this.ACC;

    }


    // Check if ball outside frame boundary and adjust accordingly
    void checkInFrame(){

        // Get the current content frame size
        int max_X = Application.frame.getContentPane().getWidth();
        int max_Y = Application.frame.getContentPane().getHeight();

        // Create blocks on each boundary and check intersection with them
        // Blocks of arbitrary size. Top, left, right, bottom.
        this.checkMechIntersection(new Rectangle(0, -100, max_X, 100));
        this.checkMechIntersection(new Rectangle(-100, 0, 100, max_Y));
        this.checkMechIntersection(new Rectangle(max_X, 0, 100, max_Y));
        this.checkMechIntersection(new Rectangle(0, max_Y, max_X, 100));

    }


    // Check if ball intersects mechanism and adjust accordingly
    void checkMechIntersection(Rectangle2D mech){

        if(this.intersects(mech)) {

            // check if ball is on top of mech
            if (this.getMinY() < mech.getMinY() && !(this.getMaxY() > mech.getMaxY())) {
                this.y_vel = this.y_vel * -1 * this.COEF_REST;
                this.y = mech.getMinY() - this.getHeight();

                // hack to stop eternal bouncing
                if (Math.abs(this.y_vel) < 3) {
                    this.y_vel = 0;
                }

                // Add in some friction on rolling
                this.x_vel = this.x_vel * this.COEF_FRIC;

                // check if ball is on the left of mech
            } else if(this.getMinX() < mech.getMinX()){
                this.x_vel = this.x_vel * -1 * this.COEF_REST;
                this.x = mech.getMinX() - this.getWidth();

                // check if ball is on the right of mech
            } else if(this.getMaxX() > mech.getMaxX()){
                this.x_vel = this.x_vel * -1 * this.COEF_REST;
                this.x = mech.getMaxX();

                // check if ball is below the mech
            } else if(this.getMaxY() > mech.getMaxY()){
                this.y_vel = this.y_vel * -1 * this.COEF_REST;
                this.y = mech.getMaxY();
            }

        }
    }


    // Draw method: correctly draw the ball on the frame
    void draw(Graphics2D g2){
        g2.fill(this);

    }


}
