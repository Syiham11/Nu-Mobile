package com.adfin.numobile.activity.kegiatan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.helper.GPSTracker;
import com.adfin.numobile.model.CDataWarga;
import com.adfin.numobile.model.DataWarga;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PeristiwaBoardcast extends AppCompatActivity {

    private List<DataWarga> lstdatawarga;
    private HashMap<Marker, DataWarga> mMarkersHashMap;

    /* Map Variable */
    ImageView markerIcon = null;
    TextView markerLabel, anotherLabel;
    View vmap;

    private GoogleMap mMap;

    private double latit, longit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GPSTracker gps = new GPSTracker(this);
        latit = gps.getLatitude(); longit = gps.getLongitude();

        setContentView(R.layout.activity_peristiwa_boardcast);

        vmap = getLayoutInflater().inflate(R.layout.infomap, null);

        markerIcon = (ImageView) vmap.findViewById(R.id.marker_icon);

        markerLabel = (TextView) vmap.findViewById(R.id.marker_label);

        anotherLabel = (TextView) vmap.findViewById(R.id.another_label);

        mMarkersHashMap = new HashMap<>();

        getDataWarga();

    }

    private void getDataWarga() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://www.terpusat.com") //Setting the Root URL
                .build(); //Finally building the adapter

        ModulAPI api = adapter.create(ModulAPI.class);
        api.getDataWargaRadius(String.valueOf(latit), String.valueOf(longit),
                new Callback<CDataWarga>()
                {
                    @Override
                    public void success(CDataWarga cdatawarga, Response response) {

                        lstdatawarga = cdatawarga.getDataWarga();

                        setUpMap();

                        plotMarkers(lstdatawarga);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        final String merror = error.getMessage();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(PeristiwaBoardcast.this, merror + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );
    }

    private void plotMarkers(List<DataWarga> warga) {
        if (warga.size() > 0) {
            for (int i = 0; i < warga.size(); i++) {

                // Create user marker with custom icon and other options
                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(Double.parseDouble(warga.get(i).getlatitude()), Double.parseDouble(warga.get(i).getlongtitude())));

                Marker currentMarker = mMap.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, warga.get(i));

                mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
            }
        }
    }

    private void setUpMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            LatLngBounds myLocat = new LatLngBounds(new LatLng(latit, longit), new LatLng(latit, longit));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocat.getCenter(), 8));

            // Check if we were successful in obtaining the map.

            if (mMap != null) {
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
                        marker.showInfoWindow();
                        return true;
                    }
                });
            } else
                Toast.makeText(getApplicationContext(), "Unable to create Maps", Toast.LENGTH_SHORT).show();
        }
    }

    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        MarkerInfoWindowAdapter() {
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            DataWarga myMarker = mMarkersHashMap.get(marker);


            if ( myMarker.getphoto() != null && !myMarker.getphoto().equals("null") ) {
                Picasso.with(getApplicationContext())
                        .load(myMarker.getphoto())
                        .resize(70, 70)
                        .into(markerIcon);
            }else{
                markerIcon.setImageDrawable(getDrawable(R.drawable.numobile));
            }

            markerLabel.setText(myMarker.getnama());
            anotherLabel.setText(myMarker.getalamat());
            return vmap;
        }
    }
}