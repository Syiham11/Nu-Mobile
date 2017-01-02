package com.adfin.numobile.helper;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.adfin.numobile.ModulAPI;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
        }else{
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) __setDataLocation();
        }
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
        longitude = location.getLongitude();
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
            String[] perms = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };
            if ( ! EasyPermissions.hasPermissions(mContext, perms) ) {
                EasyPermissions.requestPermissions(mContext, "Aplikasi membutuhkan akses lokasi",
                        200, perms);
            }
        }else{
            __showSettingsAlert();
        }
    }

    private void __showSettingsAlert() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        alertDialog.setTitle("Gunakan Lokasi ?");

        alertDialog.setMessage("Aplikasi ini ingin mengubah setting handphon anda:");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertDialog.create();

        alert.show();
    }

    private void __updateLocation() {
        if( Session.with(mContext).load("user_nu").get("username") != null ){
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint("http://numobile.id")
                    .build();

            ModulAPI api = adapter.create(ModulAPI.class);

            api.simpanLatLong(
                    Session.with(mContext).load("user_nu").get("username"),
                    Session.with(mContext).load("user_nu").get("latitude"),
                    Session.with(mContext).load("user_nu").get("longitude"),

                    new Callback<Response>() {
                        @Override
                        public void success(Response result, Response response) {}

                        @Override
                        public void failure(RetrofitError error) {}
                    }
            );
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        String latitude = String.valueOf(location.getLatitude());
        String longitude = String.valueOf(location.getLongitude());
        Session.with(mContext).load("user_nu").set("latitude", latitude);
        Session.with(mContext).load("user_nu").set("longitude", longitude);

        __updateLocation();
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
            new AppSettingsDialog.Builder((Activity) mContext, "Aplikasi tidak berjalan dengan baik tanpa GPS dan kamera.")
                    .setTitle("Permintaan Izin")
                    .setPositiveButton("Pengaturan")
                    .setNegativeButton("Batal", null)
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
