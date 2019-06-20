package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model;

import com.google.gson.annotations.SerializedName;

public class Penyakit {
    @SerializedName("id_penyakit")
    private String idPenyakit;
    @SerializedName("nama_penyakit")
    private String namaPenyakit;
    @SerializedName("tingkat_kepastian")
    private float tingkatKepastian;
    @SerializedName("total_bobot")
    private float totalBobot;
    private float valueCF;
    private float valueDS;

    public String getIdPenyakit() {
        return idPenyakit;
    }

    public void setIdPenyakit(String idPenyakit) {
        this.idPenyakit = idPenyakit;
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public float getTingkatKepastian() {
        return tingkatKepastian;
    }

    public void setTingkatKepastian(float tingkatKepastian) {
        this.tingkatKepastian = tingkatKepastian;
    }

    public float getTotalBobot() {
        return totalBobot;
    }

    public void setTotalBobot(float totalBobot) {
        this.totalBobot = totalBobot;
    }

    public float getValueCF() {
        return valueCF;
    }

    public void setValueCF(float valueCF) {
        this.valueCF = valueCF;
    }

    public float getValueDS() {
        return valueDS;
    }

    public void setValueDS(float valueDS) {
        this.valueDS = valueDS;
    }
}
