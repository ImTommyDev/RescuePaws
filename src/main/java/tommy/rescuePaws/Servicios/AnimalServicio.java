package tommy.rescuePaws.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tommy.rescuePaws.Entidades.Animal;
import tommy.rescuePaws.Entidades.Raza;
import tommy.rescuePaws.Enums.Estado;
import tommy.rescuePaws.Repositorios.AnimalRepositorio;

import java.time.LocalDate;
import java.util.List;

//Esta anotación es muy importante
@Service
//Implementa la interfaz de AnimalServicio
public class AnimalServicio implements InterfazAnimalServicio{

    //Una vez implementada la anotación de service, ya podemos llamar a la capa de repositorio
    @Autowired
    private AnimalRepositorio animalRepositorio; //Con esto comunico el servicio con el repositorio

    @Override
    public Animal guardarAnimal(Animal animal) {
        try {
            if (animal == null) {
                throw new IllegalArgumentException("El objeto animal no puede ser nulo");
            }
            return animalRepositorio.save(animal); // Guarda o actualiza
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el animal: " + e.getMessage());
        }
    }

    @Override
    public List<Animal> listarTodos() {
        try {
            return animalRepositorio.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los animales: " + e.getMessage());
        }
    }

    @Override
    public Animal buscarPorId(Integer id) {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("El ID debe ser un valor positivo");
            }
            return animalRepositorio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Animal con ID " + id + " no encontrado"));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el animal por ID: " + e.getMessage());
        }
    }

    @Override
    public List<Animal> buscarPorEstado(Estado estado) {
        try {
            if (estado == null) {
                throw new IllegalArgumentException("Debes seleccionar un estado");
            }
            return animalRepositorio.findByEstado(estado);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar animales por estado: " + e.getMessage());
        }
    }

    @Override
    public List<Animal> buscarPorRaza(Raza raza) {
        try {
            if (raza == null) {
                throw new IllegalArgumentException("Debes seleccionar una raza");
            }
            return animalRepositorio.findByRaza(raza);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar animales por raza: " + e.getMessage());
        }
    }

    @Override
    public List<Animal> buscarPorEspecie(String especie) {
        try {
            if (especie == null || especie.isBlank()) {
                throw new IllegalArgumentException("El nombre de la especie no puede estar vacío");
            }
            return animalRepositorio.findByEspecieNombreIgnoreCase(especie);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar animales por especie: " + e.getMessage());
        }
    }

    @Override
    public Animal actualizarAnimal(Integer id, Animal animal) {
        try {
            if (id == null || id <= 0 || animal == null) {
                throw new IllegalArgumentException("El id o el animal no han sido seleccionados");
            }

            Animal animalExistente = buscarPorId(id);

            // Actualizar campos
            animalExistente.setNombre(animal.getNombre());
            animalExistente.setEdad(animal.getEdad());
            animalExistente.setColor(animal.getColor());
            animalExistente.setSexo(animal.getSexo());
            animalExistente.setEspecie(animal.getEspecie());
            animalExistente.setRaza(animal.getRaza());
            animalExistente.setTamano(animal.getTamano());
            animalExistente.setComportamiento(animal.getComportamiento());
            animalExistente.setFechaIngreso(animal.getFechaIngreso());
            animalExistente.setHistoriaRescate(animal.getHistoriaRescate());
            animalExistente.setEstado(animal.getEstado());
            animalExistente.setEsterilizado(animal.getEsterilizado());
            animalExistente.setFechaEsterilizacion(animal.getFechaEsterilizacion());
            animalExistente.setVacunas(animal.getVacunas());
            animalExistente.setHistorialMedico(animal.getHistorialMedico());
            animalExistente.setPeso(animal.getPeso());
            animalExistente.setFotos(animal.getFotos());

            return animalRepositorio.save(animalExistente);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el animal: " + e.getMessage());
        }
    }

    @Override
    public void eliminarPorId(Integer id) {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("El ID debe ser un valor positivo");
            }
            if (!animalRepositorio.existsById(id)) {
                throw new RuntimeException("Animal con ID " + id + " no encontrado");
            }
            animalRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el animal: " + e.getMessage());
        }
    }

    @Override
    public List<Animal> buscarPorNombre(String nombre) {
        try {
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío");
            }
            return animalRepositorio.findByNombreContainingIgnoreCase(nombre);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar animales por nombre: " + e.getMessage());
        }
    }

    @Override
    public List<Animal> buscarPorRangoDeEdad(Integer edadMinima, Integer edadMaxima) {
        try {
            if (edadMinima == null || edadMaxima == null || edadMinima > edadMaxima || edadMinima < 0) {
                throw new IllegalArgumentException("Rango de edad inválido");
            }
            return animalRepositorio.findByEdadBetween(edadMinima, edadMaxima);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar animales por rango de edad: " + e.getMessage());
        }
    }

    @Override
    public List<Animal> buscarPorTiempoEnRefugio(Long diasMinimos) {
        try {
            if (diasMinimos == null || diasMinimos < 0) {
                throw new IllegalArgumentException("El valor de días mínimos debe ser mayor o igual a 0");
            }
            //Calculo la fecha límite restando los diasMinimos a la fecha actual
            LocalDate fechaLimite = LocalDate.now().minusDays(diasMinimos);
            //Luego utilizo el repositorio para buscar los animales cuya fecha de ingreso sea anterior a la fecha límite
            return animalRepositorio.findByFechaIngresoBefore(fechaLimite);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar animales por tiempo en refugio: " + e.getMessage());
        }
    }
}
