package com.example.studentportal.Fitur;

public class DataKeterampilan {

    private String idKeterampilan, namaKeterampilan, jenis,tingkat,verifikasi,scanBukti;

    public DataKeterampilan() {
    }

    public DataKeterampilan(String idKeterampilan, String namaKeterampilan, String jenis, String tingkat, String verifikasi, String scanBukti) {
        this.idKeterampilan = idKeterampilan;
        this.namaKeterampilan = namaKeterampilan;
        this.jenis = jenis;
        this.tingkat = tingkat;
        this.verifikasi = verifikasi;
        this.scanBukti = scanBukti;
    }

    public String getIdKeterampilan() {
        return idKeterampilan;
    }

    public void setIdKeterampilan(String idKeterampilan) {
        this.idKeterampilan = idKeterampilan;
    }

    public String getNamaKeterampilan() {
        return namaKeterampilan;
    }

    public void setNamaKeterampilan(String namaKeterampilan) {
        this.namaKeterampilan = namaKeterampilan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTingkat() {
        return tingkat;
    }

    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }

    public String getVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(String verifikasi) {
        this.verifikasi = verifikasi;
    }

    public String getScanBukti() {
        return scanBukti;
    }

    public void setScanBukti(String scanBukti) {
        this.scanBukti = scanBukti;
    }
}
