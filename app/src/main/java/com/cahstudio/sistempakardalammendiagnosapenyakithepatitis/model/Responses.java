package com.cahstudio.sistempakardalammendiagnosapenyakithepatitis.model;

import com.google.gson.annotations.SerializedName;

public class Responses {
    @SerializedName("status")
    private int status;
    @SerializedName("")
    private int id;
    @SerializedName("message")
    private String message;
    @SerializedName("kode")
    private String kode;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
