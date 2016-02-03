package nyu.edu.pqs.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import nyu.edu.pqs.model.*;

/**
 * The playView implements Listener interface 
 * and listens to the status change
 * of the game model.
 * 
 * @see EntryView
 */
public class PlayView implements ActionListener, Listener {
  private final int ROW = 6;
  private final int COL = 7;

  private final String rPlayerSelectCol = 
      "Red Player: Select a Column!";
  private final String bPlayerSelectCol = 
      "BLUE Player: Selct a Column!";

  private Model model;
  private JFrame frame = 
      new JFrame("ConnectFour Game Application");

  private JPanel boardPanel = 
      new JPanel(new GridLayout(ROW, COL));
  private JPanel dropButtonsPanel = new JPanel();
  private JPanel[][] boardPanels = new JPanel[ROW][COL];

  private JButton[] dropButtons = new JButton[COL];

  private JTextField gameStatusText = new JTextField();
  private JScrollPane scrollPanel = 
      new JScrollPane(gameStatusText);

  private Border blackBorderLine = 
      BorderFactory.createLineBorder(Color.BLACK);

  /**
   * PlayView constructor, add the view to the model and start the game.
   * 
   * @param model
   *          game model
   */
  public PlayView(Model model) {
    this.model = model;
    this.model.addListeners(this);
    this.gameStarted();
  }

  /**
   * Set the buttons and panels of playView once game started.
   */
  @Override
  public void gameStarted() {
    dropButtonsPanel.setLayout(new GridLayout(0, COL));
    for (int i = 0; i < COL; i++) {
      JButton dropButton = new JButton("Drop");
      dropButton.addActionListener(this);
      dropButtonsPanel.add(dropButton);
      dropButtons[i] = dropButton;
    }
    boardPanels = new JPanel[ROW][COL];
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COL; j++) {
        JPanel panel = new JPanel();
        panel.setBorder(blackBorderLine);
        boardPanels[i][j] = panel;
        boardPanel.add(boardPanels[i][j]);
      }
    }
    gameStatusText.setText(rPlayerSelectCol);
    gameStatusText.setFont(new Font("TimesRoman", Font.BOLD, 20));
    
    frame.add(dropButtonsPanel, BorderLayout.NORTH);
    frame.add(boardPanel, BorderLayout.CENTER);
    frame.add(scrollPanel, BorderLayout.SOUTH);
    
    frame.setSize(700, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }

  /**
   * Set the panel at row,column with the color.
   * 
   * @param row
   *          row
   * @param col
   *          column
   * @param color
   *          player color
   */
  @Override
  public void discDropped(int row, int col, Color color, GameMode mode) {
    boardPanels[this.ROW - row - 1][col].setBackground(color);
    if (mode == GameMode.DOUBLE) {
      if (color == Color.RED) {
        gameStatusText.setText(bPlayerSelectCol);
      } else {
        gameStatusText.setText(rPlayerSelectCol);
      }
    }
    if (row == ROW - 1) {
      dropButtons[col].removeActionListener(this);
    }
  }

  /**
   * Listen to the game win event and set winner 
   * using the color parameter, then output the winning message
   * 
   * @param color
   *          player color 
   */
  @Override
  public void gameWon(Color color) {
    for (JButton button : dropButtons) {
      button.removeActionListener(this);
    }
    if (color == Color.BLUE) {
      gameStatusText.setText("Game Ends! BLUE WINS! Reopen to play again!");
    } else if (color == Color.RED) {
      gameStatusText.setText("Game Ends! RED WINS! Reopen to play again!");
    }
  }

  /**
   * Listen to the game tie events and output the game tie message
   */
  @Override
  public void gameTied() {
    for (JButton button : dropButtons) {
      button.removeActionListener(this);
    }
    gameStatusText.setText("Game Tie! Reopen to play again!");
  }
  
  /**
   * 
   * Detect the button click events and set 
   *  the game view according the button being selected.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < COL; i++) {
      if (e.getSource() == dropButtons[i]) {
        model.drop(i);
      }
    }
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(boardPanel.hashCode(), scrollPanel.hashCode(),
        boardPanel.hashCode(), dropButtonsPanel.hashCode());
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    PlayView other = (PlayView) obj;
    if (this.hashCode() == other.hashCode()) {
      return true;
    }
    return false;
  }
}
