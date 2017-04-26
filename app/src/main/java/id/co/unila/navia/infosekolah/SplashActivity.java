package id.co.unila.navia.infosekolah;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import id.co.unila.navia.infosekolah.model.Sekolah;
import id.co.unila.navia.infosekolah.rest_api.MyOkHttpInterceptor;
import id.co.unila.navia.infosekolah.rest_api.SekolahEndpoint;
import id.co.unila.navia.infosekolah.rest_api.ServiceGenerator;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    //CountDownTimer ctdn;
    ProgressBar mProgressBar;
    private List<Sekolah> mSekolahList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setIndeterminate(true);

        OkHttpClient httpClient = new MyOkHttpInterceptor().getOkHttpClient();
        SekolahEndpoint service = ServiceGenerator.createService(SekolahEndpoint.class, httpClient);
        Call<List<Sekolah>> call = service.getSekolahList("", "", "");

        call.enqueue(new Callback<List<Sekolah>>() {
            @Override
            public void onResponse(Call<List<Sekolah>> call, Response<List<Sekolah>> response) {
                mProgressBar.setIndeterminate(false);
                if(response.isSuccessful()){
                    if(response.body().size() > 0){
                        mSekolahList.addAll(response.body());

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra("SEKOLAH", Parcels.wrap(mSekolahList));
                        startActivity(intent);

                        SplashActivity.this.finish();
                    }else {
                        Toast.makeText(SplashActivity.this, "Tidak ada data ditemukan..!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    String failureMessage = response.message();
                    Toast.makeText(SplashActivity.this, failureMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Sekolah>> call, Throwable t) {
                mProgressBar.setIndeterminate(false);
                Toast.makeText(SplashActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*ctdn = new CountDownTimer(5*1000, 1000) {
            int i=0;
            public void onTick(long millisUntilFinished) {
                i++;
                mProgressBar.setProgress(i);

            }
            public void onFinish() {
                i++;
                mProgressBar.setProgress(i);

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                SplashActivity.this.finish();
            }
        }.start();*/
    }
}
