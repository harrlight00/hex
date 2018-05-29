package jasper.Hex;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.concurrent.ThreadLocalRandom;

import static android.view.Gravity.RIGHT;

public class Level extends AppCompatActivity {

    //Instantiations
    private Board game;
    private int[] colors = {R.color.White, R.color.Red,R.color.Blue,R.color.Yellow,R.color.Orange,R.color.Green,R.color.Purple};
    private int[] stints; //Starting game pattern for hint outlines
    private boolean gameOver = false; //Used to prevent input after the game is over
    Counter counter; //Used to count amount of levels finished

    //Main method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        //Get existing count and put onto screen
        Intent intent = getIntent();
        counter = new Counter(intent.getIntExtra("count",0));

        initiateGame();
        //timerTextView = (TextView) findViewById(R.id.timerTextView);
    }

    //Create game board
    private void initiateGame(){
        TextView textView;
        ImageView forward;
        Context context = this;
        game = new Board(5);
        game.initiateArray();

        //Set counter to win count
        textView = findViewById(R.id.count);
        CharSequence count = "#"+(counter.getCount());
        textView.setText(count);

        //set victory and next level arrow invisible
        textView = findViewById(R.id.victory);
        textView.setVisibility(View.INVISIBLE);
        forward = findViewById(R.id.ForArrow);
        forward.setVisibility(View.INVISIBLE);

        //set initial tints for hint outlines
        ImageView imageView;

        int[] ids = {R.id.sug0_1,R.id.sug1_0,R.id.sug1_1,R.id.sug1_2,R.id.sug1_3,R.id.sug2_1,
                R.id.sug2_3,R.id.sug3_0,R.id.sug3_1,R.id.sug3_2,R.id.sug3_3,R.id.sug4_1};
        stints = genStints();
        for(int i = 0;i<12;i++) {
            imageView = findViewById(ids[i]);
            imageView.setColorFilter(ContextCompat.getColor(context, colors[stints[i]]), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }
    private int[] genStints(){
        int[] temp = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] inputs = genInputs();
        temp[0] = hintColor(inputs[0],inputs[1]);
        temp[1] = hintColor(inputs[0],inputs[2]);
        temp[2] = hintColor(inputs[0],inputs[3]);
        temp[3] = hintColor(inputs[1],inputs[3]);
        temp[4] = hintColor(inputs[1],inputs[4]);
        temp[5] = hintColor(inputs[2],inputs[3]);
        temp[6] = hintColor(inputs[3],inputs[4]);
        temp[7] = hintColor(inputs[2],inputs[5]);
        temp[8] = hintColor(inputs[3],inputs[5]);
        temp[9] = hintColor(inputs[3],inputs[6]);
        temp[10] = hintColor(inputs[4],inputs[6]);
        temp[11] = hintColor(inputs[5],inputs[6]);
        return temp;
    } //Generates the hint outlines
    private int hintColor(int color1, int color2){
        int color = color1+color2;
        //fix error if color is purple or colors are the same
        if(color1==color2){color = color1;}
        else if(color==3&&(color1!=0&&color2!=0)){color = 6;}
        return color;
    } //Sets the color of the hint outline based on solution set
    private int[] genInputs(){
        int[] inputs = {0,0,0,0,0,0,0};
        int[] counts = {0,0,0,0};
        int hex_rand, min = 0;

        for(int i=0;i<7;i++){
            hex_rand = ThreadLocalRandom.current().nextInt(min, 4);
            if(hex_rand==0){
                min = 1;
                inputs[i] = hex_rand;
            }
            else{
                if(counts[hex_rand] >= 3){i--;}
                else{
                    counts[hex_rand]++;
                    inputs[i] = hex_rand;
                }
            }
        }
        return inputs;
    } //Generates solution

    //game button methods
    @SuppressLint("RtlHardcoded")
    public void backTitle(View view) {
        Intent intent = new Intent(this, TitleScreen.class);
        intent.putExtra("count",counter.getCount());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Slide(RIGHT));
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }
    public void nextLevel(View view) {
        Intent intent = new Intent(this, Level.class);
        intent.putExtra("count",counter.getCount());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }
    public void reset(View view){
        if(gameOver) {return;}
        Context context = this;
        ImageView imageView;
        int[] ids = {R.id.LTHex, R.id.RTHex, R.id.LCHex, R.id.CHex, R.id.RCHex, R.id.LBHex, R.id.RBHex,
            R.id.hex0_1, R.id.hex1_0, R.id.hex1_1, R.id.hex1_2, R.id.hex1_3, R.id.hex2_1,
            R.id.hex2_3, R.id.hex3_0, R.id.hex3_1, R.id.hex3_2, R.id.hex3_3, R.id.hex4_1};
        for(int i = 0;i<19;i++){
            imageView = findViewById(ids[i]);
            imageView.setColorFilter(ContextCompat.getColor(context, colors[0]), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        game.hexes[0][0].reset();
        game.hexes[0][1].reset();
        game.hexes[0][2].reset();
        game.hexes[1][0].reset();
        game.hexes[1][1].reset();
        game.hexes[1][2].reset();
        game.hexes[1][3].reset();
        game.hexes[2][0].reset();
        game.hexes[2][1].reset();
        game.hexes[2][2].reset();
        game.hexes[2][3].reset();
        game.hexes[2][4].reset();
        game.hexes[3][0].reset();
        game.hexes[3][1].reset();
        game.hexes[3][2].reset();
        game.hexes[3][3].reset();
        game.hexes[4][0].reset();
        game.hexes[4][1].reset();
        game.hexes[4][2].reset();

    }
    public void skip(View view){
        if(gameOver) {return;}
        Intent intent = new Intent(this, Level.class);
        intent.putExtra("count",counter.getCount());
        startActivity(intent);
    }
    public void colorWheel(View view){
        if(gameOver) {return;}
        ImageView imageView;
        TextView textView;
        Button button;
        ImageButton image_button;

        button = findViewById(R.id.skip);
        button.setVisibility(View.INVISIBLE);
        button = findViewById(R.id.reset);
        button.setVisibility(View.INVISIBLE);
        button = findViewById(R.id.outWheel);
        button.setVisibility(View.VISIBLE);
        image_button = findViewById(R.id.miniWheel);
        image_button.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.BackArrow);
        imageView.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.wheel);
        imageView.setVisibility(View.VISIBLE);
        textView = findViewById(R.id.count);
        textView.setVisibility(View.INVISIBLE);

    }
    public void backWheel(View view){
        if(gameOver) {return;}
        ImageView imageView;
        TextView textView;
        Button button;
        ImageButton image_button;

        button = findViewById(R.id.skip);
        button.setVisibility(View.VISIBLE);
        button = findViewById(R.id.reset);
        button.setVisibility(View.VISIBLE);
        image_button = findViewById(R.id.miniWheel);
        image_button.setVisibility(View.VISIBLE);
        button = findViewById(R.id.outWheel);
        button.setVisibility(View.INVISIBLE);
        image_button = findViewById(R.id.miniWheel);
        image_button.setVisibility(View.VISIBLE);
        imageView = findViewById(R.id.BackArrow);
        imageView.setVisibility(View.VISIBLE);
        imageView = findViewById(R.id.wheel);
        imageView.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.count);
        textView.setVisibility(View.VISIBLE);

    }

    //Set tint of, and update surrounding tint (Hex buttons)
    public void changeColorLT(View view){if(gameOver) {return;}
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
        //TextView textView;
        ImageView imageView;
        Button button;
        ImageButton image_button;

        //textView = findViewById(R.id.victory);
        //textView.setVisibility(View.VISIBLE);
        imageView = findViewById(R.id.ForArrow);
        imageView.setVisibility(View.VISIBLE);
        button = findViewById(R.id.skip);
        button.setVisibility(View.INVISIBLE);
        button = findViewById(R.id.reset);
        button.setVisibility(View.INVISIBLE);
        button = findViewById(R.id.next_level);
        button.setVisibility(View.VISIBLE);
        image_button = findViewById(R.id.miniWheel);
        image_button.setVisibility(View.INVISIBLE);
        gameOver = true;
        counter.upCount();

        String filename = "counter";
        int fileContents = counter.getCount();
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents);
            outputStream.close();
        } catch (Exception e) {e.printStackTrace();}
    }
    private boolean compare(int[] a1, int[] a2){
        if(a1.length!=a2.length){return false;}
        for(int i = 0; i < a1.length; i++){if(a1[i]!=a2[i]){return false;}}
        return true;
    }

}