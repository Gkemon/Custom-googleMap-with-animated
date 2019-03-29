package com.gkemon.map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static android.content.Context.LOCATION_SERVICE;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public
class GPShelper {

    public static LocationManager locationManager;
    public static LocationListener locationListener;

    public static final int INITIAL_REQUEST = 1337;
    public static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;

    public static
    boolean canAccessLocation(Context context) {
        return (hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)&&hasPermission(context, Manifest.permission.INTERNET)&&hasPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION));
    }

    public static final String[] INITIAL_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET,Manifest.permission.ACCESS_COARSE_LOCATION

    };

    public static
    boolean hasPermission(Context context, String perm) {
        return (PackageManager.PERMISSION_GRANTED == checkSelfPermission(context, perm));
    }

    public static
    Location getLastLocation(Activity activity){
        LocationManager enabledManager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);

        assert enabledManager != null;
        if (enabledManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            if ( ContextCompat.checkSelfPermission( activity, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

                ActivityCompat.requestPermissions( activity, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  , Manifest.permission.ACCESS_FINE_LOCATION},
                        GPShelper.LOCATION_REQUEST);
            }

            Location location = enabledManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return location;
    }
    return null;
}

}