package com.example.owner.betterthanmal.ui;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.owner.betterthanmal.Constants;
import com.example.owner.betterthanmal.R;

public class ActivityStartup extends AppCompatActivity implements Constants {

    private final String TAG = "ActivityStartup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.startup_container, new FragmentSignin(), fragmentTags[6]).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        ConnectivityManager cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            Toast.makeText(this, "GET AN INTERNET CONNECTION YOU STUPID CUNT ASS BITCH MOTHER FUCKER", Toast.LENGTH_LONG).show();
            Intent enableWifiIntent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
            startActivityForResult(enableWifiIntent, 1234);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_startup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_signin && getFragmentManager().findFragmentByTag(fragmentTags[6]) == null) {
            getFragmentManager().beginTransaction().replace(R.id.startup_container, new FragmentSignin(), fragmentTags[6])
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        } else if (id == R.id.menu_register && getFragmentManager().findFragmentByTag(fragmentTags[7]) == null) {
            getFragmentManager().beginTransaction().replace(R.id.startup_container, new FragmentRegister(), fragmentTags[7])
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }

        return true;
    }
}
