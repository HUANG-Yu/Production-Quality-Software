package nyu.edu.pqs.canvas.elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {
  @Test
  public void testHashCodes() {
    Point p = new Point(2, 2);
    int expectedHashCode = 16401;
    int realHashCode = p.hashCode();
    assertEquals(expectedHashCode, realHashCode);
  }

  @Test
  public void testEqualsTrue() {
    Point p1 = new Point(102, 7);
    Point p2 = new Point(p1);
    assertTrue(p1.equals(p2));
  }

  @Test
  public void testEqualsFalse() {
    Point p1 = new Point(102, 7);
    Point p2 = new Point(111, 80);
    assertFalse(p1.equals(p2));
  }

  @Test
  public void testToString() {
    Point p = new Point(4, 5);
    String expectedString = "(4, 5)";
    String realString = p.toString();
    assertEquals(expectedString, realString);
  }

  @Test
  public void testGetXTrue() {
    Point p = new Point(10, 2);
    int realX = p.getX();
    int expectedX = 10;
    assertEquals(expectedX, realX);
  }

  @Test
  public void testGetXFalse() {
    Point p = new Point(10, 2);
    int realX = p.getX();
    int expectedX = 11;
    assertNotEquals(expectedX, realX);
  }

  @Test
  public void testGetYTrue() {
    Point p = new Point(10, 2);
    int realY = p.getY();
    int expectedY = 2;
    assertEquals(expectedY, realY);
  }

  @Test
  public void testGetYFalse() {
    Point p = new Point(5, 20);
    int realY = p.getY();
    int expectedY = 2;
    assertNotEquals(expectedY, realY);
  }
}
