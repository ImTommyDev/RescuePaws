package tommy.rescuePaws.Controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tommy.rescuePaws.Entidades.Animal;
import tommy.rescuePaws.Entidades.Raza;
import tommy.rescuePaws.Enums.Estado;
import tommy.rescuePaws.Servicios.AnimalServicio;

import java.util.List;

@RestController
@RequestMapping("RescuePaws") // Las peticiones tendrán que llevar este nombre en la URL
@CrossOrigin(value = "http://localhost:4200") // Permite el acceso desde Angular en el puerto 4200
public class AnimalControlador {

    // Logger para enviar información a la consola
    private static final Logger logger = LoggerFactory.getLogger(AnimalControlador.class);

    @Autowired
    private AnimalServicio animalServicio;

    /**
     * Obtener todos los animales
     * Endpoint: GET /RescuePaws/animales
     */
    @GetMapping("/animales")
    public ResponseEntity<List<Animal>> obtenerAnimales() {
        try {
            List<Animal> animales = animalServicio.listarTodos();
            logger.info("Animales obtenidos:");
            animales.forEach(animal -> logger.info(animal.toString()));
            return ResponseEntity.ok(animales);
        } catch (Exception e) {
            logger.error("Error al obtener animales: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Obtener un animal por su ID
     * Endpoint: GET /RescuePaws/animales/{id}
     */
    @GetMapping("/animales/{id}")
    public ResponseEntity<Animal> obtenerAnimalPorId(@PathVariable Integer id) {
        try {
            Animal animal = animalServicio.buscarPorId(id);
            logger.info("Animal obtenido: {}", animal);
            return ResponseEntity.ok(animal);
        } catch (RuntimeException e) {
            logger.error("Error al obtener animal con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Crear un nuevo animal
     * Endpoint: POST /RescuePaws/animales
     */
    @PostMapping("/animales")
    public ResponseEntity<Animal> crearAnimal(@RequestBody Animal animal) {
        try {
            Animal nuevoAnimal = animalServicio.guardarAnimal(animal);
            logger.info("Animal creado: {}", nuevoAnimal);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAnimal);
        } catch (IllegalArgumentException e) {
            logger.error("Datos inválidos para crear animal: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Error al crear animal: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Actualizar un animal existente
     * Endpoint: PUT /RescuePaws/animales/{id}
     */
    @PutMapping("/animales/{id}")
    public ResponseEntity<Animal> actualizarAnimal(@PathVariable Integer id, @RequestBody Animal animal) {
        try {
            Animal animalActualizado = animalServicio.actualizarAnimal(id, animal);
            logger.info("Animal actualizado: {}", animalActualizado);
            return ResponseEntity.ok(animalActualizado);
        } catch (RuntimeException e) {
            logger.error("Error al actualizar animal con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("Error al actualizar animal: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Eliminar un animal por su ID
     * Endpoint: DELETE /RescuePaws/animales/{id}
     */
    @DeleteMapping("/animales/{id}")
    public ResponseEntity<Void> eliminarAnimal(@PathVariable Integer id) {
        try {
            animalServicio.eliminarPorId(id);
            logger.info("Animal con ID {} eliminado correctamente", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            logger.error("Error al eliminar animal con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Error al eliminar animal: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Buscar animales por estado
     * Endpoint: GET /RescuePaws/animales/estado/{estado}
     */
    @GetMapping("/animales/estado/{estado}")
    public ResponseEntity<List<Animal>> buscarPorEstado(@PathVariable Estado estado) {
        try {
            List<Animal> animales = animalServicio.buscarPorEstado(estado);
            logger.info("Animales encontrados con estado {}: {}", estado, animales);
            return ResponseEntity.ok(animales);
        } catch (Exception e) {
            logger.error("Error al buscar animales por estado {}: {}", estado, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Buscar animales por ID raza
     * Endpoint: GET /RescuePaws/animales/raza/{idRaza}
     */
    @GetMapping("/animales/raza/{idRaza}")
    public ResponseEntity<List<Animal>> buscarPorIdRaza(@PathVariable Integer idRaza) {
        try {
            List<Animal> animales = animalServicio.buscarPorIdRaza(idRaza);
            logger.info("Animales encontrados con raza ID {}: {}", idRaza, animales);
            return ResponseEntity.ok(animales);
        } catch (Exception e) {
            logger.error("Error al buscar animales por raza ID {}: {}", idRaza, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/animales/raza/nombre/{nombreRaza}")
    public ResponseEntity<List<Animal>> buscarPorNombreRaza(@PathVariable String nombreRaza) {
        try {
            List<Animal> animales = animalServicio.buscarPorNombreRaza(nombreRaza);
            logger.info("Animales encontrados con raza nombre {}: {}", nombreRaza, animales);
            return ResponseEntity.ok(animales);
        } catch (Exception e) {
            logger.error("Error al buscar animales por raza nombre {}: {}", nombreRaza, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Buscar animales por especie
     * Endpoint: GET /RescuePaws/animales/especie/{especie}
     */
    @GetMapping("/animales/especie/{especie}")
    public ResponseEntity<List<Animal>> buscarPorEspecie(@PathVariable String especie) {
        try {
            List<Animal> animales = animalServicio.buscarPorEspecie(especie);
            logger.info("Animales encontrados con especie {}: {}", especie, animales);
            return ResponseEntity.ok(animales);
        } catch (Exception e) {
            logger.error("Error al buscar animales por especie {}: {}", especie, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Buscar animales por rango de edad
     * Endpoint: GET /RescuePaws/animales/edad
     */
    @GetMapping("/animales/edad")
    public ResponseEntity<List<Animal>> buscarPorRangoDeEdad(
            @RequestParam Integer edadMinima,
            @RequestParam Integer edadMaxima) {
        try {
            List<Animal> animales = animalServicio.buscarPorRangoDeEdad(edadMinima, edadMaxima);
            logger.info("Animales encontrados en el rango de edad {}-{}: {}", edadMinima, edadMaxima, animales);
            return ResponseEntity.ok(animales);
        } catch (Exception e) {
            logger.error("Error al buscar animales por rango de edad: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Buscar animales por tiempo en el refugio
     * Endpoint: GET /RescuePaws/animales/tiempoRefugio
     */
    @GetMapping("/animales/tiempoRefugio")
    public ResponseEntity<List<Animal>> buscarPorTiempoEnRefugio(@RequestParam Long diasMinimos) {
        try {
            List<Animal> animales = animalServicio.buscarPorTiempoEnRefugio(diasMinimos);
            logger.info("Animales encontrados con más de {} días en el refugio: {}", diasMinimos, animales);
            return ResponseEntity.ok(animales);
        } catch (Exception e) {
            logger.error("Error al buscar animales por tiempo en refugio: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
