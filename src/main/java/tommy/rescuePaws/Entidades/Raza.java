package tommy.rescuePaws.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre; // Ejemplo: "Labrador", "Siames", etc.

    @ManyToOne
    private Especie especie; // Relación con Especie
}