package tn.isamm.spring.dtos;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

public class ConsultationDto implements Serializable {
    Long id;
    Date dateConsultation;
    String rapport;
    RendezVousDto rendezVous;
    DossierMedicalDto dossierMedical;
}