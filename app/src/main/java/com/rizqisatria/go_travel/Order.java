package com.rizqisatria.go_travel;

public class Order {
    private String jemput;
    private String jml_pesanan;
    private String tanggal;
    private String tujuan;

    public Order(){

    }

    public Order(String jemput, String jml_pesanan, String tanggal, String tujuan) {
        this.jemput = jemput;
        this.jml_pesanan = jml_pesanan;
        this.tanggal = tanggal;
        this.tujuan = tujuan;
    }

    public String getJemput() {
        return jemput;
    }

    public void setJemput(String jemput) {
        this.jemput = jemput;
    }

    public String getJml_pesanan() {
        return jml_pesanan;
    }

    public void setJml_pesanan(String jml_pesanan) {
        this.jml_pesanan = jml_pesanan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
}
