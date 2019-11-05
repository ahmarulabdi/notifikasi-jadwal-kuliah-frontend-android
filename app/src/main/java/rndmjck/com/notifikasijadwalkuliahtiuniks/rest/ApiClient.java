package rndmjck.com.notifikasijadwalkuliahtiuniks.rest;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rndmjck.com.notifikasijadwalkuliahtiuniks.config.ServerConfig;

public class ApiClient {

    private static final String BASE_URL = ServerConfig.API_ENDPOINT;

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit =  new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
