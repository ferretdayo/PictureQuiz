package com.example.tsuruda_tomohiro.picturequiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tsuruda_tomohiro.picturequiz.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PictureActivity extends AppCompatActivity {
    PointView pv;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        InputStream input = null;
        try {
            input = this.openFileInput("image.png");
        } catch (FileNotFoundException e) {
            // エラー処理
        }
        Bitmap image = BitmapFactory.decodeStream(input);
        //iv = new ImageView(this);
        //iv.setImageBitmap(image);

        LinearLayout ll = new LinearLayout(this);
        BitmapDrawable background = new BitmapDrawable(image);
        ll.setBackgroundDrawable(background);
        //ll.setBackgroundResource(R.drawable.kenny);
        setContentView(ll);
        pv = new PointView(this);
        ll.addView(pv);
        //ll.addView(iv);
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
}
