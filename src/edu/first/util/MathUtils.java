package edu.first.util;

import java.util.Random;

/**
 * The class {@code Math} contains methods for performing basic numeric
 * operations such as the elementary exponential, logarithm, square root, and
 * trigonometric functions.
 *
 * @since May 13 13
 * @see Math
 * @see com.sun.squawk.util.MathUtils
 * @see Random
 * @author Joel Gallant
 */
public final class MathUtils {

    /**
     * The {@code double} value that is closer than any other to
     * <i>e</i>, the base of the natural logarithms.
     */
    public static final double E = 2.718281828459045;
    /**
     * The {@code double} value that is closer than any other to
     * <i>pi</i>, the ratio of the circumference of a circle to its diameter.
     */
    public static final double PI = 3.141592653589793;
    private static final Random RANDOM = new Random();

    // cannot be subclassed or instantiated
    private MathUtils() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Limits the original value to a maximum value. This method will retain its
     * +/- status.
     *
     * <p>
     * Ex. {@code 12, 1 returns 1}
     * {@code -12, 1 returns -1}
     * {@code 0.323, 3 returns 0.323}
     *
     * @param val original value to limit
     * @param limit absolute limit (does not have to be same sign as original
     * value)
     * @return limited value of same sign
     */
    public static double limit(double val, double limit) {
        return (abs(val) < abs(limit)) ? val : (val < 0 ? -limit : limit);
    }

    /**
     * Returns the square of the number. ({@code val * val})
     *
     * @param val value to square
     * @return squared value
     */
    public static double square(double val) {
        return pow(val, 2);
    }

    /**
     * Returns the arithmetic mean of the array.
     *
     * @param vals values to find average of
     * @return the mean value
     */
    public static double mean(double[] vals) {
        double sum = 0;
        for (int x = 0; x < vals.length; x++) {
            sum += vals[x];
        }
        return sum / (double) vals.length;
    }

    /**
     * Returns a pseudorandom, uniformly distributed double value between 0.0
     * and 1.0.
     *
     * @return a random double value
     */
    public static double random() {
        return RANDOM.nextDouble();
    }

    /**
     * Returns a pseudorandom integer. All 2^32 possible int values are produced
     * with (approximately) equal probability.
     *
     * @return a random integer value
     */
    public static int randomInt() {
        return RANDOM.nextInt();
    }

    /**
     * Returns the trigonometric sine of an angle. Special cases:
     * <ul><li>If the argument is NaN or an infinity, then the result is NaN.
     * <li>If the argument is zero, then the result is a zero with the same sign
     * as the argument.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exact result. Results
     * must be semi-monotonic.
     *
     * @param a an angle, in radians
     * @return the sine of the argument
     */
    public static double sin(double a) {
        return Math.sin(a);
    }

    /**
     * Returns the trigonometric cosine of an angle. Special cases:
     * <ul><li>If the argument is NaN or an infinity, then the result is
     * NaN.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exact result. Results
     * must be semi-monotonic.
     *
     * @param a an angle, in radians
     * @return the cosine of the argument
     */
    public static double cos(double a) {
        return Math.cos(a);
    }

    /**
     * Returns the trigonometric tangent of an angle. Special cases:
     * <ul><li>If the argument is NaN or an infinity, then the result is NaN.
     * <li>If the argument is zero, then the result is a zero with the same sign
     * as the argument.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exact result. Results
     * must be semi-monotonic.
     *
     * @param a an angle, in radians
     * @return the tangent of the argument
     */
    public static double tan(double a) {
        return Math.tan(a);
    }

    /**
     * Converts an angle measured in degrees to an approximately equivalent
     * angle measured in radians. The conversion from degrees to radians is
     * generally inexact.
     *
     * @param angdeg an angle, in degrees
     * @return the measurement of the angle {@code angdeg} in radians
     */
    public static double toRadians(double angdeg) {
        return Math.toRadians(angdeg);
    }

    /**
     * Converts an angle measured in radians to an approximately equivalent
     * angle measured in degrees. The conversion from radians to degrees is
     * generally inexact; users should
     * <i>not</i> expect {@code cos(toRadians(90.0))} to exactly equal
     * {@code 0.0}.
     *
     * @param angrad an angle, in radians
     * @return the measurement of the angle {@code angrad} in degrees
     */
    public static double toDegrees(double angrad) {
        return Math.toDegrees(angrad);
    }

