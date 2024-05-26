package co.edu.iudigital.vetquest.repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.edu.iudigital.vetquest.modelos.Mascota;

@Repository
public class RepositorioMascotas {

    private Map<Integer, Mascota> mascotas;

    public RepositorioMascotas() {
        this.mascotas = new HashMap<>();
    }

    public boolean guardarOActualizar(Mascota mascota) {
        if(mascota.getIdMascota() == null){
            mascota.setIdMascota(mascotas.size() + 1);
        }
        mascotas.put(mascota.getIdMascota(), mascota);
        return true;
    }

    public Optional<Mascota> obtenerMascota(Integer id) {
        return Optional.ofNullable(mascotas.get(id));
    }

    public Collection<Mascota> todos() {
        return mascotas.values();
    }

}
