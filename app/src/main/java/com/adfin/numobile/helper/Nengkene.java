package com.adfin.numobile.helper;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.google.android.gms.internal.zzid.runOnUiThread;

// Created by prakasa on 17/12/16.

public class Nengkene extends Service implements LocationListener, EasyPermissions.PermissionCallbacks {

    private static Context mContext;

    private Location location;
    private LocationManager manager;

    private double latitude = 0;
    private double longitude = 0;

    // Jarak minimum untuk melakukan update dalam satuan meter
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meter

    // Minimum waktu update dalam satuan milidetik
    private static final long MIN_TIME_BW_UPDATES = 500 * 60; // 1 menit

    public static Nengkene with(Context context) {
        if (context != null) mContext = context;
        return new Nengkene();
    }

    public Nengkene start() {
        manager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        __isPermitted();
        return this;
    }

    public Nengkene get() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            __isPermitted();
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) __setDataLocation();
        return this;
    }

    public double latitude(){
        return latitude;
    }

    public double longitude(){
        return longitude;
    }

    private void __setDataLocation(){
        latitude = location.getLatitude();
        longitude = location.getLatitude();
    }

    private Boolean __isGpsEnable(){
        boolean gpsEnable = false;
        if( manager.isProviderEnabled(LocationManager.GPS_PROVIDER) ) gpsEnable = true;
        return gpsEnable;
    }

    private Boolean __isNetEnable(){
        boolean netEnable = false;
        if( manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ) netEnable = true;
        return netEnable;
    }

    private void __isPermitted(){
        if(__isGpsEnable() && __isNetEnable()){
            String[] perms = { Manifest.permission.ACCESS_FINE_LOCATION };
            if ( ! EasyPermissions.hasPermissions(mContext, perms) ) {
                EasyPermissions.requestPermissions(mContext, "Aplikasi membutuhkan akses lokasi",
                        200, perms);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //mContext
    }

    @Override
    public void onProviderDisabled(String provider) {
        if(provider.equals("gps"))
        {
            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(mContext, perms)) {
            new AppSettingsDialog.Builder((Activity) mContext, "This app may not work correctly without the requested permissions. Open the app settings screen to modify app permissions.")
                    .setTitle("Permissions Required")
                    .setPositiveButton("Settings")
                    .setNegativeButton("Settings dialog canceled", null)
                    .setRequestCode(500)
                    .build()
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, mContext);
    }
}