    /**
     * Returns the correctly rounded positive square root of a {@code double}
     * value. Special cases:
     * <ul><li>If the argument is NaN or less than zero, then the result is NaN.
     * <li>If the argument is positive infinity, then the result is positive
     * infinity.
     * <li>If the argument is positive zero or negative zero, then the result is
     * the same as the argument.</ul>
     * Otherwise, the result is the {@code double} value closest to the true
     * mathematical square root of the argument value.
     *
     * @param a a value
     * @return the positive square root of {@code a}. If the argument is NaN or
     * less than zero, the result is NaN
     */
    public static double sqrt(double a) {
        return Math.sqrt(a);
    }

    /**
     * Returns the smallest (closest to negative infinity) {@code double} value
     * that is greater than or equal to the argument and is equal to a
     * mathematical integer. Special cases:
     * <ul><li>If the argument value is already equal to a mathematical integer,
     * then the result is the same as the argument. <li>If the argument is NaN
     * or an infinity or positive zero or negative zero, then the result is the
     * same as the argument. <li>If the argument value is less than zero but
     * greater than -1.0, then the result is negative zero.</ul> Note that the
     * value of {@code Math.ceil(x)} is exactly the value of
     * {@code -Math.floor(-x)}.
     *
     *
     * @param a a value
     * @return the smallest (closest to negative infinity) floating-point value
     * that is greater than or equal to the argument and is equal to a
     * mathematical integer
     */
    public static double ceil(double a) {
        return Math.ceil(a);
    }

    /**
     * Returns the largest (closest to positive infinity) {@code double} value
     * that is less than or equal to the argument and is equal to a mathematical
     * integer. Special cases:
     * <ul><li>If the argument value is already equal to a mathematical integer,
     * then the result is the same as the argument. <li>If the argument is NaN
     * or an infinity or positive zero or negative zero, then the result is the
     * same as the argument.</ul>
     *
     * @param a a value
     * @return the largest (closest to positive infinity) floating-point value
     * that less than or equal to the argument and is equal to a mathematical
     * integer
     */
    public static double floor(double a) {
        return Math.floor(a);
    }

    /**
     * Returns the absolute value of an {@code int} value. If the argument is
     * not negative, the argument is returned. If the argument is negative, the
     * negation of the argument is returned.
     *
     * <p>Note that if the argument is equal to the value of
     * {@link Integer#MIN_VALUE}, the most negative representable {@code int}
     * value, the result is that same value, which is negative.
     *
     * @param a the argument whose absolute value is to be determined
     * @return the absolute value of the argument
     */
    public static int abs(int a) {
        return Math.abs(a);
    }

    /**
     * Returns the absolute value of a {@code long} value. If the argument is
     * not negative, the argument is returned. If the argument is negative, the
     * negation of the argument is returned.
     *
     * <p>Note that if the argument is equal to the value of
     * {@link Long#MIN_VALUE}, the most negative representable {@code long}
     * value, the result is that same value, which is negative.
     *
     * @param a the argument whose absolute value is to be determined
     * @return the absolute value of the argument
     */
    public static long abs(long a) {
        return Math.abs(a);
    }

    /**
     * Returns the absolute value of a {@code float} value. If the argument is
     * not negative, the argument is returned. If the argument is negative, the
     * negation of the argument is returned. Special cases:
     * <ul><li>If the argument is positive zero or negative zero, the result is
     * positive zero.
     * <li>If the argument is infinite, the result is positive infinity.
     * <li>If the argument is NaN, the result is NaN.</ul>
     * In other words, the result is the same as the value of the expression:
     * <p>{@code Float.intBitsToFloat(0x7fffffff & Float.floatToIntBits(a))}
     *
     * @param a the argument whose absolute value is to be determined
     * @return the absolute value of the argument
     */
    public static float abs(float a) {
        return Math.abs(a);
    }

    /**
     * Returns the absolute value of a {@code double} value. If the argument is
     * not negative, the argument is returned. If the argument is negative, the
     * negation of the argument is returned. Special cases:
     * <ul><li>If the argument is positive zero or negative zero, the result is
     * positive zero.
     * <li>If the argument is infinite, the result is positive infinity.
     * <li>If the argument is NaN, the result is NaN.</ul>
     * In other words, the result is the same as the value of the expression:
     * <p>{@code Double.longBitsToDouble((Double.doubleToLongBits(a)<<1)>>>1)}
     *
     * @param a the argument whose absolute value is to be determined
     * @return the absolute value of the argument
     */
    public static double abs(double a) {
        return Math.abs(a);
    }

