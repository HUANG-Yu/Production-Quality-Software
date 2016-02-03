package nyu.edu.pqs.player;

import java.awt.Color;

import nyu.edu.pqs.model.Model;

/**
 * 
 * Declares the methods that a player can perform
 *
 */
public interface Player {
  /**
   * Set the column where a move has been made by the player
   * 
   * @param col
   *          column
   */
  public void setColMove(int col);

  /**
   * Get the column of the last move of the player
   * 
   * @return column of last move
   */
  public int getColMove();

  /**
   * The player make a move
   * @param model
   *          the binding game model
   */
  public void makeAMove(Model model);

  /**
   * get color that represent the player
   * 
   * @return
   */
  public Color getColor();

  /**
   * Get the type of the player
   * 
   * @return the type of the player
   */
  public PlayerType getPlayerType();
}
