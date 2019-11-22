package com.csi4999.grizzpark;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MapActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Load default fragment
        loadFragment(new ECFragment());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.setOnNavigationItemSelectedListener(this);

        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_EC, R.id.navigation_MSC, R.id.navigation_HHB)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_EC:
                fragment = new ECFragment();
                Toast.makeText(this, "EC", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation_MSC:
                fragment = new MSCFragment();
                Toast.makeText(this, "MSC", Toast.LENGTH_SHORT).show();

                break;

            case R.id.navigation_HHB:
                fragment = new HHBFragment();
                Toast.makeText(this, "HHB", Toast.LENGTH_SHORT).show();

                break;

        }

        return loadFragment(fragment);
    }
}
