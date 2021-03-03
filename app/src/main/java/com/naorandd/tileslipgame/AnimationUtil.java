package com.naorandd.tileslipgame;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;


// Set Animation Class
public class AnimationUtil {
    // Set Animation method
    public static void animateConstraintLayout(ConstraintLayout constraintLayout, int EnterAnimationDuration, int ExitAnimationDuration) {
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(EnterAnimationDuration);
        animationDrawable.setExitFadeDuration(ExitAnimationDuration);
        animationDrawable.start();
    }

    public static void animateButton(Button button, int AnimationDuration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat( button, "alpha", 0f, 1f);
        objectAnimator.setDuration(AnimationDuration);
        objectAnimator.start();
    }
}
