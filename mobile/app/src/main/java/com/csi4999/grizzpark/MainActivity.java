package com.csi4999.grizzpark;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {


    TextView[] textViewArray = new TextView[2];
    RequestQueue queue;
    String url ="http://3.133.144.189:4444/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(getApplicationContext());

        textViewArray[0] = findViewById(R.id.firstRow);
        textViewArray[1] = findViewById(R.id.secondRow);
        final int[] rowvar = new int [2];

        final GradientDrawable[] gradientDrawable = new GradientDrawable[2];

        gradientDrawable[0] = (GradientDrawable) textViewArray[0].getBackground().mutate();
        gradientDrawable[1] = (GradientDrawable) textViewArray[1].getBackground().mutate();

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //FROM HERE
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try{
                                    JSONArray rowstate = response.getJSONArray("data");
                                    for (int i = 0; i<4; i++){
                                        //int[] tempvar = new int [2];
                                        JSONObject obj = rowstate.getJSONObject(i);
                                        rowvar[i] = obj.getInt("numOfCars");
                                        int spots = 42-rowvar[i];
                                        textViewArray[i].setText(spots+" cars detected");
//Integer.toString(rowvar[i])

                                        if (rowvar[i] < 18){gradientDrawable[i].setColor(Color.argb(80,177,255,177));}
                                        else if (rowvar[i] == 42){gradientDrawable[i].setColor(Color.argb(80,157,0,0));}
                                        else {gradientDrawable[i].setColor(Color.argb(80,245,245,10));}
                                    }
                                }
                                catch (JSONException e){
                                    System.out.println("off");
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                            }
                        });
                queue.add(jsonObjectRequest);

                handler.postDelayed(this, 1000);
            }
        };handler.postDelayed(runnable, 1000) ;

    }
}