package edu.odu.cs;

import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Test of the Ranges class
 */
public class TestRanges {

    double precision = 0.001;

    @Test
    public void testConstructor() {
        Ranges ranges = new Ranges(1.0, 100.0);
        assertThat(ranges.sum(), closeTo(99.0, precision));

        Interval[] expected = { new Interval(1.0, 100.0) };
        assertThat (ranges, contains(expected)); // checks ranges.iterator()
    }

    @Test
    public void testSubtractLowEnd() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(0.0, 20.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(80.0, precision));
        Interval[] expected = { new Interval(20.0, 100.0) };
        assertThat (ranges, contains(expected));
    }

    @Test
    public void testSubtractHighEnd() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(90.0, 110.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(80.0, precision));
        Interval[] expected = { new Interval(10.0, 90.0) };
        assertThat (ranges, contains(expected));
    }

    @Test
    public void testSubtractInterior() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(20.0, 90.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(20.0, precision));
        Interval[] expected = { new Interval(10.0, 20.0), new Interval(90.0, 100.0) };
        assertThat (ranges, contains(expected));
    }

    @Test
    public void testSubtractAll() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(9.0, 110.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(0.0, precision));
        assertThat (ranges.iterator().hasNext(), is(false));
    }

    @Test
    public void testSubtractNone() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(101.0, 110.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(90.0, precision));
        Interval[] expected = { new Interval(10.0, 100.0)};
        assertThat (ranges, contains(expected));
    }

    @Test
    public void testSubtractEmpty() {
        Ranges ranges = new Ranges(10.0, 100.0);
        Interval interval = new Interval(51.0, 50.0);
        ranges.remove(interval);
        assertThat(ranges.sum(), closeTo(90.0, precision));
        Interval[] expected = { new Interval(10.0, 100.0)};
        assertThat (ranges, contains(expected));
    }
}