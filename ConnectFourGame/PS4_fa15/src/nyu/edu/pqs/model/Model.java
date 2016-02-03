package nyu.edu.pqs.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import nyu.edu.pqs.player.Player;
import nyu.edu.pqs.player.PlayerFactory;
import nyu.edu.pqs.player.PlayerType;
import nyu.edu.pqs.views.Listener;

/**
 * The class contains the game logic and is used to notify
 * all the relevant views.
 *
 */
public class Model {
  private final int COL = 7;
  private final int ROW = 6;
  private final int winLen = 4;

  private static final Model model = new Model();

  private Player redPlayer;
  private Player bluePlayer;

  private List<Listener> listeners = new ArrayList<Listener>();
  private Color[][] board = new Color[ROW][COL];
  private int[] rows = new int[COL];
  private int row;
  private GameMode gameMode;
  private Color playerTurn;
  
  /**
   * Check if the listener already registered  the model
   * @param listener
   * @return true if the listener already exists
   *  in the list of the model. Otherwise return false
   */
  public boolean listenerExistCheck(Listener listener) {
    for (Listener each: listeners) {
      if (each.equals(listener)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Add observer to the current observer list
   * 
   * @param listerner
   *          observers
   */
  public void addListeners(Listener listener) {
    if (!listenerExistCheck(listener)) {
      listeners.add(listener);
    }
  }

  /**
   * Remove observer from the list
   * 
   * @param listener
   *          observers
   */
  public void removeListener(Listener listener) {
    if (!listenerExistCheck(listener)) {
      throw new IllegalArgumentException("Listener doesn't exist");
    }
    listeners.remove(listener);
  }

  /**
   * Get the model singleton
   * 
   * @return model
   */
  public static Model getModelSingleton() {
    return model;
  }

  /**
   * private constructor of model
   */
  private Model() {
  }

  /**
   * Drop a disc in the selected column
   * 
   * @param col
   *          the column player choose to drop a disc
   */
  public void drop(int col) {
    if (rows[col] >= ROW) {
      throw new IllegalArgumentException("The column is full!");
    }
    if (gameMode == GameMode.SINGLE) {
      redPlayer.setColMove(col);
      redPlayer.makeAMove(this);
      bluePlayer.makeAMove(this);
    } else if (gameMode == GameMode.DOUBLE) {
      if (playerTurn == Color.RED) {
        redPlayer.setColMove(col);
        redPlayer.makeAMove(this);
        playerTurn = Color.BLUE;
      } else {
        bluePlayer.setColMove(col);
        bluePlayer.makeAMove(this);
        playerTurn = Color.RED;
      }
    }
  }

  /**
   * Player in the given color select column to move
   * 
   * @param color
   *          player color
   * @param col
   *          column
   */
  public void makeMove(Color color, int col) {
    row = rows[col]++;
    board[row][col] = color;
    fireGameMoveEvent(row, col, color);
    if (winCheck(row, col)) {
      fireGameWinEvent(color);
    }
    if (boardFullCheck()) {
      fireGameTieEvent();
    }
  }

  /**
   * add the needed number of players to the game according to the game mode
   * 
   * @param mode
   *          game mode
   */
  public void addPlayers(GameMode mode) {
    this.gameMode = mode;
    playerTurn = Color.RED;
    if (mode == GameMode.SINGLE) {
      bluePlayer = PlayerFactory.addPlayers(PlayerType.COMPUTER, Color.BLUE);
      redPlayer = PlayerFactory.addPlayers(PlayerType.HUMAN, Color.RED);
    } else {
      bluePlayer = PlayerFactory.addPlayers(PlayerType.HUMAN, Color.BLUE);
      redPlayer = PlayerFactory.addPlayers(PlayerType.HUMAN, Color.RED);
    }
  }

  /**
   * get the mode of the game
   * @return the game mode of the model
   */
  public GameMode getGameMode() {
    return gameMode;
  }

  /**
   * Notify the views that the game started
   */
  public void fireGameStartEvent() {
    for (Listener listener : listeners) {
      listener.gameStarted();
    }
  }

  /**
   * Notify the views that which player won the game
   * 
   * @param color
   */
  public void fireGameWinEvent(Color color) {
    for (Listener listener : listeners) {
      listener.gameWon(color);
    }
  }

  /**
   * Notify the views that the game ends in tie
   */
  public void fireGameTieEvent() {
    for (Listener listener : listeners) {
      listener.gameTied();
    }
  }

  /**
   * Notify the views that a player in a given color made a move in which row
   * and coloumn
   * 
   * @param row
   * @param col
   * @param color
   */
  public void fireGameMoveEvent(int row, int col, Color color) {
    for (Listener listener : listeners) {
      listener.discDropped(row, col, color, gameMode);
    }
  }

  /**
   * Check if the current move lead the game win
   * 
   * @param row
   * @param col
   * @return true if it exists. otherwise false
   */
  public boolean winCheck(int row, int col) {
    if (checkVerticalSequence(row, col)) {
      return true;
    } else if (checkHorizontalSequence(row, col)) {
      return true;
    } else if (checkDiagonalSequence(row, col)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Check if there exists horizontal length equal or greater than 4 that has
   * the same color
   * 
   * @param row
   * @param col
   * @return true if it exists. otherwise false
   */
  public boolean checkHorizontalSequence(int row, int col) {
    int sequenceLen = 0;
    int i = col;
    Color color = board[row][col];
    while (i <= ROW && board[row][i] == color) {
      sequenceLen++;
      i++;
    }
    i = col;
    while (i > 0 && board[row][i - 1] == color) {
      sequenceLen++;
      i--;
    }
    return (sequenceLen >= winLen) ? true : false;
  }

  /**
   * Check if there exists vertical length equal or greater than 4 that has the
   * same color
   * 
   * @param row
   * @param col
   * @return true if it exists. otherwise false
   */
  public boolean checkVerticalSequence(int row, int col) {
    int sequenceLen = 0;
    int i = row;
    Color color = board[row][col];
    while (i <= ROW-1 && board[i][col] == color) {
      sequenceLen++;
      i++;
    }
    i = row;
    while (i > 0 && board[i - 1][col] == color) {
      sequenceLen++;
      i--;
    }
    return (sequenceLen >= winLen) ? true : false;
  }

  /**
   * Check if there exists diagonal length equal or greater than 4 that has the
   * same color
   * 
   * @param row
   * @param col
   * @return return true if it exists. otherwise false
   */
  public boolean checkDiagonalSequence(int row, int col) {
    // calculate from top left to right down diagonal
    int topLeftLen = 0;
    // calculate from top right to left down diagonal
    int topRightLen = 0;
    Color color = board[row][col];
    for (int i = row, j = col; i < ROW && j < COL; i++, j++) {
      if (board[i][j] == color) {
        topLeftLen++;
      } else {
        break;
      }
    }
    for (int i = row, j = col; i > 0 && j > 0; i--, j--) {
      if (board[i - 1][j - 1] == color) {
        topLeftLen++;
      } else {
        break;
      }
    }
    for (int i = row, j = col; i > 0 && j < 6; i--, j++) {
      if (board[i - 1][j + 1] == color) {
        topRightLen++;
      } else {
        break;
      }
    }
    for (int i = row, j = col; i < ROW && j >= 0; i++, j--) {
      if (board[i][j] == color) {
        topRightLen++;
      } else {
        break;
      }
    }
    return (topLeftLen >= winLen || topRightLen >= winLen) ? true : false;
  }

  /**
   * Check if the all grids on board are filled so no more space to drop a disc
   * 
   * @return true if the board is full. Otherwise false.
   */
  public boolean boardFullCheck() {
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COL; j++) {
        if (board[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 
   * @param col
   * @param color
   * @return
   */
  public boolean playerWinCheck(int col, Color color) {
    if (col < 0 || col > ROW) {
      throw new IllegalArgumentException("Illegal column: " + col);
    }
    int i = rows[col];
    if (i >= ROW) {
      return false;
    }
    board[i][col] = color;
    if (winCheck(i, col)) {
      board[i][col] = null;
      return true;
    } else {
      board[i][col] = null;
      return false;
    }
  }

  /**
   * Check if the selected column is eligible for the disc to drop
   * 
   * @param col
   * @return
   */
  public boolean dropEligible(int col) {
    if (col < 0 || col > ROW) {
      throw new IllegalArgumentException("col " + col);
    }
    int i = rows[col];
    i++;
    return (i >= ROW) ? false : true;
  }
  
  /**
   * This is only used for unit test
   * 
   * @return the current listeners on the model
   */
  public List<Listener> getListeners() {
    return listeners;
  }

  /**
   * This is only used for unit test
   * 
   * @param col
   * @param row
   */
  public void setRow(int col, int row) {
    rows[col] = row;
  }

  /**
   * This is only used for unit test
   * 
   * @param row
   * @param col
   * @param color
   */
  public void setGrid(int row, int col, Color color) {
    this.board[row][col] = color;
  }
  
}
