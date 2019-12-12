package com.example.helloworld2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int lvlBlur=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void invertColor(View v){

        ImageView img = (ImageView) findViewById(R.id.imgView);
        int redColor, greenColor, blueColor;
        Bitmap btbt = ((BitmapDrawable) img.getDrawable()).getBitmap();

        Bitmap bm = btbt.copy(btbt.getConfig(), true);
//        Log.d("MMIIIIIII","valeur"+bm.getColor(5,5));
//        bm.setPixel(0,0,Color.rgb(255,255,255));


        int[][][] pixels = new int[btbt.getWidth()][btbt.getHeight()][4];


        for (int i = 0; i < btbt.getWidth(); i++) {
            for (int j = 0; j < btbt.getHeight(); j++) {
                pixels[i][j][0] = Color.red(btbt.getPixel(i, j))^255;
                pixels[i][j][1] = Color.green(btbt.getPixel(i, j))^255;
                pixels[i][j][2] = Color.blue(btbt.getPixel(i, j))^255;
                pixels[i][j][3] = Color.alpha(btbt.getPixel(i, j));

                Log.d("MMIIIIIIIIIIII", "i:" + i + " j:" + j + " RGBA=" + pixels[i][j][0] + "," + pixels[i][j][1] + "," + pixels[i][j][2] + "," + pixels[i][j][3]);
                bm.setPixel(i, j, Color.rgb(pixels[i][j][0], pixels[i][j][1], pixels[i][j][2]));
                img.setImageBitmap(bm);

            }
        }
    }
    public void faceSwap(View v){

        ImageView img = (ImageView) findViewById(R.id.imgView);
        ImageView img2 = (ImageView) findViewById(R.id.imgView2);

        int redColor, greenColor, blueColor;
        Bitmap btbt = ((BitmapDrawable) img.getDrawable()).getBitmap();

        Bitmap bm = btbt.copy(btbt.getConfig(), true);

        Bitmap btbt2 = ((BitmapDrawable) img2.getDrawable()).getBitmap();

        Bitmap bm2 = btbt.copy(btbt.getConfig(), true);
//        Log.d("MMIIIIIII","valeur"+bm.getColor(5,5));
//        bm.setPixel(0,0,Color.rgb(255,255,255));


        int[][][] pixels = new int[btbt.getWidth()][btbt.getHeight()][4];


        for (int i = 0; i < btbt.getWidth(); i++) {
            for (int j = 0; j < btbt.getHeight(); j++) {
                pixels[i][j][0] = Color.red(btbt.getPixel(i, j));
                pixels[i][j][1] = Color.green(btbt.getPixel(i, j));
                pixels[i][j][2] = Color.blue(btbt.getPixel(i, j));
                pixels[i][j][3] = Color.alpha(btbt2.getPixel(i, j));

                Log.d("MMIIIIIIIIIIII", "i:" + i + " j:" + j + " RGBA=" + pixels[i][j][0] + "," + pixels[i][j][1] + "," + pixels[i][j][2] + "," + pixels[i][j][3]);
                bm.setPixel(i, j, Color.argb(pixels[i][j][3],pixels[i][j][0], pixels[i][j][1], pixels[i][j][2]));
                img.setImageBitmap(bm);

            }
        }
    }
    public void btnLvlBlur(View v){
        lvlBlur=1;
        Log.d("BRADA", "lvlBlur= "+lvlBlur);
    }

    public void btnLvlBlur2(View v){
        lvlBlur=2;
        Log.d("BRADA", "lvlBlur= "+lvlBlur);
    }

    public void opacity(View v){

        ImageView img = (ImageView) findViewById(R.id.imgView);
        Bitmap btbt = ((BitmapDrawable) img.getDrawable()).getBitmap();

        Bitmap bm = btbt.copy(btbt.getConfig(), true);
//        Log.d("MMIIIIIII","valeur"+bm.getColor(5,5));
//        bm.setPixel(0,0,Color.rgb(255,255,255));


        int[][][] pixels = new int[btbt.getWidth()][btbt.getHeight()][4];
        int pixBlur= 25;
        switch(lvlBlur) {
            case 1:
                pixBlur=9;
                break;
            case 2:
                pixBlur=25;
                break;
            default:
                // code block
        }
        Log.d("BRADA","ta valeur, brada, elle est parmi nos framana Man ! ->" + pixBlur);

        for (int i = lvlBlur; i < btbt.getWidth()-(lvlBlur*2); i++) {
            for (int j = lvlBlur; j < btbt.getHeight()-(lvlBlur*2); j++) {

                pixels[i][j][0] =  (
                        (Color.red(btbt.getPixel(i-1, j-1)))
                        +(Color.red(btbt.getPixel(i, j-1)))
                        +(Color.red(btbt.getPixel(i+1, j-1)))
                        +(Color.red(btbt.getPixel(i-1, j)))
                        +(Color.red(btbt.getPixel(i, j)))
                        +(Color.red(btbt.getPixel(i+1, j)))
                        +(Color.red(btbt.getPixel(i-1, j+1)))
                        +(Color.red(btbt.getPixel(i, j+1)))
                        +(Color.red(btbt.getPixel(i+1, j+1)))
                )/9;
                pixels[i][j][1] = (
                        +(Color.green(btbt.getPixel(i-1, j-1)))
                        +(Color.green(btbt.getPixel(i, j-1)))
                        +(Color.green(btbt.getPixel(i+1, j-1)))
                        +(Color.green(btbt.getPixel(i-1, j)))
                        +(Color.green(btbt.getPixel(i, j)))
                        +(Color.green(btbt.getPixel(i+1, j)))
                        +(Color.green(btbt.getPixel(i-1, j+1)))
                        +(Color.green(btbt.getPixel(i, j+1)))
                        +(Color.green(btbt.getPixel(i+1, j+1)))
                )/9;
                pixels[i][j][2] = (
                        +(Color.blue(btbt.getPixel(i-1, j-1)))
                        +(Color.blue(btbt.getPixel(i, j-1)))
                        +(Color.blue(btbt.getPixel(i+1, j-1)))
                        +(Color.blue(btbt.getPixel(i-1, j)))
                        +(Color.blue(btbt.getPixel(i, j)))
                        +(Color.blue(btbt.getPixel(i+1, j)))
                        +(Color.blue(btbt.getPixel(i-1, j+1)))
                        +(Color.blue(btbt.getPixel(i, j+1)))
                        +(Color.blue(btbt.getPixel(i+1, j+1)))
                )/9;
//                pixels[i][j][3] = 255;


                if(pixBlur >= 25){
                    pixels[i][j][0] =  (
                                    pixels[i][j][0]+
                                            (Color.red(btbt.getPixel(i - 2, j - 2)))
                                    +(Color.red(btbt.getPixel(i - 1, j - 2)))
                                    + (Color.red(btbt.getPixel(i, j - 2)))
                                    + (Color.red(btbt.getPixel(i + 1, j - 2)))
                                    + (Color.red(btbt.getPixel(i + 2, j - 2)))
                                    + (Color.red(btbt.getPixel(i - 2, j - 1)))
                                    + (Color.red(btbt.getPixel(i + 2, j - 1)))
                                    + (Color.red(btbt.getPixel(i - 2, j)))
                                    + (Color.red(btbt.getPixel(i + 2, j)))
                                    + (Color.red(btbt.getPixel(i - 2, j + 1)))
                                    + (Color.red(btbt.getPixel(i + 2, j + 1)))
                                    + (Color.red(btbt.getPixel(i - 2, j + 2)))
                                    + (Color.red(btbt.getPixel(i + 2, j + 2)))
                                    + (Color.red(btbt.getPixel(i - 1, j + 2)))
                                    + (Color.red(btbt.getPixel(i + 1, j + 2)))
                                    + (Color.red(btbt.getPixel(i, j + 2)))
                    )/17;

                    pixels[i][j][1] = ( pixels[i][j][1]

                                    +(Color.green(btbt.getPixel(i-2, j-2)))
                                    +(Color.green(btbt.getPixel(i-1, j-2)))
                                    +(Color.green(btbt.getPixel(i, j-2)))
                                    +(Color.green(btbt.getPixel(i+1, j-2)))
                                    +(Color.green(btbt.getPixel(i+2, j-2)))
                                    +(Color.green(btbt.getPixel(i-2, j-1)))
                                    +(Color.green(btbt.getPixel(i+2, j-1)))
                                    +(Color.green(btbt.getPixel(i-2, j)))
                                    +(Color.green(btbt.getPixel(i+2, j)))
                                    +(Color.green(btbt.getPixel(i-2, j+1)))
                                    +(Color.green(btbt.getPixel(i+2, j+1)))
                                    +(Color.green(btbt.getPixel(i-2, j+2)))
                                    +(Color.green(btbt.getPixel(i+2, j+2)))
                                    +(Color.green(btbt.getPixel(i-1, j+2)))
                                    +(Color.green(btbt.getPixel(i+1, j+2)))
                                    +(Color.green(btbt.getPixel(i, j+2)))
                    )/17;

                    pixels[i][j][2] = (pixels[i][j][2]
                                    +(Color.blue(btbt.getPixel(i-2, j-2)))
                                    +(Color.blue(btbt.getPixel(i-1, j-2)))
                                    +(Color.blue(btbt.getPixel(i, j-2)))
                                    +(Color.blue(btbt.getPixel(i+1, j-2)))
                                    +(Color.blue(btbt.getPixel(i+2, j-2)))
                                    +(Color.blue(btbt.getPixel(i-2, j-1)))
                                    +(Color.blue(btbt.getPixel(i+2, j-1)))
                                    +(Color.blue(btbt.getPixel(i-2, j)))
                                    +(Color.blue(btbt.getPixel(i+2, j)))
                                    +(Color.blue(btbt.getPixel(i-2, j+1)))
                                    +(Color.blue(btbt.getPixel(i+2, j+1)))
                                    +(Color.blue(btbt.getPixel(i-2, j+2)))
                                    +(Color.blue(btbt.getPixel(i+2, j+2)))
                                    +(Color.blue(btbt.getPixel(i-1, j+2)))
                                    +(Color.blue(btbt.getPixel(i+1, j+2)))
                                    +(Color.blue(btbt.getPixel(i, j+2)))
                    )/17;
                }


//                Log.d("MMIIIIIIIIIIII", "i:" + i + " j:" + j + " RGBA=" + pixels[i][j][0] + "," + pixels[i][j][1] + "," + pixels[i][j][2] + "," + pixels[i][j][3]);
                bm.setPixel(i, j, Color.rgb(pixels[i][j][0], pixels[i][j][1], pixels[i][j][2]));
                img.setImageBitmap(bm);

            }
        }


    }

    public void grayscale(View v){

        ImageView img = (ImageView) findViewById(R.id.imgView);
        int redColor, greenColor, blueColor;
        Bitmap btbt = ((BitmapDrawable) img.getDrawable()).getBitmap();

        Bitmap bm = btbt.copy(btbt.getConfig(), true);
//        Log.d("MMIIIIIII","valeur"+bm.getColor(5,5));
//        bm.setPixel(0,0,Color.rgb(255,255,255));


        int[][][] pixels = new int[btbt.getWidth()][btbt.getHeight()][4];


        for (int i = 0; i < btbt.getWidth(); i++) {
            for (int j = 0; j < btbt.getHeight(); j++) {

                pixels[i][j][0] = (30*(Color.red(btbt.getPixel(i, j)))+(59*Color.green(btbt.getPixel(i, j)))+11*(Color.blue(btbt.getPixel(i, j))))/100;
                pixels[i][j][1] = pixels[i][j][0];
                pixels[i][j][2] = pixels[i][j][0];
                pixels[i][j][3] = Color.alpha(btbt.getPixel(i, j));

                Log.d("MMIIIIIIIIIIII", "i:" + i + " j:" + j + " RGBA=" + pixels[i][j][0] + "," + pixels[i][j][1] + "," + pixels[i][j][2] + "," + pixels[i][j][3]);
                bm.setPixel(i, j, Color.rgb(pixels[i][j][0], pixels[i][j][1], pixels[i][j][2]));
                img.setImageBitmap(bm);

            }
        }
    }
    public void grayscaleRed(View v){

        ImageView img = (ImageView) findViewById(R.id.imgView);
        int redColor, greenColor, blueColor;
        Bitmap btbt = ((BitmapDrawable) img.getDrawable()).getBitmap();

        Bitmap bm = btbt.copy(btbt.getConfig(), true);
//        Log.d("MMIIIIIII","valeur"+bm.getColor(5,5));
//        bm.setPixel(0,0,Color.rgb(255,255,255));


        int[][][] pixels = new int[btbt.getWidth()][btbt.getHeight()][4];


        for (int i = 0; i < btbt.getWidth(); i++) {
            for (int j = 0; j < btbt.getHeight(); j++) {
                pixels[i][j][0] = (30*(Color.red(btbt.getPixel(i, j)))+(59*Color.green(btbt.getPixel(i, j)))+11*(Color.blue(btbt.getPixel(i, j))))/100;
                pixels[i][j][1] =  pixels[i][j][0];
                pixels[i][j][2] = 255;
                pixels[i][j][3] = Color.alpha(btbt.getPixel(i, j));

                Log.d("MMIIIIIIIIIIII", "i:" + i + " j:" + j + " RGBA=" + pixels[i][j][0] + "," + pixels[i][j][1] + "," + pixels[i][j][2] + "," + pixels[i][j][3]);
                bm.setPixel(i, j, Color.rgb(pixels[i][j][0], pixels[i][j][1], pixels[i][j][2]));
                img.setImageBitmap(bm);

            }
        }
    }

}
