package com.example.studentportal.Fitur;

public class DataOrganisasi {
    private String idOrganisasi, namaOrganisasi, tempat, tahunMasuk, tahunKeluar, jabatan, verifikasi, scanBukti;

    public DataOrganisasi() {
    }

    public DataOrganisasi(String idOrganisasi, String namaOrganisasi, String tempat, String tahunMasuk, String tahunKeluar, String jabatan, String verifikasi, String scanBukti) {
        this.idOrganisasi = idOrganisasi;
        this.namaOrganisasi = namaOrganisasi;
        this.tempat = tempat;
        this.tahunMasuk = tahunMasuk;
        this.tahunKeluar = tahunKeluar;
        this.jabatan = jabatan;
        this.verifikasi = verifikasi;
        this.scanBukti = scanBukti;
    }


    public String getIdOrganisasi() {
        return idOrganisasi;
    }

    public void setIdOrganisasi(String idOrganisasi) {
        this.idOrganisasi = idOrganisasi;
    }

    public String getNamaOrganisasi() {
        return namaOrganisasi;
    }

    public void setNamaOrganisasi(String namaOrganisasi) {
        this.namaOrganisasi = namaOrganisasi;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(String tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public String getTahunKeluar() {
        return tahunKeluar;
    }

    public void setTahunKeluar(String tahunKeluar) {
        this.tahunKeluar = tahunKeluar;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
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
