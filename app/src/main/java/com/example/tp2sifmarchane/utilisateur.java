package com.example.tp2sifmarchane;

import android.os.Parcel;
import android.os.Parcelable;

public class utilisateur implements Parcelable {

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

    protected utilisateur(Parcel in) {
        nom = in.readString();
        age = in.readString();
        position = in.readString();
        nationalite = in.readString();
    }

    public static final Creator<utilisateur> CREATOR = new Creator<utilisateur>() {
        @Override
        public utilisateur createFromParcel(Parcel in) {
            return new utilisateur(in);
        }

        @Override
        public utilisateur[] newArray(int size) {
            return new utilisateur[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeString(age);
        parcel.writeString(position);
        parcel.writeString(nationalite);
    }
}
