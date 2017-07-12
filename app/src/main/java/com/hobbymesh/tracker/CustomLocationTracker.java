package com.hobbymesh.tracker;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;

import fr.quentinklein.slt.LocationTracker;
import fr.quentinklein.slt.TrackerSettings;

/**
 * Created by janmusil on 24/06/2017.
 */

public class CustomLocationTracker extends LocationTracker {

    public CustomLocationTracker(@NonNull Context context) {
        super(context);
    }

    public CustomLocationTracker(@NonNull Context context, @NonNull TrackerSettings trackerSettings) {
        super(context, trackerSettings);
    }

    @Override
    public void onLocationFound(@NonNull Location location) {

    }

    @Override
    public void onTimeout() {

    }
}
