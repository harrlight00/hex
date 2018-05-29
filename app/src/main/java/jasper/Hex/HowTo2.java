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

public class HowTo2 extends AppCompatActivity {

    //Instantiations
    private Board game;
    private int[] colors = {R.color.White, R.color.Red,R.color.Blue,R.color.Yellow,R.color.Orange,R.color.Green,R.color.Purple};
    int s_count = 0; //Used to store win_count

    //Main method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to2);
        Intent intent = getIntent();
        s_count = intent.getIntExtra("count",0);

        initiateGame();
    }

    //Create game board
    private void initiateGame(){
        game = new Board(5);
        game.initiateArray();
    }

    //Button methods
    @SuppressLint("RtlHardcoded")
    public void howTo1(View view) {
        Intent intent = new Intent(this, HowTo1.class);
        intent.putExtra("count", s_count);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Slide(RIGHT));
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }
    public void howTo3(View view) {
        Intent intent = new Intent(this, HowTo3.class);
        intent.putExtra("count", s_count);
        startActivity(intent);
    }
    public void colorWheel(View view){
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
        textView = findViewById(R.id.howTo2);
        textView.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.howTo2_2);
        textView.setVisibility(View.INVISIBLE);

    }
    public void backWheel(View view){
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
        textView = findViewById(R.id.howTo2);
        textView.setVisibility(View.VISIBLE);
        textView = findViewById(R.id.howTo2_2);
        textView.setVisibility(View.VISIBLE);

    }

    //Set tint of, and update surrounding tint (Hex buttons)
    public void changeColorLT(View view){
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
    }
    public void changeColorRT(View view){
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
    }
    public void changeColorLC(View view){
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
    }
    public void changeColorC(View view){
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
    }
    public void changeColorRC(View view){
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
    }
    public void changeColorLB(View view){
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
    }
    public void changeColorRB(View view){
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
    }

}
