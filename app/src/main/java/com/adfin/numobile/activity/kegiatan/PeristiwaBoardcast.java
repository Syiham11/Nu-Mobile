package com.adfin.numobile.activity.kegiatan;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adfin.numobile.ModulAPI;
import com.adfin.numobile.R;
import com.adfin.numobile.activity.warga.AnggotaLihatActivity;
import com.adfin.numobile.helper.ListAdapterWarga;
import com.adfin.numobile.model.CDataWarga;
import com.adfin.numobile.model.DataWarga;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PeristiwaBoardcast extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<DataWarga> lstdatawarga;
    private HashMap<Marker, DataWarga> mMarkersHashMap;

    /* Map Variable */
    ImageView markerIcon = null;
    TextView markerLabel, anotherLabel, tahunLabel, wingLabel;
    View vmap;

    private GoogleMap mMap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peristiwa_boardcast);

        vmap = getLayoutInflater().inflate(R.layout.infomap, null);

        markerIcon = (ImageView) vmap.findViewById(R.id.marker_icon);

        markerLabel = (TextView) vmap.findViewById(R.id.marker_label);

        anotherLabel = (TextView) vmap.findViewById(R.id.another_label);

        tahunLabel = (TextView) vmap.findViewById(R.id.tahun);

        wingLabel = (TextView) vmap.findViewById(R.id.wing);

        mMarkersHashMap = new HashMap<Marker, DataWarga>();

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);

        getDataWarga();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataWarga();
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void getDataWarga() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://www.terpusat.com") //Setting the Root URL
                .build(); //Finally building the adapter

        ModulAPI api = adapter.create(ModulAPI.class);

        api.getDataWarga(
                new Callback<CDataWarga>()
                {
                    @Override
                    public void success(CDataWarga cdatawarga, Response response) {

                        lstdatawarga = cdatawarga.getDataWarga();

                        final String[] namaWarga = new String[lstdatawarga.size()];
                        final String[] alamatWarga = new String[lstdatawarga.size()];
                        final String[] imageWarga = new String[lstdatawarga.size()];
                        final String[] latWarga = new String[lstdatawarga.size()];
                        final String[] longWarga = new String[lstdatawarga.size()];

                        for (int i = 0; i < lstdatawarga.size(); i++) {
                            namaWarga[i] = lstdatawarga.get(i).getnama();
                            alamatWarga[i] = lstdatawarga.get(i).getalamat();
                            latWarga[i] = lstdatawarga.get(i).getphoto();
                            longWarga[i] = lstdatawarga.get(i).getlatitude();
                        }

                        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
                        ListAdapterWarga adapter=new ListAdapterWarga(PeristiwaBoardcast.this, imageWarga, namaWarga, alamatWarga);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        mSwipeRefreshLayout.setRefreshing(false);

                        setUpMap();

                        plotMarkers(lstdatawarga);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        final String merror = error.getMessage();

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(PeristiwaBoardcast.this, merror.toString() + " Terjadi Kesalahan Kooneksi ", Toast.LENGTH_LONG).show();
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
            LatLngBounds IPB = new LatLngBounds(new LatLng(-6.558405, 106.725864), new LatLng(-6.557413, 106.733277));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IPB.getCenter(), 17));

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
        public MarkerInfoWindowAdapter() {
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            DataWarga myMarker = mMarkersHashMap.get(marker);


            if (myMarker.getphoto().length() > 0) {
                Picasso.with(getApplicationContext())
                        .load("http://www.terpusat.com/NUMobile/" + myMarker.getphoto())
                        .resize(70, 70)
                        .into(markerIcon);
            }

            markerLabel.setText(myMarker.getnama());
            anotherLabel.setText("Lokasi : " + myMarker.getalamat());
            tahunLabel.setText("Tahun");
            wingLabel.setText("Wing");

            return vmap;
        }
    }
}
