package com.example.roysin.splitanimation;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by Roysin on 15/9/8.
 */
public class SplitAnimation {

    private View mTarget;
    private Context mContext;
    private long mDuration ;
    private  Interpolator mInterpolator ;
    private View mCopy;

    public SplitAnimation(View view) {

        mTarget = view;
        if (mTarget instanceof CircleImageView) {

            mCopy = new CircleImageView((ImageView)mTarget);
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            parent.addView(mCopy);

        }else if (mTarget instanceof ImageView) {

            mCopy = new ImageView(mContext);
            Drawable src = ((ImageView) mTarget).getDrawable();
            mCopy.setBackground(src);
            ViewGroup.LayoutParams lp = mTarget.getLayoutParams();
            mCopy.setLayoutParams(lp);
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            parent.addView(mCopy);
        }



        mContext = view.getContext();
        mDuration =1500;
        mInterpolator = new LinearInterpolator();

    }


    public View getSplitedView(){
        return mCopy;
    }
    public void start() {

        if (mCopy != null) {
            TranslateAnimation copyTrani = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF,0.f,
                    Animation.RELATIVE_TO_SELF,1f,
                    Animation.RELATIVE_TO_SELF,0.f,
                    Animation.RELATIVE_TO_SELF,0.f
            );

            copyTrani.setFillAfter(true);
            copyTrani.setDuration(getDuration());

            ScaleAnimation copyScale = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

            copyScale.setFillAfter(true);
            copyScale.setDuration(getDuration());





            TranslateAnimation targetTrani = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF,0.f,
                    Animation.RELATIVE_TO_SELF,-1f,
                    Animation.RELATIVE_TO_SELF,0.f,
                    Animation.RELATIVE_TO_SELF,0.f
            );

            targetTrani.setFillAfter(true);
            targetTrani.setDuration(getDuration());

            ScaleAnimation targetScale = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

            targetScale.setFillAfter(true);
            targetScale.setDuration(getDuration());




            AnimationSet copySet = new AnimationSet(true);
            copySet.setFillAfter(true);
            copySet.addAnimation(copyTrani);
            copySet.addAnimation(copyScale);

            AnimationSet targetSet = new AnimationSet(true);
            targetSet.setFillAfter(true);
            targetSet.addAnimation(targetTrani);
            targetSet.addAnimation(targetScale);


            mCopy.startAnimation(copySet);
            mTarget.startAnimation(targetSet);

        }
    }

    public void setDuration(long time) {
        mDuration = time;

    }

    public long getDuration() {
        return mDuration;
    }

    public void setInterpolator( Interpolator i){
        mInterpolator = i;
    }
    public Interpolator getInterpolator() {
        return mInterpolator;
    }
}
