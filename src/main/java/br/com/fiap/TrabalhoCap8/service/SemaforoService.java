package br.com.fiap.TrabalhoCap8.service;

import br.com.fiap.TrabalhoCap8.model.Semaforo;
import br.com.fiap.TrabalhoCap8.repository.SemaforoRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;
@Service
public class SemaforoService {
    @Autowired
    private SemaforoRepository semaforoRepository;
    @Bean
    public  List<Semaforo> list() {
        return semaforoRepository.findAll();
    }
    @Bean
    public  Semaforo create(Semaforo semaforo) {
        return semaforoRepository.save(semaforo);
    }
    @Bean
    public  void remove(Long id) {
        semaforoRepository.deleteById(id);
    }
}
