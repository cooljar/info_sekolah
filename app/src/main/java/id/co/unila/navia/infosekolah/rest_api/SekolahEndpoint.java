package id.co.unila.navia.infosekolah.rest_api;

import java.util.List;

import id.co.unila.navia.infosekolah.model.Sekolah;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by japra_awok on 13/03/2017.
 */

public interface SekolahEndpoint {
    @GET("mst-sekolah")
    Call<List<Sekolah>> getSekolahList(
            @Query("MstSekolahSearch[namaTipe]") String namaTipe,
            @Query("MstSekolahSearch[namaKecamatan]") String namaKecamatan,
            @Query("MstSekolahSearch[nama]") String nama
    );
}
