package com.example.julia.mygame.tictactoe.winner.impl;

import com.example.julia.mygame.tictactoe.Game;
import com.example.julia.mygame.logic.Player;
import com.example.julia.mygame.tictactoe.Square;
import com.example.julia.mygame.tictactoe.winner.WinnerChecker;

/**
 * @author julia
 */
public class WinnerCheckerDiagonalLeft implements WinnerChecker {
    private Game game;

    public WinnerCheckerDiagonalLeft(Game game) {
        this.game = game;
    }

    public Player checkWinner() {
        Square[][] squares = game.getSquares();
        Player currPlayer;
        Player lastPlayer = null;
        int successCounter = 1;
        for (int i = 0; i < squares.length; i++) {
            currPlayer = squares[i][i].getPlayer();

            if (currPlayer != null) {
                if (lastPlayer == currPlayer) {
                    successCounter++;

                    if (successCounter == squares.length) {
                        return currPlayer;
                    }
                }
            }

            lastPlayer = currPlayer;
        }
        return null;
    }
}
