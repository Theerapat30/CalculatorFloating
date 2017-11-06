package com.theerapat.calculatorfloating;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by theerapat on 11/6/2017.
 */

public class FloatingViewServiceBase extends Service {

    @BindView(R.id.collapsed_container) public View viewCollapsed;
    @BindView(R.id.expanded_container) public View viewExpanded;

    @BindView(R.id.button_press_0) public Button buttonNumber0;
    @BindView(R.id.button_press_1) public Button buttonNumber1;
    @BindView(R.id.button_press_2) public Button buttonNumber2;
    @BindView(R.id.button_press_3) public Button buttonNumber3;
    @BindView(R.id.button_press_4) public Button buttonNumber4;
    @BindView(R.id.button_press_5) public Button buttonNumber5;
    @BindView(R.id.button_press_6) public Button buttonNumber6;
    @BindView(R.id.button_press_7) public Button buttonNumber7;
    @BindView(R.id.button_press_8) public Button buttonNumber8;
    @BindView(R.id.button_press_9) public Button buttonNumber9;
    @BindView(R.id.button_press_clear) public Button buttonClear;
    @BindView(R.id.button_press_bracket) public Button buttonBracket;
    @BindView(R.id.button_press_mod) public Button buttonMod;
    @BindView(R.id.button_press_divide) public Button buttonDivide;
    @BindView(R.id.button_press_multiply) public Button buttonMultiply;
    @BindView(R.id.button_press_minus) public Button buttonMinus;
    @BindView(R.id.button_press_plus) public Button buttonPlus;
    @BindView(R.id.button_press_point) public Button buttonPoint;
    @BindView(R.id.button_press_backspace) public ImageButton buttonBackspace;
    @BindView(R.id.button_press_equals) public Button buttonEquals;

    protected View viewFloating;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        viewFloating = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);
        ButterKnife.bind(this, viewFloating);
        super.onCreate();
    }

    /**
     * Detect if the floating view is collapsed or expanded.
     *
     * @return true if the floating view is collapsed.
     */
    protected boolean isViewCollapsed() {
        return viewFloating == null || viewFloating.findViewById(R.id.collapsed_container).getVisibility() == View.VISIBLE;
    }
}
