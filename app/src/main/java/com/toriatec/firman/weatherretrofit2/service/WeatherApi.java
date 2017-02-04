package com.toriatec.firman.weatherretrofit2.service;

import com.toriatec.firman.weatherretrofit2.model.weather.ModelWeather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by firmanmac on 2/4/17.
 */

public interface WeatherApi {

    //https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Jakarta%2C%20ID%22)%20and%20u%3D%27c%27&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys

    public static String baseURL = "https://query.yahooapis.com/v1/public/";

    Retrofit client = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Jakarta%2C%20ID%22)%20and%20u%3D%27c%27&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    public Call<ModelWeather> getModelWeatherCall();

}
