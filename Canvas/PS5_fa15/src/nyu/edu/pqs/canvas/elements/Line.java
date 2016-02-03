package nyu.edu.pqs.canvas.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class to represent a line, which is consists of a list of points.
 * It contains methods like addPoint, erasePoin, etc.
 * 
 * @author yuhuang
 *
 */
public class Line {
  private List<Point> line;

  /**
   * Default line constructor without any parameters
   */
  public Line() {
    this.line = new ArrayList<Point>();
  }

  /**
   * Constructor with parameters. Use a given line to initialize a line.
   * 
   * @param init
   */
  public Line(Line init) {
    this.line = init.line;
  }

  /**
   * Add a point to the current line instance
   * 
   * @param p
   *          the point needs to be added
   */
  public void addPoint(Point p) {
    line.add(p);
  }

  /*
   * for later when there is an eraser on the screen public void
   * erasePoint(Point p) { line.remove(p); }
   */
  /**
   * to remove a point in a line
   * 
   * @param p
   *          the point wanted to be removed if present in the line's list.
   *          Otherwise do nothing.
   */
  public void erasePoint(Point p) {
    line.remove(p);
  }

  /**
   * Return back how many Point objects makes up the current line instance
   * 
   * @return the number of points in this line object
   */
  public int getNumOfPoint() {
    return line.size();
  }

  /**
   * Get the points list that make up the current line instance
   * 
   * @return
   */
  public List<Point> getPointsInLine() {
    return new Line(this).line;
  }

  /**
   * Override the equals methods. Two line instance are equal only if each of
   * the points in the list of the same index are equal.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof Line)) {
      return false;
    }
    Line cast = (Line) obj;
    Line copy = new Line(this);
    if (cast.getNumOfPoint() != this.getNumOfPoint()) {
      return false;
    }
    // Same shape appear on the same location on canvas might not be equals due
    // to the order the series points adding to the line.
    for (int i = 0; i < copy.getNumOfPoint(); i++) {
      if (!(cast.line.get(i).equals(copy.line.get(i)))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Override the hashCode method, using prime numbers to compute.
   */
  @Override
  public int hashCode() {
    int result = 17;
    for (int i = 0; i < this.line.size(); i++) {
      result = 31 * result + this.line.get(i).hashCode();
    }
    return result;
  }

  /**
   * Override the toString method by concatenating each point information in the
   * list to the result string.
   */
  @Override
  public String toString() {
    String result = "";
    for (int i = 0; i < this.line.size(); i++) {
      result = result + " " + this.line.get(i).toString();
    }
    return result;
  }
}
