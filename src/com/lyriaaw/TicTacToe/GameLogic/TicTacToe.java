package com.lyriaaw.TicTacToe.GameLogic;

import java.util.List;

/**
 * (commentaires)
 *
 * @author Amalric Lombard de Buffières <amalric.debuffieres@icloud.com>
 * @version 1.0.0
 */
public class TicTacToe {

    private List<Player> playerList;
    private Player currentPlayer;

    private static final int gridSize = 3;
    private int[][] grid;

    private boolean isGameRunning;


    public TicTacToe() {
        isGameRunning = false;
    }
    public List<Player> getPlayerList() {
        return playerList;
    }
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public static int getGridSize() {
        return gridSize;
    }
    public int[][] getGrid() {
        return grid;
    }
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
    public boolean isGameRunning() {
        return isGameRunning;
    }
    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }








    /**
     * Create and Init the Game session
     * @param playerList the list of player who will play the game
     */
    public void initGame(List<Player> playerList) {
        // Never trust input !
        if (playerList == null || playerList.size() == 0) {
            System.out.println("Player List is invalid");
            return;
        }
        // the player list is correct, we add is to the game;
        this.playerList = playerList;


        // Creating the Game Grid
        grid = new int[gridSize][gridSize];
        for (int jt = 0; jt < gridSize; jt++) {
            for (int it = 0; it < gridSize; it++) {
                grid[jt][it] = 0;
            }
        }


        // Setting the current player
        currentPlayer = playerList.get(0);
        currentPlayer.switchCurrentPlayer();



        // Launching the game
        isGameRunning = true;
    }


    /**
     * Must be called when somebody plays
     * @param playMessage The informations about the player's move
     *                    Format : "X:{x};Y:{y}"
     *                    with {x} and {y} the x and y selected by the user
     */
    public void somebodyPlayed(String playMessage) {
        if (!isGameRunning) return;

        Coordinate playerCoordinate = selectCoordinateFromInputString(playMessage);

        if (playerCoordinate == null || !playerCoordinate.areValid(gridSize)) {
            System.err.println("Coordinates Error");
            return;
        }


        if (setPossession(playerCoordinate)) {
            updateCurrentPlayer();
        }

        checkForWinner();

        System.out.println(generateGridRepresentation());

    }

    /**
     *
     * @param inputString The coordinates string
     * @return Coordinate selectedCoordinate
     */
    public static Coordinate selectCoordinateFromInputString(String inputString) {

        String[] splittedString = inputString.split(";");

        if (splittedString.length < 2) return null;     // check if the String was in a regular format

        String xString = splittedString[0];
        String yString = splittedString[1];

        return Coordinate.getCoordinatesFromStrings(xString, yString);

    }

    /**
     * Verify if a player is winning the game
     */
    private void checkForWinner() {

        // Check on columns
        for (int it = 0; it < gridSize; it++) {
            if (grid[0][it] == grid[1][it] && grid[1][it] == grid[2][it]) {
                setWinner(grid[0][it]);
            }
        }

        // Check on lines
        for (int jt = 0; jt < gridSize; jt++) {
            if (grid[jt][0] == grid[jt][1] && grid[jt][1] == grid[jt][2]) {
                setWinner(grid[jt][0]);
            }
        }

        // Crossed : \
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            setWinner(grid[0][0]);
        }

        // Crossed : /
        if (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) {
            setWinner(grid[2][0]);
        }



    }

    /**
     * Check if a winner can be set and set him
     * If somebody win, set the isGameRunning to false and the currentPlayer to the winning player
     * @param winnerId the id of the tryed winner
     */
    private void setWinner(int winnerId) {
        if (!isGameRunning || winnerId == 0) return;

        isGameRunning = false;

        currentPlayer = playerList.get(winnerId - 1);


    }


    /**
     * Update the player who must plays
     *
     */
    private void updateCurrentPlayer() {
        currentPlayer.switchCurrentPlayer();


        int currentPlayerIndex = currentPlayer.getPlayerId() - 1;
        currentPlayerIndex++;

        try {
            currentPlayer = playerList.get(currentPlayerIndex);
        } catch (IndexOutOfBoundsException e) {
            // e.printStackTrace();

            currentPlayer = playerList.get(0);
        }

        currentPlayer.switchCurrentPlayer();

    }

    /**
     * Set the selected case to belong to the current Player
     * @param coordinate The goal coordinate
     * @return true if the belonging has been setted correctly, false if the case already belong to somebody
     */
    private boolean setPossession(Coordinate coordinate) {

        int x = coordinate.getX();
        int y = coordinate.getY();

        if (grid[y][x] != 0) return false;

        grid[y][x] = currentPlayer.getPlayerId();

        return true;

    }


    /**
     * Generate a String that can be displayed to show the grid in console
     * @return String generatedGrid - The grid as a String
     */
    public String generateGridRepresentation() {
        String generatedGrid = "";

        for (int jt = 0; jt < gridSize; jt++) {
            for (int it = 0; it < gridSize; it++) {
                generatedGrid += grid[jt][it];
            }
            generatedGrid += "\n";
        }

        return generatedGrid;
    }



}
