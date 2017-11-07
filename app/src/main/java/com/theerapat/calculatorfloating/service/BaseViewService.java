package com.theerapat.calculatorfloating.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.theerapat.calculatorfloating.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by theerapat on 11/6/2017.
 */

public class BaseViewService extends Service {
    private final String TAG = BaseViewService.class.getSimpleName();

    @BindView(R.id.collapsed_container) public View viewCollapsed;
    @BindView(R.id.expanded_container) public View viewExpanded;

    private WindowManager windowManager;
    private WindowManager.LayoutParams params;

    private View viewFloating;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        includeSetupTeardown();
        super.onCreate();
    }

    private void includeSetupTeardown(){
        Log.i(TAG, "includeSetupTeardown()");
        bindView();
        setupWindowManager();
        setOnTouchFloatingView();
    }

    private void bindView(){
        viewFloating = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);
        ButterKnife.bind(this, viewFloating);
    }

    /*Add the view to the window.
    Specify the view position by initial view will be added to top-left corner*/
    private void setupWindowManager(){
        Log.i(TAG, "setupWindowManager()");
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(viewFloating, params);
    }

    /*Drag and move floating view using user's touch action*/
    private void setOnTouchFloatingView(){
        Log.i(TAG, "setOnTouchFloatingView()");
        viewFloating.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // A pressed gesture has started
                        Log.i(TAG, "case MotionEvent.ACTION_DOWN");
                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;
                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP: // A pressed gesture has finished
                        Log.i(TAG, "case MotionEvent.ACTION_UP");
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);
                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsed()) {
                                //When user clicks on the image view of the collapsed layout,
                                //visibility of the collapsed layout will be changed to "View.GONE"
                                //and expanded view will become visible.
                                viewCollapsed.setVisibility(View.GONE);
                                viewExpanded.setVisibility(View.VISIBLE);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE: //A change has happened during a press gesture (between ACTION_DOWN and ACTION_UP).
                        Log.i(TAG, "case MotionEvent.ACTION_MOVE");
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        //Update the layout with new X & Y coordinate
                        windowManager.updateViewLayout(viewFloating, params);
                        return true;
                }
                return false;
            }
        });
    }

     /*Detect if the floating view is collapsed or expanded.
     @return true if the floating view is collapsed.*/
    protected boolean isViewCollapsed() {
        return viewFloating == null || viewFloating.findViewById(R.id.collapsed_container).getVisibility() == View.VISIBLE;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewFloating != null)
            windowManager.removeView(viewFloating);
    }
}
