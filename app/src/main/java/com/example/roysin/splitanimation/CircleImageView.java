package com.example.roysin.splitanimation;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/9/8.
 */
public class CircleImageView extends ImageView{


    private static final String TAG = "CircleImageView";
    private Path mPath;
    private ImageView mOriginal;
    private long mProgress;

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
        if(mPath==null){
            mPath = new Path();
        }else {
            mPath.reset();
        }
        mProgress=100;
    }

    /**
     * 开始从矩形变为圆角矩形再到圆形
     * @param prog 最大值为100，表示完成圆形形变
     */
    public void transformTo(long prog){
        mProgress = prog;
        mProgress=mProgress>100?100:mProgress;
        mProgress=mProgress<0?0:mProgress;
        Log.d(TAG, "transformTo " + mProgress);
        postInvalidate();
    }

    private Path getRoundRectPath(){
        synchronized (mPath){

            long height = mOriginal.getHeight();
            long width = mOriginal.getWidth();
            float radius = height > width? width/2.0f:height/2.0f;

            mPath.reset();
            mPath.addRoundRect(new RectF(0,0,height,width),
                    radius*mProgress/100.f,
                    radius*mProgress/100.f,
                    Path.Direction.CW);
            Log.d(TAG, "getRoundRectPath " + radius*mProgress/100.f);
            return mPath;
        }


    }
    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw called ");
        canvas.clipPath(getRoundRectPath());
        super.onDraw(canvas);
    }

    public void setCircleRate(long rate){
       transformTo(rate);
    }

    public long getCircleRate(){
        return mProgress;
    }

}
