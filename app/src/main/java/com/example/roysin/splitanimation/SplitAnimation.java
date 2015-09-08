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

    public SplitAnimation(View view) {

        mTarget = view;
        mContext = view.getContext();
        mDuration =1500;
        mInterpolator = new LinearInterpolator();
    }

    public void start() {

        View copy = null;
        if (mTarget instanceof ImageView) {
            copy = new ImageView(mContext);
            Drawable src = ((ImageView) mTarget).getDrawable();
            copy.setBackground(src);
        }
        if (copy != null) {
            ViewGroup.LayoutParams lp = mTarget.getLayoutParams();
            copy.setLayoutParams(lp);
            ViewGroup parent = (ViewGroup) mTarget.getParent();
            parent.addView(copy);


            TranslateAnimation copyTrani = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF,0.f,
                    Animation.ABSOLUTE,400f,
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
                    Animation.ABSOLUTE,-400.f,
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


            copy.startAnimation(copySet);
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
