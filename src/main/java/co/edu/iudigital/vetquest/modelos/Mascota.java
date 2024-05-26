package co.edu.iudigital.vetquest.modelos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {

    private Integer idMascota;
    private String nombre;
    private String especie;
    private String raza;
    private String color;
    private LocalDate fechaNacimiento;
    
}
