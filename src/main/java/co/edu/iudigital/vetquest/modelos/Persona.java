package co.edu.iudigital.vetquest.modelos;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
	
	private Integer id;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;
	private String direccion;
	private Set<Mascota> mascotas;
	
}
