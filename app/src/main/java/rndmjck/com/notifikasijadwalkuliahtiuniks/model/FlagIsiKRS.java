package rndmjck.com.notifikasijadwalkuliahtiuniks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rndmjck on 21/08/18.
 */

public class FlagIsiKRS {

    @SerializedName("buka")
    @Expose
    private Boolean buka;

    public Boolean getBuka() {
        return buka;
    }

    public void setBuka(Boolean buka) {
        this.buka = buka;
    }
}
