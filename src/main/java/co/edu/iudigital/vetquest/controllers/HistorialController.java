package co.edu.iudigital.vetquest.controllers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.iudigital.vetquest.modelos.Entrada;
import co.edu.iudigital.vetquest.modelos.Historial;
import co.edu.iudigital.vetquest.modelos.Mascota;
import co.edu.iudigital.vetquest.modelos.Persona;
import co.edu.iudigital.vetquest.repositorios.RepositorioHistorial;
import co.edu.iudigital.vetquest.repositorios.RepositorioMascotas;
import co.edu.iudigital.vetquest.repositorios.RepositorioPersonas;

@Controller
@RequestMapping(path = "/historial")
public class HistorialController {

    private final RepositorioPersonas repoPersonas;
    private final RepositorioMascotas repoMascotas;
    private final RepositorioHistorial repoHistorial;

    public HistorialController(RepositorioPersonas repoPersonas,
            RepositorioMascotas repoMascotas,
            RepositorioHistorial repoHistorial) {
        this.repoPersonas = repoPersonas;
        this.repoMascotas = repoMascotas;
        this.repoHistorial = repoHistorial;
    }

    @ModelAttribute
    public void agregarAtributosComunes(Model model) {
        model.addAttribute("modulo", "historial");
        model.addAttribute("personas", repoPersonas.todos());
        model.addAttribute("nuevaEntrada", new Entrada());
    }

    @GetMapping(path = "")
    public String mascota(@RequestParam(required = false, name = "persona") Integer idPersona,
            @RequestParam(required = false, name = "mascota") Integer idMascota,
            Model model) {
        Optional<Persona> personaOp = repoPersonas.obtenerPersona(idPersona);
        Optional<Mascota> mascotaOp = repoMascotas.obtenerMascota(idMascota);
        if (personaOp.isPresent()) {
            Persona persona = personaOp.get();
            Set<Mascota> mascotas = persona.getMascotas();
            model.addAttribute("persona", persona);
            model.addAttribute("mascotas", mascotas);
            if (mascotaOp.isPresent()) {
                Mascota mascota = mascotaOp.get();
                model.addAttribute("mascota", mascota);
                Optional<Historial> historialOp = repoHistorial.getHistorial(mascota.getIdMascota());
                if (historialOp.isPresent()) {
                    Historial historial = historialOp.get();
                    model.addAttribute("historial", historial);
                }
            }
        }
        return "historial/historial";
    }

    @PostMapping(path = "")
    public String postMethodName(@RequestParam("procedimientos") String procedimientos,
    @RequestParam(name = "observaciones") String observaciones,
    @RequestParam(name = "recordatorio", required = false) LocalDate recordatorio,
    @RequestParam(name = "fecha", required = false) LocalDate fecha,
    @RequestParam(name = "idPersona") Integer idPersona,
    @RequestParam(name = "idMascota") Integer idMascota) {
        if (fecha == null){
            fecha = LocalDate.now();
        }
        Entrada entrada = new Entrada(null, fecha, procedimientos, observaciones, recordatorio);
        repoHistorial.actualizarHistorial(idMascota, entrada);
        return "redirect:historial?persona=" + idPersona + "&mascota=" + idMascota;
    }

}
