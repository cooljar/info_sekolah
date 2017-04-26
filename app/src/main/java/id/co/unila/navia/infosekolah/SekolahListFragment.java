package id.co.unila.navia.infosekolah;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.unila.navia.infosekolah.adapter.ListSekolahAdapter;
import id.co.unila.navia.infosekolah.helpers.OnItemClickListener;
import id.co.unila.navia.infosekolah.model.Sekolah;

import java.util.ArrayList;
import java.util.List;

public class SekolahListFragment extends Fragment {
    private List<Sekolah> mSekolahList = new ArrayList<>();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    public SekolahListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SekolahListFragment newInstance(List<Sekolah> sekolahList) {
        SekolahListFragment fragment = new SekolahListFragment();
        Bundle args = new Bundle();
        args.putParcelable("LIST_SEKOLAH", Parcels.wrap(sekolahList));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSekolahList = Parcels.unwrap(getArguments().getParcelable("LIST_SEKOLAH"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sekolah, container, false);

        ButterKnife.bind(this, view);

        ListSekolahAdapter mAdapter = new ListSekolahAdapter(getActivity(), mSekolahList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Sekolah sekolah = mSekolahList.get(position);
                Toast.makeText(getActivity(), sekolah.nama, Toast.LENGTH_SHORT).show();

                    /*Intent intent = new Intent(ListSekolahActivity.this, SekolahDetailActivity.class);
                    intent.putExtra("SEKOLAH", Parcels.wrap(sekolah));
                    startActivity(intent);*/
            }
        });
        return view;
    }
}
