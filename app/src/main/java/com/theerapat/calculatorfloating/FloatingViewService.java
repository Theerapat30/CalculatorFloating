package com.theerapat.calculatorfloating;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by theerapat on 11/3/2017.
 */

public class FloatingViewService extends Service {
    private WindowManager mWindowManager;
    private View mFloatingView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        includeSetupTeardown();
    }

    private void includeSetupTeardown(){
        setComponent();
        setupWindowManager();
    }

    private void setComponent(){
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    }

    /*Add the view to the window.
    Specify the view position by initial view will be added to top-left corner*/
    private void setupWindowManager(){
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;
        mWindowManager.addView(mFloatingView, params);
    }

}
