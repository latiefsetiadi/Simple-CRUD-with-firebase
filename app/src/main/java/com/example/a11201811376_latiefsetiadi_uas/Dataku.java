package com.example.a11201811376_latiefsetiadi_uas;

public class Dataku {
    String id;
    String nim;
    String nama;
    String semester;
    String ipk;

    public Dataku(){

    }
    public Dataku(String id, String nim, String nama, String semester, String ipk) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
        this.ipk = ipk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }
}
