package com.rizqisatria.go_travel;

public class User {
    private String email,nomer,jenis;


    public User(){}

    public User(String email, String nomer, String jenis) {
        this.email = email;
        this.nomer = nomer;
        this.jenis = jenis;
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