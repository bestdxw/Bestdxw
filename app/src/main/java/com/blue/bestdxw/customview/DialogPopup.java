package com.blue.bestdxw.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;

import com.blue.bestdxw.R;
import com.bm.library.PhotoView;

import razerdp.basepopup.BasePopupWindow;

/**
 * pop
 *
 * @author Blue
 * @date 2018/6/9 0009 16:13
 */
public class DialogPopup extends BasePopupWindow implements View.OnClickListener{
    PhotoView showGirl;

    public DialogPopup(Context context, Bitmap bit) {
        super(context);
        showGirl = (PhotoView) findViewById(R.id.show_girl);
        setViewClickListener(this,showGirl);
        if (null != bit){
            showGirl.setImageBitmap(bit);
        }
    }

    @Override
    protected Animation initShowAnimation() {
        //抖动动画
//        AnimationSet set=new AnimationSet(false);
//        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        shakeAnima.setInterpolator(new CycleInterpolator(5));
//        shakeAnima.setDuration(400);
//        set.addAnimation(getDefaultAlphaAnimation());
//        set.addAnimation(shakeAnima);
        return getDefaultScaleAnimation();
    }

    @Override
    protected Animation initExitAnimation() {
        return getDefaultScaleAnimation(false);
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_girl);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anim);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
