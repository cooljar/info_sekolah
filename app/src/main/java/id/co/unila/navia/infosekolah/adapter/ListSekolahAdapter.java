package id.co.unila.navia.infosekolah.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.unila.navia.infosekolah.R;
import id.co.unila.navia.infosekolah.helpers.OnItemClickListener;
import id.co.unila.navia.infosekolah.model.Sekolah;

/**
 * Created by japra_awok on 24/03/2017.
 */

public class ListSekolahAdapter extends RecyclerView.Adapter<ListSekolahAdapter.ViewHolder> {

    private Context context;
    private List<Sekolah> sekolahList;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public ListSekolahAdapter(Context context, List<Sekolah> oderList) {
        this.context = context;
        this.sekolahList = oderList;
    }

    @Override
    public ListSekolahAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sekolah_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListSekolahAdapter.ViewHolder holder, int position) {
        Sekolah sekolah = sekolahList.get(position);
        holder.tvNamaSekolah.setText(sekolah.getNama());
        holder.tvNpsn.setText(sekolah.getNpsn());
        holder.tvAlamat.setText(sekolah.alamat);
        holder.tvTelp.setText(sekolah.no_telp);
        Picasso.with(context)
                .load(sekolah.foto)
                .resize(230, 230)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.ivGbr);
    }

    @Override
    public int getItemCount() {
        return sekolahList == null ? 0 : sekolahList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tvNamaSekolah)TextView tvNamaSekolah;
        @BindView(R.id.tvNpsn) TextView tvNpsn;
        @BindView(R.id.tvAlamat) TextView tvAlamat;
        @BindView(R.id.tvTelp) TextView tvTelp;
        @BindView(R.id.ivGbr) ImageView ivGbr;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
