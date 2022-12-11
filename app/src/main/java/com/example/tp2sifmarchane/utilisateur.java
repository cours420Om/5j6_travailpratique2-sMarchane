package com.example.tp2sifmarchane;

public class utilisateur {

    private String nom;
    private String age;
    private String position;
    private String nationalite;


    public utilisateur(){

    }

    public utilisateur(String nom, String age, String position, String nationalite) {
        this.nom = nom;
        this.age = age;
        this.position = position;
        this.nationalite = nationalite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    @Override
    public String toString() {
        return nom;
    }

}
