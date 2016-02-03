package nyu.edu.pqs.canvas.elements;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import nyu.edu.pqs.canvas.model.CanvasModel;
import nyu.edu.pqs.canvas.views.CanvasListener;
import nyu.edu.pqs.canvas.views.CanvasMainView;

import org.junit.Test;

public class CanvasModelTest {

  CanvasModel model = CanvasModel.getCanvasModelSingleton();

  @Test
  public void testDrawLine() {
    Line l = CanvasModelTest.createLine(new Point(1, 1), new Point(32, 32));
    model.drawLine(l);
    assertEquals(l, model.getLines().get(model.getLines().size() - 1));
  }

  @Test
  public void testGetLines() {
    Line line1 = CanvasModelTest
        .createLine(new Point(10, 13), new Point(31, 1));
    Line line2 = CanvasModelTest
        .createLine(new Point(11, 14), new Point(32, 2));
    List<Line> expectedLines = CanvasModelTest.createLineList(
        CanvasModelTest.createLine(new Point(10, 13), new Point(31, 1)),
        CanvasModelTest.createLine(new Point(11, 14), new Point(32, 2)));
    model.drawLine(line1);
    model.drawLine(line2);
    assertEquals(expectedLines, model.getLines());
  }

  @Test
  public void testAddListener() {
    CanvasListener tobeAdded = new CanvasMainView(model);
    model.addListener(tobeAdded);
    int expected = model.getNumOfListeners();
    assertEquals(expected, 2);
  }

  @Test
  public void testRemoveListener() {
    CanvasListener tobeRemoved = new CanvasMainView(model);
    model.addListener(tobeRemoved);
    model.removeListener(tobeRemoved);
    assertEquals(model.getNumOfListeners(), 1);
  }

  @Test
  public void testAddPointToLine() {
    Point p = new Point(10, 20);
    model.addPointToLine(p);
    Line expectedLine = CanvasModelTest.createLine(new Point(10, 20));
    assertEquals(model.getCurrentLine(), expectedLine);
  }

  @Test
  public void testStopDrawingLine() {
    Point p = new Point(99, 200);
    model.addPointToLine(p);
    model.stopDrawingLine();
    boolean isNullFlag = false;
    if (model.getCurrentLine() == null) {
      isNullFlag = true;
    }
    assertTrue(isNullFlag);
  }

  @Test
  public void testEraseLine() {
    Line line1 = CanvasModelTest
        .createLine(new Point(10, 13), new Point(31, 1));
    Line line2 = CanvasModelTest
        .createLine(new Point(11, 14), new Point(32, 2));
    List<Line> expectedLines = CanvasModelTest.createLineList(CanvasModelTest
        .createLine(new Point(10, 13), new Point(31, 1)));
    model.drawLine(line1);
    model.drawLine(line2);
    model.eraseLine(line2);
    assertEquals(model.getLines(), expectedLines);
  }

  // helper method to help initialize a line with given points as parameters.
  public static Line createLine(Point... p) {
    Line l = new Line();
    for (Point cur : p) {
      l.addPoint(cur);
    }
    return l;
  }

  // helper method to create a list of lines
  public static List<Line> createLineList(Line... lines) {
    List<Line> list = new ArrayList<Line>();
    for (Line cur : lines) {
      list.add(cur);
    }
    return list;
  }

}
