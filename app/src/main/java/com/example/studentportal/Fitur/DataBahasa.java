package com.example.studentportal.Fitur;

public class DataBahasa {
    private String idBahasa, periodeBahasa, tahunWisuda, namaBahasa, skor, tanggalTes, verifikasi, scanBukti;

    public DataBahasa() {
    }

    public DataBahasa(String idBahasa, String periodeBahasa, String tahunWisuda, String namaBahasa, String skor, String tanggalTes, String verifikasi, String scanBukti) {
        this.idBahasa = idBahasa;
        this.periodeBahasa = periodeBahasa;
        this.tahunWisuda = tahunWisuda;
        this.namaBahasa = namaBahasa;
        this.skor = skor;
        this.tanggalTes = tanggalTes;
        this.verifikasi = verifikasi;
        this.scanBukti = scanBukti;
    }

    public String getIdBahasa() {
        return idBahasa;
    }

    public void setIdBahasa(String idBahasa) {
        this.idBahasa = idBahasa;
    }

    public String getPeriodeBahasa() {
        return periodeBahasa;
    }

    public void setPeriodeBahasa(String periodeBahasa) {
        this.periodeBahasa = periodeBahasa;
    }

    public String getTahunWisuda() {
        return tahunWisuda;
    }

    public void setTahunWisuda(String tahunWisuda) {
        this.tahunWisuda = tahunWisuda;
    }

    public String getNamaBahasa() {
        return namaBahasa;
    }

    public void setNamaBahasa(String namaBahasa) {
        this.namaBahasa = namaBahasa;
    }

    public String getSkor() {
        return skor;
    }

    public void setSkor(String skor) {
        this.skor = skor;
    }

    public String getTanggalTes() {
        return tanggalTes;
    }

    public void setTanggalTes(String tanggalTes) {
        this.tanggalTes = tanggalTes;
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
