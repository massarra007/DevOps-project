package tn.isamm.spring.dtos;

import lombok.Value;
import tn.isamm.spring.entities.Specialite;

import java.io.Serializable;


public class MedecinDto implements Serializable {
    Long id;
    String nom;
    String prenom;
    String email;
    Specialite specialite;
    int nbHoraires;
}