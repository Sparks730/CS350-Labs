package edu.odu.cs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * An example of using JUnit to support a system test (running an entire program)
 */
public class TestHighway {

    ByteArrayOutputStream outputStream; // will contain captured output from System.out
    PrintStream saved;  // used to restore System.out

    @BeforeEach
    void setUp() {
        // Redirect System.out to outputString;
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        saved = System.out;
        System.setOut(ps);
    }

    @AfterEach
    void cleanUp() {
        System.setOut(saved);
    }

    @Test
    public void test000() throws IOException{
        String[] args = {"src/test/data/test000.dat"};
        Highway.main(args);
        String output = outputStream.toString();
        assertThat (output, matchesPattern("(?s).*Not covered: *80.0.*"));
        assertThat(output, containsString("20.0%"));
    }

    @Test
    public void test005() throws IOException{
        String[] args = {"src/test/data/test005.dat"};
        Highway.main(args);
        String output = outputStream.toString();
        // A slightly different approach - match line by line
        String[] lines = output.split("\n");
        String[] expected = {
            "Total distance: *10000.0",
            "Not covered: *0.1",
            "Percentage covered: *100.0%",
        };
        for (int i = 0; i < expected.length; ++i) {
            assertThat (lines[i], matchesPattern(".*" + expected[i] + ".*"));
        }
    }

}