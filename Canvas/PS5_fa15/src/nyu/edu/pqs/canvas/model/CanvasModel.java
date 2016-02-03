package nyu.edu.pqs.canvas.model;

import java.util.ArrayList;
import java.util.List;

import nyu.edu.pqs.canvas.elements.Line;
import nyu.edu.pqs.canvas.elements.Point;
import nyu.edu.pqs.canvas.views.CanvasListener;

/**
 * This is the model class which controls the logic of the drawing on Canvas. It
 * uses singleton to implement the model.
 * 
 * @author yuhuang
 *
 */
public class CanvasModel {
  private static CanvasModel model;
  private List<CanvasListener> listeners = new ArrayList<CanvasListener>();
  private Line currentLine;
  private List<Line> lines = new ArrayList<Line>();

  /**
   * Private constructor without any parameter
   */
  private CanvasModel() {

  }

  /**
   * Get the CanvasModel instance. If none, create one and return.
   * 
   * @return the CanvasModel singleton
   */
  public static CanvasModel getCanvasModelSingleton() {
    if (model == null) {
      return new CanvasModel();
    }
    return model;
  }

  /**
   * return the list of lines currently has in the model
   * 
   * @return the list of lines on canvas
   */
  public List<Line> getLines() {
    return new ArrayList<Line>(this.lines);
  }

  /**
   * add the given listener to the listener list of the canvas model
   * 
   * @param toBeAdded
   *          the listener to be added
   */
  public void addListener(CanvasListener toBeAdded) {
    listeners.add(toBeAdded);
  }

  /**
   * remove the given listener out of the listener list of the canvas model
   * 
   * @param toBeRemoved
   *          the listener to be removed if present in the list of listener
   */
  public void removeListener(CanvasListener toBeRemoved) {
    listeners.remove(toBeRemoved);
  }

  /**
   * Add a point to the currentLine instance
   * 
   * @param toBeAdded
   *          the point to be added to the line
   */
  public void addPointToLine(Point toBeAdded) {
    if (currentLine == null) {
      currentLine = new Line();
      this.drawLine(currentLine);
    }
    currentLine.addPoint(toBeAdded);
    fireUpdateDrawingEvent();
  }

  /**
   * Stop drawing the current line when no more points need to be added.
   */
  public void stopDrawingLine() {
    currentLine = null;
    fireUpdateDrawingEvent();
  }

  /**
   * Update the changes to all the listeners and reflect the change on the views
   */
  private void fireUpdateDrawingEvent() {
    for (CanvasListener eachListener : listeners) {
      eachListener.updateDrawingOnCanvas(this);
    }
  }

  /**
   * Add a line to the list of lines in the model
   */
  public void drawLine(Line toBeDrawn) {
    lines.add(toBeDrawn);
  }

  /**
   * Remove a line out of the list of lines in the model
   * 
   * @param toBeErased
   *          the line to be erased if present in the model's list of lines
   */
  public void eraseLine(Line toBeErased) {
    lines.remove(toBeErased);
  }

  /**
   * This method is only used for testing. Get the number of listeners.
   * 
   * @return
   */
  public int getNumOfListeners() {
    return listeners.size();
  }

  /**
   * This method is only used for testing. Get the current drawing line
   * instance.
   * 
   * @return the current line in the model
   */
  public Line getCurrentLine() {
    if (this.currentLine == null) {
      return null;
    }
    return new Line(currentLine);
  }
}
