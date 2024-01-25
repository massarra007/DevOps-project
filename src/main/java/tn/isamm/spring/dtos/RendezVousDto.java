package tn.isamm.spring.dtos;

import lombok.Value;
import tn.isamm.spring.entities.StatusRDV;

import java.io.Serializable;
import java.util.Date;

public class RendezVousDto implements Serializable {
    Long id;
    Date date;
    StatusRDV statusRDV;
    PatientDto patient;
    MedecinDto medecin;
}