package nyu.edu.pqs.connect4.unitest;

import static org.junit.Assert.*;

import java.awt.Color;

import nyu.edu.pqs.player.ComputerPlayer;
import nyu.edu.pqs.player.PlayerType;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ComputerPlayerTest {
  
  @Rule
  public ExpectedException exception = ExpectedException.none();

  ComputerPlayer computerPlayer = new ComputerPlayer.ComputerPlayerBuilder(Color.BLUE).build();

  @Test
  public void testEqualsTrue() {
    ComputerPlayer localComputerPlayer = new ComputerPlayer.ComputerPlayerBuilder(Color.BLUE).build();;
    assertTrue(localComputerPlayer.equals(computerPlayer));
  }

  @Test
  public void testEqualsFalse() {
    ComputerPlayer localComputerPlayer = new ComputerPlayer.ComputerPlayerBuilder(Color.BLACK).build();;
    localComputerPlayer.setColMove(2);
    assertFalse(localComputerPlayer.equals(computerPlayer));
  }

  @Test
  public void testGetPlayerTypeTrue() {
    PlayerType expectedType = PlayerType.COMPUTER;
    PlayerType realType = computerPlayer.getPlayerType();
    assertTrue(expectedType.equals(realType));
  }

  @Test
  public void testGetPlayerTypeFalse() {
    PlayerType expectedType = PlayerType.HUMAN;
    PlayerType realType = computerPlayer.getPlayerType();
    assertFalse(expectedType.equals(realType));
  }

  @Test
  public void testGetColorTrue() {
    Color expectedColor = Color.BLUE;
    Color realColor = computerPlayer.getColor();
    assertTrue(expectedColor.equals(realColor));
  }

  @Test
  public void testGetColorFalse() {
    Color expectedColor = Color.GRAY;
    Color realColor = computerPlayer.getColor();
    assertFalse(expectedColor.equals(realColor));
  }

  @Test
  public void testSetColMove() {
    computerPlayer.setColMove(2);
    int expectedCol = 2;
    int realCol = computerPlayer.getColMove();
    assertEquals(expectedCol, realCol);
  }

}
