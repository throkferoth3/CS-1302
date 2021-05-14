package cs1302.shapes;

/**
 * A rectangle is a quadrilateral with four right angles.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Rectangle">Wikipedia: Rectangle</a>
 */
public class Rectangle extends Shape {

    /** Length of bottom side of rectangle. */
    private double length;

    /** length of side of rectangle. */
    private double width;

    /**
     * Constructs an {@link Rectangle} object with the specified length and width.
     *
     * @param l the length of the rectangle
     * @param w the width of the rectangle
     */
    public Rectangle (double l, double w) {
        this.length = l;
        this.width = w;
        this.setName("Rectangle");
    } // Rectangle

    /**
     * Returns length of the rectangle.
     *
     * @return the length of the rectangle
     */
    public double getLength() {
        return this.length;
    } // getLength

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    } // getWidth

    /**
     * Returns the perimeter of the rectangle.
     *
     * @return the perimeter of the rectangle
     */
    @Override
    public double getPerimeter() {
        return (this.length * 2) + (this.width * 2);
    } // getPerimeter

    /**
     * Returns the area of the rectangle.
     *
     * @return the area of the rectangle
     */
    @Override
    public double getArea() {
        return this.length * this.width;
    } // getArea

}
