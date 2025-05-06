package br.com.fiap.TrabalhoCap8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.TrabalhoCap8.model.HistoricoAjustes;
import br.com.fiap.TrabalhoCap8.service.HistoricoAjustesService;
import java.util.List;

@RestController
@RequestMapping("/api/ajustes")
public class HistoricoAjustesController {

    @Autowired
    private HistoricoAjustesService historicoAjustesService;

    @GetMapping
    public List<HistoricoAjustes> list() {
        return historicoAjustesService.list();
    }

    @PostMapping
    public HistoricoAjustes create(@RequestBody HistoricoAjustes ajuste) {
        return historicoAjustesService.create(ajuste);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        historicoAjustesService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
