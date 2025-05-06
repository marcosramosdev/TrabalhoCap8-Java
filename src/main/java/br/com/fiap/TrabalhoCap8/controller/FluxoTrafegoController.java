package br.com.fiap.TrabalhoCap8.controller;

import br.com.fiap.TrabalhoCap8.model.FluxoTrafego;
import br.com.fiap.TrabalhoCap8.service.FluxoTrafegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fluxos")
public class FluxoTrafegoController {

    @Autowired
    private FluxoTrafegoService fluxoTrafegoService;

    @GetMapping
    @Bean
    public List<FluxoTrafego> list() {
        return fluxoTrafegoService.list();
    }
    @Bean
    @PostMapping
    public FluxoTrafego create(@RequestBody FluxoTrafego fluxo) {
        return fluxoTrafegoService.create(fluxo);
    }
    @Bean
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        fluxoTrafegoService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
