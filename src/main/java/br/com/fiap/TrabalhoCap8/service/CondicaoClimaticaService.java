package br.com.fiap.TrabalhoCap8.service;

import br.com.fiap.TrabalhoCap8.model.CondicaoClimatica;
import br.com.fiap.TrabalhoCap8.repository.CondicaoClimaticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CondicaoClimaticaService {
    @Autowired
    private CondicaoClimaticaRepository condicaoClimaticaRepository;

    public List<CondicaoClimatica> list() {
        return condicaoClimaticaRepository.findAll();
    }


    public CondicaoClimatica create(CondicaoClimatica condicao) {
        return condicaoClimaticaRepository.save(condicao);
    }


    public void remove(Long id) {
        condicaoClimaticaRepository.deleteById(id);
    }

}
