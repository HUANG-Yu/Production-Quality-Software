package nyu.edu.pqs.canvas.app;

import nyu.edu.pqs.canvas.model.CanvasModel;
import nyu.edu.pqs.canvas.views.CanvasMainView;

/**
 * The entrance of the Canvas Application
 * 
 * @author yuhuang
 *
 */
public class CanvasApp {

  /**
   * create the model instance and bind its to the listeners
   */
  public void startDrawing() {
    CanvasModel model = CanvasModel.getCanvasModelSingleton();
    new CanvasMainView(model);
    new CanvasMainView(model);
    new CanvasMainView(model);
  }

  /**
   * entrance of the application
   * 
   * @param args
   */
  public static void main(String[] args) {
    new CanvasApp().startDrawing();
  }
}
