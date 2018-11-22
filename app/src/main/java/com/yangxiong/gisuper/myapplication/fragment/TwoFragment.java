package com.yangxiong.gisuper.myapplication.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.base.BaseFragment;
import com.yangxiong.gisuper.myapplication.common.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by yangxiong on 2018/11/2.
 */
public class TwoFragment extends BaseFragment {


    @BindView(R.id.tv_anim)
    ImageView tvAnim;

    @Override
    protected int contentViewLayout() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView() {
        // "scaleX" "scaleY" "rotationX"  "rotationY"  "translationX"  "translationY"
        EventBus.getDefault( ).post(new MessageEvent( ));
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat(
                "rotationY", 0f, 3600f);
       /* PropertyValuesHolder colorHolder = PropertyValuesHolder.ofInt(
                "BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
*/
        PropertyValuesHolder translationHolder = PropertyValuesHolder.ofFloat(
                "translationY", -600f, 600f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(tvAnim, rotationHolder/*, colorHolder*/,translationHolder);
        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();


    }

    @Override
    protected void initData() {

    }

}
