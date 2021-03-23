package divisionio.example.tictactoe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static int[] playGround = new int[9];
    private static int player = 0;
    private static boolean gameReady = true;
    private TextView textGameState;
    private Button startNewGame;

    public void isClicked(View view){
        ImageView image = (ImageView) view;
        int position = Integer.parseInt(image.getTag().toString());

        if (locationIsEmpty(position) && gameReady) {
            if (player == 0) {
                playGround[position] = player;
                player = 1;
            } else {
                playGround[position] = player;
                player = 0;
            }
            chipAnimation(player, image);
            setPlayersTurn(player);
            checkWinner(playGround);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textGameState = (TextView) findViewById(R.id.txtViewGameState);
        startNewGame = (Button) findViewById(R.id.btnPlayAgain);

        startNewGame.setText("Reset Game");

        initPlayGround();
    }

    // Game Management
    private void setPlayersTurn(int player) {
        textGameState.setText((player != 0) ? "Player two's turn" : "Player one's turn");
    }

    private boolean locationIsEmpty(int pos) {
        return playGround[pos] < 0;
    }

    private void checkWinner(int[] playGround) {
        int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
                {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {0, 4, 8}};

        for (int[] winningPosition : winningPositions) {

            if ((playGround[winningPosition[0]] == playGround[winningPosition[1]]) &&
                    (playGround[winningPosition[1]] == playGround[winningPosition[2]]) &&
                    (playGround[winningPosition[0]] != -1)) {

                gameReady = false;
                printWinner(player);
                startNewGame.setText("Start new game");
            } else {

                gameReady = false;

                for (int gameState : playGround) {
                    if (gameState == -1)
                        gameReady = true;
                }
                if (!gameReady) {
                    textGameState.setText("Undecided game");
                    startNewGame.setText("Start new game");
                }
            }
        }
    }

    private void printWinner ( int player){
        textGameState.setText((player == 1) ? "Player one (yellow) wins" : "Player two (red) wins");
    }

    public void buttonClicked (View view){
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int child = 0; child < gridLayout.getChildCount(); child++) {
            ImageView children = (ImageView) gridLayout.getChildAt(child);
            children.setImageDrawable(null);
        }
        initPlayGround();
        player = 0;
        gameReady = true;
        startNewGame.setText("Reset game");
        textGameState.setText("Player one's turn");
    }

    // Animation Methods
    private void chipAnimation ( int player, ImageView image){
        image.setTranslationY(-1500);
        image.setImageResource((player == 0) ? R.drawable.redchip : R.drawable.yellowchip);
        image.animate().translationYBy(1500).setDuration(350);
    }

    // Methods for Playground
    private void initPlayGround () {
        Arrays.fill(playGround, -1);
    }

    public void logPlayGround (View view){
        Log.i("playGround", Arrays.toString(playGround));
    }
}