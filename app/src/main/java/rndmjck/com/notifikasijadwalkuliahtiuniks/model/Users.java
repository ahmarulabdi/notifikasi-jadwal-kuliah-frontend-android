package rndmjck.com.notifikasijadwalkuliahtiuniks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rndmjck on 14/08/18.
 */

public class Users {
    @SerializedName("id_users")
    @Expose
    private String idUsers;
    @SerializedName("nip_nim")
    @Expose
    private String nipNim;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("hak_akses")
    @Expose
    private String hakAkses;

    public String getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(String idUsers) {
        this.idUsers = idUsers;
    }

    public String getNipNim() {
        return nipNim;
    }

    public void setNipNim(String nipNim) {
        this.nipNim = nipNim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }
}
