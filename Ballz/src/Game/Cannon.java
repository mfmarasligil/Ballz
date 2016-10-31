package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Cannon class, sets up the construction of the cannon and defines the interactions with the user.
 */


/** This was the start of a cannon class (think slingshot) but then I bailed out as realised maybe it should be
 *  a child of the mechanism class and that had nothing really in it.
 */
public class Cannon {

    private int x_pos;
    private int y_pos;
    private int length;
    private int thickness;
    private int opening;



    // Default constructor
    Cannon(){
        this.length = 10;
        this.thickness = 5;
    }

    // Constructor specific to ball dimensions
    Cannon(int ballRadius){

        this.length = ballRadius * 5;
        this.thickness = this.length/10;
        this.opening = ballRadius;

        this.x_pos = 0;
        this.y_pos = Application.frame.getHeight()-this.length;

    }

    // Draw method, paints the component on screen correctly
    void Draw(Graphics2D g2){

        g2.fillRect(this.x_pos, this.y_pos, this.thickness, this.length);

    }


}
