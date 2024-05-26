package co.edu.iudigital.vetquest.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.iudigital.vetquest.modelos.Mascota;
import co.edu.iudigital.vetquest.modelos.Persona;
import co.edu.iudigital.vetquest.repositorios.RepositorioMascotas;
import co.edu.iudigital.vetquest.repositorios.RepositorioPersonas;

@Controller
@RequestMapping(path = "/persona")
public class PersonaController {

	private final RepositorioPersonas repoPersonas;
	private final RepositorioMascotas repoMascotas;

	public PersonaController(RepositorioPersonas repoPersonas, RepositorioMascotas repoMascotas) {
		this.repoPersonas = repoPersonas;
		this.repoMascotas = repoMascotas;
	}

	@ModelAttribute
	public void agregarAtributosComunes(Model model) {
		model.addAttribute("modulo", "personas");
		model.addAttribute("personas", repoPersonas.todos());
		model.addAttribute("mascotas", repoMascotas.todos());
	}

	@GetMapping(path = "")
	public String persona(@RequestParam(required = false, name = "personas") Integer personaId, Model model) {
		Optional<Persona> persona = repoPersonas.obtenerPersona(personaId);
		if (persona.isPresent()){
			model.addAttribute("mascotasPersona", persona.get().getMascotas());
		}
		model.addAttribute("persona", persona.orElse(new Persona()));
		return "persona/persona";
	}

	@PostMapping(path = "/guardar")
	public String guardarPersona(@ModelAttribute Persona persona, Model model) {
		repoPersonas.guardarOActualizar(persona);
		model.addAttribute("persona", persona);
		model.addAttribute("personas", repoPersonas.todos());
		return "redirect:/persona?personas=" + persona.getId();
	}

	@PostMapping(path = "/agregarMascota")
	public String agregarMascota(
			@RequestParam(name = "agregar") Integer idMascota,
			@RequestParam(name = "idPersona") Integer idPersona,
			Model model,
			RedirectAttributes redirectAttributes) {
				Optional<Persona> personaOptional = repoPersonas.obtenerPersona(idPersona);
				if (personaOptional.isPresent()){
					Persona persona = personaOptional.get();
					Optional<Mascota> mascotaOptional = repoMascotas.obtenerMascota(idMascota);
					if (mascotaOptional.isPresent()){
						repoPersonas.agregarMascota(persona.getId(), mascotaOptional.get());
					}
				}
		return "redirect:/persona?personas=" + idPersona;
	}

}
