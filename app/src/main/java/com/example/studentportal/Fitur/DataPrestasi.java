package com.example.studentportal.Fitur;

public class DataPrestasi {
    private String idPrestasi, namaLomba,tahun,juara,tingkat,jenis,verifikasi,scanBukti;

    public DataPrestasi() {
    }

    public DataPrestasi(String idPrestasi, String namaLomba,String tahun,String juara,  String tingkat, String jenis, String verifikasi, String scanBukti) {
        this.idPrestasi = idPrestasi;
        this.namaLomba = namaLomba;
        this.tahun = tahun;
        this.juara = juara;
        this.tingkat = tingkat;
        this.jenis = jenis;
        this.verifikasi = verifikasi;
        this.scanBukti = scanBukti;
    }

    public String getIdPrestasi() {
        return idPrestasi;
    }

    public void setIdPrestasi(String idPrestasi) {
        this.idPrestasi = idPrestasi;
    }

    public String getNamaLomba() {
        return namaLomba;
    }

    public void setNamaLomba(String namaLomba) {
        this.namaLomba = namaLomba;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getJuara() {
        return juara;
    }

    public void setJuara(String juara) {
        this.juara = juara;
    }

    public String getTingkat() {
        return tingkat;
    }

    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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
