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

        CircleImageView circle = new CircleImageView(target);
        ViewGroup vgroup = (ViewGroup) target.getParent();
        target.setVisibility(View.INVISIBLE);
        vgroup.addView(circle);
        SplitAnimation splitAnimation = new SplitAnimation(circle);
        splitAnimation.setDuration(300);
        splitAnimation.start();

    }
}
