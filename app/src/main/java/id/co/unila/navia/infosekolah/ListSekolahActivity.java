package id.co.unila.navia.infosekolah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.unila.navia.infosekolah.active_record.ArSekolah;
import id.co.unila.navia.infosekolah.adapter.ListSekolahAdapter;
import id.co.unila.navia.infosekolah.helpers.OnItemClickListener;
import id.co.unila.navia.infosekolah.model.Sekolah;
import id.co.unila.navia.infosekolah.rest_api.MyOkHttpInterceptor;
import id.co.unila.navia.infosekolah.rest_api.SekolahEndpoint;
import id.co.unila.navia.infosekolah.rest_api.ServiceGenerator;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSekolahActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ProgressDialog progress;
    private LinearLayoutManager mLinearLayoutManager;
    private ListSekolahAdapter mAdapter;

    private ArSekolah arSekolah;
    private List<Sekolah> mSekolahList = new ArrayList<>();
    String tipe = "";

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_sekolah);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data Sekolah");

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

        mAdapter = new ListSekolahAdapter(this, mSekolahList);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Sekolah sekolah = mSekolahList.get(position);

                Intent intent = new Intent(ListSekolahActivity.this, SekolahDetailActivity.class);
                intent.putExtra("SEKOLAH", Parcels.wrap(sekolah));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mSekolahList.clear();
                mSekolahList.addAll(arSekolah.findAll());
                mAdapter.notifyDataSetChanged();

                return false;
            }
        });

        searchView.setQueryHint("Cari berdasarkan kecamatan..");
        searchView.setOnQueryTextListener(this);

        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (TextUtils.isEmpty(query)) {
            return false;
        }else{
            Log.e("SCH", query);

            mSekolahList.clear();

            List<Sekolah> sekolahFiltered = new ArrayList<Sekolah>();

            if (query.length() > 0){
                Log.e("SCH1", query);
                sekolahFiltered.addAll(arSekolah.findAllByKecamatan(query));
            }else{
                Log.e("SCH2", query);
                sekolahFiltered.addAll(arSekolah.findAll());
            }


            mSekolahList.addAll(sekolahFiltered);
            mAdapter.notifyDataSetChanged();
        }

        return true;
    }
}
