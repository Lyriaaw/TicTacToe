package com.lyriaaw.TicTacToe;

import com.lyriaaw.TicTacToe.GameLogic.Coordinate;
import com.lyriaaw.TicTacToe.GameLogic.Player;
import com.lyriaaw.TicTacToe.GameLogic.TicTacToe;
import com.lyriaaw.TicTacToe.Gui.Frame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * (commentaires)
 *
 * @author Amalric Lombard de Buffières <amalric.debuffieres@icloud.com>
 * @version 1.0.0
 */
public class Main {
    public static void main(String args[]) {
        System.out.println("Hello World ! Launching the software");

        // Launching the game as UI
        Frame frame = new Frame();

        // executeTest();

        // playGame();




    }

    /*public static void executeTest() {
        System.out.println("Executing tests ");

    }

    public static void playGame() {
        System.out.println("Launching the game");

        TicTacToe gameLogic = new TicTacToe();




        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Amalric", 1));
        playerList.add(new Player("Mylena", 2));

        gameLogic.initGame(playerList);


        // Game loop
        do {

            System.out.println("Player = " + gameLogic.getCurrentPlayer().getName());

            String userInput;
            do {
                System.out.printf("Enter the input String (format : \"X:{x};Y:{y}\" ) : ");
                userInput = readUserInput();
            } while (userInput.isEmpty());

            gameLogic.somebodyPlayed(userInput);

            System.out.println(gameLogic.generateGridRepresentation());

        } while (gameLogic.isGameRunning());




        System.out.println("Player : " + gameLogic.getCurrentPlayer().getName() + " won, Congratulations");

    }*/

    public static String readUserInput() {
        BufferedReader userInputReader;
        userInputReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            return userInputReader.readLine();
        } catch (IOException e) {
            // e.printStackTrace();
            System.err.println("IO Exception");
            return "";
        }
    }
}
