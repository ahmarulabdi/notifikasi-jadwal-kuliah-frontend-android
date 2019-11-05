package rndmjck.com.notifikasijadwalkuliahtiuniks.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rndmjck.com.notifikasijadwalkuliahtiuniks.response.JadwalKuliahResponse;
import rndmjck.com.notifikasijadwalkuliahtiuniks.response.DetailUsersResponse;
import rndmjck.com.notifikasijadwalkuliahtiuniks.response.FlagIsiKRSResponse;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("users/login")
    Call<DetailUsersResponse> login(
            @Field("nip_nim") String nipNim,
            @Field("password") String password
    );

    @GET("index/isikrs")
    Call<FlagIsiKRSResponse> flagIsiKRS();

    @FormUrlEncoded
    @POST("index/setisikrs")
    Call<FlagIsiKRSResponse> setIsiKRS(
            @Field("isi_buka") Boolean isiBuka
    );

    @GET("jadwalkuliah/data")
    Call<JadwalKuliahResponse> getJadwalKuliah();

    @FormUrlEncoded
    @POST("jadwalkuliah/reviewkrs")
    Call<JadwalKuliahResponse> reviewKRS(
            @Field("id_jadwal_kuliahs[]") List<Integer> idJadwalKuliah
    );

    @FormUrlEncoded
    @POST("jadwalkuliah/simpankrs")
    Call<JadwalKuliahResponse> simpanKRS(
            @Field("id_users") String idUsers,
            @Field("id_jadwal_kuliahs[]") List<Integer> idJadwalKuliah
    );

    @GET("jadwalkuliah/jadwalmahasiswa/{id_users}")
    Call<JadwalKuliahResponse> jadwalMahasiswa(
            @Path("id_users") String idUsers
    );


}
