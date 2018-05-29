package jasper.Hex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.FileInputStream;

public class TitleScreen extends AppCompatActivity {

    //Instantiations
    int s_count = 0;

    //Main method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
        Intent intent = getIntent();

        String filename = "counter";
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);
            s_count = inputStream.read();
            inputStream.close();
        } catch (Exception e) {s_count = intent.getIntExtra("count",1);}
    }

    //Button methods
    public void levelBrowser(View view) {
        Intent intent = new Intent(this, Level.class);
        intent.putExtra("count", s_count);
        startActivity(intent);
    }
    public void howTo1(View view){
        Intent intent = new Intent(this, HowTo1.class);
        intent.putExtra("count", s_count);
        startActivity(intent);
    }
}
