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
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/9/8.
 */
public class CircleImageView extends ImageView{


    private Paint mPaint;
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
        if(mPath==null|| mPaint == null){
            mPath = new Path();
            mPaint = new Paint();
        }else {
            mPath.reset();
            mPaint.reset();
        }

        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint.setXfermode(xfermode);
        mPaint.setFilterBitmap(false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Canvas xfcanvas= new Canvas();
        long height = mOriginal.getHeight();
        long width = mOriginal.getWidth();
        float radius = height > width? width/2.0f:height/2.0f;
        xfcanvas.drawCircle(radius,radius,radius,mPaint);
//        xfcanvas.drawBitmap(getBitmap(),0,0,mPaint);



    }

    public Bitmap getBitmap() {
        Bitmap b= Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas =new Canvas(b);
        long height = mOriginal.getHeight();
        long width = mOriginal.getWidth();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        float radius = height > width? width/2.0f:height/2.0f;
        canvas.drawCircle(radius,radius,radius,paint);
        return  b;

    }
}
