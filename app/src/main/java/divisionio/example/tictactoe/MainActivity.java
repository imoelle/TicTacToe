package divisionio.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static int[][] playGround = new int[3][3];
    private static int player = 0;

    public void isClicked(View view) throws InterruptedException {
        ImageView image = (ImageView) view;
        image.setTranslationY(-1500);
        image.setImageResource((player == 0) ? R.drawable.redchip : R.drawable.yellowchip);
        image.animate().translationYBy(1500).setDuration(300);
        if(player==0) player++;
        else player--;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPlayGround();
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