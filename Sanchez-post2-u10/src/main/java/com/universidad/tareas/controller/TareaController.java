package com.universidad.tareas.controller;

import com.universidad.tareas.exception.TareaNotFoundException;
import com.universidad.tareas.model.Tarea;
import com.universidad.tareas.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tarea> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Tarea> crear(@Valid @RequestBody Tarea tarea) {
        Tarea creada = service.crear(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PatchMapping("/{id}/completar")
    public ResponseEntity<Tarea> completar(@PathVariable Long id) {
        return ResponseEntity.ok(service.completar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(TareaNotFoundException.class)
    public ResponseEntity<String> handleNotFound(TareaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