    /**
     * Returns the greater of two {@code int} values. That is, the result is the
     * argument closer to the value of {@link Integer#MAX_VALUE}. If the
     * arguments have the same value, the result is that same value.
     *
     * @param a an argument
     * @param b another argument
     * @return the larger of {@code a} and {@code b}
     */
    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * Returns the greater of two {@code long} values. That is, the result is
     * the argument closer to the value of {@link Long#MAX_VALUE}. If the
     * arguments have the same value, the result is that same value.
     *
     * @param a an argument
     * @param b another argument
     * @return the larger of {@code a} and {@code b}
     */
    public static long max(long a, long b) {
        return Math.max(a, b);
    }

    /**
     * Returns the greater of two {@code float} values. That is, the result is
     * the argument closer to positive infinity. If the arguments have the same
     * value, the result is that same value. If either value is NaN, then the
     * result is NaN. Unlike the numerical comparison operators, this method
     * considers negative zero to be strictly smaller than positive zero. If one
     * argument is positive zero and the other negative zero, the result is
     * positive zero.
     *
     * @param a an argument
     * @param b another argument
     * @return the larger of {@code a} and {@code b}
     */
    public static float max(float a, float b) {
        return Math.max(a, b);
    }

    /**
     * Returns the greater of two {@code double} values. That is, the result is
     * the argument closer to positive infinity. If the arguments have the same
     * value, the result is that same value. If either value is NaN, then the
     * result is NaN. Unlike the numerical comparison operators, this method
     * considers negative zero to be strictly smaller than positive zero. If one
     * argument is positive zero and the other negative zero, the result is
     * positive zero.
     *
     * @param a an argument
     * @param b another argument
     * @return the larger of {@code a} and {@code b}
     */
    public static double max(double a, double b) {
        return Math.max(a, b);
    }

    /**
     * Returns the smaller of two {@code int} values. That is, the result the
     * argument closer to the value of {@link Integer#MIN_VALUE}. If the
     * arguments have the same value, the result is that same value.
     *
     * @param a an argument
     * @param b another argument
     * @return the smaller of {@code a} and {@code b}
     */
    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    /**
     * Returns the smaller of two {@code long} values. That is, the result is
     * the argument closer to the value of {@link Long#MIN_VALUE}. If the
     * arguments have the same value, the result is that same value.
     *
     * @param a an argument
     * @param b another argument
     * @return the smaller of {@code a} and {@code b}
     */
    public static long min(long a, long b) {
        return Math.min(a, b);
    }

    /**
     * Returns the smaller of two {@code float} values. That is, the result is
     * the value closer to negative infinity. If the arguments have the same
     * value, the result is that same value. If either value is NaN, then the
     * result is NaN. Unlike the numerical comparison operators, this method
     * considers negative zero to be strictly smaller than positive zero. If one
     * argument is positive zero and the other is negative zero, the result is
     * negative zero.
     *
     * @param a an argument
     * @param b another argument
     * @return the smaller of {@code a} and {@code b}
     */
    public static float min(float a, float b) {
        return Math.min(a, b);
    }

    /**
     * Returns the smaller of two {@code double} values. That is, the result is
     * the value closer to negative infinity. If the arguments have the same
     * value, the result is that same value. If either value is NaN, then the
     * result is NaN. Unlike the numerical comparison operators, this method
     * considers negative zero to be strictly smaller than positive zero. If one
     * argument is positive zero and the other is negative zero, the result is
     * negative zero.
     *
     * @param a an argument
     * @param b another argument
     * @return the smaller of {@code a} and {@code b}
     */
    public static double min(double a, double b) {
        return Math.min(a, b);
    }

    /**
     * Performs x*2^n by exponent manipulation.
     *
     * @param x root
     * @param n exponent
     * @return x*2^n
     */
    public static double scalbn(double x, int n) {
        return com.sun.squawk.util.MathUtils.scalbn(x, n);
    }

    /**
     * Returns the closest int to the argument. The result is rounded to an
     * integer by adding 1/2, taking the floor of the result, and casting the
     * result to type int. In other words, the result is equal to the value of
     * the expression:
     * <p> {@code (int)Math.floor(a + 0.5f)}
     *
     * <p>
     * Special cases:
     * <ul>
     * <li> If the argument is NaN, the result is 0.
     * <li> If the argument is negative infinity or any value less than or equal
     * to the value of Integer.MIN_VALUE, the result is equal to the value of
     * Integer.MIN_VALUE.
     * <li> If the argument is positive infinity or any value greater than or
     * equal to the value of Integer.MAX_VALUE, the result is equal to the value
     * of Integer.MAX_VALUE.
     * </ul>
     *
     * @param a a floating-point value to be rounded to an integer
     * @return the value of the argument rounded to the nearest int value
     */
    public static int round(float a) {
        return com.sun.squawk.util.MathUtils.round(a);
    }

    /**
     * Returns the closest long to the argument. The result is rounded to an
     * integer by adding 1/2, taking the floor of the result, and casting the
     * result to type long. In other words, the result is equal to the value of
     * the expression:
     * <p> {@code (long)Math.floor(a + 0.5d)}
     *
     * <p>
     * Special cases:
     * <ul>
     * <li> If the argument is NaN, the result is 0.
     * <li> If the argument is negative infinity or any value less than or equal
     * to the value of Integer.MIN_VALUE, the result is equal to the value of
     * Integer.MIN_VALUE.
     * <li> If the argument is positive infinity or any value greater than or
     * equal to the value of Integer.MAX_VALUE, the result is equal to the value
     * of Integer.MAX_VALUE.
     * </ul>
     *
     * @param a a floating-point value to be rounded to an integer
     * @return the value of the argument rounded to the nearest long value
     */
    public static long round(double a) {
        return com.sun.squawk.util.MathUtils.round(a);
    }

    /**
     * Returns Euler's number <i>e</i> raised to the power of a double value.
     * <p>
     * Special cases:
     * <ul>
     * <li> If the argument is NaN, the result is NaN.
     * <li> If the argument is positive infinity, then the result is positive
     * infinity.
     * <li> If the argument is negative infinity, then the result is positive
     * zero.
     * </ul>
     *
     * <p> A result must be within 1 ulp of the correctly rounded result.
     * Results must be semi-monotonic.
     *
     * @param a the exponent to raise <i>e</i> to
     * @return the value <i>e</i>^a, where <i>e</i> is the base of the natural
     * logarithms
     */
    public static double exp(double a) {
        return com.sun.squawk.util.MathUtils.exp(a);
    }

    /**
     * Returns the natural logarithm (base <i>e</i>) of a double value.
     * <p>
     * Special cases:
     * <ul>
     * <li> If the argument is NaN or less than zero, then the result is NaN.
     * <li> If the argument is positive infinity, then the result is positive
     * infinity.
     * <li> If the argument is positive zero or negative zero, then the result
     * is negative infinity.
     * </ul>
     *
     * <p> A result must be within 1 ulp of the correctly rounded result.
     * Results must be semi-monotonic.
     *
     * @param a a value
     * @return the value ln {@code a}, the natural logarithm of {@code a}
     */
    public static double log(double a) {
        return com.sun.squawk.util.MathUtils.log(a);
    }

    /**
     * Returns the value of the first argument raised to the power of the second
     * argument.
     * <p>
     * Special cases:
     * <ul>
     * <li> If the second argument is positive or negative zero, then the result
     * is 1.0.
     * <li> If the second argument is 1.0, then the result is the same as the
     * first argument.
     * <li> If the second argument is NaN, then the result is NaN.
     * <li> If the first argument is NaN and the second argument is nonzero,
     * then the result is NaN.
     * <li> If the absolute value of the first argument is greater than 1 and
     * the second argument is positive infinity, or the absolute value of the
     * first argument is less than 1 and the second argument is negative
     * infinity, then the result is positive infinity.
     * <li> If the absolute value of the first argument is greater than 1 and
     * the second argument is negative infinity, or the absolute value of the
     * first argument is less than 1 and the second argument is positive
     * infinity, then the result is positive zero.
     * <li> If the absolute value of the first argument equals 1 and the second
     * argument is infinite, then the result is NaN.
     * <li> If the first argument is positive zero and the second argument is
     * greater than zero, or the first argument is positive infinity and the
     * second argument is less than zero, then the result is positive zero.
     * <li> If the first argument is positive zero and the second argument is
     * less than zero, or the first argument is positive infinity and the second
     * argument is greater than zero, then the result is positive infinity.
     * <li> If the first argument is negative zero and the second argument is
     * greater than zero but not a finite odd integer, or the first argument is
     * negative infinity and the second argument is less than zero but not a
     * finite odd integer, then the result is positive zero.
     * <li> If the first argument is negative zero and the second argument is a
     * positive finite odd integer, or the first argument is negative infinity
     * and the second argument is a negative finite odd integer, then the result
     * is negative zero.
     * <li> If the first argument is negative zero and the second argument is
     * less than zero but not a finite odd integer, or the first argument is
     * negative infinity and the second argument is greater than zero but not a
     * finite odd integer, then the result is positive infinity.
     * <li> If the first argument is negative zero and the second argument is a
     * negative finite odd integer, or the first argument is negative infinity
     * and the second argument is a positive finite odd integer, then the result
     * is negative infinity.
     * <li> If the first argument is finite and less than zero if the second
     * argument is a finite even integer, the result is equal to the result of
     * raising the absolute value of the first argument to the power of the
     * second argument if the second argument is a finite odd integer, the
     * result is equal to the negative of the result of raising the absolute
     * value of the first argument to the power of the second argument if the
     * second argument is finite and not an integer, then the result is NaN.
     * <li> If both arguments are integers, then the result is exactly equal to
     * the mathematical result of raising the first argument to the power of the
     * second argument if that result can in fact be represented exactly as a
     * double value.
     * </ul>
     *
     * @param x the base
     * @param y the exponent
     * @return the value x^y
     */
    public static double pow(double x, double y) {
        return com.sun.squawk.util.MathUtils.pow(x, y);
    }

    /**
     * Returns the arc sine of an angle, in the range of -pi/2 through pi/2.
     * <p>
     * Special cases:
     * <ul>
     * <li> If the argument is NaN or its absolute value is greater than 1, then
     * the result is NaN.
     * <li> If the argument is zero, then the result is a zero with the same
     * sign as the argument.
     * </ul>
     *
     * @param a the value whose arc sine is to be returned
     * @return the arc sine of the argument
     */
    public static double asin(double a) {
        return com.sun.squawk.util.MathUtils.asin(a);
    }

    /**
     * Returns the arc cosine of an angle, in the range of 0 through pi.
     * <p>Special cases:
     * <ul>
     * <li>If the argument is NaN or its absolute value is greater than 1, then
     * the result is NaN.
     * </ul>
     *
     * @param a the value whose arc cosine is to be returned
     * @return the arc cosine of the argument
     */
    public static double acos(double a) {
        return com.sun.squawk.util.MathUtils.acos(a);
    }

    /**
     * Returns the arc tangent of an angle, in the range of {@code -pi/2}
     * through {@code pi/2}.
     * <p>
     * Special cases:
     * <ul>
     * <li> If the argument is NaN, then the result is NaN.
     * <li> If the argument is zero, then the result is a zero with the same
     * sign as the argument.
     * </ul>
     *
     * @param a the value whose arc tangent is to be returned
     * @return the arc tangent of the argument
     */
    public static double atan(double a) {
        return com.sun.squawk.util.MathUtils.atan(a);
    }

    /**
     * Converts rectangular coordinates {@code (x, y)} to polar
     * {@code (r, theta)}. This method computes the phase theta by computing an
     * arc tangent of {@code y/x} in the range of {@code -pi} to {@code pi}.
     * <p>
     * Special cases:
     * <ul>
     * <li> If either argument is NaN, then the result is NaN.
     * <li> If the first argument is positive zero and the second argument is
     * positive, or the first argument is positive and finite and the second
     * argument is positive infinity, then the result is positive zero.
     * <li> If the first argument is negative zero and the second argument is
     * positive, or the first argument is negative and finite and the second
     * argument is positive infinity, then the result is negative zero.
     * <li> If the first argument is positive zero and the second argument is
     * negative, or the first argument is positive and finite and the second
     * argument is negative infinity, then the result is the double value
     * closest to pi.
     * <li> If the first argument is negative zero and the second argument is
     * negative, or the first argument is negative and finite and the second
     * argument is negative infinity, then the result is the double value
     * closest to -pi.
     * <li> If the first argument is positive and the second argument is
     * positive zero or negative zero, or the first argument is positive
     * infinity and the second argument is finite, then the result is the double
     * value closest to pi/2.
     * <li> If the first argument is negative and the second argument is
     * positive zero or negative zero, or the first argument is negative
     * infinity and the second argument is finite, then the result is the double
     * value closest to -pi/2.
     * <li> If both arguments are positive infinity, then the result is the
     * double value closest to pi/4.
     * <li> If the first argument is positive infinity and the second argument
     * is negative infinity, then the result is the double value closest to
     * 3*pi/4.
     * <li> If the first argument is negative infinity and the second argument
     * is positive infinity, then the result is the double value closest to
     * -pi/4.
     * <li> If both arguments are negative infinity, then the result is the
     * double value closest to -3*pi/4.
     * </ul>
     *
     * @param y the ordinate coordinate
     * @param x the abscissa coordinate
     * @return the <i>theta</i> component of the point {@code (r, theta)} in
     * polar coordinates that corresponds to the point {@code (x, y)} in
     * Cartesian coordinates.
     */
    public static double atan2(double y, double x) {
        return com.sun.squawk.util.MathUtils.atan2(y, x);
    }

    /**
     * Returns the double value that is closest in value to the argument and is
     * equal to a mathematical integer. If two double values that are
     * mathematical integers are equally close, the result is the integer value
     * that is even.
     * <p>
     * Special cases:
     * <ul>
     * <li> If the argument value is already equal to a mathematical integer,
     * then the result is the same as the argument.
     * <li> If the argument is NaN or an infinity or positive zero or negative
     * zero, then the result is the same as the argument.
     * </ul>
     *
     * @param a a double value
     * @return the closest floating-point value to a that is equal to a
     * mathematical integer
     */
    public static double rint(double a) {
        return com.sun.squawk.util.MathUtils.rint(a);
    }

    /**
     * Returns the natural logarithm of the sum of the argument and 1. Note that
     * for small values {@code x}, the result of {@code log1p(x)} is much closer
     * to the true result of {@code ln(1 + x)} than the floating-point
     * evaulation of {@code log(1.0+x)}.
     * <p>
     * Special cases:
     * <ul>
     * <li> If the argument is NaN or less than -1, then the result is NaN.
     * <li> If the argument is positive infinity, then the result is positive
     * infinity.
     * <li> If the argument is negative one, then the result is negative
     * infinity.
     * <li> If the argument is zero, then the result is a zero with the same
     * sign as the argument.
     * </ul>
     * A result must be within 1 ulp of the correctly rounded result. Results
     * must be semi-monotonic.
     *
     * @param a a value
     * @return the value {@code ln(x + 1)}, the natural log of {@code x + 1}
     */
    public static double log1p(double a) {
        return com.sun.squawk.util.MathUtils.log1p(a);
    }

    /**
     * Returns {@code (e^x)-1}. Note that for values of {@code x} near 0, the
     * exact sum of {@code expm1(x) + 1} is much closer to the true result of
     * {@code e^x} than {@code exp(x)}.
     * <p>
     * Special cases:
     * <ul>
     * <li> If the argument is NaN, the result is NaN.
     * <li> If the argument is positive infinity, then the result is positive
     * infinity.
     * <li> If the argument is negative infinity, then the result is -1.0.
     * <li> If the argument is zero, then the result is a zero with the same
     * sign as the argument.
     * </ul>
     * <p> A result must be within 1 ulp of the correctly rounded result.
     * Results must be semi-monotonic. The result of {@code expm1} for any
     * finite input must be greater than or equal to -1.0. Note that once the
     * exact result of {@code (e^x) - 1} is within 1/2 ulp of the limit value
     * -1, -1.0 should be returned.
     *
     * @param a the exponent to raise {@code e} to in the computation of
     * {@code (e^x) -1}
     * @return the value (e^x) - 1
     */
    public static double expm1(double a) {
        return com.sun.squawk.util.MathUtils.expm1(a);
    }

    /**
     * Computes the remainder operation on two arguments as prescribed by the
     * IEEE 754 standard. The remainder value is mathematically equal to {@code f1 - f2
     * × n}, where {@code n} is the mathematical integer closest to the exact
     * mathematical value of the quotient {@code f1/f2}, and if two mathematical
     * integers are equally close to {@code f1/f2}, then {@code n} is the
     * integer that is even. If the remainder is zero, its sign is the same as
     * the sign of the first argument.
     * <p>
     * Special cases:
     * <ul>
     * <li> If either argument is NaN, or the first argument is infinite, or the
     * second argument is positive zero or negative zero, then the result is
     * NaN.
     * <li> If the first argument is finite and the second argument is infinite,
     * then the result is the same as the first argument.
     * </ul>
     *
     * @param x the dividend
     * @param p the divisor
     * @return the remainder when {@code f1} is divided by {@code f2}
     */
    public static double IEEEremainder(double x, double p) {
        return com.sun.squawk.util.MathUtils.IEEEremainder(x, p);
    }
}
