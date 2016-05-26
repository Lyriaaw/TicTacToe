package com.lyriaaw.TicTacToe.Gui;

import com.lyriaaw.TicTacToe.GameLogic.Player;
import com.lyriaaw.TicTacToe.GameLogic.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * (commentaires)
 *
 * @author Amalric Lombard de Buffières <amalric.debuffieres@icloud.com>
 * @version 1.0.0
 */
public class Frame extends JFrame implements ActionListener {

    private TicTacToe gameLogic;
    private List<Player> playerList;

    private JPanel mainPanel;

    private JPanel playersPanel;
    private List<PlayerGui> playerGuiList;

    private JPanel gridPanel;
    private JButton gridButtons[][];






    public Frame() throws HeadlessException {
        this.setSize(400, 500);
        this.setTitle("Tic Tac Toe | The Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        initGame();




        this.setVisible(true);
    }

    private void initGame() {
        initGameComponents();
        initGraphicalComponents();
    }



    private void initGameComponents() {

        gameLogic = new TicTacToe();

        Player player1 = new Player("Player1", 1);
        Player player2 = new Player("Player2", 2);

        playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);

        gameLogic.initGame(playerList);


    }


    private void initGraphicalComponents() {

        mainPanel = new JPanel();

        GridLayout mainLayout = new GridLayout(3, 1);
        mainPanel.setLayout(mainLayout);


        gridPanel = new JPanel();
        initGrid(gridPanel);

        playersPanel = new JPanel();
        initPlayerPanel(playersPanel);




        mainPanel.add(gridPanel);
        mainPanel.add(playersPanel);

        this.setContentPane(mainPanel);
    }


    private void initGrid(JPanel myPanel) {
        int gridSize = TicTacToe.getGridSize();

        GridLayout gridLayout = new GridLayout(gridSize, gridSize);
        myPanel.setLayout(gridLayout);

        gridButtons = new JButton[gridSize][gridSize];

        for (int jt = 0; jt < gridSize; jt++) {
            for (int it = 0; it < gridSize; it++) {
                gridButtons[jt][it] = new JButton();
                gridButtons[jt][it].setActionCommand("X:" + it + ";Y:" + jt);
                gridButtons[jt][it].addActionListener(this);
                myPanel.add(gridButtons[jt][it]);
            }
        }

        updateGrid();

    }

    private void updateGrid() {
        int[][] gameGrid = gameLogic.getGrid();
        int gridSize = TicTacToe.getGridSize();

        for (int jt = 0; jt < gridSize; jt++) {
            for (int it = 0; it < gridSize; it++) {

                String buttonText = getButtonTextFromPlayerId(gameGrid[jt][it]);
                gridButtons[jt][it].setText(buttonText);


            }
        }
    }


    private void initPlayerPanel(JPanel myPanel) {
        playerGuiList = new ArrayList<>();

        for (Player player : gameLogic.getPlayerList()) {
            PlayerGui playerGui = new PlayerGui(player);
            myPanel.add(playerGui);
            playerGuiList.add(playerGui);
        }

    }

    private void updatePlayersPanel() {
        for (PlayerGui playerGui : playerGuiList) {
            playerGui.update();
        }
    }


    /**
     * Return a text to place on the button depending on the playerId
     * Can be used to add new players of make a custom UI
     * @param playerId Id of the current player
     * @return Text to place on the button
     */
    public static String getButtonTextFromPlayerId(int playerId) {
        switch (playerId) {
            case 1:
                return "X";
            case 2:
                return "O";
            default:
                return " ";
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        gameLogic.somebodyPlayed(e.getActionCommand());

        if (gameLogic.isGameRunning()) {
            updateGameInfos();
            updateGui();
        } else {
            System.out.println("player " + gameLogic.getCurrentPlayer().getName() + " won ! Contragtulations !!");
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

    }

    private void updateGameInfos() {
        playerList = gameLogic.getPlayerList();
    }

    private void updateGui() {
        updateGrid();
        updatePlayersPanel();
    }
}
