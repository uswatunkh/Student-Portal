package com.example.studentportal.Fitur;

public class DataKuisioner {
    int idNomor;
    private String idEvdos;
    private String pertanyaan;

    public DataKuisioner( ) {



    }

    public DataKuisioner(int idNomor, String idEvdos,String pertanyaan) {
        this.idNomor = idNomor;
        this.idEvdos = idEvdos;
        this.pertanyaan = pertanyaan;



    }



    public int getIdNomor() {
        return idNomor;
    }

    public void setIdNomor(int idNomor) {
        this.idNomor = idNomor;
    }

    public String getIdEvdos() {
        return idEvdos;
    }

    public void setIdEvdos(String idEvdos) {
        this.idEvdos = idEvdos;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }
}
