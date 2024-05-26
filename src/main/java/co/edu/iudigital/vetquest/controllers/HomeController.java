package co.edu.iudigital.vetquest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/", "", "/home"})
public class HomeController {

	@ModelAttribute
    public void agregarAtributosComunes(Model model) {
        model.addAttribute("modulo", "home");
    }
	
	@GetMapping(path = "")
	public String home(){
		
		return "home/home";
	}


}
