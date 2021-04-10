package com.example.studentportal;

public class KeterampilanItem {

    String Id,Nama, Jenis, Tingkat, Scan;




    public KeterampilanItem (String id,String nama, String jenis, String tingkat, String scan) {
        Id =id;
        Nama = nama;
        Jenis = jenis;
        Tingkat = tingkat;
        Scan =scan;
    }
    public String getId() {
        return Id;
    }
    public String getNama() {
        return Nama;
    }

    public String getJenis() {
        return Jenis;
    }

    public String getTingkat() {
        return Tingkat;
    }
    public String getScan() {
        return Scan;
    }
}
