package com.rizqisatria.go_travel;

public class pesanan {
    public String tujuan;
    public String jemput;
    public String jumlah;
    public String tanggal;

    public pesanan() {
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getJemput() {
        return jemput;
    }

    public void setJemput(String jemput) {
        this.jemput = jemput;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public pesanan(String tujuan, String jemput, String jumlah, String tanggal) {

        this.tujuan = tujuan;
        this.jemput = jemput;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }
}
