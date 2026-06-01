package com.universidad.tareas.service;

import com.universidad.tareas.exception.TareaNotFoundException;
import com.universidad.tareas.model.Tarea;
import com.universidad.tareas.repository.TareaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository repo;

    public TareaService(TareaRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Tarea crear(Tarea tarea) {
        if (tarea.getTitulo() == null || tarea.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        return repo.save(tarea);
    }

    public Tarea buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new TareaNotFoundException(id));
    }

    @Transactional
    public Tarea completar(Long id) {
        Tarea tarea = buscarPorId(id);
        tarea.setCompletada(true);
        return repo.save(tarea);
    }

    public List<Tarea> listarTodas() {
        return repo.findAll();
    }


    public List<Tarea> listarPorEstado(boolean completada) {
        return repo.findByCompletada(completada);
    }


    @Transactional
    public void eliminar(Long id) {
        buscarPorId(id);
        repo.deleteById(id);
    }
}
