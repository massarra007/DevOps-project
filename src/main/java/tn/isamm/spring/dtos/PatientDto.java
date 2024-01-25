package tn.isamm.spring.dtos;

import lombok.Value;
import tn.isamm.spring.entities.Genre;

import java.io.Serializable;
import java.util.Date;

public class PatientDto implements Serializable {
    Long id;
    String nom;
    String prenom;
    Date dateNaissance;
    boolean malade;
    String adresse;
    String numeroTelephone;
    Genre genre;
}