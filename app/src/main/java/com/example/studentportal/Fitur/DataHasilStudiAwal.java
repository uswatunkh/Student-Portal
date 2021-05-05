package com.example.studentportal.Fitur;

public class DataHasilStudiAwal {
    String Semester;
    public DataHasilStudiAwal() {
    }


    public DataHasilStudiAwal (String semester) {
        Semester = semester;

    }


    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }
}