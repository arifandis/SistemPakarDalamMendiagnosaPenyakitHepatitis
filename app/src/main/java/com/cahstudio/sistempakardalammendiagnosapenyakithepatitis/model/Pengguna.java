package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pengguna {
    @SerializedName("id")
    private int id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("usia")
    @Expose
    private String usia;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("gejala")
    @Expose
    private String gejala;
    @SerializedName("kemungkinan_cf")
    @Expose
    private String kemungkinanCf;
    @SerializedName("kemungkinan_ds")
    @Expose
    private String kemungkinanDs;
    @SerializedName("tanggal")
    private String tanggal;

    public Pengguna(int id, String nama, String usia, String jenisKelamin, String gejala, String kemungkinanCf, String kemungkinanDs) {
        this.id = id;
        this.nama = nama;
        this.usia = usia;
        this.jenisKelamin = jenisKelamin;
        this.gejala = gejala;
        this.kemungkinanCf = kemungkinanCf;
        this.kemungkinanDs = kemungkinanDs;
    }

    public Pengguna() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getGejala() {
        return gejala;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

    public String getKemungkinanCf() {
        return kemungkinanCf;
    }

    public void setKemungkinanCf(String kemungkinanCf) {
        this.kemungkinanCf = kemungkinanCf;
    }

    public String getKemungkinanDs() {
        return kemungkinanDs;
    }

    public void setKemungkinanDs(String kemungkinanDs) {
        this.kemungkinanDs = kemungkinanDs;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
