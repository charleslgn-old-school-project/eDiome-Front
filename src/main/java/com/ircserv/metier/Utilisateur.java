package com.ircserv.metier;


import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Utilisateur implements Serializable {

    public Utilisateur() {
    }


    private int noUtilisateur;
    private String nom;
    private String prenom;
    private String identifiant;
    private String mot_de_passe;
    private Date date_naissance;
    private Date date_inscription;
    private String mail_pro;
    private String telephone_pro;

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int no_utilisateur) {
        this.noUtilisateur = no_utilisateur;
    }

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


    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }


    public String getMail_pro() {
        return mail_pro;
    }

    public void setMail_pro(String mail_pro) {
        this.mail_pro = mail_pro;
    }


    public String getTelephone_pro() {
        return telephone_pro;
    }

    public void setTelephone_pro(String telephone_pro) {
        this.telephone_pro = telephone_pro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return noUtilisateur == that.noUtilisateur && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(identifiant, that.identifiant) && Objects.equals(mot_de_passe, that.mot_de_passe) && Objects.equals(date_naissance, that.date_naissance) && Objects.equals(date_inscription, that.date_inscription) && Objects.equals(mail_pro, that.mail_pro) && Objects.equals(telephone_pro, that.telephone_pro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noUtilisateur, nom, prenom, identifiant, mot_de_passe, date_naissance, date_inscription, mail_pro, telephone_pro);
    }
}

