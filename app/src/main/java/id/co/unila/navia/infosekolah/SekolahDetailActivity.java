package id.co.unila.navia.infosekolah;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.unila.navia.infosekolah.model.Sekolah;
import id.co.unila.navia.infosekolah.util.FitXyTransformation;

public class SekolahDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Sekolah sekolah;
    private MapFragment mapFragment;

    int layoutWidth, layoutHeight;

    @BindView(R.id.llMain) LinearLayout llMain;
    @BindView(R.id.ivFoto) ImageView ivFoto;
    @BindView(R.id.llContainer) LinearLayout llContainer;
    @BindView(R.id.tvNama) TextView tvNama;
    @BindView(R.id.tvTipe) TextView tvTipe;
    @BindView(R.id.tvKecamatan) TextView tvKecamatan;
    @BindView(R.id.tvNomorPokok) TextView tvNomorPokok;
    @BindView(R.id.tvAlamat) TextView tvAlamat;
    @BindView(R.id.tvKepsek) TextView tvKepsek;
    @BindView(R.id.tvEmail) TextView tvEmail;
    @BindView(R.id.tvWebsite) TextView tvWebsite;
    @BindView(R.id.tvJumlahGuru) TextView tvJumlahGuru;
    @BindView(R.id.tvJumlahKelas) TextView tvJumlahKelas;
    @BindView(R.id.tvDayaTampung) TextView tvDayaTampung;
    @BindView(R.id.tvRun) TextView tvRun;
    @BindView(R.id.tvMaxUn) TextView tvMaxUn;
    @BindView(R.id.tvRunIps) TextView tvRunIps;
    @BindView(R.id.tvMaxUnIps) TextView tvMaxUnIps;
    @BindView(R.id.tvJumlahPrestasi) TextView tvJumlahPrestasi;
    @BindView(R.id.tvAkreditasi) TextView tvAkreditasi;
    @BindView(R.id.tvNilaiAkreditasi) TextView tvNilaiAkreditasi;

    @BindView(R.id.tvRunLabelSma) TextView tvRunLabelSma;
    @BindView(R.id.tvMaxUnLabelSma) TextView tvMaxUnLabelSma;
    @BindView(R.id.llRips) LinearLayout llRips;
    @BindView(R.id.llMips) LinearLayout llMips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sekolah_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Sekolah");

        ButterKnife.bind(this);

        sekolah = Parcels.unwrap(getIntent().getParcelableExtra("SEKOLAH"));

        if(sekolah.tipe.equals("Sekolah Menengah Atas (SMA)")){
            llRips.setVisibility(View.VISIBLE);
            llMips.setVisibility(View.VISIBLE);

            tvRunLabelSma.setText("Rata-rata Nulai UN IPA");
            tvMaxUnLabelSma.setText("Nilai UN IPA Tertinggi");
        }else{
            llRips.setVisibility(View.GONE);
            llMips.setVisibility(View.GONE);
        }

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        ViewTreeObserver vto = llMain.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llMain.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                layoutWidth = llMain.getWidth();
                layoutHeight = llMain.getHeight();

                ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
                params.height = layoutHeight / 4;
                mapFragment.getView().setLayoutParams(params);

                Picasso.with(SekolahDetailActivity.this)
                        .load(sekolah.foto)
                        .transform(new FitXyTransformation(layoutWidth, false))
                        //.resize(100, 100)
                        //.onlyScaleDown() // the image will only be resized if it's bigger than 6000x2000 pixels.
                        //.placeholder(R.drawable.loading) // can also be a drawable
                        .into(ivFoto);

                tvNama.setText(sekolah.nama);
                tvTipe.setText(sekolah.tipe);
                tvKecamatan.setText(sekolah.kecamatan);
                tvNomorPokok.setText(sekolah.npsn);
                tvAlamat.setText(sekolah.alamat);
                tvKepsek.setText(sekolah.nama_kepsek);
                tvEmail.setText(sekolah.email);
                tvWebsite.setText(sekolah.website);
                tvJumlahGuru.setText(String.valueOf(sekolah.jumlah_guru));
                tvJumlahKelas.setText(String.valueOf(sekolah.jumlah_kelas));
                tvDayaTampung.setText(String.valueOf(sekolah.daya_tampung));
                tvRun.setText(sekolah.r_nilai_un);
                tvMaxUn.setText(sekolah.max_nilai_un);
                tvJumlahPrestasi.setText(String.valueOf(sekolah.jumlah_prestasi));
                tvAkreditasi.setText(sekolah.akreditasi);
                tvNilaiAkreditasi.setText(sekolah.nilai_akreditasi);

                if(sekolah.tipe.equals("Sekolah Menengah Atas (SMA)")){
                    tvMaxUnIps.setText(sekolah.max_nilai_un_ips);
                    tvRunIps.setText(sekolah.r_nilai_un_ips);
                }

                mapFragment.getMapAsync(SekolahDetailActivity.this);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double lat = sekolah.lat;
        double lng = sekolah.lng;

        final LatLng currentLocationPoint = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLocationPoint, 15);
        mMap.animateCamera(cameraUpdate, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                mMap.addMarker(new MarkerOptions().position(currentLocationPoint).title(sekolah.nama));
            }

            @Override
            public void onCancel() {

            }
        });
    }
}
