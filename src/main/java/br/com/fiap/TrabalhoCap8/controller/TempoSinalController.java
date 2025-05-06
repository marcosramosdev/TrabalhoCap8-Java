import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.TrabalhoCap8.model.TempoSinal;
import br.com.fiap.TrabalhoCap8.service.TempoSinalService;
import java.util.List;

@RestController
@RequestMapping("/api/tempos")
public class TempoSinalController {

    @Autowired
    private TempoSinalService tempoSinalService;

    @GetMapping
    public List<TempoSinal> list() {
        return tempoSinalService.list();
    }

    @PostMapping
    public TempoSinal create(@RequestBody TempoSinal tempo) {
        return tempoSinalService.create(tempo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        tempoSinalService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
