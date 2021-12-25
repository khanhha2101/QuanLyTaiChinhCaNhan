package com.example.quanlytaichinhcanhan.animation;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.quanlytaichinhcanhan.R;
import com.ramotion.foldingcell.animations.AnimationEndListener;

public class TranslateAnimationUtil implements View.OnTouchListener{

    private GestureDetector gestureDetector;

    public TranslateAnimationUtil (Context context, View view) {
        gestureDetector = new GestureDetector(context, new SimpleGestureDetector(view));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public class SimpleGestureDetector extends GestureDetector.SimpleOnGestureListener {

        private View viewAnimation;
        private boolean isFinishAnimation = true;

        public SimpleGestureDetector (View viewAnimation) {
            this.viewAnimation = viewAnimation;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (distanceY > 0) {
                hiddenView();
            } else {
                showView();
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        private void hiddenView() {
            if (viewAnimation == null || viewAnimation.getVisibility() == View.GONE) {
                return;
            }

            Animation animationDown = AnimationUtils.loadAnimation(viewAnimation.getContext(), R.anim.bottom_down);
            animationDown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    viewAnimation.setVisibility(View.VISIBLE);
                    isFinishAnimation = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    viewAnimation.setVisibility(View.GONE);
                    isFinishAnimation = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if (isFinishAnimation) {
                viewAnimation.startAnimation(animationDown);
            }
        }

        private void showView() {
            if (viewAnimation == null || viewAnimation.getVisibility() == View.VISIBLE) {
                return;
            }

            Animation animation = AnimationUtils.loadAnimation(viewAnimation.getContext(), R.anim.bottom_up);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    viewAnimation.setVisibility(View.VISIBLE);
                    isFinishAnimation = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isFinishAnimation = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if (isFinishAnimation) {
                viewAnimation.startAnimation(animation);
            }
        }
    }
}
