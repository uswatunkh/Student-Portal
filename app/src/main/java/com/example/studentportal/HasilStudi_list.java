package com.example.studentportal;

public class HasilStudi_list {

    private String kode;
    private String namaKuliah;
    private String sks;
    private int nilaiAngka;
    private String nilaiHuruf;
    private int totalNilai;


    public HasilStudi_list(  String kode, String namaKuliah, String sks, int nilaiAngka,String nilaiHuruf, int totalNilai) {

        this.kode = kode;
        this.namaKuliah = namaKuliah;
        this.sks = sks;
        this.nilaiAngka = nilaiAngka;
        this.nilaiHuruf = nilaiHuruf;
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

    public int getNilaiAngka() {
        return nilaiAngka;
    }

    public void setNilaiAngka(int nilaiAngka) {
        this.nilaiAngka = nilaiAngka;
    }

    public String getNilaiHuruf() {
        return nilaiHuruf;
    }

    public void setNilaiHuruf(String nilaiHuruf) {
        this.nilaiHuruf = nilaiHuruf;
    }

    public int getTotalNilai() {
        return totalNilai;
    }

    public void setTotalNilai(int totalNilai) {
        this.totalNilai = totalNilai;
    }
}