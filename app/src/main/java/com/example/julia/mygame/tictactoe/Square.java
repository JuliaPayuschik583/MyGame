package com.example.julia.mygame.tictactoe;

import com.example.julia.mygame.logic.Player;

/**
 * @author julia
 */
public class Square {
    private Player player;

    public void fill(Player player) {
        this.player = player;
    }

    public boolean isFilled() {
        if (player != null) {
            return true;
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }
}
