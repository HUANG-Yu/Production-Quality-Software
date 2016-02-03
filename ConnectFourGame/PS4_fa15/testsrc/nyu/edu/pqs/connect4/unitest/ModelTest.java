package nyu.edu.pqs.connect4.unitest;

import static org.junit.Assert.*;

import java.awt.Color;

import nyu.edu.pqs.model.*;
import nyu.edu.pqs.views.Listener;
import nyu.edu.pqs.views.PlayView;

import org.junit.Test;

public class ModelTest {

  Model model = Model.getModelSingleton();

  @Test
  public void testDropEligibleTrue() {
    model.setRow(3, 5);
    model.setRow(1, 4);
    assertTrue(!model.dropEligible(3));
    assertTrue(model.dropEligible(1));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testDropEligibleExceedMaxCol() {
    model.dropEligible(9);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testDropEligibleNegativeCol() {
    model.dropEligible(-10);
  }
  
  @Test
  public void testWinCheckTrue() {
    model.setRow(1, 1);
    model.setGrid(2, 2, Color.RED);
    model.setGrid(3, 3, Color.RED);
    model.setGrid(4, 4, Color.RED);
    assertTrue(model.playerWinCheck(1, Color.RED));
  }

  @Test
  public void testWinCheckFalse() {
    model.setRow(5, 5);
    model.setGrid(4, 4, Color.BLUE);
    model.setGrid(3, 3, Color.RED);
    model.setGrid(2, 2, Color.BLUE);
    assertFalse(model.playerWinCheck(5, Color.RED));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testWinCheckNegativeCol() {
    model.playerWinCheck(-12, Color.RED);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testWinCheckExceedMaxCol() {
    model.playerWinCheck(12, Color.RED);
  }
  
  @Test
  public void testWinCheckEqualsRow() {
    assertFalse(model.playerWinCheck(6, Color.RED));
  }
  
  @Test
  public void testCheckHorizontalSequenceTrue() {
    model.setGrid(3, 1, Color.RED);
    model.setGrid(3, 2, Color.RED);
    model.setGrid(3, 3, Color.RED);
    model.setGrid(3, 4, Color.RED);
    assertTrue(model.checkHorizontalSequence(3, 4));
  }
  
  @Test
  public void testCheckHorizontalSequenceFalse() {
    model.setGrid(5, 1, Color.RED);
    model.setGrid(5, 2, Color.BLUE);
    model.setGrid(5, 3, Color.RED);
    model.setGrid(5, 4, Color.RED);
    assertTrue(!model.checkHorizontalSequence(5, 4));
  }
  
  @Test
  public void testCheckVerticalSequenceFalse() {
    model.setGrid(1, 2, Color.RED);
    model.setGrid(2, 2, Color.RED);
    model.setGrid(3, 2, Color.RED);
    model.setGrid(4, 2, Color.RED);
    assertTrue(model.checkVerticalSequence(1, 2));
  }
  
  @Test
  public void testCheckVerticalSequenceTrue() {
    model.setGrid(1, 4, Color.RED);
    model.setGrid(2, 4, Color.BLUE);
    model.setGrid(3, 4, Color.RED);
    model.setGrid(4, 4, Color.RED);
    assertTrue(!model.checkVerticalSequence(4, 4));
  }
  
  @Test
  public void testCheckDiagonalFromTopLeft() {
    model.setGrid(1, 1, Color.RED);
    model.setGrid(2, 2, Color.RED);
    model.setGrid(3, 3, Color.RED);
    model.setGrid(4, 4, Color.RED);
    assertTrue(model.checkDiagonalSequence(3, 3));
  }
  
  @Test
  public void testCheckDiagonalFromTopRight() {
    model.setGrid(1, 5, Color.BLUE);
    model.setGrid(2, 4, Color.BLUE);
    model.setGrid(3, 3, Color.BLUE);
    model.setGrid(4, 2, Color.RED);
    assertTrue(!model.checkDiagonalSequence(4, 2));
  }
  
  @Test
  public void testBoardFullCheck() {
    // Board half filled to test false
    for (int i = 0; i < 1; i++) {
      for (int j = 0; j < 3; j++) {
        model.setGrid(i, j, Color.RED);
      }
    }
    // Board all filled to test false
    assertTrue(!model.boardFullCheck());
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        model.setGrid(i, j, Color.BLUE);
      }
    }
    assertTrue(model.boardFullCheck());
  }
  
}
