package edu.odu.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

/**
 * Test of the Interval class
 */
public class TestInterval {
       
    double precision = 0.001;

    @Test
    public void testConstructor()
    {
        Interval interval = new Interval(5.0, 10.0);
        assertThat(interval.empty(), is(false));
        assertThat (interval.width(), is(closeTo(5.0, precision)));
        assertThat (interval.getMin(), is(closeTo(5.0, precision)));
        assertThat (interval.getMax(), is(closeTo(10.0, precision)));
        assertThat (interval.contains(7.5), is(true));
        assertThat (interval.contains(5.0), is(true));
        assertThat (interval.contains(10.0), is(true));
        assertThat (interval.contains(4.0), is(false));
        assertThat (interval.contains(10.1), is(false));

        Interval lower = new Interval(4.0, 5.1);
        assertThat(interval.overlaps(lower), is(true));

        Interval higher = new Interval(10.0, 10.1);
        assertThat(interval.overlaps(higher), is(true));
        assertThat(lower.overlaps(higher), is(false));
        assertThat(higher.overlaps(lower), is(false));

        assertThat(interval, is (new Interval(5.0, 10.0)));
        assertThat(interval, is (not(equalTo(lower))));
        assertThat(interval, is (not(equalTo(higher))));   
    }


    @Test
    public void testConstructorOnEmpty()
    {
        Interval interval = new Interval(10.0, 5.0);
        assertThat(interval.empty(), is(true));
        assertThat (interval.width(), is(closeTo(0.0, precision)));
        assertThat (interval.contains(7.5), is(false));

        Interval lower = new Interval(4.0, 5.1);
        assertThat(interval.overlaps(lower), is(false));

        Interval higher = new Interval(10.0, 10.1);
        assertThat(interval.overlaps(higher), is(false));
        assertThat(interval.overlaps(interval), is(false));

        assertThrows(NoSuchElementException.class, () -> {
            interval.getMin();
        });
        assertThrows(NoSuchElementException.class, () -> {
            interval.getMax();
        });

        assertThat (interval, is(new Interval(10.0, 5.0)));
        assertThat (interval, is(new Interval(8.0, 5.0))); // all empty intervals should be equal to one another
        assertThat (interval, is(not(lower)));
    }

    @Test
    public void testBelow() {
        Interval interval = new Interval(50.0, 100.0);
        Interval below75 = interval.below(75.0);
        assertThat(below75.getMin(), is(closeTo(50.0, precision)));
        assertThat(below75.getMax(), is(closeTo(75.0, precision)));
        assertThat(below75.empty(), is(false));
        assertThat(below75.width(), is(closeTo(25.0, precision)));
        assertThat(below75.overlaps(interval), is(true));
        assertThat(below75, is(new Interval(50.0, 75.0)));
    }

    @Test
    public void testBelowAll() {
        Interval interval = new Interval(50.0, 100.0);
        Interval below175 = interval.below(175.0);
        assertThat(below175.getMin(), is(closeTo(50.0, precision)));
        assertThat(below175.getMax(), is(closeTo(100.0, precision)));
        assertThat(below175.empty(), is(false));
        assertThat(below175.width(), is(closeTo(50.0, precision)));
        assertThat(below175.overlaps(interval), is(true));
        assertThat(below175, is(interval));
    }

    @Test
    public void testBelowNone() {
        Interval interval = new Interval(50.0, 100.0);
        Interval below25 = interval.below(25.0);
        assertThat(below25.empty(), is(true));
        assertThat(below25.width(), is(closeTo(0.0, precision)));
        assertThat(below25.overlaps(interval), is(false));
    }
    
    @Test
    public void testAbove() {
        Interval interval = new Interval(50.0, 100.0);
        Interval above75 = interval.above(75.0);
        assertThat(above75.getMin(), is(closeTo(75.0, precision)));
        assertThat(above75.getMax(), is(closeTo(100.0, precision)));
        assertThat(above75.empty(), is(false));
        assertThat(above75.width(), is(closeTo(25.0, precision)));
        assertThat(above75.overlaps(interval), is(true));
        assertThat(above75, is(new Interval(75.0, 100.0)));
    }

    @Test
    public void testAboveAll() {
        Interval interval = new Interval(50.0, 100.0);
        Interval above25 = interval.above(25.0);
        assertThat(above25.getMin(), is(closeTo(50.0, precision)));
        assertThat(above25.getMax(), is(closeTo(100.0, precision)));
        assertThat(above25.empty(), is(false));
        assertThat(above25.width(), is(closeTo(50.0, precision)));
        assertThat(above25.overlaps(interval), is(true));
        assertThat(above25, is(interval));
    }

    @Test
    public void testAboveNone() {
        Interval interval = new Interval(50.0, 100.0);
        Interval above125 = interval.above(125.0);
        assertThat(above125.empty(), is(true));
        assertThat(above125.width(), is(closeTo(0.0, precision)));
        assertThat(above125.overlaps(interval), is(false));
    }

}