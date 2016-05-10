package com.example.tsuruda_tomohiro.picturequiz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tsuruda_tomohiro on 2016/05/01.
 */
public class PointView extends View {
    long start_time = 0;
    Paint p;
    ArrayList<Ball> bl;
    public PointView(Context context){
        super(context);
        bl = new ArrayList<Ball>();
        p = new Paint();
    }

    public boolean onTouchEvent(MotionEvent e){
        Date dt = new Date();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                start_time = dt.getTime()/10;
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                Ball b = new Ball();
                b.x = e.getX();
                b.y = e.getY();
                //押してから手を離した時間でボールの大きさを決める
                b.size = dt.getTime()/10 - start_time;
                bl.add(b);
                break;
        }

        //描画
        this.invalidate();

        return true;
    }

    protected void onDraw(Canvas cs){
        super.onDraw(cs);
        Canvas temp;
        Paint paint;
        Paint transparentPaint;
        //Bitmapの作成
        Bitmap bitmap = Bitmap.createBitmap(cs.getWidth(), cs.getHeight(), Bitmap.Config.ARGB_8888);
        temp = new Canvas(bitmap);
        //黒色で塗りつぶし
        paint = new Paint();
        paint.setColor(Color.BLACK);
        temp.drawRect(0, 0, temp.getWidth(), temp.getHeight(), paint);
        //ボールの描画(背景が見えるようになる)
        for(int i = 0;i < bl.size(); i++) {
            Ball b = bl.get(i);
            transparentPaint = new Paint();
            transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
            transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            temp.drawCircle(b.x, b.y, (int)b.size, transparentPaint);
        }
        cs.drawBitmap(bitmap, 0, 0, p);
    }

    public class Ball{
        float x;
        float y;
        long size;
    }
}
