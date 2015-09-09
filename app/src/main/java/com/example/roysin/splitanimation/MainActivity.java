package com.example.roysin.splitanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView target;
    private Button btnPlay;
    private ImageView splitView;
    private CircleImageView circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        target = (ImageView) findViewById(R.id.target);

        btnPlay = (Button) findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        ViewGroup vgroup = (ViewGroup) target.getParent();
        if(splitView != null) {
            vgroup.removeView(splitView);
            vgroup.removeView(circle);
        }

        circle = new CircleImageView(target);
        target.setVisibility(View.INVISIBLE);
        vgroup.addView(circle);
        SplitAnimation splitAnimation = new SplitAnimation(circle);
        splitView = (ImageView) splitAnimation.getSplitedView();
        splitAnimation.setTotalDuration(400);
        splitAnimation.start();

    }
}
