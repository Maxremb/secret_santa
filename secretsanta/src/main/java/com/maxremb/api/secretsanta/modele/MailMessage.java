package com.maxremb.api.secretsanta.modele;

public class MailMessage {

    private String sujet;
    private String corps;
    private String nomOrganisateur;

    //getter setter 
    public String getSujet() {
        return sujet;
    }
    public void setSujet(String sujet) {
        this.sujet = sujet;
    }
    public String getCorps() {
        return corps;
    }
    public void setCorps(String corps) {
        this.corps = corps;
    }
    public String getNomOrganisateur() {
        return nomOrganisateur;
    }
    public void setNomOrganisateur(String nomOrganisateur) {
        this.nomOrganisateur = nomOrganisateur;
    }

    //constructeur
    public MailMessage(String sujet, String corps, String nomOrganisateur) {
        this.sujet = sujet;
        this.corps = corps;
        this.nomOrganisateur = nomOrganisateur;
    }

    public MailMessage() {
    }

}
