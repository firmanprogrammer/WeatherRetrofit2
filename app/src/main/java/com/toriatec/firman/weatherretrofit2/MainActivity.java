package com.toriatec.firman.weatherretrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.toriatec.firman.weatherretrofit2.adapter.AdapterWeather;
import com.toriatec.firman.weatherretrofit2.model.weather.Forecast;
import com.toriatec.firman.weatherretrofit2.model.weather.ModelWeather;
import com.toriatec.firman.weatherretrofit2.service.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.id_rv_main);
        progressBar = (ProgressBar) findViewById(R.id.id_progress_main);

        progressBar.setVisibility(View.INVISIBLE); //menghilangkan progress bar

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDataWeathers();
    }

    private void loadDataWeathers() {

        progressBar.setVisibility(View.VISIBLE);//menampilkan progress bar

        WeatherApi apiService = WeatherApi.client.create(WeatherApi.class);//membuat service
        Call<ModelWeather> call = apiService.getModelWeatherCall(); //melakukan call dengan method getQuestion namun in hanya untuk call karena deklarasi GET sudah ada di link WeatherApi.

        //proses call
        call.enqueue(new Callback<ModelWeather>() {
            @Override
            public void onResponse(Call<ModelWeather> call, Response<ModelWeather> response) {
                ModelWeather modelWeather = response.body(); //merubah respon body menjadi model

                AdapterWeather adapter = new AdapterWeather(getApplicationContext(), modelWeather.getQuery().getResults().getChannel().getItem().getForecast(),
                    new AdapterWeather.OnItemClickListener() {
                        @Override
                        public void onClick(Forecast Item) {
                            Toast.makeText(getApplicationContext(),"Day: "+Item.getDay(),Toast.LENGTH_LONG).show();
                        }
                });
                progressBar.setVisibility(View.INVISIBLE); //menghilangkan progres bar
                recyclerView.setAdapter(adapter); //men-set adapter pada recycler view
            }

            @Override
            public void onFailure(Call<ModelWeather> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.fillInStackTrace()); //Log error
                progressBar.setVisibility(View.INVISIBLE); //menghilangkan progres bar
            }
        });
    }
}
