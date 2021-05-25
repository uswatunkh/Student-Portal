package com.example.studentportal.Fitur;

public class DataEvdos {

    private String idDosen, namaDosen,fotoDosen, namaMatakuliah, nidn, idMatakuliah;

    public DataEvdos() {
    }

    public DataEvdos(String idDosen, String namaDosen,String fotoDosen, String namaMatakuliah, String nidn, String idMatakuliah) {
        this.idDosen = idDosen;
        this.namaDosen = namaDosen;
        this.fotoDosen = fotoDosen;
        this.namaMatakuliah = namaMatakuliah;
        this.nidn = nidn;
        this.idMatakuliah = idMatakuliah;
    }

    public String getIdDosen() {
        return idDosen;
    }

    public void setIdDosen(String idDosen) {
        this.idDosen = idDosen;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getFotoDosen() {
        return fotoDosen;
    }

    public void setFotoDosen(String fotoDosen) {
        this.fotoDosen = fotoDosen;
    }

    public String getNamaMatakuliah() {
        return namaMatakuliah;
    }

    public void setNamaMatakuliah(String namaMatakuliah) {
        this.namaMatakuliah = namaMatakuliah;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getIdMatakuliah() {
        return idMatakuliah;
    }

    public void setIdMatakuliah(String idMatakuliah) {
        this.idMatakuliah = idMatakuliah;
    }
}

