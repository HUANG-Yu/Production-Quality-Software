package nyu.edu.pqs.canvas.elements;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LineTest {
  @Test
  public void testAddPoint() {
    Line realLine = LineTest.createLine(new Point(1, 2), new Point(5, 7));
    List<Point> expectedLine = LineTest.createPointsList(new Point(1, 2),
        new Point(5, 7));
    for (int i = 0; i < expectedLine.size(); i++) {
      assertTrue(expectedLine.get(i).equals(realLine.getPointsInLine().get(i)));
    }
  }

  @Test
  public void testErasePoint() {
    Line realLine = LineTest.createLine(new Point(11, 90), new Point(33, 4));
    realLine.erasePoint(new Point(33, 4));
    List<Point> expectedLine = LineTest.createPointsList(new Point(11, 90));
    assertTrue(expectedLine.equals(realLine.getPointsInLine()));
  }

  @Test
  public void testgetNumOfPoint() {
    Line realLine = LineTest.createLine(new Point(110, 87), new Point(111, 88),
        new Point(112, 89));
    List<Point> expectedLine = LineTest.createPointsList(new Point(110, 87),
        new Point(111, 88), new Point(112, 89));
    assertEquals(realLine.getNumOfPoint(), expectedLine.size());
  }

  @Test
  public void testGetPointsInLine() {
    Line realLine = LineTest.createLine(new Point(3, 3), new Point(2, 2));
    List<Point> expectedLine = LineTest.createPointsList(new Point(3, 3),
        new Point(2, 2));
    assertEquals(realLine.getPointsInLine(), expectedLine);
  }

  @Test
  public void testEqualsTrue() {
    Line realLine = LineTest.createLine(new Point(30, 30), new Point(50, 50));
    List<Point> expectedLine = LineTest.createPointsList(new Point(30, 30),
        new Point(50, 50));
    assertEquals(realLine.getNumOfPoint(), expectedLine.size());
    for (int i = 0; i < expectedLine.size(); i++) {
      assertEquals(realLine.getPointsInLine().get(i), expectedLine.get(i));
    }
  }

  @Test
  public void testEqualsFalse() {
    Line realLine = LineTest.createLine(new Point(20, 30), new Point(20, 30));
    List<Point> expectedLine = LineTest.createPointsList(new Point(90, 67),
        new Point(40, 13));
    for (int i = 0; i < ((expectedLine.size() < realLine.getNumOfPoint()) ? expectedLine
        .size() : realLine.getNumOfPoint()); i++) {
      assertNotEquals(realLine.getPointsInLine().get(i), expectedLine.get(i));
    }
  }

  @Test
  public void testHashCode() {
    Line realLine = LineTest.createLine(new Point(21, 39), new Point(17, 22));
    int expectedHashCode = 561060;
    int realHashCode = realLine.hashCode();
    assertEquals(expectedHashCode, realHashCode);
  }

  @Test
  public void testToString() {
    Line realLine = LineTest.createLine(new Point(1, 1));
    String expectedString = " (1, 1)";
    String realString = realLine.toString();
    assertEquals(expectedString, realString);
  }

  // helper method to help initialize a line with given points as parameters.
  public static Line createLine(Point... p) {
    Line l = new Line();
    for (Point cur : p) {
      l.addPoint(cur);
    }
    return l;
  }

  // helper method to initialize a list of points
  public static List<Point> createPointsList(Point... p) {
    List<Point> list = new ArrayList<Point>();
    for (Point cur : p) {
      list.add(cur);
    }
    return list;
  }

}
