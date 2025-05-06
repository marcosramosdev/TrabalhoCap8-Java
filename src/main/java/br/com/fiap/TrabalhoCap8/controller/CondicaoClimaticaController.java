import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.TrabalhoCap8.model.CondicaoClimatica;
import br.com.fiap.TrabalhoCap8.service.CondicaoClimaticaService;
import java.util.List;

@RestController
@RequestMapping("/api/condicoes")
public class CondicaoClimaticaController {

    @Autowired
    private CondicaoClimaticaService CondicaoClimaticaService;


    @GetMapping
    public List<CondicaoClimatica> list() {
        return CondicaoClimaticaService.list();
    }


    @PostMapping
    public CondicaoClimatica create(@RequestBody CondicaoClimatica condicao) {
        return CondicaoClimaticaService.create(condicao);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        CondicaoClimaticaService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
