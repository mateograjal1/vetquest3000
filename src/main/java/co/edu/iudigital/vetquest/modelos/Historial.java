package co.edu.iudigital.vetquest.modelos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Historial {

    private Integer id;
    private Integer idMascota;
    private List<Entrada> entradas;

}
