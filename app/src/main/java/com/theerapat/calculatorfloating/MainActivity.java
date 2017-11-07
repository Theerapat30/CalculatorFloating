package com.theerapat.calculatorfloating;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.theerapat.calculatorfloating.service.view.CalculatorStandardFloatingViewService;

public class MainActivity extends AppCompatActivity {
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        render();
    }

    /*Check if the application has draw over other apps permission or not?
    This permission is by default available for API<23. But for API > 23
    you have to ask for the permission in runtime.*/
    private void render(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this))
            requestPermission();
        else
            initializeView();
    }

    /*If the draw over permission is not available open the settings screen
    to grant the permission.*/
    private void requestPermission(){
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION)
            proceed(resultCode);
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    //Check if the permission is granted or not.
    private void proceed(int resultCode){
        if (resultCode == RESULT_OK)
            initializeView();
        else { //Permission is not available
            Toast.makeText(this,
                    "Draw over other app permission not available. Closing the application",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * Set and initialize the view elements.
     */
    private void initializeView() {
        findViewById(R.id.button_open_floating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, CalculatorStandardFloatingViewService.class));
                finish();
            }
        });
    }
}
