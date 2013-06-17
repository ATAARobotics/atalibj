package edu.first.identifiers;

/**
 * Returns the reversed value of a position.
 *
 * @since June 17 13
 * @author Joel Gallant
 */
public class ReversedPosition implements Position {

    private final Position position;

    /**
     * Constructs the object using the actual position to get.
     *
     * @param position the input to return the reverse of
     */
    public ReversedPosition(Position position) {
        if (position == null) {
            throw new NullPointerException("Null position given");
        }
        this.position = position;
    }

    /**
     * Returns the equivalent of {@code !position}.
     *
     * @return reversed value
     */
    public boolean getPosition() {
        return !position.getPosition();
    }
}
