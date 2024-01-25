package tn.isamm.spring.controllers;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.modelmapper.ModelMapper;
import tn.isamm.spring.dtos.ConsultationDto;
import tn.isamm.spring.entities.Consultation;
import tn.isamm.spring.repository.ConsultationRepository;
import tn.isamm.spring.repository.RendezVousRepository;
import tn.isamm.spring.service.ICliniqueService;

@Controller
@AllArgsConstructor
public class ConsultationController {
    ConsultationRepository consultationRepository;
    private final ICliniqueService cliniqueService;
    RendezVousRepository rendezVousRepository;
    private final ModelMapper modelMapper=new ModelMapper();
    @GetMapping(path= "/user/consultations")
    public String consultation (Model model,
                                @RequestParam(name= "page", defaultValue = "0") int page,
                                @RequestParam(name="size", defaultValue = "5") int size){
        Page<Consultation> pageconsultation= consultationRepository.findAll(PageRequest.of(page, size));

        model.addAttribute("listConsultation",pageconsultation);
        model.addAttribute("pages", new int[pageconsultation.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "consultation/consultations";
    }
    @GetMapping(path="/admin/deleteConsultation")
    public String deleteConsultation(Long id,  int page){
        consultationRepository.deleteById(id);
        return "redirect:/user/consultations?page="+page;
    }
    @PostMapping(path="/admin/saveConsultation")
    public String saveConsultation(@Validated ConsultationDto consultationDto,
                                   BindingResult bindingResult,
                                   @RequestParam(defaultValue = "0") int page){
        Consultation consultation = this.modelMapper.map(consultationDto, Consultation.class);
        if (bindingResult.hasErrors()) return "formConsultation";
        cliniqueService.saveConsultation(consultation);
        return "redirect:/user/consultations?page="+page;
    }
    @GetMapping(path="/admin/formConsultation")
    public String formConsultation(Model model){
        model.addAttribute("consultation",new Consultation());
        model.addAttribute("rendezvous",rendezVousRepository.findAll());
        return "consultation/formConsultation";
    }
    @GetMapping(path="/admin/EditConsultation")
    public String editConsultation(Model model, Long id,int page){
        Consultation consultation = consultationRepository.findById(id).orElse(null); // avec .get je le recuper s'il existe mais on peut utiliser orElse(null) null s'il ne trouve pas le patient
        if(consultation==null) throw new NullPointerException("Consultation introuvable");
        model.addAttribute("consultation", consultation);
        model.addAttribute("rendezvous",rendezVousRepository.findAll());
        model.addAttribute("page", page);
        return "consultation/EditConsultation";
    }
}