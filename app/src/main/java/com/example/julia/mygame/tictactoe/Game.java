package com.example.julia.mygame.tictactoe;

import com.example.julia.mygame.logic.Player;
import com.example.julia.mygame.logic.Session;
import com.example.julia.mygame.tictactoe.winner.WinnerChecker;
import com.example.julia.mygame.tictactoe.winner.impl.WinnerCheckerDiagonalLeft;
import com.example.julia.mygame.tictactoe.winner.impl.WinnerCheckerDiagonalRight;
import com.example.julia.mygame.tictactoe.winner.impl.WinnerCheckerHorizontal;
import com.example.julia.mygame.tictactoe.winner.impl.WinnerCheckerVertical;
import static com.example.julia.mygame.constant.Const.*;

/**
 * @author julia
 */
public class Game {

    private Session session;

    //поле
    private Square[][] squares;

    //начата ли игра?
    private boolean gameStarted;

    //Считает колличество заполненных ячеек
    private int filled;

    //всего ячеек
    private int squareCount;

    private WinnerChecker[] winnerCheckers;

    public Game() {
        session = new Session();
        squares = new Square[SQUARE_COUNT][SQUARE_COUNT];
        // заполнение поля
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                squares[i][j] = new Square();
                squareCount++;
            }
        }

        gameStarted = false;

        winnerCheckers = new WinnerChecker[4];
        winnerCheckers[0] = new WinnerCheckerHorizontal(this);
        winnerCheckers[1] = new WinnerCheckerVertical(this);
        winnerCheckers[2] = new WinnerCheckerDiagonalLeft(this);
        winnerCheckers[3] = new WinnerCheckerDiagonalRight(this);
    }

    public void start() {
        session.resetPlayers();
        gameStarted = true;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public boolean touchSquare(int x, int y) {
        if (squares[x][y].isFilled()) {
            return false;
        }
        squares[x][y].fill(session.getCurrentActivePlayer());
        filled++;
        return true;
    }

    public boolean isFieldFilled() {
        return squareCount == filled;
    }

    public void reset() {
        resetSquares();
        session.resetPlayers();
    }

    private void resetSquares() {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                squares[i][j].fill(null);
            }
        }
        filled = 0;
    }

    public Player checkWinner() {
        for (WinnerChecker winChecker : winnerCheckers) {
            Player winner = winChecker.checkWinner();
            if (winner != null) {
                return winner;
            }
        }
        return null;
    }

    public Session getSession() {
        return session;
    }
}
