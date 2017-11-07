package com.theerapat.calculatorfloating.factory.implement;

import android.view.View;

import com.theerapat.calculatorfloating.R;
import com.theerapat.calculatorfloating.factory.PressButtonFactory;

/**
 * Created by theerapat on 11/7/2017.
 */

public class PressButtonFactoryImpl implements PressButtonFactory {

    @Override
    public String pressStandardNumber(View view) {
        switch (view.getId()){
            case R.id.button_press_0:
                return "0";
            case R.id.button_press_1:
                return "1";
            case R.id.button_press_2:
                return "2";
            case R.id.button_press_3:
                return "3";
            case R.id.button_press_4:
                return "4";
            case R.id.button_press_5:
                return "5";
            case R.id.button_press_6:
                return "6";
            case R.id.button_press_7:
                return "7";
            case R.id.button_press_8:
                return "8";
            case R.id.button_press_9:
                return "9";
            default:
                return null;
        }
    }
}
