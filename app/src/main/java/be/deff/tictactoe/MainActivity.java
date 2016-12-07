package be.deff.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red

    private int activePlayer = 0;
    private boolean finished = false;

    //unplayed area
    private int[] gameState = {2,2,2,2,2,2,2,2,2};

    private int[][] winningPos = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    public void dropin(View view) {
        ImageView counter = (ImageView) view;

        int tappedarea = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedarea] == 2 && !finished) {

            gameState[tappedarea] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }


            counter.animate().translationYBy(1000f).setDuration(300);

            for (int[] winpos : winningPos) {
                if (gameState[winpos[0]] == gameState[winpos[1]] &&
                        gameState[winpos[1]] == gameState[winpos[2]] &&
                        gameState[winpos[0]] != 2) {

                    finished = true;

                    String winner = "Red";
                    if (gameState[winpos[0]] == 0) {
                        winner = "Yellow";
                    }

                    System.out.println(gameState[winpos[0]]);

                    TextView msg = (TextView) findViewById(R.id.winnermsg);
                    msg.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);


                } else {
                    finished=true;
                    for (int gameStateposition : gameState){
                        if (gameStateposition == 2) {
                            finished = false;
                        }
                    }
                    if(finished){
                        TextView msg = (TextView) findViewById(R.id.winnermsg);
                        msg.setText("Draw!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }

                }
            }
        }
    }
    public void playAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayOut);
        for (int i = 0;i<gridLayout.getChildCount(); i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
        finished = false;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
