package tn.isamm.spring.controllers;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.isamm.spring.dtos.MedecinDto;
import tn.isamm.spring.entities.Medecin;
import tn.isamm.spring.repository.MedecinRepository;

@Controller
@AllArgsConstructor
public class MedecinController {
    MedecinRepository medecinRepository;
    private final ModelMapper modelMapper=new ModelMapper();
    private static final String KEYWORD="keyword";
    private static final String MEDECIN="medecin";
    @GetMapping(path= "/user/medecins")
    public String medecin (Model model,
                           @RequestParam(name= "page", defaultValue = "0") int page,
                           @RequestParam(name="size", defaultValue = "5") int size,
                           @RequestParam(name="keyword", defaultValue = "") String keyword){
        Page<Medecin> pagemedecins= medecinRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listMedecins",pagemedecins);
        model.addAttribute("pages", new int[pagemedecins.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute(KEYWORD,keyword);
        return "medecin/medecins";
    }
    @GetMapping(path="/admin/formMedecin")
    public String formMedecin(Model model){
        model.addAttribute(MEDECIN, new Medecin());
        return "Medecin/formMedecin";
    }
    @GetMapping(path="/admin/deleteMedecin")
    public String deleteMedecin(Long id, String keyword, int page){
        medecinRepository.deleteById(id);
        return "redirect:/user/medecins?page="+page+"&keyword="+keyword;
    }

    @PostMapping(path="/admin/saveMedecin")
    public String saveMedecin(@Validated MedecinDto medecinDto,
                              BindingResult bindingResult, // =>stock les erreurs
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String keyword){
        Medecin medecin = this.modelMapper.map(medecinDto, Medecin.class);
        if (bindingResult.hasErrors()) return "Medecin/formMedecin";
        medecinRepository.save(medecin);
        return "redirect:/user/medecins?page="+page+"&keyword="+keyword;
    }
    @GetMapping(path="/admin/EditMedecin")
    public String editMedecin(Model model, Long id, String keyword, int page){
        Medecin medecin = medecinRepository.findById(id).orElse(null);
        if(medecin==null) throw new NullPointerException("Medecin introuvable");
        model.addAttribute(MEDECIN, medecin);
        model.addAttribute("page", page);
        model.addAttribute(KEYWORD,keyword);
        return "Medecin/EditMedecin";
    }

    @GetMapping(path="/admin/ShowMedecin")
    public String showMedecin(Model model, Long id, String keyword, int page){
        Medecin medecin = medecinRepository.findById(id).orElse(null);
        if(medecin==null) throw new NullPointerException("Medecin introuvable");
        model.addAttribute(MEDECIN, medecin);
        model.addAttribute("page", page);
        model.addAttribute(KEYWORD,keyword);
        return "Medecin/detailsMedecin";
    }
}