package com.example.roysin.splitanimation;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * Created by Roysin on 15/9/8.
 */
public class SplitAnimation {

    private static final String TAG = "SplitAnimation";
    private View mTarget;
    private Context mContext;
    private long mTotalDuration;
    private  Interpolator mZoomInInterpolator;
    private View mCopy;
    private float zoomInDurationRatio;
    private Interpolator mZoomOutInterpolator;
    private float zoomInScaleRatio;
    private float zoomOutScaleRatio;

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
        mTotalDuration =1500;
        zoomInDurationRatio = 0.80f;
        zoomInScaleRatio = 0.25f;
        zoomOutScaleRatio = 0.40f;
        mZoomInInterpolator = new DecelerateInterpolator();
        mZoomOutInterpolator = new AccelerateInterpolator();
    }


    public View getSplitedView(){
        return mCopy;
    }
    public void start() {

        if (mCopy != null) {

            ScaleAnimation copyScale = new ScaleAnimation(
                    1.0f,
                    getZoomInScaleRatio(),
                    1.0f,
                    getZoomInScaleRatio(),
                    Animation.RELATIVE_TO_SELF,1f,Animation.RELATIVE_TO_SELF,0.5f);

            copyScale.setFillAfter(true);
            copyScale.setDuration(getZoomInDuration());
            copyScale.setInterpolator(getZoomInInterpolator());
            ScaleAnimation copyBounce = new ScaleAnimation(
                    1.0f,
                    getZoomOutScaleRatio(),
                    1.0f,
                    getZoomOutScaleRatio(),
                    Animation.RELATIVE_TO_SELF,1f,Animation.RELATIVE_TO_SELF,0.5f);

            copyBounce.setFillAfter(true);
            copyBounce.setDuration(getZoomOutDuration());
            copyBounce.setStartOffset(getZoomInDuration()+100);
            copyBounce.setInterpolator(getZoomOutInterpolator());

            ScaleAnimation targetScale = new ScaleAnimation(
                    1.0f,
                    getZoomInScaleRatio(),
                    1.0f,
                    getZoomInScaleRatio(),
                    Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0.5f);
            targetScale.setFillAfter(true);
            targetScale.setDuration(getZoomInDuration());
            targetScale.setInterpolator(getZoomInInterpolator());

            ScaleAnimation targetBounce = new ScaleAnimation(
                    1.0f,
                    getZoomOutScaleRatio(),
                    1.0f,
                    getZoomOutScaleRatio(),
                    Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0.5f);
            targetBounce.setFillAfter(true);
            targetBounce.setDuration(getZoomOutDuration());
            targetBounce.setStartOffset(getZoomInDuration()+100);
            targetBounce.setInterpolator(getZoomOutInterpolator());

            AnimationSet copySet = new AnimationSet(true);
            copySet.setFillAfter(true);
            copySet.addAnimation(copyScale);
            copySet.addAnimation(copyBounce);

            AnimationSet targetSet = new AnimationSet(true);
            targetSet.setFillAfter(true);
            targetSet.addAnimation(targetScale);
            targetSet.addAnimation(targetBounce);

            if((mCopy instanceof CircleImageView )&&(mTarget instanceof CircleImageView ))
            {
                Log.d(TAG, "transform now ");
                TransformAnimator copyTransform = new TransformAnimator((CircleImageView)mCopy);
                copyTransform.setDuration(getZoomInDuration());
                copyTransform.setInterpolator(getZoomInInterpolator());

                TransformAnimator targetTransform = new TransformAnimator((CircleImageView)mTarget);
                targetTransform.setDuration(getZoomInDuration());
                targetTransform.setInterpolator(getZoomInInterpolator());

                copyTransform.startAnimation();
                targetTransform.startAnimation();
            }

            mCopy.startAnimation(copySet);
            mTarget.startAnimation(targetSet);


        }
    }

    public void setTotalDuration(long time) {
        mTotalDuration = time;

    }

    public long getTotalDuration(){
        return mTotalDuration;
    }
    public void setZoonInDurationRatio(float ratio){
        zoomInDurationRatio=ratio;
    }
    public long getZoomInDuration() {
         return (long) (mTotalDuration * zoomInDurationRatio);
    }
    public float getZoomInDurationRatio() {
        return  zoomInDurationRatio;
    }

    public void setZoomInInterpolator(Interpolator i){
        mZoomInInterpolator = i;
    }
    public Interpolator getZoomInInterpolator() {
        return mZoomInInterpolator;
    }

    public void setZoomOutInterpolator(Interpolator i){
        mZoomOutInterpolator = i ;
    }
    public Interpolator getZoomOutInterpolator(){
        return mZoomOutInterpolator;
    }

    public long getZoomOutDuration() {
        return (long) (mTotalDuration *(1- zoomInDurationRatio));
    }
    public float getZoomInScaleRatio() {
        return zoomInScaleRatio;
    }

    public void setZoomInScaleRatio( float ratio) {
         zoomInScaleRatio=ratio;
    }

    public void setZoomOutToScaleRatio(float ratio) {
        zoomOutScaleRatio=ratio;
    }
    public float getZoomOutScaleRatio() {
        return  zoomOutScaleRatio*1.0f/zoomInScaleRatio;
    }
    public  float getZoomOutToScaleRatio(){
        return zoomOutScaleRatio;
    }
}
