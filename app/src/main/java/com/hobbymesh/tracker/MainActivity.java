package com.hobbymesh.tracker;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.hobbymesh.tracker.model.CustomLocation;
import com.hobbymesh.tracker.model.Route;

import fr.quentinklein.slt.TrackerSettings;

public class MainActivity extends ListActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_LOCATION = 678;
    private CustomLocationTracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View stopBt = findViewById(R.id.mainStopTrackingBt);
        final View startBt = findViewById(R.id.mainStartTrackingBt);

        stopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTracker();
                stopBt.setVisibility(View.INVISIBLE);
                startBt.setVisibility(View.VISIBLE);
            }
        });

        startBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startTracker();
                stopBt.setVisibility(View.VISIBLE);
                startBt.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            startTracker();
        }
    }

    private void stopTracker() {
        mTracker.stopListening();
    }

    private void startTracker() {
        // You can pass an ui Context but it is not mandatory getApplicationContext() would also works
        // Be aware if you target android 23, you'll need to handle the runtime-permissions !
        // see http://developer.android.com/reference/android/support/v4/content/ContextCompat.html
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // You need to ask the user to enable the permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
        } else {

            final DaoSession daoSession = ((CustomApplication) getApplication()).getDaoSession();

            final Route route = new Route("");
            daoSession.insert(route);


            TrackerSettings settings = new TrackerSettings()
                    .setUseGPS(true)
                    .setUseNetwork(true)
                    .setUsePassive(true)
                    .setTimeBetweenUpdates(1000)
                    .setMetersBetweenUpdates(100);

            mTracker = new CustomLocationTracker(MainActivity.this, settings) {
                @Override
                public void onLocationFound(Location location) {
                    Log.v(TAG, "Location: " + location);
                    CustomLocation customLocation = new CustomLocation(location.getLatitude(), location.getLongitude(), route);


                    daoSession.insert(customLocation);
                }
            };
            mTracker.startListening();
        }
    }
}
