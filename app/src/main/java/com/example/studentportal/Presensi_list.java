package com.example.studentportal;

public class Presensi_list {

    private String mingguKe;
    private String tanggalPresensi;
    private String namaMK;
    private String ket;

    public Presensi_list(String mingguKe, String tanggalPresensi, String namaMK, String ket) {

        this.mingguKe = mingguKe;
        this.tanggalPresensi = tanggalPresensi;
        this.namaMK = namaMK;
        this.ket = ket;

    }

    public String getMingguKe() {
        return mingguKe;
    }

    public void setMingguKe(String mingguKe) {
        this.mingguKe = mingguKe;
    }

    public String getTanggalPresensi() {
        return tanggalPresensi;
    }

    public void setTanggalPresensi(String tanggalPresensi) {
        this.tanggalPresensi = tanggalPresensi;
    }

    public String getNamaMK() {
        return namaMK;
    }

    public void setNamaMK(String namaMK) {
        this.namaMK = namaMK;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }
}
