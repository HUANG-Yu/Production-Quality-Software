package nyu.edu.pqs.views;

import java.awt.Color;

import nyu.edu.pqs.model.GameMode;

/**
 * 
 * This is the interface for the View (observer) and declares methods that are
 * required to start the game and listen to changes from the Model.
 *
 */
public interface Listener {

  /**
   * Listens to the disc drop event and present it on the view
   * 
   * @param row
   *          row
   * @param col
   *          column
   * @param color
   *          player color
   */
  void discDropped(int row, int col, Color color, GameMode mode);

  /**
   * Listen to the game start event and initialize the concrete view
   */
  void gameStarted();

  /**
   * Listen to the game tie events and output the game tie message
   */
  void gameTied();

  /**
   * Listen to the game win event and set winner 
   * using the color parameter, then
   * output the winning message
   * 
   * @param color
   *          player color
   */
  void gameWon(Color color);
}
