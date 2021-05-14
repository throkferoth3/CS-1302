package cs1302.shapes;

/**
 * A square is a regular quadrilateral, which means that it has four equal sides
 * and four equal angles.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Square">Wikipedia: Square</a>
 */
public class Square extends Rectangle {

    /**
     * Constructs {@link Square} object with specified sidelength.
     *
     * @param s the side length of square
     */
    public Square (double s) {
        super(s, s);
    }

}
