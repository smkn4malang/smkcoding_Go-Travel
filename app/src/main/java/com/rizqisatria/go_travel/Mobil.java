package com.rizqisatria.go_travel;

public class Mobil {
    public String mobil;
    public String plat;
    public String warna;

    public Mobil(String mobil, String plat, String warna) {
        this.mobil = mobil;
        this.plat = plat;
        this.warna = warna;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }
}
