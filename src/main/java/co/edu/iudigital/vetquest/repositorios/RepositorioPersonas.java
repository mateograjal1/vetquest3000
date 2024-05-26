package co.edu.iudigital.vetquest.repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.edu.iudigital.vetquest.modelos.Mascota;
import co.edu.iudigital.vetquest.modelos.Persona;

@Repository
public class RepositorioPersonas {

    private Map<Integer, Persona> personas;

    public RepositorioPersonas() {
        this.personas = new HashMap<>();
    }

    public boolean guardarOActualizar(Persona persona) {
        if (!personas.containsKey(persona.getId())){
            personas.put(persona.getId(), persona);
        } else {
            Persona personaOld = personas.get(persona.getId());
            personaOld.setId(persona.getId());
            personaOld.setNombre(persona.getNombre());
            personaOld.setApellidos(persona.getApellidos());
            personaOld.setEmail(persona.getEmail());
            personaOld.setDireccion(persona.getDireccion());
            personas.put(persona.getId(), personaOld);
        }
        return true;
    }

    public boolean agregarMascota(Integer idPersona, Mascota mascota){
        Persona persona = personas.get(idPersona);
        if (persona.getMascotas() == null){
            persona.setMascotas(new HashSet<>());
        }
        persona.getMascotas().add(mascota);
        personas.put(idPersona, persona);
        return true;
    }


    public Optional<Persona> obtenerPersona(Integer id) {
        return Optional.ofNullable(personas.get(id));
    }

    public Collection<Persona> todos() {
        return personas.values();
    }

}
