package co.edu.iudigital.vetquest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.iudigital.vetquest.modelos.Entrada;
import co.edu.iudigital.vetquest.modelos.Historial;
import co.edu.iudigital.vetquest.modelos.Mascota;
import co.edu.iudigital.vetquest.modelos.Persona;
import co.edu.iudigital.vetquest.repositorios.RepositorioHistorial;
import co.edu.iudigital.vetquest.repositorios.RepositorioMascotas;
import co.edu.iudigital.vetquest.repositorios.RepositorioPersonas;

@SpringBootTest
class Vetquest3000ApplicationTests {

	private static RepositorioPersonas repoPersonas;
	private static RepositorioMascotas repoMascotas;
	private static RepositorioHistorial repoHistorial;

	@BeforeAll
	static void cargarObjetos() {
		repoPersonas = new RepositorioPersonas();
		repoMascotas = new RepositorioMascotas();
		repoHistorial = new RepositorioHistorial();
	}

	private Persona crearPersona() {
		return new Persona(-1, "Fulanito", "De Prueba", "Telefono", "correo@correo.com", "Mi casa", null);
	}

	private Mascota crearMascota() {
		return new Mascota(-1, "Nibbler", "Alienigena", "Nibbleniano", "Negro con blanco", LocalDate.MIN);
	}

	private Entrada crearEntrada() {
		return new Entrada(null, LocalDate.now(), "Procedimientos", "Observaciones", LocalDate.now().plusDays(3));
	}

	@Test
	void testIngresaPersona() {
		Persona personaTest = crearPersona();
		repoPersonas.guardarOActualizar(personaTest);
		Persona p = repoPersonas.obtenerPersona(-1).orElse(null);
		assertNotNull(p);
		assertEquals(-1, p.getId());
		assertEquals("Fulanito", p.getNombre());
		assertEquals("De Prueba", p.getApellidos());
		assertEquals("Telefono", p.getTelefono());
		assertEquals("correo@correo.com", p.getEmail());
		assertEquals("Mi casa", p.getDireccion());
	}

	@Test
	void ingresaMascota() {
		Mascota mascotaTest = crearMascota();
		repoMascotas.guardarOActualizar(mascotaTest);
		Mascota m = repoMascotas.obtenerMascota(-1).orElse(null);
		assertNotNull(m);
		assertEquals(-1, m.getIdMascota());
		assertEquals("Nibbler", m.getNombre());
		assertEquals("Alienigena", m.getEspecie());
		assertEquals("Negro con blanco", m.getColor());
		assertEquals(LocalDate.MIN, m.getFechaNacimiento());
	}

	@Test
	void testAgregaMascota() {
		Persona personaTest = crearPersona();
		Mascota mascotaTest = crearMascota();
		repoPersonas.guardarOActualizar(personaTest);
		repoMascotas.guardarOActualizar(mascotaTest);
		repoPersonas.agregarMascota(personaTest.getId(), mascotaTest);
		Persona p = repoPersonas.obtenerPersona(-1).get();
		repoPersonas.agregarMascota(p.getId(), mascotaTest);
		p = repoPersonas.obtenerPersona(p.getId()).get();
		assertNotNull(p.getMascotas());
		assertEquals(1, p.getMascotas().size());
		assertEquals(mascotaTest.getIdMascota(), p.getMascotas().stream()
				.filter(mascota -> mascota.getIdMascota() == mascotaTest.getIdMascota()).findFirst().orElse(new Mascota()).getIdMascota());
	}

	@Test
	void testAgregarEntrada() {
		Entrada entradaTest = crearEntrada();
		repoHistorial.actualizarHistorial(-1, entradaTest);
		Historial historial = repoHistorial.getHistorial(-1).orElse(null);
		assertNotNull(historial);
		assertNotNull(historial.getEntradas());
		assertEquals(1, historial.getEntradas().size());
		Entrada e = historial.getEntradas().get(0);
		assertEquals("Observaciones", e.getObservaciones());
		assertEquals("Procedimientos", e.getProcedimientos());
		assertEquals(LocalDate.now(), e.getFecha());
		assertEquals(LocalDate.now().plusDays(3), e.getRecordatorio());
	}

}
