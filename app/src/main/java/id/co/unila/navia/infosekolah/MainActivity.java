package id.co.unila.navia.infosekolah;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.unila.navia.infosekolah.active_record.ArSekolah;
import id.co.unila.navia.infosekolah.model.Sekolah;

public class MainActivity extends AppCompatActivity {

    private List<Sekolah> mSekolahList = new ArrayList<>();
    private ArSekolah arSekolah;

    @BindView(R.id.ibSd) ImageButton ibSd;
    @BindView(R.id.ibSmp) ImageButton ibSmp;
    @BindView(R.id.ibSma) ImageButton ibSma;
    @BindView(R.id.ibSmk) ImageButton ibSmk;
    @BindView(R.id.ibPeta) ImageButton ibPeta;
    @BindView(R.id.ibPeringkat) ImageButton ibPeringkat;
    @BindView(R.id.ibBantuan) ImageButton ibBantuan;
    @BindView(R.id.ibTentang) ImageButton ibTentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu Utama");

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("SEKOLAH")) {
                List<Sekolah> data = Parcels.unwrap(getIntent().getParcelableExtra("SEKOLAH"));
                arSekolah = new ArSekolah(this, data);
                mSekolahList.addAll(arSekolah.findAll());
            }
        }
    }

    @OnClick(R.id.ibSd)
    public void listSd() {
        List<Sekolah> data = arSekolah.findAllByTipeId("Sekolah Dasar (SD)");

        Intent intent = new Intent(MainActivity.this, ListSekolahActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        intent.putExtra("TIPE", "Sekolah Dasar (SD)");
        startActivity(intent);
    }

    @OnClick(R.id.ibSmp)
    public void listSmp() {
        List<Sekolah> data = arSekolah.findAllByTipeId("Sekolah Menengah Pertama (SMP)");

        Intent intent = new Intent(MainActivity.this, ListSekolahActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        intent.putExtra("TIPE", "Sekolah Menengah Pertama (SMP)");
        startActivity(intent);
    }

    @OnClick(R.id.ibSma)
    public void listSma() {
        List<Sekolah> data = arSekolah.findAllByTipeId("Sekolah Menengah Atas (SMA)");

        Intent intent = new Intent(MainActivity.this, ListSekolahActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        intent.putExtra("TIPE", "Sekolah Menengah Atas (SMA)");
        startActivity(intent);
    }

    @OnClick(R.id.ibSmk)
    public void listSmk() {
        List<Sekolah> data = arSekolah.findAllByTipeId("Sekolah Menengah Kejuruan (SMK)");

        Intent intent = new Intent(MainActivity.this, ListSekolahActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        intent.putExtra("TIPE", "Sekolah Menengah Kejuruan (SMK)");
        startActivity(intent);
    }

    @OnClick(R.id.ibPeta)
    public void openPeta() {
        List<Sekolah> data = arSekolah.findAll();

        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        startActivity(intent);
    }

    @OnClick(R.id.ibPeringkat)
    public void openPeringkat() {
        List<Sekolah> data = arSekolah.findAll();

        Intent intent = new Intent(MainActivity.this, PeringkatActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        startActivity(intent);
    }

    @OnClick(R.id.ibBantuan)
    public void openBandtuan() {
        Intent intent = new Intent(MainActivity.this, BantuanActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ibTentang)
    public void openTentang() {
        Intent intent = new Intent(MainActivity.this, TentangActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Aplikasi akan ditutup. Lanjutkan?");
        builder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                });
        builder.setNegativeButton("Batal",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
