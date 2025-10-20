package com.maxrmeb.secretsanta.secretsanta.modele;

public class Personne {

    private String nom;
    private String prenom; 
    private String email;
    private String telephone;

    //getter et setters
    public String getNom() {
        return nom; 
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    //constructeur
    public Personne(String nom, String prenom, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }
    
    public Personne(String prenom) {
        this.prenom = prenom;
    }

    public String toString() {
        return this.prenom;
    }

}
