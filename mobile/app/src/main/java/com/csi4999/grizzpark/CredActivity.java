package com.csi4999.grizzpark;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

public class CredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_cred);

        //Popup stuff
        DisplayMetrics inDisplay = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(inDisplay);
        int width = inDisplay.widthPixels;
        int height = inDisplay.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));

    }
}