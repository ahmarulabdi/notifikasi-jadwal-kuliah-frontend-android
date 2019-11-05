package rndmjck.com.notifikasijadwalkuliahtiuniks.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import rndmjck.com.notifikasijadwalkuliahtiuniks.model.JadwalKuliah;

/**
 * Created by rndmjck on 22/08/18.
 */

public class JadwalKuliahResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<JadwalKuliah> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<JadwalKuliah> getData() {
        return data;
    }

    public void setData(List<JadwalKuliah> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
