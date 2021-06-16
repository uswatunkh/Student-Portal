package com.example.studentportal;

public class HasilStudi_list {

    int idNomor;
    private String kode;
    private String namaKuliah;
    private String sks;
    private String nilaiAngka;
    private String nilaiHuruf;
    private String totalNilai;


    public HasilStudi_list( String kode, String namaKuliah, String sks, String nilaiAngka,String nilaiHuruf, String totalNilai) {
        this.idNomor = idNomor;
        this.kode = kode;
        this.namaKuliah = namaKuliah;
        this.sks = sks;
        this.nilaiAngka = nilaiAngka;
        this.nilaiHuruf = nilaiHuruf;
        this.totalNilai = totalNilai;

    }

    public int getIdNomor() {
        return idNomor;
    }

    public void setIdNomor(int idNomor) {
        this.idNomor = idNomor;
    }

    public void setNilaiAngka(String nilaiAngka) {
        this.nilaiAngka = nilaiAngka;
    }

    public void setTotalNilai(String totalNilai) {
        this.totalNilai = totalNilai;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaKuliah() {
        return namaKuliah;
    }

    public void setNamaKuliah(String namaKuliah) {
        this.namaKuliah = namaKuliah;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getNilaiAngka() {
        return nilaiAngka;
    }

    public void setNilaiAngka() {
        this.nilaiAngka = nilaiAngka;
    }

    public String getNilaiHuruf() {
        return nilaiHuruf;
    }

    public void setNilaiHuruf(String nilaiHuruf) {
        this.nilaiHuruf = nilaiHuruf;
    }

    public String getTotalNilai() {
        return totalNilai;
    }

    public void setTotalNilai() {
        this.totalNilai = totalNilai;
    }
}