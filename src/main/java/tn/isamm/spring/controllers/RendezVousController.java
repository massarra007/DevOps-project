package tn.isamm.spring.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.isamm.spring.dtos.RendezVousDto;
import tn.isamm.spring.entities.Medecin;
import tn.isamm.spring.entities.Patient;
import tn.isamm.spring.entities.RendezVous;
import tn.isamm.spring.repository.MedecinRepository;
import tn.isamm.spring.repository.PatientRepository;
import tn.isamm.spring.repository.RendezVousRepository;
import tn.isamm.spring.service.ICliniqueService;

import java.util.Date;

@RestController
@AllArgsConstructor
public class RendezVousController {
    PatientRepository patientRepository;
    RendezVousRepository rendezVousRepository;
    MedecinRepository medecinRepository;
    private final ICliniqueService cliniqueService;
    private final ModelMapper modelMapper=new ModelMapper();
    private static final String KEYWORD="keyword";

    @GetMapping(path = "user/rdv")
    public String rendezvous(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size,
                             @RequestParam(name = "keyword", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date keyword) {

        Page<RendezVous> pageRDV;

        if (keyword == null) {
            // Si keyword est vide, afficher tous les rendez-vous
            pageRDV = rendezVousRepository.findAll(PageRequest.of(page, size));
        } else {
            // Sinon, effectuer une recherche basée sur la date
            pageRDV = rendezVousRepository.findByDate(keyword, PageRequest.of(page, size));
        }

        model.addAttribute("listRDV", pageRDV.getContent());
        model.addAttribute("pages", new int[pageRDV.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute(KEYWORD, keyword);

        return "RDV/Rendezvous";
    }


    @GetMapping(path = "/admin/formRDV")
    public String formRendezVous(Model model , String nomPatient, String nomMedecin ) {
        model.addAttribute("rendezvous", new RendezVous());
        model.addAttribute("nomPatient",nomPatient);
        model.addAttribute("nomMedecin",nomMedecin);
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("medecins", medecinRepository.findAll());
        return "RDV/formRDV";
    }


    @PostMapping("/admin/saveRDV")
    public String saveRendezVous(@ModelAttribute RendezVousDto rendezVousDto,
                                 @RequestParam("patientName") String patientName,
                                 @RequestParam("medecinName") String medecinName,
                                 @RequestParam("page") String pageStr) {
        int page = 0;
        page = Integer.parseInt(pageStr);

        Patient patient = patientRepository.findByNom(patientName);
        Medecin medecin = medecinRepository.findByNom(medecinName);
        RendezVous rendezVous=this.modelMapper.map(rendezVousDto,RendezVous.class);
        if (patient != null && medecin != null) {
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);

            cliniqueService.saveRendezVous(patientName,medecinName,rendezVous);


            return "redirect:/user/rdv";
        } else {

            return "redirect:/admin/saveRDV?page=" + page;
        }
    }

    @GetMapping(path="/admin/deleteRDV")
    public String deleteRDV(Long id,  int page){
        rendezVousRepository.deleteById(id);
        return "redirect:/user/rdv?page="+page;
    }
    @GetMapping(path="/admin/EditRDV")
    public String editRDV(Model model, @RequestParam Long id, @RequestParam int page) {
        RendezVous rendezVous = rendezVousRepository.findById(id).orElse(null);

        if (rendezVous == null) {
            throw new NullPointerException("Rendez-vous introuvable");
        }

        model.addAttribute("rendezVous", rendezVous);
        model.addAttribute("page", page);
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("medecins", medecinRepository.findAll());

        return "RDV/EditRDV";
    }


}
