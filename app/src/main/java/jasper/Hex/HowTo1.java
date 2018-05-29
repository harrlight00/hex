package jasper.Hex;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

import static android.view.Gravity.RIGHT;

public class HowTo1 extends AppCompatActivity {

    int s_count = 0;
    Hex[][] hexes = {{new Hex(), new Hex(), new Hex()},{}};
    private int[] colors = {R.color.White, R.color.Red,R.color.Blue,R.color.Yellow,R.color.Orange,R.color.Green,R.color.Purple};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to1);
        Intent intent = getIntent();
        s_count = intent.getIntExtra("count",0);
    }

    public void TitleScreen(View view){
        Intent intent = new Intent(this, TitleScreen.class);
        intent.putExtra("count", s_count);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Slide(RIGHT));
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }
    public void HowTo2(View view){
        Intent intent = new Intent(this, HowTo2.class);
        intent.putExtra("count", s_count);
        startActivity(intent);
    }

    public void changeColorL(View view){
        Context context = this;
        hexes[0][0].upColor();
        int s_col = hexes[0][0].getColor();

        ImageView imageView;
        imageView = findViewById(R.id.left_fill);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=hexes[0][1].changeColor(hexes,0,1);
        imageView = findViewById(R.id.mid_fill);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
    }
    public void changeColorR(View view){
        Context context = this;
        hexes[0][2].upColor();
        int s_col = hexes[0][2].getColor();

        ImageView imageView;
        imageView = findViewById(R.id.right_fill);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
        s_col=hexes[0][1].changeColor(hexes,0,1);
        imageView = findViewById(R.id.mid_fill);
        imageView.setColorFilter(ContextCompat.getColor(context, colors[s_col]), android.graphics.PorterDuff.Mode.SRC_IN);
    }
}
