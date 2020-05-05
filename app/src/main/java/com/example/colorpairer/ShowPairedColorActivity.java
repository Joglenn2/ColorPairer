package com.example.colorpairer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ShowPairedColorActivity extends AppCompatActivity {
    /** The alpha value of default color cube. **/
    private final int alpha1 = 200;
    /** The alpha value of clicked color cube. **/
    private final int alpha2 = 90;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_paired_color);
        LinearLayout complementary = findViewById(R.id.comlementary);
        LinearLayout analagous = findViewById(R.id.analogous);
        LinearLayout triadic = findViewById(R.id.triadic);
        LinearLayout splitComplementary = findViewById(R.id.splitComplementary);
        int[] colors = new int[] {Color.WHITE, Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE};
        setButton(complementary, colors);
        setButton(analagous, colors);
        setButton(triadic, colors);
        setButton(splitComplementary, colors);

    }

    private void setButton(LinearLayout layout, final int[] colors) {
        if (layout.getChildCount() != colors.length) {
            return;
        }
        for (int i = 0; i < layout.getChildCount(); i++) {
            final Button button = (Button) layout.getChildAt(i);
            final int color = colors[i];
            button.setBackgroundColor(color);
            button.getBackground().setAlpha(alpha1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (button.getTag() == null || button.getTag().equals(0)) {
                        button.setText("" + color);
                        button.getBackground().setAlpha(alpha2);
                        button.setTag(1);
                    } else {
                        button.setText("");
                        button.getBackground().setAlpha(alpha1);
                        button.setTag(0);
                    }
                }
            });
        }
    }
}
