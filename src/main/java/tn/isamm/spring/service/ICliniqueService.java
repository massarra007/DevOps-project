package tn.isamm.spring.service;

import tn.isamm.spring.entities.Consultation;
import tn.isamm.spring.entities.Medecin;
import tn.isamm.spring.entities.Patient;
import tn.isamm.spring.entities.RendezVous;

import java.util.List;

public interface ICliniqueService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(String patientName, String medecinName, RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
    List<Consultation> showAllConsultations();
}
