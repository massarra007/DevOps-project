package tn.isamm.spring.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.isamm.spring.dtos.PatientDto;
import tn.isamm.spring.entities.Patient;
import tn.isamm.spring.repository.PatientRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientController {
    PatientRepository patientRepository;
    private final ModelMapper modelMapper=new ModelMapper();
    private static final String KEYWORD="keyword";
    private static final String PATIENT="patient";
    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listpatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute(KEYWORD, keyword);
        return "patient/patients";// nom de la vue
    }

    @GetMapping(path = "/user/patients")
    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    @GetMapping(path = "/admin/delete")
    public String delete(Long id, String keyword, int page) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/admin/formPatients")
    public String formPatients(Model model) {
        model.addAttribute(PATIENT, new Patient());
        return "patient/formPatients";
    }

    @PostMapping(path = "/admin/save")
    public String save(@Validated PatientDto patientDto,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        Patient patient=this.modelMapper.map(patientDto,Patient.class);
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping(path = "/admin/EditPatient")
    public String editPatient(Model model, Long id, String keyword, int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new NullPointerException("Patient introuvable");
        model.addAttribute(PATIENT, patient);
        model.addAttribute("page", page);
        model.addAttribute(KEYWORD, keyword);
        return "patient/EditPatient";
    }

    @GetMapping(path = "/user/listPatient")
    public String listPatient(Model model, Long id,
                              @RequestParam(defaultValue = "") String keyword,
                              @RequestParam(defaultValue = "0") int page) {
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute(PATIENT, patient);
        model.addAttribute(KEYWORD, keyword);
        model.addAttribute("page", page);
        return "patient/listPatient";
    }


    @GetMapping(path = "/admin/ShowPatient")
    public String showPatient(Model model, Long id, String keyword, int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new NullPointerException("Patient introuvable");
        model.addAttribute(PATIENT, patient);
        model.addAttribute("page", page);
        model.addAttribute(KEYWORD, keyword);
        return "patient/detailsPatient";
    }


}