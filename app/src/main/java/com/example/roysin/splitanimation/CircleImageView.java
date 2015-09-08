package com.example.roysin.splitanimation;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/9/8.
 */
public class CircleImageView extends ImageView{


//    private Paint mPaint;
    private Path mPath;
    private ImageView mOriginal;

    private CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(final ImageView v) {
        this(v.getContext());
        mOriginal = v;
        ViewGroup.LayoutParams lp = mOriginal.getLayoutParams();
        setImageDrawable(mOriginal.getDrawable());
        setLayoutParams(lp);
        init();
    }

    private void init(){
        if(mPath==null){//|| mPaint == null){
            mPath = new Path();
//            mPaint = new Paint();
        }else {
            mPath.reset();
//            mPaint.reset();
        }

//        mPaint.setAntiAlias(true);
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        long height = mOriginal.getHeight();
        long width = mOriginal.getWidth();
        float radius = height > width? width/2.0f:height/2.0f;
        mPath.addCircle(radius, radius, radius, Path.Direction.CW);

        canvas.clipPath(mPath);
        super.onDraw(canvas);
    }

}
