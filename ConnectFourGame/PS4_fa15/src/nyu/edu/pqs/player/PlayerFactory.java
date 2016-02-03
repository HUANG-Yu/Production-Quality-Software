package nyu.edu.pqs.player;

import java.awt.Color;

/**
 * The class implementing factory, whch is used to 
 * create player based on the given PlayerType
 */
public class PlayerFactory extends Factory {

  public static Player addPlayers(PlayerType type, Color color) {
    if (type == null || color == null) {
      throw new IllegalArgumentException("Illegal player type or color");
    }
    if (type == PlayerType.COMPUTER) {
      return new ComputerPlayer.ComputerPlayerBuilder(color).build();
    } else {
      return new HumanPlayer.HumanPlayerBuilder(color).build();
    }
  }

}
