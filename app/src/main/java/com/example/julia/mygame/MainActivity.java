package com.example.julia.mygame;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.julia.mygame.logic.Player;
import com.example.julia.mygame.tictactoe.Game;
import com.example.julia.mygame.tictactoe.Square;

import static com.example.julia.mygame.constant.Const.*;

/**
 * @author julia
 */
public class MainActivity extends Activity {

    private TableLayout tableLayout;
    private Game game;
    private Button[][] buttonsSquare = new Button[SQUARE_COUNT][SQUARE_COUNT];

    public MainActivity() {
        game = new Game();
        game.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = (TableLayout) findViewById(R.id.table_layout);
        buildGameSquares();
    }

    private void buildGameSquares() {
        Square[][] squares = game.getSquares();
        for (int i = 0; i < squares.length; i++) {
            TableRow row = new TableRow(this); // создание строки таблицы
            for (int j = 0; j < squares[i].length; j++) {
                Button button = new Button(this);
                button.setOnClickListener(new ButtonListener(i, j)); // установка слушателя, реагирующего на клик по кнопке
                row.addView(button, SQUARE_SIZE, SQUARE_SIZE);
                buttonsSquare[i][j] = button;
            }
            tableLayout.addView(row);
        }
    }

    private class ButtonListener implements View.OnClickListener {
        private int x = 0;
        private int y = 0;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            Button button = (Button) view;
            if (game.touchSquare(x, y)) {
                Player currentActivePlayer = game.getSession().getCurrentActivePlayer();
                game.getSession().switchPlayers();
                button.setText(currentActivePlayer.getName());
            }
            Player winner = game.checkWinner();
            if (winner != null) {
                gameOver(winner);
            }
            if (game.isFieldFilled()) {  // в случае, если поле заполнено
                gameOver();
            }
        }
    }

    private void gameOver(Player player) {
        CharSequence text = "Draw";
        if (player != null) {
            text = "Player \"" + player.getName() + "\" won!";
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private void gameOver() {
        gameOver(null);
    }

    private void refresh() {
        Square[][] field = game.getSquares();

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                if (field[i][j].getPlayer() == null) {
                    buttonsSquare[i][j].setText("");
                } else {
                    buttonsSquare[i][j].setText(field[i][j].getPlayer().getName());
                }
            }
        }
    }

}
