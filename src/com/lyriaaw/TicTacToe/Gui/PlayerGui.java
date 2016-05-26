package com.lyriaaw.TicTacToe.Gui;

import com.lyriaaw.TicTacToe.GameLogic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * (commentaires)
 *
 * @author Amalric Lombard de Buffières <amalric.debuffieres@icloud.com>
 * @version 1.0.0
 */
public class PlayerGui extends JPanel{

    private JLabel playerName;
    private Player correspondingPlayer; // The corresponding player in the game logic

    public PlayerGui() {
    }

    public PlayerGui(Player correspondingPlayer) {
        this.correspondingPlayer = correspondingPlayer;

        initPlayer();
    }

    public JLabel getPlayerName() {
        return playerName;
    }

    public void setPlayerName(JLabel playerName) {
        this.playerName = playerName;
    }

    public Player getCorrespondingPlayer() {
        return correspondingPlayer;
    }

    public void setCorrespondingPlayer(Player correspondingPlayer) {
        this.correspondingPlayer = correspondingPlayer;
    }


    /**
     * Init the player UI
     */
    private void initPlayer() {

        playerName = new JLabel();

        update();

        this.add(playerName);
    }

    /**
     * Update the player UI
     */
    public void update() {
        String correspondingPlayerInfo = correspondingPlayer.getName() + " : " + Frame.getButtonTextFromPlayerId(correspondingPlayer.getPlayerId());

        if (correspondingPlayer.isCurrentPlayer()) {
            playerName.setFont(new Font("Georgia", Font.BOLD, 20));
        } else {
            playerName.setFont(new Font("Georgia", Font.PLAIN, 20));
        }

        playerName.setText(correspondingPlayerInfo);
    }

}
