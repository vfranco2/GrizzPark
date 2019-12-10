package com.csi4999.grizzpark;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.csi4999.grizzpark.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech t1;
    TextView[] textViewArray = new TextView[2];
    CardView[] cardViewArray = new CardView[2];
    RequestQueue queue;
    String url ="http://3.133.144.189:4444/";
    int[] rowvar = new int [2];
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.navmenubar);
        textViewArray[0] = findViewById(R.id.fourthRowEC);
        textViewArray[1] = findViewById(R.id.thirdRowEC);
        cardViewArray[0] = findViewById(R.id.fourthCardEC);
        cardViewArray[1] = findViewById(R.id.thirdCardEC);
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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch(menuItem.getItemId()){
                            case R.id.nav_EC:
                                Intent interestsEC = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(interestsEC);
                                break;
                            case R.id.nav_HHB:
                                Intent interestsHHB = new Intent(MainActivity.this, HHBActivity.class);
                                startActivity(interestsHHB);
                                break;
                            case R.id.nav_MSC:
                                Intent interestsMSC = new Intent(MainActivity.this, MSCActivity.class);
                                startActivity(interestsMSC);
                                break;
                            case R.id.nav_sign:
                                Intent interestsSign = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(interestsSign);
                                break;
                            case R.id.nav_cred:
                                Intent interestsCred = new Intent(MainActivity.this, CredActivity.class);
                                startActivity(interestsCred);
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
                            for (int i = 0; i<2; i++){
                                JSONObject obj = rowstate.getJSONObject(i);
                                rowvar[i] = obj.getInt("numOfCars");
                                if (rowvar[i] > 42){
                                    rowvar[i] = 42;
                                }
                                else {
                                    System.out.print("good");
                                }
                                int spots = 42-rowvar[i];
                                textViewArray[i].setText(spots+" spots available");
                                if (rowvar[i] < 18){cardViewArray[i].setCardBackgroundColor(0xff76ba1b);}
                                else if (rowvar[i] == 42){cardViewArray[i].setCardBackgroundColor(0xffe62020);}
                                else {cardViewArray[i].setCardBackgroundColor(0xffffdc00);}

                            }
                            System.out.println(rowvar[0]);
                            System.out.println(rowvar[1]);
                            FloatingActionButton fab = findViewById(R.id.fab);
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final String rowline;
                                    final String rowname;
                                    if (rowvar[0] > rowvar[1]){
                                        rowline = Integer.toString(42-rowvar[1]);
                                        rowname = " three.";
                                    }
                                    else {
                                        rowline = Integer.toString(42-rowvar[0]);
                                        rowname = " four.";
                                    }
                                    t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                        @Override
                                        public void onInit(int status) {
                                            if(status != TextToSpeech.ERROR) {
                                                t1.setLanguage(Locale.UK);
                                            } else{System.out.print("Whoops");}
                                            t1.speak("There are "+rowline+" spots available in row "+rowname, TextToSpeech.QUEUE_FLUSH, null);
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