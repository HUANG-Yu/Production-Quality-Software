package nyu.edu.pqs.player;

import java.awt.Color;
import java.security.SecureRandom;

import nyu.edu.pqs.model.Model;

/**
 * An implementation of the Player, representing players controlled by computer
 *
 * @see HumanPlayer, Player
 */
public class ComputerPlayer implements Player {
  private final int COL = 7;

  private Color playerColor;
  private int dropCol;

  /**
   * Inner builder class to initialize ComputerPlayer
   *
   */
  // For scale extension and reusability in the future,
  // more feature like player name, player winning times
  // can be added to as the attributes
  public static class ComputerPlayerBuilder {
    private final Color playerColor;

    public ComputerPlayerBuilder(Color playerColer) {
      this.playerColor = Color.BLUE;
    }

    public ComputerPlayer build() {
      return new ComputerPlayer(this);
    }
  }

  /**
   * ComputerPlayer Constructor
   * 
   * @param builder
   */
  private ComputerPlayer(ComputerPlayerBuilder builder) {
    this.playerColor = builder.playerColor;
  }

  /**
   * Set the column where a move has been made by the computer player
   * 
   * @param col
   *          column
   */
  @Override
  public void setColMove(int col) {
    this.dropCol = col;
  }

  /**
   * The computer player make a move
   * 
   * @param model
   *          the binding game model
   */
  @Override
  public void makeAMove(Model model) {
    if (model == null) {
      throw new IllegalArgumentException("game is not initialized");
    }
    int i = 0;
    while (i < COL) {
      if (model.playerWinCheck(i, playerColor)) {
        setColMove(i);
        model.makeMove(playerColor, dropCol);
        break;
      }
      i++;
    }
    if (i == COL) {
      int j = 0;
      while (j < COL) {
        if (model.playerWinCheck(j, Color.RED)) {
          setColMove(j);
          model.makeMove(playerColor, dropCol);
          break;
        }
        j++;
      }
      if (j == COL) {
        // Math.rand sucks here
        SecureRandom random = new SecureRandom();
        while (true) {
          int rand = random.nextInt(COL);
          if (model.dropEligible(rand)) {
            setColMove(rand);
            model.makeMove(playerColor, dropCol);
            break;
          }
        }
      }
    }
  }

  /**
   * Get the column of the last move of the computer player
   * 
   * @return column of last move
   */
  @Override
  public int getColMove() {
    return this.dropCol;
  }

  /**
   * get color that represent the computer player
   * 
   * @return
   */
  @Override
  public Color getColor() {
    return playerColor;
  }

  /**
   * Get the type of the player which is COMPUTER
   * 
   * @return the type of the player
   */
  @Override
  public PlayerType getPlayerType() {
    return PlayerType.COMPUTER;
  }

  @Override
  public int hashCode() {
    return playerColor.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof ComputerPlayer)) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    ComputerPlayer other = (ComputerPlayer) obj;
    if (dropCol == other.dropCol && playerColor.equals(other.playerColor)) {
      return true;
    }
    return false;
  }

}
