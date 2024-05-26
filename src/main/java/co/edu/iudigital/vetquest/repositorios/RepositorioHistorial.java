package co.edu.iudigital.vetquest.repositorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.edu.iudigital.vetquest.modelos.Entrada;
import co.edu.iudigital.vetquest.modelos.Historial;

@Repository
public class RepositorioHistorial {

    private Map<Integer, Historial> historiales;

    public RepositorioHistorial(){
        historiales = new HashMap<>();
    }

    public Optional<Historial> getHistorial(Integer idMascota){
        return Optional.ofNullable(historiales.get(idMascota));
    }

    public boolean actualizarHistorial(Integer idMascota, Entrada entrada){
        Optional<Historial> historialOp = getHistorial(idMascota);
        Historial historial = historialOp.orElse(new Historial());
        if (historial.getEntradas() == null){
            historial.setEntradas(new ArrayList<>());
        }
        historial.getEntradas().add(entrada);
        historiales.put(idMascota, historial);
        return true;
    }

}
