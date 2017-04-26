package id.co.unila.navia.infosekolah;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.unila.navia.infosekolah.active_record.ArSekolah;
import id.co.unila.navia.infosekolah.model.Sekolah;

public class PeringkatActivity extends AppCompatActivity {

    private ArSekolah arSekolah;

    @BindView(R.id.btSd) Button btSd;
    @BindView(R.id.btSmp) Button btSmp;
    @BindView(R.id.btSma) Button btSma;
    @BindView(R.id.btSmk) Button btSmk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_peringkat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Peringkat");

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("SEKOLAH")) {
                List<Sekolah> data = Parcels.unwrap(getIntent().getParcelableExtra("SEKOLAH"));
                arSekolah = new ArSekolah(this, data);
            }
        }
    }

    @OnClick(R.id.btSd)
    public void listSd() {
        List<Sekolah> data = arSekolah.findAllByTipeId("Sekolah Dasar (SD)");

        Intent intent = new Intent(PeringkatActivity.this, ListSekolahPeringkatActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        intent.putExtra("TIPE", "Sekolah Dasar (SD)");
        startActivity(intent);
    }

    @OnClick(R.id.btSmp)
    public void listSmp() {
        List<Sekolah> data = arSekolah.findAllByTipeId("Sekolah Menengah Pertama (SMP)");

        Intent intent = new Intent(PeringkatActivity.this, ListSekolahPeringkatActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        intent.putExtra("TIPE", "Sekolah Menengah Pertama (SMP)");
        startActivity(intent);
    }

    @OnClick(R.id.btSma)
    public void listSma() {
        List<Sekolah> data = arSekolah.findAllByTipeId("Sekolah Menengah Atas (SMA)");

        Intent intent = new Intent(PeringkatActivity.this, ListSekolahPeringkatActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        intent.putExtra("TIPE", "Sekolah Menengah Atas (SMA)");
        startActivity(intent);
    }

    @OnClick(R.id.btSmk)
    public void listSmk() {
        List<Sekolah> data = arSekolah.findAllByTipeId("Sekolah Menengah Kejuruan (SMK)");

        Intent intent = new Intent(PeringkatActivity.this, ListSekolahPeringkatActivity.class);
        intent.putExtra("SEKOLAH", Parcels.wrap(data));
        intent.putExtra("TIPE", "Sekolah Menengah Kejuruan (SMK)");
        startActivity(intent);
    }
}
