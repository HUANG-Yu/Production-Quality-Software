package nyu.edu.pqs.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import nyu.edu.pqs.model.*;

/**
 * 
 * The entry view of the game providing two game modes options to start the game
 *
 */
public class EntryView implements ActionListener {
  private JFrame selectModeFrame = 
      new JFrame("ConnectFour Game Application");

  private JButton singlePlayerButton = 
      new JButton("Play Against Computer");
  private JButton doublePlayerButton = 
      new JButton("Play Against A Friend");

  /**
   * Entry view constructor, set the greeting message and buttons actions
   */
  public EntryView() {
    this.createSelectMode();
    this.bindButtonsActions();
  }

  /**
   * Bind the buttons to its relevant listeners
   */
  private void bindButtonsActions() {
    singlePlayerButton.addActionListener(this);
    doublePlayerButton.addActionListener(this);
  }

  /**
   * Detect the button click events and set 
   * the game view according the game mode being selected.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == singlePlayerButton) {
      Model singleModeModel = Model.getModelSingleton();
      new PlayView(singleModeModel);
      singleModeModel.addPlayers(GameMode.SINGLE);
    }
    if (e.getSource() == doublePlayerButton) {
      Model doubleModeModel = Model.getModelSingleton();
      new PlayView(doubleModeModel);
      doubleModeModel.addPlayers(GameMode.DOUBLE);
    }
    selectModeFrame.setVisible(false);
  }

  /**
   * Provide a panel with two buttons to select game modes
   */
  public void createSelectMode() {
    JPanel infoPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    singlePlayerButton.setSize(new Dimension(120, 120));
    doublePlayerButton.setSize(new Dimension(120, 120));
    selectModeFrame.setSize(400, 120);

    buttonPanel.add(singlePlayerButton);
    buttonPanel.add(doublePlayerButton);
    infoPanel.add(buttonPanel);

    selectModeFrame.add(infoPanel, BorderLayout.CENTER);
    selectModeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    selectModeFrame.setVisible(true);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(selectModeFrame.hashCode(), 
        singlePlayerButton.hashCode(), doublePlayerButton.hashCode());
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    EntryView other = (EntryView) obj;
    if (this.hashCode() == other.hashCode()) {
      return true;
    }
    return false;
  }
}
