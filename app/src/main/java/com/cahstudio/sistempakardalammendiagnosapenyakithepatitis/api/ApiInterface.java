package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.api;

import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Admin;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Gejala;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengetahuan;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Pengguna;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Penyakit;
import com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model.Responses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("android_service/getGejala.php")
    Call<List<Gejala>> getGejalaList();

    @GET("android_service/getPengetahuanByGejala.php")
    Call<List<Pengetahuan>> getPengetahuanByGejala(@Query("gejalas") String gejalas);

    @GET("android_service/getPenyakit.php")
    Call<List<Penyakit>> getPenyakitList();

    @GET("android_service/getPengguna.php")
    Call<List<Pengguna>> getPenggunaList();

    @GET("android_service/getPengetahuan.php")
    Call<List<Pengetahuan>> getPengetahuanList();

    @POST("android_service/sendHasilDiagnosa.php")
    Call<Responses> sendHasilDiagnosa(@Body Pengguna pengguna);

    @POST("android_service/login.php")
    Call<Responses> login(@Body Admin admin);

    @GET("android_service/deletePenggunaById.php")
    Call<Responses> deletePengguna(@Query("id") int id);

    @GET("android_service/deleteGejalaByKode.php")
    Call<Responses> deleteGejala(@Query("kode_gejala") String id);

    @GET("android_service/deletePenyakitById.php")
    Call<Responses> deletePenyakit(@Query("kode_gejala") String id);

    @GET("android_service/deletePengetahuanById.php")
    Call<Responses> deletePengetahuan(@Query("id") int id);

    @POST("android_service/addGejala.php")
    Call<Responses> tambahGejala(@Body Gejala gejala);

    @POST("android_service/addPenyakit.php")
    Call<Responses> tambahPenyakit(@Body Penyakit penyakit);

    @POST("android_service/addPengetahuan.php")
    Call<Responses> tambahPengetahuan(@Body Pengetahuan pengetahuan);

    @GET("android_service/getPenyakitById.php")
    Call<Penyakit> getPenyakitById(@Query("id_penyakit") String idPenyakit);

    @GET("android_service/getGejalaByKode.php")
    Call<Gejala> getGejalaByKode(@Query("kode_gejala") String kodeGejala);

    @GET("android_service/getPengetahuanById.php")
    Call<Pengetahuan> getPengetahuanById(@Query("id_pengetahuan") int idPengetahuan);

    @GET("android_service/getPenggunaById.php")
    Call<Pengguna> getPenggunaById(@Query("id") int id);

    @POST("android_service/updatePenyakitById.php")
    Call<Responses> updatePenyakit(@Body Penyakit penyakit);

    @POST("android_service/updateGejalaByKode.php")
    Call<Responses> updateGejala(@Body Gejala gejala);

    @POST("android_service/updatePengetahuanById.php")
    Call<Responses> updatePengetahuan(@Body Pengetahuan pengetahuan);
}
