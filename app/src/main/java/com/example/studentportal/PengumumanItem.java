package com.example.studentportal;

public class PengumumanItem {
    String  isiPengumuman, tanggalPengumuman;




    public PengumumanItem( String isiPengumuman, String tanggalPengumuman) {

        this.isiPengumuman = isiPengumuman;
        this.tanggalPengumuman = tanggalPengumuman;
    }


    public String getIsi() {
        return isiPengumuman;
    }

    public String getTgl() {
        return tanggalPengumuman;
    }


}
