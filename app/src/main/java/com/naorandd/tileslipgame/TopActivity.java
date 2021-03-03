package com.naorandd.tileslipgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopActivity extends Activity implements View.OnClickListener {

    private ConstraintLayout mConstraintLayout;
    private Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        start_button = findViewById(R.id.title_button);
        start_button.setOnClickListener(this);

        mConstraintLayout = findViewById(R.id.mConstraintLayout);
        // Set background animation
        AnimationUtil.animateConstraintLayout(mConstraintLayout,
            CommonDefine.BACKGROUND_ENTER_ANIMATION_DURATION,CommonDefine.BACKGROUND_EXIT_ANIMATION_DURATION);

        AnimationUtil.animateButton(start_button,
                CommonDefine.BACKGROUND_ENTER_ANIMATION_DURATION);

    }

    // Do button click function
    public void onClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}