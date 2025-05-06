package br.com.fiap.TrabalhoCap8.model;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "tb_semaforo")
public class Semaforo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_HISTORICO_AJUSTES", allocationSize = 1, sequenceName = "SEQ_HISTORICO_AJUSTES")
    private Long idSemaforo;

    @Lob
    private byte[] localidade;

    private Date atualizacao;
    private String status;

    @OneToMany(mappedBy = "semaforo")
    private List<FluxoTrafego> fluxos;

    @OneToMany(mappedBy = "semaforo")
    private List<HistoricoAjustes> historicos;

    @OneToMany(mappedBy = "semaforo")
    private List<CondicaoClimatica> condicoesClimaticas;

    @OneToMany(mappedBy = "semaforo")
    private List<TempoSinal> tempos;

}
