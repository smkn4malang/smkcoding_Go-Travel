package com.rizqisatria.go_travel;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public Config(Context mCtx){
        this.context = mCtx;
        pref = mCtx.getSharedPreferences("config", 0);
        editor = pref.edit();
    }

    public void setTujuan(String mTujuan){
        editor.putString("Tujuan", mTujuan);
        editor.commit();
    }

    public String getTujuan(){
        return pref.getString("Tujuan", "Isi Tujuan Terlebih Dahulu");
    }
    public void setJemput(String mJemput){
        editor.putString("Jemput", mJemput);
        editor.commit();
    }

    public String getJemput(){
        return pref.getString("Jemput", "Isi Jemput Terlebih Dahulu");
    }
    public void setPesanan(String mPesanan){
        editor.putString("Pesanan", mPesanan);
        editor.commit();
    }

    public String getPesanan(){
        return pref.getString("Pesanan", "Isi Pesanan Anda Terlebih Dahulu");
    }
}
