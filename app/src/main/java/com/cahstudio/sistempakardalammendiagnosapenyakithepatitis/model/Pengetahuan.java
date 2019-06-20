package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model;

import com.google.gson.annotations.SerializedName;

public class Pengetahuan {
    @SerializedName("id_pengetahuan")
    private int idPengetahuan;
    @SerializedName("kode_gejala")
    private String kodeGejala;
    @SerializedName("id_penyakit")
    private String idPenyakit;
    @SerializedName("bobot")
    private String bobot;

    public int getIdPengetahuan() {
        return idPengetahuan;
    }

    public void setIdPengetahuan(int idPengetahuan) {
        this.idPengetahuan = idPengetahuan;
    }

    public String getKodeGejala() {
        return kodeGejala;
    }

    public void setKodeGejala(String kodeGejala) {
        this.kodeGejala = kodeGejala;
    }

    public String getIdPenyakit() {
        return idPenyakit;
    }

    public void setIdPenyakit(String idPenyakit) {
        this.idPenyakit = idPenyakit;
    }

    public String getBobot() {
        return bobot;
    }

    public void setBobot(String bobot) {
        this.bobot = bobot;
    }
}
