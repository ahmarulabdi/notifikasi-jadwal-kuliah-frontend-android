package rndmjck.com.notifikasijadwalkuliahtiuniks.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rndmjck.com.notifikasijadwalkuliahtiuniks.model.Users;

/**
 * Created by rndmjck on 21/08/18.
 */

public class DetailUsersResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private Users data;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Users getData() {
        return data;
    }

    public void setData(Users data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

