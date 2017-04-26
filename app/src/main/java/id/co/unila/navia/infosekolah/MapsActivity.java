package id.co.unila.navia.infosekolah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import id.co.unila.navia.infosekolah.active_record.ArSekolah;
import id.co.unila.navia.infosekolah.model.Sekolah;
import id.co.unila.navia.infosekolah.rest_api.MyOkHttpInterceptor;
import id.co.unila.navia.infosekolah.rest_api.SekolahEndpoint;
import id.co.unila.navia.infosekolah.rest_api.ServiceGenerator;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private List<Sekolah> mSekolahList = new ArrayList<>();
    private ArSekolah arSekolah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("SEKOLAH")) {
                List<Sekolah> data = Parcels.unwrap(getIntent().getParcelableExtra("SEKOLAH"));
                arSekolah = new ArSekolah(this, data);
                mSekolahList.addAll(arSekolah.findAll());
            }
        }

        mapFragment.getMapAsync(MapsActivity.this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        final List<MarkerOptions> markerOptionses = new ArrayList<MarkerOptions>();

        for(Sekolah sekolah: mSekolahList){
            LatLng lokasi = new LatLng(sekolah.lat, sekolah.lng);
            MarkerOptions mOpt = new MarkerOptions()
                    .position(lokasi)
                    .title(sekolah.nama)
                    .snippet(sekolah.alamat)
                    ;

            switch(sekolah.tipe) {
                case "Sekolah Dasar (SD)":
                    mOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    break;
                case "Sekolah Menengah Pertama (SMP)":
                    mOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    break;
                case "Sekolah Menengah Atas (SMA)":
                    mOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    break;
                case "Sekolah Menengah Kejuruan (SMK)":
                    mOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                    break;
            }

            markerOptionses.add(mOpt);
            builder.include(lokasi);
        }

        final LatLngBounds bounds = builder.build();

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                int padding = 50; // offset from edges of the map in pixels
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                mMap.animateCamera(cameraUpdate);

                for (MarkerOptions mopt : markerOptionses){
                    mMap.addMarker(mopt);
                }
            }
        });
    }
}
