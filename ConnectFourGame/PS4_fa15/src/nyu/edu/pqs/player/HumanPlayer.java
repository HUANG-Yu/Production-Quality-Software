package nyu.edu.pqs.player;

import java.awt.Color;

import nyu.edu.pqs.model.Model;

/**
 * An implementation of the Player, representing
 * players controlled by human
 * @see ComputerPlayer
 */
public class HumanPlayer implements Player {
  private Color playerColor;
  private int dropCol;

  /**
   * Inner builder class to initialize HumanPlayer
   *
   */
  // For scale extension and reusability in the future
  //more feature like player name, player winning times
  // can be added to as the attributes
  public static class HumanPlayerBuilder {
    private final Color playerColor;

    /**
     * Builder Constructor
     * 
     * @param playerColor
     */
    public HumanPlayerBuilder(Color playerColor) {
      this.playerColor = playerColor;
    }

    /**
     * Create the actual needed class instance
     * 
     * @return
     */
    
    public HumanPlayer build() {
      return new HumanPlayer(this);
    }
  }

  /**
   * private HumanPlayer constructor
   * @param builder
   */
  private HumanPlayer(HumanPlayerBuilder builder) {
    this.playerColor = builder.playerColor;
  }

  /**
   * Set the column where a move has been 
   * made by the humanplayer
   * 
   * @param col
   *          column
   */
  @Override
  public void setColMove(int col) {
    if (col < 0 || col > 7) {
      throw new IllegalArgumentException();
    }
    this.dropCol = col;
  }

  /**
   * Get the column of the last move of the human player
   * 
   * @return column of last move
   */
  @Override
  public int getColMove() {
    return this.dropCol;
  }

  /**
   * The human player make a move
   * @param model
   *          the binding game model
   */
  @Override
  public void makeAMove(Model model) {
    if (model == null) {
      throw new IllegalArgumentException(
          "connect4 game model is not initialized");
    }
    model.makeMove(playerColor, dropCol);
  }

  /**
   * get color that represent the human player
   * 
   * @return
   */
  @Override
  public Color getColor() {
    return playerColor;
  }

  /**
   * Get the type of the player, which is HUMAN
   * 
   * @return the type of the player
   */
  @Override
  public PlayerType getPlayerType() {
    return PlayerType.HUMAN;
  }

  @Override
  public int hashCode() {
    return playerColor.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof HumanPlayer)) {
      return false;
    }
    HumanPlayer other = (HumanPlayer) obj;
    if (dropCol == other.dropCol && playerColor.equals(other.playerColor)) {
      return true;
    }
    return false;
  }

}
