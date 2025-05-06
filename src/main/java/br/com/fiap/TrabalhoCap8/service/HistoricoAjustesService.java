package br.com.fiap.TrabalhoCap8.service;


import br.com.fiap.TrabalhoCap8.model.HistoricoAjustes;
import br.com.fiap.TrabalhoCap8.repository.HistoricoAjustesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoAjustesService {

    @Autowired
    private HistoricoAjustesRepository historicoAjustesRepository;


    public List<HistoricoAjustes> list() {
        return historicoAjustesRepository.findAll();
    }


    public HistoricoAjustes create(HistoricoAjustes ajuste) {
        return historicoAjustesRepository.save(ajuste);
    }

    public void remove(Long id) {
        historicoAjustesRepository.deleteById(id);
    }
}
