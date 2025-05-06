package br.com.fiap.TrabalhoCap8.service;

import br.com.fiap.TrabalhoCap8.model.FluxoTrafego;
import br.com.fiap.TrabalhoCap8.repository.FluxoTrafegoRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class FluxoTrafegoService {
    @Autowired
    private FluxoTrafegoRepository fluxoTrafegoRepository;

    public List<FluxoTrafego> list() {
        return fluxoTrafegoRepository.findAll();
    }

    public FluxoTrafego create(FluxoTrafego fluxo) {
        return fluxoTrafegoRepository.save(fluxo);
    }

    public void remove(Long id) {
        fluxoTrafegoRepository.deleteById(id);
    }

}
