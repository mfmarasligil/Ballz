package Game;

/**
 * Created by sebastianjohnvonfreyend on 06/10/2016.
 */

/**
 * I don't really like this, I feel like the location class just overcomplicates and doesn't give
 * us much extra functionality, do you think we can chop it?
 */
public class Location {

    private int x;
    private int y;

    public Location(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
