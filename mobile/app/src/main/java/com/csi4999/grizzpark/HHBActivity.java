package com.csi4999.grizzpark;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.csi4999.grizzpark.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class HHBActivity extends AppCompatActivity {

    TextToSpeech t1;
    TextView textHHB;
    CardView cardHHB;
    RequestQueue queue;
    String url ="http://3.133.144.189:4444/";
    int[] rowvar = new int [3];
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hhb);
        mDrawerLayout = findViewById(R.id.navmenubar);
        textHHB = findViewById(R.id.firstRowHHB);
        cardHHB = findViewById(R.id.firstCardHHB);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        queue = Volley.newRequestQueue(getApplicationContext());

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //FROM HERE
                getDbData();
                handler.postDelayed(this, 1000);
            }
        };handler.postDelayed(runnable, 1000) ;

        NavigationView navigationView = findViewById(R.id.nav_viewhhb);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch(menuItem.getItemId()){
                            case R.id.nav_EC:
                                Intent interestsEC = new Intent(HHBActivity.this, MainActivity.class);
                                startActivity(interestsEC);
                                break;
                            case R.id.nav_HHB:
                                Intent interestsHHB = new Intent(HHBActivity.this, HHBActivity.class);
                                startActivity(interestsHHB);
                                break;
                            case R.id.nav_MSC:
                                Intent interestsMSC = new Intent(HHBActivity.this, MSCActivity.class);
                                startActivity(interestsMSC);
                                break;
                            case R.id.nav_sign:
                                Intent interestsSign = new Intent(HHBActivity.this, LoginActivity.class);
                                startActivity(interestsSign);
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void getDbData(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray rowstate = response.getJSONArray("data");

                            JSONObject obj = rowstate.getJSONObject(2);
                            rowvar[2] = obj.getInt("numOfCars");
                            if (rowvar[2] > 15) {
                                rowvar[2] = 15;
                            }
                            else{
                                System.out.println("hhb good");
                            }
                            int spots = 15-rowvar[2];
                            textHHB.setText(spots+" spots available");
                            if (rowvar[2] < 8){cardHHB.setCardBackgroundColor(0xff76ba1b);}
                            else if (rowvar[2] == 15){cardHHB.setCardBackgroundColor(0xffe62020);}
                            else {cardHHB.setCardBackgroundColor(0xffffdc00);}

                            System.out.println(rowvar[2]);
                            FloatingActionButton fab = findViewById(R.id.fabhhb);
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final String rowline;
                                    rowline = Integer.toString(15-rowvar[2]);
                                    t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                        @Override
                                        public void onInit(int status) {
                                            if(status != TextToSpeech.ERROR) {
                                                t1.setLanguage(Locale.UK);
                                            } else{System.out.print("Whoops");}
                                            t1.speak("There are "+rowline+" spots available in the handicapped row.", TextToSpeech.QUEUE_FLUSH, null);
                                        }
                                    });
                                }
                            });
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
    }

    //Handle drawer click opening
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}