package com.rizqisatria.go_travel;

public class User {
    private String email,nomer,jenis,saldo;
    public User(){}

    public User(String email, String nomer, String jenis, String saldo) {
        this.email = email;
        this.nomer = nomer;
        this.jenis = jenis;
        this.saldo = saldo;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
