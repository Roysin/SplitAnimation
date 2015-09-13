package com.example.roysin.splitanimation;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/9/9.
 */
public class TransformAnimator {

    private View mTarget;
    private Context mContext;
    private int mProgress;
    private long mDuration;
    private Interpolator mInterpolator;

    public  TransformAnimator( View view){


        if(view instanceof CircleImageView){
            mTarget =(CircleImageView) view;
            mContext=view.getContext();

        }else if(view instanceof ImageView){
            mTarget =(ImageView) view;
            mContext=view.getContext();
        }

        init();
    }

    private void init() {
        mProgress = 0;
        mDuration = 1500;
        mInterpolator = new LinearInterpolator();
    }

    public void startAnimation(){
        if(mTarget instanceof CircleImageView){
            ValueAnimator animator =ValueAnimator.ofInt(0,100);
            animator.setInterpolator(getInterpolator());
            animator.setDuration(getDuration());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {

                    mProgress= (int) animation.getAnimatedValue();

                    Log.d("TransformAnitmation", "mProgress" + mProgress);

                    transformImageView(mProgress);
                }
            });
            animator.start();
        }
    }

    private void transformImageView(int prog) {
        if(mTarget instanceof CircleImageView){
            ((CircleImageView) mTarget).transformTo(prog);
        }
    }

    public void setInterpolator( Interpolator in){
        mInterpolator = in;
    }
    public void setDuration(long duration) {
         mDuration = duration;
    }
    public long getDuration() {
        return mDuration;
    }

    public TimeInterpolator getInterpolator() {
        return mInterpolator;
    }
}
