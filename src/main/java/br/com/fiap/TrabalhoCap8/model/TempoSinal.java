package br.com.fiap.TrabalhoCap8.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tempo_sinal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TempoSinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_TEMPO_SINAL", allocationSize = 1, sequenceName = "SEQ_TEMPO_SINAL")
    private Long idTempo;

    private String tempoVerde;
    private String tempoAmarelo;
    private String tempoVermelho;

    @ManyToOne
    @JoinColumn(name = "tb_semaforo_id_semaforo")
    private Semaforo semaforo;

}
