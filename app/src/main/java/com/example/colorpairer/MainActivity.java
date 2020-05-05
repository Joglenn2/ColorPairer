package com.example.colorpairer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;

import com.skydoves.colorpickerview.AlphaTileView;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;
import com.skydoves.colorpickerview.sliders.AlphaSlideBar;
import com.skydoves.colorpickerview.sliders.BrightnessSlideBar;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final ColorPickerView colorPickerView = findViewById(R.id.colorPickerView);
        final AlphaTileView alphaTileView = findViewById(R.id.alphaTileView);

        AlphaSlideBar alphaSlideBar = findViewById(R.id.alphaSlideBar);
        colorPickerView.attachAlphaSlider(alphaSlideBar);

        BrightnessSlideBar brightnessSlideBar = findViewById(R.id.brightnessSlide);
        colorPickerView.attachBrightnessSlider(brightnessSlideBar);

        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                alphaTileView.setPaintColor(colorPickerView.getColor());
                alphaTileView.setBackgroundColor(colorPickerView.getColor());
            }
        });
        final Button trySearching = findViewById(R.id.trySearching);
        trySearching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorPickerView.getColor() == Color.WHITE) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Hey!");
                    builder.setMessage("You have chosen the default color, do you wish to continue?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startSearching(colorPickerView);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.create();
                    builder.show();
                } else {
                    startSearching(colorPickerView);
                }
            }
        });
    }

    private void startSearching(ColorPickerView colorPickerView) {
        Intent intent = new Intent(MainActivity.this, ShowPairedColorActivity.class);
        intent.putExtra("pickedColor",colorPickerView.getColor());
        startActivity(intent);
        //finish();
    }

    public void imageFromGallery(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            ColorPickerView colorPickerView = findViewById(R.id.colorPickerView);
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Drawable drawable = new BitmapDrawable(getResources(), selectedImage);
                colorPickerView.setPaletteDrawable(drawable);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
