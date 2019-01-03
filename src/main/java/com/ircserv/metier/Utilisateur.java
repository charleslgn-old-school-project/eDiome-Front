package com.ircserv.metier;

import java.io.Serializable;
import java.sql.Date;

public class Utilisateur implements Serializable {

    private int noUtilisateur;
    private String nom;
    private String prenom;
    private String identifiant;
    private String mot_de_passe;
    private java.sql.Date date_naissance;
    private java.sql.Date date_inscription;
    private String mail_pro;
    private String telephone_pro;

    public Utilisateur(){}



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

    public java.sql.Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(java.sql.Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public java.sql.Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(java.sql.Date date_inscription) {
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
