package id.co.unila.navia.infosekolah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.unila.navia.infosekolah.active_record.ArSekolah;
import id.co.unila.navia.infosekolah.model.Sekolah;
import id.co.unila.navia.infosekolah.rest_api.MyOkHttpInterceptor;
import id.co.unila.navia.infosekolah.rest_api.SekolahEndpoint;
import id.co.unila.navia.infosekolah.rest_api.ServiceGenerator;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSekolahPeringkatActivity extends AppCompatActivity implements
        TabLayout.OnTabSelectedListener{

    private TabLayout.Tab tab;
    private ArSekolah arSekolah;
    private List<Sekolah> mSekolahList = new ArrayList<>();
    String tipe = "";

    @BindView(R.id.tabs) TabLayout tabLayout;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sekolah_peringkat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Peringkat Sekolah");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("SEKOLAH")) {
                List<Sekolah> data = Parcels.unwrap(getIntent().getParcelableExtra("SEKOLAH"));
                arSekolah = new ArSekolah(this, data);
                mSekolahList.addAll(arSekolah.findAll());

            }

            if (extras.containsKey("TIPE")) {
                tipe = intent.getStringExtra("TIPE");
            }
        }

        getSupportActionBar().setSubtitle(tipe);

        ButterKnife.bind(this);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("AKREDITASI"));
        tabLayout.addTab(tabLayout.newTab().setText("RATA-RATA UN"));
        tabLayout.addTab(tabLayout.newTab().setText("UN TERTINGGI"));
        tabLayout.addTab(tabLayout.newTab().setText("DAYA TAMPUNG"));
        tabLayout.addTab(tabLayout.newTab().setText("PRESTASI"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(this);

        tab = tabLayout.getTabAt(0);
        onTabSelected(tab);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.e("SWIPE", "SWIPED" + String.valueOf(tab.getPosition()));

        List<Sekolah> sortedSekolah = mSekolahList;

        final int tabPosition = tab.getPosition();

        Collections.sort(sortedSekolah, new Comparator<Sekolah>() {
            @Override
            public int compare(Sekolah lhs, Sekolah rhs) {
                int result = 0;
                Double l;
                Double r;
                switch (tabPosition) {
                    case 0:
                        result = lhs.akreditasi.compareTo(rhs.akreditasi);
                        break;
                    case 1:
                        l = Double.parseDouble(lhs.r_nilai_un);
                        r = Double.parseDouble(rhs.r_nilai_un);
                        result = l.compareTo(r);
                        break;
                    case 2:
                        l = Double.parseDouble(lhs.max_nilai_un);
                        r = Double.parseDouble(rhs.max_nilai_un);
                        result = l.compareTo(r);
                        break;
                    case 3:
                        result =  lhs.daya_tampung.compareTo(rhs.daya_tampung);
                        break;
                    case 4:
                        result =  lhs.jumlah_prestasi.compareTo(rhs.jumlah_prestasi);
                        break;
                }

                return result;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch(tabPosition) {
            case 0:
                transaction.replace(R.id.container, SekolahListFragment.newInstance(sortedSekolah));
                break;
            case 1:
                transaction.replace(R.id.container, SekolahListFragment.newInstance(sortedSekolah));
                break;
            case 2:
                transaction.replace(R.id.container, SekolahListFragment.newInstance(sortedSekolah));
                break;
            case 3:
                transaction.replace(R.id.container, SekolahListFragment.newInstance(sortedSekolah));
                break;
            case 4:
                transaction.replace(R.id.container, SekolahListFragment.newInstance(sortedSekolah));
                break;
            default:
        }

        transaction.commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
