package Game;

import java.awt.*;

/**
 * Simple block class
 */

// Used java.awt.shape rectangle as the extended class but not sure if it's best
public class Block extends Rectangle {

    // Initialise interface controls
    private boolean dragging = false;
    public Point initLoc;

    // Relative constructor for horizontal blocks at random points on the screen
    Block(double ballRadius){

        this.width = (int)ballRadius * 5;
        this.height = this.width/10;

        // Initialise block positions in the frame
        // Do not place a block in the top 25% of the frame
        this.x = (int)((Application.frame.getWidth()-this.width) * Math.random());
        this.y = (int)((((0.75*Application.frame.getHeight())-this.height) * Math.random()) + (0.25*Application.frame.getHeight()));

    }

    void setDragging(boolean state){
        this.dragging = state;
    }

    boolean getDragging(){
        return this.dragging;
    }

    // Draw method to correctly paint the component on the screen
    void draw(Graphics2D g2){
        g2.fillRect(this.x, this.y, this.width, this.height);

    }


}