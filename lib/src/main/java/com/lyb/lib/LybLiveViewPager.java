package com.lyb.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class LybLiveViewPager extends ViewPager {

    private static final int MIN_DISTANCE = 100;

    float eventX = 0;
    float eventY = 0;

    public void setOnEvent(OnEvent onEvent) {
        this.onEvent = onEvent;
    }

    private OnEvent onEvent;

    public boolean isInterecpt() {
        return isInterecpt;
    }

    public void setInterecpt(boolean interecpt) {
        isInterecpt = interecpt;
    }

    /*
    是否拦截时间。 直播停止不拦截，默认拦截。
     */
    private boolean isInterecpt = true;

    public LybLiveViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if(!isInterecpt){
            return super.onInterceptTouchEvent(ev);
        }
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                eventY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                float distanceY = eventY - ev.getY();
                if(Math.abs(distanceY) > MIN_DISTANCE){
                    Log.v("event", "纵向滑动");
                    if(onEvent != null){
                        if(distanceY > 0) {
                            onEvent.onEventUp();
                        }else{
                            onEvent.onEventDown();;
                        }
                    }
                    return true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    public interface OnEvent{
        void onEventUp();
        void onEventDown();
    }

}
