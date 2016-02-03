package nyu.edu.pqs.connect4game;

import nyu.edu.pqs.views.*;

/**
 * 
 * The game entrance to start the game
 */
public class Connect4App {
  public void startGame() {
    new EntryView();
  }

  public static void main(String[] args) {
    new Connect4App().startGame();
  }
}
