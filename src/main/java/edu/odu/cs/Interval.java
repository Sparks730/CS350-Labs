package edu.odu.cs;

import java.util.NoSuchElementException;

/**
 * A contiguous range of floating point numbers. Mathematically, this
 * is an <a href='https://mathworld.wolfram.com/ClosedInterval.html'>closed interval</a>.
 */
public class Interval {
  private double low;
  private double high;

  /**
   * Create an interval from min to max. If (min > max), the interval is empty.
   * 
   * @param min the lowest number in the new interval
   * @param max the highest number in the new interval
   */
  public Interval(double min, double max) {
    low = min;
    high = max;
  }

  /**
   * Is the interval empty? (I.e., true iff it contains no numbers.)
   * 
   * @return true iff the interval is empty
   */
  public boolean empty() {
    return high < low;
  }

  /**
   * Tests to see if a number is in the interval (including at either end).
   * 
   * @param d the number to look for
   * @return true iff the number is in the interval
   */
  public boolean contains(double d) {
    return d >= low && d <= high;
  }

  /**
   * Determine whether this interval shares any numbers with another.
   * 
   * @param x another interval
   * @return true iff both intervals are non-empty and the two intervals have at least one number in common.
   */
  public boolean overlaps(Interval x) {
    if (empty() || x.empty()) {
      return false;
    }
    return !(low > x.high || high < x.low);
  }

  /**
   * The distance from the start of the interval to its end.
   * 
   * @return
   */
  public double width() {
    return Math.max(0.0, high - low);
  }

  /**
   * @return the smallest number in the interval
   * @throws NoSuchElementException if the interval is empty
   */
  public double getMin() {
    if (!empty()) {
      return low;
    } else {
      throw new NoSuchElementException("getMin() called on an empty interval");
    }
  }

  /**
   * @return the largest number in the interval
   * @throws NoSuchElementException if the interval is empty
   */
  public double getMax() {
    if (!empty()) {
      return high;
    } else {
      throw new NoSuchElementException("getMax() called on an empty interval");
    }
  }

  /**
   * Create an interval representing the portion of this one below a certain value
   * 
   * @param d a value
   * @return an interval denoting all numbers in this interval that are no greater
   *         than d
   */
  public Interval below(double d) {
    return new Interval(low, Math.min(d, high));
  }

  /**
   * Create an interval representing the portion of this one above a certain value
   * 
   * @param d a value
   * @return an interval denoting all numbers in this interval that are no less
   *         than d
   */
  public Interval above(double d) {
    return new Interval(Math.max(d, low), high);
  }

  /**
   * Compare two Intervals for equality. 
   * @return true if both are non-empty intervals and their min and max values are respectively equal, or if
   *     both are empty intervals.
   */
  public boolean equals(Object obj) {
    if (obj instanceof Interval) {
      Interval other = (Interval)obj;
      if (empty() && other.empty()) 
        return true;
      return (low == other.low) && (high == other.high);
    } else {
      return false;
    }
  }

  /**
   * For debugging purposes. Contents and format are not specified.
   */
  public String toString() {
    return "(" + low + ',' + high + ')';
  }
}
