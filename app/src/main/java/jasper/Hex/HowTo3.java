package jasper.Hex;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.Gravity.RIGHT;

public class HowTo3 extends AppCompatActivity {

    //Instantiations
    private Board game;
    private int[] colors = {R.color.White, R.color.Red,R.color.Blue,R.color.Yellow,R.color.Orange,R.color.Green,R.color.Purple};
    private int[] stints;
    private int s_count; //win count
    private boolean gameOver = false; //used to prevent input after game is over

    //Main method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to3);
        Intent intent = getIntent();
        s_count = intent.getIntExtra("count",0);

        initiateGame();
    }

    //Create game board
    private void initiateGame(){
        TextView textView;
        ImageView forward;
        Context context = this;
        game = new Board(5);
        game.initiateArray();

        //set victory and next level arrow invisible
        textView = findViewById(R.id.victory);
        textView.setVisibility(View.INVISIBLE);
        forward = findViewById(R.id.ForArrow);
        forward.setVisibility(View.VISIBLE);

        //set initial tints for hint outline
        ImageView imageView;

        //Sets clue outlines
        int[] ids = {R.id.sug0_1,R.id.sug1_0,R.id.sug1_1,R.id.sug1_2,R.id.sug1_3,R.id.sug2_1,
                R.id.sug2_3,R.id.sug3_0,R.id.sug3_1,R.id.sug3_2,R.id.sug3_3,R.id.sug4_1};
        stints = new int[] {3,5,3,3,5,2,2,6,1,1,6,1};
        for(int i = 0;i<12;i++) {
            imageView = findViewById(ids[i]);
            imageView.setColorFilter(ContextCompat.getColor(context, colors[stints[i]]), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }

    //Button methods
    @SuppressLint("RtlHardcoded")
    public void howTo1(View view) {
        Intent intent = new Intent(this, HowTo2.class);
        intent.putExtra("count", s_count);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Slide(RIGHT));
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }
    public void titleScreen(View view) {
        Intent intent = new Intent(this, TitleScreen.class);
        intent.putExtra("count", s_count);
        startActivity(intent);
    }
    public void colorWheel(View view){
        if(gameOver) {return;}
        ImageView imageView;
        TextView textView;
        Button button;
        ImageButton image_button;

        image_button = findViewById(R.id.miniWheel);
        image_button.setVisibility(View.INVISIBLE);
        button = findViewById(R.id.outWheel);
        button.setVisibility(View.VISIBLE);
        imageView = findViewById(R.id.BackArrow);
        imageView.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.wheel);
        imageView.setVisibility(View.VISIBLE);
        textView = findViewById(R.id.howTo3);
        textView.setVisibility(View.INVISIBLE);

    }
    public void backWheel(View view){
        if(gameOver) {return;}
        ImageView imageView;
        TextView textView;
        Button button;
        ImageButton image_button;

        image_button = findViewById(R.id.miniWheel);
        image_button.setVisibility(View.VISIBLE);
        button = findViewById(R.id.outWheel);
        button.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.BackArrow);
        imageView.setVisibility(View.VISIBLE);
        imageView = findViewById(R.id.wheel);
        imageView.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.howTo3);
        textView.setVisibility(View.VISIBLE);

    }

    //Set tint of, and update surrounding tint (Hex buttons)
    public void changeColorLT(View view){
        if(gameOver) {return;}
        Context context = this;
        game.hexes[0][0].upColor();
        int s_col = game.hexes[0][0].getColor();

        ImageView imageView;
        imageView = findViewById(R.id.LTHex);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[0][1].changeColor(game.hexes,0,1);
        imageView = findViewById(R.id.hex0_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[1][0].changeColor(game.hexes,1,0);
        imageView = findViewById(R.id.hex1_0);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[1][1].changeColor(game.hexes,1,1);
        imageView = findViewById(R.id.hex1_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);

        checkVictory();
    }
    public void changeColorRT(View view){
        if(gameOver) {return;}
        Context context = this;
        game.hexes[0][2].upColor();
        int s_col = game.hexes[0][2].getColor();

        ImageView imageView;
        imageView = findViewById(R.id.RTHex);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[0][1].changeColor(game.hexes,0,1);
        imageView = findViewById(R.id.hex0_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[1][2].changeColor(game.hexes,1,2);
        imageView = findViewById(R.id.hex1_2);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[1][3].changeColor(game.hexes,1,3);
        imageView = findViewById(R.id.hex1_3);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);

        checkVictory();
    }
    public void changeColorLC(View view){
        if(gameOver) {return;}
        Context context = this;
        game.hexes[2][0].upColor();
        int s_col = game.hexes[2][0].getColor();

        ImageView imageView;
        imageView = findViewById(R.id.LCHex);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[1][0].changeColor(game.hexes,1,0);
        imageView = findViewById(R.id.hex1_0);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[2][1].changeColor(game.hexes,2,1);
        imageView = findViewById(R.id.hex2_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[3][0].changeColor(game.hexes,3,0);
        imageView = findViewById(R.id.hex3_0);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);

        checkVictory();
    }
    public void changeColorC(View view){
        if(gameOver) {return;}
        Context context = this;
        game.hexes[2][2].upColor();
        int s_col = game.hexes[2][2].getColor();

        ImageView imageView;
        imageView = findViewById(R.id.CHex);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[2][1].changeColor(game.hexes,2,1);
        imageView = findViewById(R.id.hex2_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[1][1].changeColor(game.hexes,1,1);
        imageView = findViewById(R.id.hex1_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[1][2].changeColor(game.hexes,1,2);
        imageView = findViewById(R.id.hex1_2);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[2][3].changeColor(game.hexes,2,3);
        imageView = findViewById(R.id.hex2_3);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[3][1].changeColor(game.hexes,3,1);
        imageView = findViewById(R.id.hex3_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[3][2].changeColor(game.hexes,3,2);
        imageView = findViewById(R.id.hex3_2);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);

        checkVictory();
    }
    public void changeColorRC(View view){
        if(gameOver) {return;}
        Context context = this;
        game.hexes[2][4].upColor();
        int s_col = game.hexes[2][4].getColor();

        ImageView imageView;
        imageView = findViewById(R.id.RCHex);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[2][3].changeColor(game.hexes,2,3);
        imageView = findViewById(R.id.hex2_3);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[1][3].changeColor(game.hexes,1,3);
        imageView = findViewById(R.id.hex1_3);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[3][3].changeColor(game.hexes,3,3);
        imageView = findViewById(R.id.hex3_3);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);

        checkVictory();
    }
    public void changeColorLB(View view){
        if(gameOver) {return;}
        Context context = this;
        game.hexes[4][0].upColor();
        int s_col = game.hexes[4][0].getColor();

        ImageView imageView;
        imageView = findViewById(R.id.LBHex);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[3][0].changeColor(game.hexes,3,0);
        imageView = findViewById(R.id.hex3_0);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[3][1].changeColor(game.hexes,3,1);
        imageView = findViewById(R.id.hex3_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[4][1].changeColor(game.hexes,4,1);
        imageView = findViewById(R.id.hex4_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);

        checkVictory();
    }
    public void changeColorRB(View view){
        if(gameOver) {return;}
        Context context = this;
        game.hexes[4][2].upColor();
        int s_col = game.hexes[4][2].getColor();

        ImageView imageView;
        imageView = findViewById(R.id.RBHex);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[3][3].changeColor(game.hexes,3,3);
        imageView = findViewById(R.id.hex3_3);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[3][2].changeColor(game.hexes,3,2);
        imageView = findViewById(R.id.hex3_2);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=game.hexes[4][1].changeColor(game.hexes,4,1);
        imageView = findViewById(R.id.hex4_1);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);

        checkVictory();
    }

    //Victory checks
    private void checkVictory(){
        int[] current = {0,0,0,0,0,0,0,0,0,0,0,0};
        current[0] = game.hexes[0][1].getColor();
        current[1] = game.hexes[1][0].getColor();
        current[2] = game.hexes[1][1].getColor();
        current[3] = game.hexes[1][2].getColor();
        current[4] = game.hexes[1][3].getColor();
        current[5] = game.hexes[2][1].getColor();
        current[6] = game.hexes[2][3].getColor();
        current[7] = game.hexes[3][0].getColor();
        current[8] = game.hexes[3][1].getColor();
        current[9] = game.hexes[3][2].getColor();
        current[10] = game.hexes[3][3].getColor();
        current[11] = game.hexes[4][1].getColor();

        if(compare(stints,current)){setVictory();}
    }
    private void setVictory(){
        TextView textView;
        ImageView imageView;
        ImageButton image_button;

        textView = findViewById(R.id.howTo3);
        textView.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.victory);
        textView.setVisibility(View.VISIBLE);
        imageView = findViewById(R.id.ForArrow);
        imageView.setVisibility(View.VISIBLE);
        image_button = findViewById(R.id.miniWheel);
        image_button.setVisibility(View.INVISIBLE);
        gameOver = true;
    }
    private boolean compare(int[] a1, int[] a2){
        if(a1.length!=a2.length){return false;}
        for(int i = 0; i < a1.length; i++){if(a1[i]!=a2[i]){return false;}}
        return true;
    }

}
