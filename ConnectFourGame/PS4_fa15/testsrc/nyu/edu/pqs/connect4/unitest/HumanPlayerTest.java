package nyu.edu.pqs.connect4.unitest;

import static org.junit.Assert.*;
import nyu.edu.pqs.player.HumanPlayer;
import nyu.edu.pqs.player.PlayerType;

import org.junit.Test;

import java.awt.Color;

public class HumanPlayerTest {
  HumanPlayer humanPlayer = new HumanPlayer.HumanPlayerBuilder(Color.RED).build();

  @Test
  public void testEqualsTrue() {
    HumanPlayer localhumanPlayer = new HumanPlayer.HumanPlayerBuilder(Color.RED).build();
    assertTrue(localhumanPlayer.equals(humanPlayer));
  }

  @Test
  public void testEqualsFalse() {
    HumanPlayer localhumanPlayer = new HumanPlayer.HumanPlayerBuilder(Color.BLACK).build();
    assertFalse(localhumanPlayer.equals(humanPlayer));
  }

  @Test
  public void testGetPlayerTypeTrue() {
    PlayerType expectedType = PlayerType.HUMAN;
    PlayerType realType = humanPlayer.getPlayerType();
    assertTrue(expectedType.equals(realType));
  }

  @Test
  public void testGetPlayerTypeFalse() {
    PlayerType expectedType = PlayerType.COMPUTER;
    PlayerType realType = humanPlayer.getPlayerType();
    assertFalse(expectedType.equals(realType));
  }

  @Test
  public void testGetColorTrue() {
    Color expectedColor = Color.RED;
    Color realColor = humanPlayer.getColor();
    assertTrue(expectedColor.equals(realColor));
  }

  @Test
  public void testGetColorFalse() {
    Color expectedColor = Color.GRAY;
    Color realColor = humanPlayer.getColor();
    assertFalse(expectedColor.equals(realColor));
  }

  @Test
  public void testSetColMove() {
    humanPlayer.setColMove(5);
    int expectedCol = 5;
    int realCol = humanPlayer.getColMove();
    assertEquals(expectedCol, realCol);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetColMove_NegativeCol() {
    humanPlayer.setColMove(-5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testSetColMove_ExceedMaxCol() {
    humanPlayer.setColMove(10);
  }

}
