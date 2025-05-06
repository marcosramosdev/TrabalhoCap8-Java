package br.com.fiap.TrabalhoCap8.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "historico_ajustes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HistoricoAjustes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_HISTORICO_AJUSTES", sequenceName = "SEQ_HISTORICO_AJUSTES", allocationSize = 1)
    private Long idAjuste;

    private Date dataHora;
    private Date dataHoraAjuste;

    @Lob
    private String motivoAjuste;

    private String novoTempoVerde;
    private String novoTempoVermelho;
    private String novoTempoAmarelo;

    @ManyToOne
    @JoinColumn(name = "tb_semaforo_id_semaforo")
    private Semaforo semaforo;

}
