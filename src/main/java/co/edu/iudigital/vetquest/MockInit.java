package co.edu.iudigital.vetquest;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.stereotype.Component;

import co.edu.iudigital.vetquest.modelos.Entrada;
import co.edu.iudigital.vetquest.modelos.Mascota;
import co.edu.iudigital.vetquest.modelos.Persona;
import co.edu.iudigital.vetquest.repositorios.RepositorioHistorial;
import co.edu.iudigital.vetquest.repositorios.RepositorioMascotas;
import co.edu.iudigital.vetquest.repositorios.RepositorioPersonas;
import jakarta.annotation.PostConstruct;

@Component
public class MockInit {

    private final RepositorioPersonas repoPersonas;
    private final RepositorioMascotas repoMascotas;
    private final RepositorioHistorial repoHistorial;

    public MockInit(RepositorioPersonas repositorioPersonas, RepositorioMascotas repositorioMascotas, RepositorioHistorial repositorioHistorial) {
        this.repoMascotas = repositorioMascotas;
        this.repoPersonas = repositorioPersonas;
        this.repoHistorial = repositorioHistorial;
    }

    @PostConstruct
    private void init() {
        Mascota mascota1 = new Mascota(1, "Dogmeat", "Perro", "Pastor Aleman", "Cafe con negro",
                LocalDate.of(2020, 1, 1));
        Mascota mascota2 = new Mascota(2, "Stitch", "Alien", "Hibrido", "Azul", LocalDate.of(2020, 1, 12));
        Persona persona1 = new Persona(1214715965, "Mateo", "Grajales Jaramillo", "3506217748", "mateo@email.com",
                "Cra 77 # 26 26", null);
        Persona persona2 = new Persona(1214715966, "Claire", "RedField", "3500000002", "claire@email.com",
                "Raccon City 2 A", null);
        Entrada entrada = new Entrada(1, LocalDate.now(), "Vacunación rabia", "Paciente vomitó luego de la vacuna", LocalDate.now().plusDays(30));

        persona1.setMascotas(new HashSet<>());
        persona1.getMascotas().add(mascota1);
        persona1.getMascotas().add(mascota2);
        
        repoPersonas.guardarOActualizar(persona1);
        repoPersonas.guardarOActualizar(persona2);
        repoMascotas.guardarOActualizar(mascota1);
        repoMascotas.guardarOActualizar(mascota2);

        repoHistorial.actualizarHistorial(1, entrada);
    }

}
