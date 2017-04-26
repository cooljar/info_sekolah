package id.co.unila.navia.infosekolah.rest_api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by japra_awok on 13/03/2017.
 */

public class ServiceGenerator {
    public static final String API_BASE_URL = "http://carisekolahmetro.esy.es/api/web/";
    //public static final String API_BASE_URL = "http://192.168.43.88/my_project/sekolah/api/web/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass, OkHttpClient httpClient) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
