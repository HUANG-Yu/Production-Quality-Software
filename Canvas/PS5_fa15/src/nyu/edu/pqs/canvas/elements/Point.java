package nyu.edu.pqs.canvas.elements;

import java.util.Comparator;

/**
 * This is the class to represent a point using axis representation (X, Y). It
 * contains methods like getX, getY, etc.
 * 
 * @author yuhuang
 *
 */
public class Point implements Comparator<Point> {
  private int zeroIndexX;
  private int zeroIndexY;

  /**
   * Default constructor without any parameters, the point will be set to (0,
   * 0).
   */
  public Point() {
    this.zeroIndexX = 0;
    this.zeroIndexY = 0;
  }

  /**
   * Constructor with parameters to initialize a point
   * 
   * @param x
   *          the given number to set x-axis
   * @param y
   *          the given number to set 7-axis
   */
  public Point(int x, int y) {
    this.zeroIndexX = x;
    this.zeroIndexY = y;
  }

  /**
   * Constructor using a given point to initialize the point
   * 
   * @param givenP
   *          the given point
   */
  public Point(Point givenP) {
    this.zeroIndexX = givenP.getX();
    this.zeroIndexY = givenP.getY();
  }

  /**
   * get the x-axis number of the current point instance
   * 
   * @return
   */
  public int getX() {
    return this.zeroIndexX;
  }

  /**
   * get the y-axis number of the current point instance
   * 
   * @return
   */
  public int getY() {
    return this.zeroIndexY;
  }

  /**
   * Override the equals method. Two Points are equal only if their
   * corresponding X and Y are equal.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || !(obj instanceof Point)) {
      return false;
    }
    Point cast = (Point) obj;
    return this.zeroIndexX == cast.zeroIndexX
        && this.zeroIndexY == cast.zeroIndexY;
  }

  /**
   * Override the hashCode method, using prime number to compute.
   */
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + this.zeroIndexX;
    result = 31 * result + this.zeroIndexY;
    return result;
  }

  /**
   * Override the toString method, outputing the point instance's info in (X, Y)
   * format.
   */
  @Override
  public String toString() {
    return "(" + zeroIndexX + ", " + zeroIndexY + ")";
  }

  @Override
  public int compare(Point o1, Point o2) {
    if (o1.getX() != o2.getX()) {
      return (o1.getX() < o2.getX()) ? -1 : 1;
    }
    if (o1.getY() == o2.getY()) {
      return 0;
    }
    return (o1.getY() < o2.getY()) ? -1 : 1;
  }
}
