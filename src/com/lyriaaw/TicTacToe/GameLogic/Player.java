package com.lyriaaw.TicTacToe.GameLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * (commentaires)
 *
 * @author Amalric Lombard de Buffières <amalric.debuffieres@icloud.com>
 * @version 1.0.0
 */
public class Player {

    private String name;
    private int playerId;

    private boolean currentPlayer;

    public Player(String name, int playerId) {
        this.playerId = playerId;
        this.name = name;
        currentPlayer = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }


    /**
     * Make the user select his two coordinates from the console
     * @return Coordinates selectedCoordinates - The selected coordinates
     */
    public Coordinate selectCaseCoordinatesFromConsole() {

        BufferedReader userInputReader;
        userInputReader = new BufferedReader(new InputStreamReader(System.in));

        String xCoordinate = "";
        String yCoordinate = "";

        try {
            System.out.printf("X : ");
            xCoordinate = userInputReader.readLine();

            System.out.printf("Y : ");
            yCoordinate = userInputReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Coordinate.getCoordinatesFromStrings(xCoordinate, yCoordinate);


    }



    /**
     * Invers the currentPlayer value in order to define if this player is the currentPlayer;
     */
    public void switchCurrentPlayer() {
        this.currentPlayer = !this.currentPlayer;
    }



}
