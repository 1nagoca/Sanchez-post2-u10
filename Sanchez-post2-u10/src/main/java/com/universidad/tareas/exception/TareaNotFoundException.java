package com.universidad.tareas.exception;

public class TareaNotFoundException extends RuntimeException {
    public TareaNotFoundException(Long id) {
        super("Tarea no encontrada con ID: " + id);
    }
    public TareaNotFoundException(String message) {
        super(message);
    }
}
