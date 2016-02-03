package nyu.edu.pqs.canvas.views;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import nyu.edu.pqs.canvas.elements.Line;
import nyu.edu.pqs.canvas.elements.Point;
import nyu.edu.pqs.canvas.model.CanvasModel;

/**
 * This is the JPanel class containing the actual canvas.
 * @author yuhuang
 *
 */
public class CanvasPanelView extends JPanel {

  private static final long serialVersionUID = 1L;
  private CanvasModel model;

  /**
   * The default constructor without any parameters.
   */
  public CanvasPanelView() {
  }

  /**
   * Override the paintComponent method.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (model != null && model.getLines() != null) {
      for (Line currentLine : model.getLines()) {
        List<Point> pointsInLine = currentLine.getPointsInLine();
        int[] pointsX = new int[pointsInLine.size()];
        int[] pointsY = new int[pointsInLine.size()];
        for (int i = 0; i < pointsInLine.size(); i++) {
          pointsX[i] = pointsInLine.get(i).getX();
          pointsY[i] = pointsInLine.get(i).getY();
        }
        g.drawPolyline(pointsX, pointsY, pointsX.length);
      }
    }
  }

  /**
   * Update the drawing on canvas.
   * @param model the canvas model singlton
   */
  public void updateCanvasPanelView(CanvasModel model) {
    this.model = model;
    repaint();
  }
}
