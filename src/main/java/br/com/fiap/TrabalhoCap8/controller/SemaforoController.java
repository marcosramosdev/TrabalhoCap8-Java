package br.com.fiap.TrabalhoCap8.controller;

import br.com.fiap.TrabalhoCap8.model.Semaforo;
import br.com.fiap.TrabalhoCap8.service.SemaforoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semaforos")
public class SemaforoController {

    @Autowired
    private SemaforoService semaforoService;

    @GetMapping
    public List<Semaforo> list() {
        return semaforoService.list();
    }

    @PostMapping
    public Semaforo create(@RequestBody Semaforo semaforo) {
        return semaforoService.create(semaforo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        semaforoService.remove(id);
        return ResponseEntity.noContent().build();
    }
}