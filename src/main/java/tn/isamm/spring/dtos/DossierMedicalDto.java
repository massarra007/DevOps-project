package tn.isamm.spring.dtos;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

public class DossierMedicalDto implements Serializable {
    long id;
    PatientDto patient;
    List<ConsultationDto> consultations;
}