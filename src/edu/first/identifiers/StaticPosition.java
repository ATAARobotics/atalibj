package edu.first.identifiers;

/**
 * Never changing position that will always return the same thing.
 *
 * @since June 13 13
 * @author Joel Gallant
 */
public final class StaticPosition implements Position {

    private final boolean position;

    /**
     * Constructs the position using the value to use.
     *
     * @param position value that will always be returned
     */
    public StaticPosition(boolean position) {
        this.position = position;
    }

    /**
     * Returns the value given in the constructor.
     *
     * @return value that will always be used
     */
    @Override
    public boolean getPosition() {
        return position;
    }
}
