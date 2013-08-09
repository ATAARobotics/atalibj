package edu.first.main;

/**
 * The central repository for all constant values. Values here are expected to
 * be accessible to the entire program, and for anyone to view.
 *
 * Values in this interface follow this general contract:
 * <ul>
 * <li> Values should never need to be changed when code is deployed
 * <li> Values should represent something universally true in all cases
 * <li> Values should not be costly or lengthly to initialize
 * <li> Values should be self-documented (self-explanatory) or have JavaDocs
 * attached
 * </ul>
 *
 * There are many different possible ways to make use of this class.
 *
 * <b>
 * For putting values into {@code Constants}:
 * </b>
 * <ul>
 * <li> Create categorical interfaces with values that are alike and make this
 * class extend them
 * <li> Separate values in this interface using line breaks and comments
 * <li> Use arrays as categories inside of this class indicating similar
 * constant types
 * </ul>
 *
 * <b>
 * For accessing values in {@code Constants}:
 * </b>
 * <ul>
 * <li> If using this <i>first</i> structure of putting values into
 * {@code Constants}, implement the appropriate interface to reduce class
 * loading time
 * <li> If not using the first method, implement this interface on classes to
 * access the constants within it
 * </ul>
 *
 * @since May 07 13
 * @author Joel Gallant
 */
public interface Constants {
}
