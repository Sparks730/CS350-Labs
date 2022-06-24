package edu.odu.cs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Highway {

  /**
   * 
   * @param args
   * @return
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      new Highway().doIt(System.in);
    } else {
      BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(args[0])));
      new Highway().doIt(in);
    }
  }

  private void doIt(InputStream in) {
    Scanner input = new Scanner(in);
    double xMin = input.nextDouble();
    double xMax = input.nextDouble();
    System.out.printf("Total distance: %7.1f%n", xMax - xMin);
    Ranges gaps = new Ranges(xMin, xMax);
    while (input.hasNext()) {
      double x0 = input.nextDouble();
      double x1 = input.nextDouble();
      Interval covered = new Interval(x0, x1);
      gaps.remove(covered);
    }
    double uncovered = gaps.sum();
    System.out.printf("Not covered: %7.1f%n", uncovered);
    double pct = 100.0 * ((xMax - xMin) - uncovered) / (xMax - xMin);
    System.out.printf("Percentage covered: %5.1f%%%n", pct);
    input.close();
  }

}