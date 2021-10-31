package com.example.login.Model;

public class Hero {
    String nom;
    String rol;
    String sub_rol;

    public Hero(String nom){
        this.nom = nom;
    }

    public Hero(String nom, String rol, String sub_rol) {
        this.nom = nom;
        this.rol = rol;
        this.sub_rol = sub_rol;
    }

    public String getNom(){     return nom; }
    public String getRol(){     return rol; }
    public String getSub_rol(){ return sub_rol; }

    public void setNom(String nom){     this.nom = nom; }
    public void setRol(String rol){     this.rol = rol; }
    public void setSub_rol(String sub_rol){ this.sub_rol = sub_rol; }

}
