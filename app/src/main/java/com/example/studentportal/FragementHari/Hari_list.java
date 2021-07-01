package com.example.studentportal.FragementHari;

public class Hari_list {
    String idNomor;
    private String kode;
    private String namaMatakuliah;
    private String dosen;
    private String jamke;
    private String ruang;



    public Hari_list(String idNomor, String kode, String namaMatakuliah, String dosen, String jamke, String ruang) {
        this.idNomor = idNomor;
        this.kode = kode;
        this.namaMatakuliah = namaMatakuliah;
        this.dosen = dosen;
        this.jamke = jamke;
        this.ruang = ruang;
    }

    public String getIdNomor() {
        return idNomor;
    }

    public void setIdNomor(String idNomor) {
        this.idNomor = idNomor;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaMatakuliah() {
        return namaMatakuliah;
    }

    public void setNamaMatakuliah(String namaMatakuliah) {
        this.namaMatakuliah = namaMatakuliah;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getJamke() {
        return jamke;
    }

    public void setJamke(String jamke) {
        this.jamke = jamke;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }
}
