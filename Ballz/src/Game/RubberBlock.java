package Game;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class RubberBlock extends Block {

    private ArrayList<Point> points;
    private GeneralPath path;
    private Area result;

    RubberBlock(double ballRadius) {
        super(ballRadius);

        // Initialize the ArrayList
        this.points = new ArrayList<Point>();
    }

    /**
     *  Paints the rectangle and the white path given by the internal ArrayList points.
     *  Colour can be set in here as well.
     *  @param g2 Graphics2D object to be painted on
     *  */
    public void draw(Graphics2D g2) {
        // Reinitalize the path and move to first point
        path = new GeneralPath();

        // Go through the points array and draw a line between the current and the next point
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);

            // Move to the first point if it exists
            if(i == 0) {
                path.moveTo(points.get(0).getX(), points.get(0).getY());
            } else {
                // Else draw a line
                path.lineTo(p1.getX(), p1.getY());
            }
        }

        result = new Area(this);
        result.subtract(new Area(path));

        g2.setColor(new Color(63, 137, 100));
        g2.fill(this);

        g2.setColor(new Color(255, 255, 255));
        g2.setStroke(new BasicStroke(9));
        g2.draw(path);
    }

    /**
     * Checks, if the given point is inside the drag area or not. If so,
     * adds this point to the internal ArrayList of points in order for them
     * to be painted on the screen by draw().
     * @param point
     */
    /* Adds a point to the internal ArrayList */
    public void addPoint(Point point) {

        // Check, if the point is inside the drag area, then add it. Else don't.
        if(this.contains(point)) {
            this.points.add(point);
        } else {
            System.out.println("Draw somewhere else, you can't draw here!");
        }
    }

    /**
     * Return the ArrayList of points from the drawn path
     * @return ArrayList<Points>
     */
    public ArrayList<Point> getPoints() {
        return this.points;
    }

    /**
     * Return the internal block that gets overPainted
     * @return
     */
    public Block getBlock() {
        return this;
    }

    /**
     * Return the path drawn by the user.
     * @return
     */
    public GeneralPath getPath() {

        if(this.path == null) {
            this.path = new GeneralPath();
        }

        return this.path;
    }

    /**
     * Returns the area derived from the subtraction (block - path)
     * @return
     */
    public Area getResult() {
        return this.result;
    }
}
