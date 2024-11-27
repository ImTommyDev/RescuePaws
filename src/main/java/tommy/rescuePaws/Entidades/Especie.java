package tommy.rescuePaws.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspecie;
    private String nombre; // Ejemplo: "Perro", "Gato", etc.
}
