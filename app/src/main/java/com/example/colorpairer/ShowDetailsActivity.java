package com.example.colorpairer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        Button comTitle = findViewById(R.id.comTitle);
        comTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowDetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        TextView text = findViewById(R.id.textView3);
        String toSet = "Complementary colors are two colors that are on opposite sides of the color wheel. " +
                "A pair of complementary colors is made up of one cool color and one warm color. " +
                "They create what is known as simultaneous contrast, the highest contrasts available on the color wheel." +
                "Simultaneous contrast occurs due to a natural illusion when you place two complementary colors next to one another. " +
                "Both colors will appear brighter and grab a viewer's attention." +
                "or example, sunsets with gradients from deep blues to bright oranges are more eye-catching. " +
                "Similarly, if your tube of red paint isn't bright enough, paint something green next to it.";
        text.setText(toSet);
        text.setTextColor(Color.BLACK);
    }
}
