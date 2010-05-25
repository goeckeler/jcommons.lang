package org.jcommons.lang.number;

/**
 * Number utilities that build upon {@link org.apache.commons.lang.math.NumberUtils}.
 *
 * @author Thorsten Goeckeler
 *
 * @see org.apache.commons.lang.math.NumberUtils
 */
public final class NumberUtils
  extends org.apache.commons.lang.math.NumberUtils
{
  /** hide constructor */
  private NumberUtils() {
  }

  /**
   * Null safe comparison of numbers.
   *
   * @param <T> will be taken as the type of the respective number, e.g. Long, Double, etc.
   *
   * @param n1 the left hand side number to compare, can be null
   * @param n2 the right hand side number to compare, can be null
   * @return 0 if they are equal (or both are null), 1 if n2 is greater, otherwise -1
   */
  public static <T extends Comparable<T>> int compare(final T n1, final T n2) {
    if (n1 == null) { return (n2 == null) ? 0 : -1; }
    if (n2 == null) return 1;

    return n1.compareTo(n2);
  }

  /**
   * Null safe implementation of number equality.
   *
   * @param n1 the left hand side number to check, can be null
   * @param n2 the right hand side number to check, can be null
   * @return true if they have an equal value, otherwise false
   */
  public static boolean equals(final Number n1, final Number n2) {
    if (n1 == null) { return (n2 == null); }
    if (n2 == null) return false;

    return n1.equals(n2);
  }

  /**
   * Null safe check if a number is empty whereas <code>null</code> and <code>0</code> are regarded as empty.
   *
   * Please be aware that <code>0.3</code> will also be regarded as a null value! For <code>double</code> and
   * <code>float</code> values consider using <code>Math.signum()</code> as a check.
   *
   * @param number the respective number to check
   * @return true if the number is not set or preset with 0
   */
  public static boolean isNull(final Number number) {
    return (number == null || number.longValue() == 0L);
  }

  /**
   * Null safe check if a number is not empty whereas <code>null</code> and <code>0</code> are regarded as empty.
   *
   * Please be aware that <code>0.3</code> will also be regarded as a null value! For <code>double</code> and
   * <code>float</code> values consider using <code>Math.signum()</code> as a check.
   *
   * @param number the respective number to check
   * @return true if the number is anything but 0 or null
   */
  public static boolean isNotNull(final Number number) {
    return !isNull(number);
  }
}
