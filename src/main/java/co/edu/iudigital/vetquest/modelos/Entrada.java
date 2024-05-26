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
public class Entrada {

    private Integer id;
    private LocalDate fecha;
    private String procedimientos;
    private String observaciones;
    private LocalDate recordatorio;

}
