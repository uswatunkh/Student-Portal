package com.example.studentportal.Fitur;

public class DataMagang {

    private String idMagang, judul, tempat,provinsi,kota,tanggalmulaiMagang,tanggalselesaiMagang,ringkasan,
            scanBukti,uploadLaporan,verifikasi;
    public DataMagang() {
    }

    public DataMagang(String idMagang, String judul, String tempat, String provinsi, String kota,String tanggalmulaiMagang,
                      String tanggalselesaiMagang,String ringkasan,String scanBukti, String uploadLaporan,String verifikasi) {
        this.idMagang = idMagang;
        this.judul = judul;
        this.tempat = tempat;
        this.provinsi = provinsi;
        this.kota = kota;
        this.tanggalmulaiMagang = tanggalmulaiMagang;
        this.tanggalselesaiMagang = tanggalselesaiMagang;
        this.ringkasan = ringkasan;
        this.scanBukti = scanBukti;
        this.uploadLaporan = uploadLaporan;
        this.verifikasi = verifikasi;
    }

    public String getIdMagang() {
        return idMagang;
    }

    public void setIdMagang(String idMagang) {
        this.idMagang = idMagang;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getTanggalmulaiMagang() {
        return tanggalmulaiMagang;
    }

    public void setTanggalmulaiMagang(String tanggalmulaiMagang) {
        this.tanggalmulaiMagang = tanggalmulaiMagang;
    }

    public String getTanggalselesaiMagang() {
        return tanggalselesaiMagang;
    }

    public void setTanggalselesaiMagang(String tanggalselesaiMagang) {
        this.tanggalselesaiMagang = tanggalselesaiMagang;
    }

    public String getRingkasan() {
        return ringkasan;
    }

    public void setRingkasan(String ringkasan) {
        this.ringkasan = ringkasan;
    }

    public String getScanBukti() {
        return scanBukti;
    }

    public void setScanBukti(String scanBukti) {
        this.scanBukti = scanBukti;
    }

    public String getUploadLaporan() {
        return uploadLaporan;
    }

    public void setUploadLaporan(String uploadLaporan) {
        this.uploadLaporan = uploadLaporan;
    }

    public String getVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(String verifikasi) {
        this.verifikasi = verifikasi;
    }
}
