package main.java.uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.demo.modelo.Bodega;
import uniandes.edu.co.demo.repository.BodegaRepository;

import java.util.List;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {

    @Autowired
    private BodegaRepository bodegaRepository;

    // Crear una nueva bodega
    @PostMapping("/new/save")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega) {
        try {
            bodegaRepository.save(bodega);
            return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las bodegas
    @GetMapping("")
    public ResponseEntity<List<Bodega>> obtenerTodasLasBodegas() {
        try {
            List<Bodega> bodegas = bodegaRepository.buscarTodasLasBodegas();
            return ResponseEntity.ok(bodegas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener una bodega por nombre
    @GetMapping("/{nombre}")
    public ResponseEntity<Bodega> obtenerBodegaPorNombre(@PathVariable("nombre") String nombre) {
        try {
            Bodega bodega = bodegaRepository.buscarPorNombre(nombre);
            if (bodega != null) {
                return ResponseEntity.ok(bodega);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Eliminar una bodega por ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarBodega(@PathVariable("id") String id) {
        try {
            bodegaRepository.deleteById(id);
            return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la bodega: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
