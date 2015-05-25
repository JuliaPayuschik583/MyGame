package com.example.julia.mygame.tictactoe.winner.impl;

import com.example.julia.mygame.tictactoe.Game;
import com.example.julia.mygame.logic.Player;
import com.example.julia.mygame.tictactoe.Square;
import com.example.julia.mygame.tictactoe.winner.WinnerChecker;

/**
 * @author julia
 */
public class WinnerCheckerHorizontal implements WinnerChecker {
    private Game game;

    public WinnerCheckerHorizontal(Game game) {
        this.game = game;
    }

    public Player checkWinner() {
        Square[][] squares = game.getSquares();
        for (int i = 0; i < squares.length; i++) {
            Player lastPlayer = null;
            int successCounter = 1;

            for (int j = 0; j < squares[i].length; j++) {
                Player currPlayer = squares[i][j].getPlayer();

                if (lastPlayer != null && currPlayer != null && currPlayer == lastPlayer) {
                    successCounter++;
                    if (successCounter == squares[i].length) {
                        return currPlayer;
                    }
                }
                lastPlayer = currPlayer;
            }
        }
        return null;
    }
}
