package com.example.colorpairer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ShowPairedColorActivity extends AppCompatActivity {
    private float[] colorHSL = new float[3];
    /** The alpha value of default color cube. **/
    private final int alpha1 = 200;
    /** The alpha value of clicked color cube. **/
    private final int alpha2 = 90;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final int colorARGB = intent.getIntExtra("pickedColor", 0);

        setContentView(R.layout.activity_show_paired_color);
        LinearLayout complementary = findViewById(R.id.comlementary);
        LinearLayout analogous = findViewById(R.id.analogous);
        LinearLayout triadic = findViewById(R.id.triadic);
        LinearLayout splitComplementary = findViewById(R.id.splitComplementary);
        LinearLayout monochromatic = findViewById(R.id.tetradic);

        ColorUtils.colorToHSL(colorARGB, colorHSL);
        setButton(complementary, complementaryGenerator(colorARGB));
        setButton(analogous, analogousGenerator(colorARGB));
        setButton(triadic, triadicGenerator(colorARGB));
        setButton(splitComplementary, splitComplementaryGenerator(colorARGB));
        setButton(monochromatic, tetradicGenerator(colorARGB));
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
    private int[] complementaryGenerator(int colorARGB) {
        int complementaryARGB = ColorUtils.HSLToColor(new float[] {(colorHSL[0] + 180) % 360, colorHSL[1], colorHSL[2]});
        return new int[] {colorARGB, complementaryARGB};
    }

    private int[] analogousGenerator(int colorARGB) {
        int analogousGreater = ColorUtils.HSLToColor(new float[] {(colorHSL[0] + 30) % 360, colorHSL[1], colorHSL[2]});
        int analogousLesser = ColorUtils.HSLToColor(new float[] {(colorHSL[0] - 30) % 360, colorHSL[1], colorHSL[2]});
        return new int[] {analogousLesser, colorARGB, analogousGreater};
    }

    private int[] triadicGenerator(int colorARGB) {
        int triadicGreater = ColorUtils.HSLToColor(new float[] {(colorHSL[0] + 120) % 360, colorHSL[1], colorHSL[2]});
        int triadicLesser = ColorUtils.HSLToColor(new float[] {(colorHSL[0] - 120) % 360, colorHSL[1], colorHSL[2]});
        return new int[] {triadicLesser, colorARGB, triadicGreater};
    }

    private int[] splitComplementaryGenerator(int colorARGB) {
        int splitGreater = ColorUtils.HSLToColor(new float[] {(colorHSL[0] + 210) % 360, colorHSL[1], colorHSL[2]});
        int splitLesser = ColorUtils.HSLToColor(new float[] {(colorHSL[0] + 150) % 360, colorHSL[1], colorHSL[2]});
        return new int[] {splitLesser, colorARGB, splitGreater};
    }

    private int[] tetradicGenerator(int colorARGB) {
        int tetradicFirst = ColorUtils.HSLToColor(new float[] {(colorHSL[0] + 90) % 360, colorHSL[1], colorHSL[2]});
        int tetradicSecond = ColorUtils.HSLToColor(new float[] {(colorHSL[0] + 180) % 360, colorHSL[1], colorHSL[2]});
        int tetradicThird = ColorUtils.HSLToColor(new float[] {(colorHSL[0] + 270) % 360, colorHSL[1], colorHSL[2]});
        return new int[] {colorARGB, tetradicFirst, tetradicSecond, tetradicThird};
    }
    public void onComplementaryClick(View view) {
        Intent intent = new Intent(ShowPairedColorActivity.this, ShowDetailsActivity.class);
        startActivity(intent);
    }
}
