package com.example.mtchat_android.activitys.interlocutionActivity.flowing;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

public class Flowing {
    private FlowingDrawer mDrawer;

    public Flowing(FlowingDrawer mDrawer) {
        this.mDrawer = mDrawer;

        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {

                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {

            }
        });
    }
}
