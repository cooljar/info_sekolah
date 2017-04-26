package id.co.unila.navia.infosekolah.active_record;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.unila.navia.infosekolah.model.Sekolah;

/**
 * Created by japra_awok on 17/04/2017.
 */

public class ArSekolah {
    private Context context;
    public List<Sekolah> mSekolah;

    public ArSekolah(Context context, List<Sekolah> mSekolah) {
        this.context = context;
        this.mSekolah = mSekolah;
    }

    public List<Sekolah> findAll(){
        return mSekolah;
    }

    public List<Sekolah> findAllByTipeId(String tipe){
        List<Sekolah> sekolahFiltered = new ArrayList<Sekolah>();

        for(Sekolah skl : mSekolah){
            if (tipe.length() > 0){
                if(tipe.equals(skl.tipe)){
                    sekolahFiltered.add(skl);
                }
            }
        }

        return sekolahFiltered;
    }

    public List<Sekolah> findAllByKecamatan(String kecamatan){
        Log.e("SCH_1", kecamatan);
        List<Sekolah> sekolahFiltered = new ArrayList<Sekolah>();

        for(Sekolah skl : mSekolah){
            Log.e("SCH_2", kecamatan);
            if (kecamatan.length() > 0){
                Log.e("SCH_3", kecamatan);
                Log.e("SCH_3", kecamatan.toLowerCase());
                Log.e("SCH_3", skl.kecamatan.toLowerCase());
                if(skl.kecamatan.toLowerCase().contains(kecamatan.toLowerCase())){
                    Log.e("SCH_4", kecamatan);
                    sekolahFiltered.add(skl);
                }
            }
        }

        return sekolahFiltered;
    }
}
