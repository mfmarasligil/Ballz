package Game;

import java.awt.image.BufferedImage;
/**
 * 
 * Abstract class for mechanisms in the maze game
 *
 */

abstract class Mechanism {
	
	protected BufferedImage image;
	 protected Location location;

	 
	 public Mechanism(Location location) {

	        this.location = location;
	      

	    }
	 
	    public Location getLocation() {
	        return this.location;
	    }

	    public void setLocation(int x, int y) {
	        this.location.setX(x);
	        this.location.setY(y);
	    }

}
