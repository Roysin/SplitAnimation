package com.example.roysin.splitanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private ImageView target;
    private Button btnPlay;
    private ImageView splitView;
    private CircleImageView circle;
    private SplitAnimation mSplitAnimation;
    private TextView tvTotalDuration;
    private TextView tvZoomInDratio;
    private TextView tvZoomOutSRatio;
    private TextView tvZoomInSratio;
    private SeekBar sbZoominD,sbZoominS,sbTotalD,sbZoomOutS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        target = (ImageView) findViewById(R.id.target);
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(this);

        sbZoominD = (SeekBar) findViewById(R.id.sb_zoomin_duration_ratio);
        sbZoominS = (SeekBar) findViewById(R.id.sb_zoomin_scale_ratio);
        sbTotalD = (SeekBar) findViewById(R.id.sb_total_duration);
        sbZoomOutS = (SeekBar) findViewById(R.id.sb_zoomout_scale_ratio);
        sbZoomOutS.setOnSeekBarChangeListener(this);
        sbTotalD.setOnSeekBarChangeListener(this);
        sbZoominD.setOnSeekBarChangeListener(this);
        sbZoominS.setOnSeekBarChangeListener(this);

        tvTotalDuration = (TextView) findViewById(R.id.tv_total_duration);
        tvZoomInDratio= (TextView) findViewById(R.id.tv_zoomin_d_ration);
        tvZoomOutSRatio= (TextView) findViewById(R.id.tv_zoomout_scale_ratio);
        tvZoomInSratio= (TextView) findViewById(R.id.tv_zoomin_s_ratio);

    }

    @Override
    public void onClick(View v) {

        if(mSplitAnimation == null) {
            ViewGroup vgroup = (ViewGroup) target.getParent();
            circle = new CircleImageView(target);
            target.setVisibility(View.INVISIBLE);
            vgroup.addView(circle);
            mSplitAnimation = new SplitAnimation(circle);
            splitView = (ImageView) mSplitAnimation.getSplitedView();
            mSplitAnimation.setTotalDuration(400);

            setSeekBarAndValue();



        }

            mSplitAnimation.start();
//        ViewGroup vgroup = (ViewGroup) target.getParent();
//        vgroup.removeView(splitView);
//        vgroup.removeView(circle);

    }

    public void setSeekBarAndValue(){
        if(mSplitAnimation == null ){
            return;
        }
        sbTotalD.setProgress((int) (mSplitAnimation.getTotalDuration()/30.f));
        sbZoominD.setProgress((int) (mSplitAnimation.getZoomInDurationRatio()*100.f));
        sbZoominS.setProgress((int) (mSplitAnimation.getZoomInScaleRatio()*100.f));
        sbZoomOutS.setProgress((int) (mSplitAnimation.getZoomOutToScaleRatio()*100.f));
        tvTotalDuration.setText(String.valueOf(mSplitAnimation.getTotalDuration()));
        tvZoomInDratio.setText(String.valueOf(mSplitAnimation.getZoomInDurationRatio()));
        tvZoomOutSRatio.setText(String.valueOf(mSplitAnimation.getZoomOutToScaleRatio()));
        tvZoomInSratio.setText(String.valueOf(mSplitAnimation.getZoomInScaleRatio()));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(mSplitAnimation==null){
           return;
        }
        switch (seekBar.getId()){
            case R.id.sb_total_duration:
                mSplitAnimation.setTotalDuration(progress * 30);
                tvTotalDuration.setText(String.valueOf(progress*30));
                break;
            case R.id.sb_zoomin_duration_ratio:
                mSplitAnimation.setZoonInDurationRatio(progress / 100.f);
                tvZoomInDratio.setText(String.valueOf(progress/100.f));
                break;
            case R.id.sb_zoomin_scale_ratio:
                mSplitAnimation.setZoomInScaleRatio(progress / 100.f);
                tvZoomInSratio.setText(String.valueOf(progress/100.f));
                break;
            case R.id.sb_zoomout_scale_ratio:
                mSplitAnimation.setZoomOutToScaleRatio(progress / 100.f);
                tvZoomOutSRatio.setText(String.valueOf(progress/100.f));
                break;
            default:break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
