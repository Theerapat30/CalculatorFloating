package com.theerapat.calculatorfloating.service.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.theerapat.calculatorfloating.MainActivity;
import com.theerapat.calculatorfloating.R;
import com.theerapat.calculatorfloating.factory.implement.PressNumberFactoryImpl;
import com.theerapat.calculatorfloating.service.BaseViewService;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by theerapat on 11/3/2017.
 */

public class CalculatorStandardFloatingViewService extends BaseViewService {
    private final String TAG = CalculatorStandardFloatingViewService.class.getSimpleName();

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

    @BindView(R.id.edit_text_display_number) public EditText editTextDisplayNumber;

    private boolean isMod = false;
    private boolean isDivide = false;
    private boolean isMultiply = false;
    private boolean isMinus = false;
    private boolean isPlus = false;

    private String tempNumber = "";
    private String baseNumber = "";
    private String quoteNumber = "";

    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    private View.OnClickListener onClickNumber = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                pressNumberByView(v);
            } catch (Exception e){
                Log.e(TAG, "onClick()", e);
            }
        }
    };

    private void pressNumberByView(View view){
        String number = new PressNumberFactoryImpl().pressStandardNumber(view);
        if (tempNumber.length()==0 && "0".equals(number))
            tempNumber="";
        else{
            tempNumber += number;
            Double numberDouble = Double.parseDouble(tempNumber);
            String numberDisplay = DECIMAL_FORMAT.format(numberDouble);
            editTextDisplayNumber.setText(numberDisplay);
        }
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate()");
        super.onCreate();
        bindEvents();
    }

    private void bindEvents(){
        Log.i(TAG, "bindEvents()");
        buttonNumber0.setOnClickListener(onClickNumber);
        buttonNumber1.setOnClickListener(onClickNumber);
        buttonNumber2.setOnClickListener(onClickNumber);
        buttonNumber3.setOnClickListener(onClickNumber);
        buttonNumber4.setOnClickListener(onClickNumber);
        buttonNumber5.setOnClickListener(onClickNumber);
        buttonNumber6.setOnClickListener(onClickNumber);
        buttonNumber7.setOnClickListener(onClickNumber);
        buttonNumber8.setOnClickListener(onClickNumber);
        buttonNumber9.setOnClickListener(onClickNumber);
    }

    @OnClick(R.id.button_press_clear)
    public void buttonClear(){
        Log.i(TAG, "buttonClear()");
    }

    @OnClick(R.id.button_press_bracket)
    public void buttonBracket(){
        Log.i(TAG, "buttonBracket()");
    }

    @OnClick(R.id.button_press_mod)
    public void buttonMod(){
        Log.i(TAG, "buttonMod()");
    }

    @OnClick(R.id.button_press_divide)
    public void buttonDivide(){
        Log.i(TAG, "buttonDivide()");
    }

    @OnClick(R.id.button_press_multiply)
    public void buttonMultiply(){
        Log.i(TAG, "buttonMultiply()");
    }

    @OnClick(R.id.button_press_minus)
    public void buttonMinus(){
        Log.i(TAG, "buttonMinus()");
    }

    @OnClick(R.id.button_press_plus)
    public void buttonPlus(){
        Log.i(TAG, "buttonPlus()");
    }

    @OnClick(R.id.button_press_point)
    public void buttonPoint(){
        Log.i(TAG, "buttonPoint()");
    }

    @OnClick(R.id.button_press_equals)
    public void buttonEquals(){
        Log.i(TAG, "buttonEquals()");
    }

    @OnClick(R.id.button_press_backspace)
    public void buttonBackspace(){
        Log.i(TAG, "buttonBackspace()");
    }

    @OnClick(R.id.image_view_close_button)
    public void imageViewCloseCollapsed(){
        Log.i(TAG, "imageViewCloseCollapsed()");
        stopSelf();
    }

    //Open the application on this button click
    @OnClick(R.id.image_view_open_button)
    public void imageViewOpenExpanded(){
        Log.i(TAG, "imageViewOpenExpanded()");
        Intent intent = new Intent(CalculatorStandardFloatingViewService.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        stopSelf();
    }

}
