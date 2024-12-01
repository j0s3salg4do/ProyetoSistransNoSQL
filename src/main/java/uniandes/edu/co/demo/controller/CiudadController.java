package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.demo.modelo.Ciudad;
import uniandes.edu.co.demo.repository.CiudadRepository;

import java.util.List;

@RestController
@RequestMapping("/Ciudad")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    // Crear una nueva ciudad
    @PostMapping("/new/save")
    public ResponseEntity<String> crearCiudad(@RequestBody Ciudad ciudad) {
        try {
            ciudadRepository.save(ciudad);
            return new ResponseEntity<>("Ciudad creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la ciudad: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las ciudades
    @GetMapping("")
    public ResponseEntity<List<Ciudad>> obtenerTodasLasCiudades() {
        try {
            List<Ciudad> ciudades = ciudadRepository.buscarTodasLasCiudades();
            return ResponseEntity.ok(ciudades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una ciudad por su código
    @GetMapping("/{codigo}")
    public ResponseEntity<Ciudad> obtenerCiudadPorCodigo(@PathVariable("codigo") String codigo) {
        try {
            Ciudad ciudad = ciudadRepository.buscarPorCodigo(codigo);
            if (ciudad != null) {
                return ResponseEntity.ok(ciudad);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar una ciudad existente
    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarCiudad(@PathVariable("id") String id, @RequestBody Ciudad ciudad) {
        try {
            Ciudad ciudadExistente = ciudadRepository.findById(id).orElse(null);
            if (ciudadExistente != null) {
                ciudadExistente.setNombre(ciudad.getNombre());
                ciudadExistente.setCodigo(ciudad.getCodigo());
                ciudadRepository.save(ciudadExistente);
                return new ResponseEntity<>("Ciudad actualizada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ciudad no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la ciudad: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una ciudad por su ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCiudad(@PathVariable("id") String id) {
        try {
            ciudadRepository.deleteById(id);
            return new ResponseEntity<>("Ciudad eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la ciudad: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
