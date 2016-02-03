package nyu.edu.pqs.canvas.views;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nyu.edu.pqs.canvas.elements.Point;
import nyu.edu.pqs.canvas.model.CanvasModel;

/**
 * This is the main view of the canvas application, which contains method
 * updateDrawingOnCanvas, stopDrawingEvent, etc.
 * 
 * @author yuhuang
 *
 */
// using both observer pattern and composite pattern thus avoiding the main view
// to directly extends JPanel, which has no other choice to extends other class
public class CanvasMainView implements CanvasListener {
  private CanvasModel model;
  private CanvasPanelView panelView = new CanvasPanelView();

  /**
   * The Constructor to create the GUI.
   * 
   * @param model
   *          the canvas model singleton
   */
  public CanvasMainView(CanvasModel model) {
    this.model = model;
    JFrame frame = new JFrame("Canvas Application");
    JPanel mainPanel = new JPanel(new BorderLayout());

    /**
     * Bind the listener to the panel to reflect changes of the mouse
     */
    mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
      private void drawEvent(MouseEvent e) {
        model.addPointToLine(new Point(e.getX(), e.getY()));

      }

      @Override
      public void mouseDragged(MouseEvent event) {
        drawEvent(event);
      }
    });

    mainPanel.addMouseListener(new MouseAdapter() {
      private void stopDrawingEvent() {
        model.stopDrawingLine();
      }

      @Override
      public void mouseReleased(MouseEvent event) {
        stopDrawingEvent();
      }
    });

    mainPanel.add(panelView, BorderLayout.CENTER);
    frame.add(mainPanel);

    frame.setSize(600, 600);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);

    model.addListener(this);
  }

  /**
   * Override the updateDrawingOnCanvas to reflect the changes made on the
   * canvas
   */
  @Override
  public void updateDrawingOnCanvas(CanvasModel model) {
    panelView.updateCanvasPanelView(model);
  }

  /**
   * Start drawing if detected mouse motion on the panel after stop
   * @param e
   */
  public void startDrawingEvent(MouseEvent e) {
    model.addPointToLine(new Point(e.getX(), e.getY()));
  }

  /**
   * Stop drawing if detected mouse release event
   */
  public void stopDrawingEvent() {
    model.stopDrawingLine();
  }

}
