/**
 * author Florian Mirko Becker Version 0.1 2021
 * <p>
 *     a set of Math functions not listed in java.lang.Math
 */
public final class MathUtils
{
    public static final double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax) {
        return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
    }

    /**
     * @param i an integer
     * @return -1 for i < 0, else 1
     */
    public static final int sign(int i) {
        return i < 0 ? -1 : 1;
    }
}