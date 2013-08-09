package edu.first.identifiers;

/**
 * A position that has two possible states: on and off. A position can be
 * virtually anything that returns a boolean in some sense, but should make
 * sense in the context of a "position". It should be capable of being in either
 * position at any time. Sometimes its output can be predicted (ex. solenoids),
 * but usually it is a sensor and its position is useful to know.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public interface Position {

    /**
     * Returns the current actual position. Can either be on or off,
     * {@code true} being on and {@code false} being off.
     *
     * @return current position
     * @throws IllegalStateException when input cannot be reached
     */
    public boolean getPosition();
}
