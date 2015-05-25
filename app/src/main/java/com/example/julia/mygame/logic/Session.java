package com.example.julia.mygame.logic;

import java.util.Date;

import static com.example.julia.mygame.constant.Const.PLAYERS_SIZE;

/**
 * @author julia
 */
public class Session {

    //игроки
    private Player[] players;

    //текущий игрок
    private Player activePlayer;

    private Date date;

    public Session() {
        players = new Player[PLAYERS_SIZE];
        date = new Date();
    }

    public Player[] getPlayers() {
        return players;
    }

    public Date getDate() {
        return date;
    }

    public Player getCurrentActivePlayer() {
        return activePlayer;
    }

    public void resetPlayers() {
        players[0] = new Player("X");
        players[1] = new Player("O");
        setCurrentActivePlayer(players[0]);//X
    }

    private void setCurrentActivePlayer(Player player) {
        activePlayer = player;
    }

    public void switchPlayers() {
        if (activePlayer == players[0]) {
            activePlayer = players[1];
        }
        else {
            activePlayer = players[0];
        }
    }
}
