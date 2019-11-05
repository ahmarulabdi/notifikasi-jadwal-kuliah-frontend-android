package rndmjck.com.notifikasijadwalkuliahtiuniks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rndmjck on 15/08/18.
 */

public class JadwalKuliah {

    @SerializedName("id_jadwal_kuliah")
    @Expose
    private String idJadwalKuliah;
    @SerializedName("kode")
    @Expose
    private String kode;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("tempat")
    @Expose
    private String tempat;
    @SerializedName("hari")
    @Expose
    private String hari;
    @SerializedName("jam_mulai")
    @Expose
    private String jamMulai;
    @SerializedName("jam_selesai")
    @Expose
    private String jamSelesai;
    @SerializedName("sks")
    @Expose
    private String sks;
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("nama_dosen")
    @Expose
    private String namaDosen;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    public String getIdJadwalKuliah() {
        return idJadwalKuliah;
    }

    public void setIdJadwalKuliah(String idJadwalKuliah) {
        this.idJadwalKuliah = idJadwalKuliah;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
