package br.com.fiap.TrabalhoCap8.service;

import br.com.fiap.TrabalhoCap8.model.TempoSinal;
import br.com.fiap.TrabalhoCap8.repository.TempoSinalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempoSinalService {
    @Autowired
    private TempoSinalRepository tempoSinalRepository;

    public List<TempoSinal> list() {
        return tempoSinalRepository.findAll();
    }

    public TempoSinal create(TempoSinal tempo) {
        return tempoSinalRepository.save(tempo);
    }
    public void remove(Long id) {
        tempoSinalRepository.deleteById(id);
    }

}
