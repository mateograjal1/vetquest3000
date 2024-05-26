package co.edu.iudigital.vetquest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.iudigital.vetquest.modelos.Mascota;
import co.edu.iudigital.vetquest.repositorios.RepositorioMascotas;

@Controller
@RequestMapping(path = "/mascota")
public class MascotaController {
	
	private final RepositorioMascotas repoMascotas;

	public MascotaController(RepositorioMascotas repositorioMascotas){
		this.repoMascotas = repositorioMascotas;
	}

	@ModelAttribute
    public void agregarAtributosComunes(Model model) {
        model.addAttribute("modulo", "mascotas");
		model.addAttribute("mascotas", repoMascotas.todos());
    }
	
	@GetMapping(path = "")
	public String mascota(@RequestParam(required = false, name = "mascotas") Integer mascotaId, Model model){
		model.addAttribute("mascota", repoMascotas.obtenerMascota(mascotaId).orElse(new Mascota()));
		return "mascota/mascota";
	}
	
	@PostMapping(path = "")
	public String guardarPersona(@ModelAttribute Mascota mascota, Model model){
		repoMascotas.guardarOActualizar(mascota);
		model.addAttribute("mascota", mascota);
		return "mascota/mascota";
	}

}
