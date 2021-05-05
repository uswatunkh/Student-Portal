package com.example.studentportal.Fitur;

public class DataKuisioner {
    private String idEvdos;
    private String pertanyaan;
    private  Integer a=1;

    public DataKuisioner( ) {



    }

    public DataKuisioner( String idEvdos,String pertanyaan) {
        this.idEvdos = idEvdos;
        this.pertanyaan = pertanyaan;



    }

    public Integer getA() {
        return a++;
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
