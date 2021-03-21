package divisionio.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static int[][] playGround = new int[3][3];
    private static int player = 0;

    public void isClicked(View view) throws InterruptedException {
        ImageView image = (ImageView) view;
        int[] location = getLocationFromString(image);

        if (locationIsEmpty(location[0], location[1])) {
            if (player == 0) {
                setGameIndex(location[0], location[1]);
                player = 1;
            } else {
                setGameIndex(location[0], location[1]);
                player = 0;
            }
            chipAnimation(player, image);
            setPlayersTurn(player);
        } else {
            Toast.makeText(this, "This position is occupied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPlayGround();
    }

    // Tag Management
    private int[] getLocationFromString(ImageView view) {
        String[] locationString = view.getTag().toString().split(":");
        return getCoordinates(locationString);
    }

    private int[] getCoordinates(String[] locationAsString) {
        int[] locationAsInt = new int[locationAsString.length];

        for (int i = 0; i < locationAsInt.length; i++)
            locationAsInt[i] = Integer.parseInt(locationAsString[i]);

        return locationAsInt;
    }

    // Game Management
    private void setPlayersTurn(int player) {
        TextView playersTurn = (TextView) findViewById(R.id.txtViewGameState);
        playersTurn.setText((player != 0) ? "It is player two's turn" : "It is player one's turn");
    }

    private boolean locationIsEmpty(int x, int y) {
        return playGround[x][y] < 0;
    }

    private void setGameIndex(int row, int column) {
        playGround[row][column] = player;
    }

    // Animation Methods
    private void chipAnimation(int player, ImageView image) {
        image.setTranslationY(-1500);
        image.setImageResource((player == 0) ? R.drawable.redchip : R.drawable.yellowchip);
        image.animate().translationYBy(1500).setDuration(300);
    }

    // Methods for Playground
    private void initPlayGround() {
        for (int row = 0; row < playGround.length; row++) {
            Arrays.fill(playGround[row], -1);
        }
    }

    public void logPlayGround(View view) {
        for (int row = 0; row < playGround.length; row++) {
            for (int col = 0; col < playGround[row].length; col++)
                Log.i("PlayGround", "(" + String.valueOf(row) +
                        ", " + String.valueOf(col) + ") = " +
                        String.valueOf(playGround[row][col]));
        }
    }
}