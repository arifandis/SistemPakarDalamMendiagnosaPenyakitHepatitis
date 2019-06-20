package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model;

import com.google.gson.annotations.SerializedName;

public class Gejala {
    @SerializedName("kode_gejala")
    private String kodeGejala;
    @SerializedName("gejala")
    private String gejala;
    @SerializedName("bobot")
    private String bobot;

    public String getKodeGejala() {
        return kodeGejala;
    }

    public void setKodeGejala(String kodeGejala) {
        this.kodeGejala = kodeGejala;
    }

    public String getGejala() {
        return gejala;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }

    public String getBobot() {
        return bobot;
    }

    public void setBobot(String bobot) {
        this.bobot = bobot;
    }
}
