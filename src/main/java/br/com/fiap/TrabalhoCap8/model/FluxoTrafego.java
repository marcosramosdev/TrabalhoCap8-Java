package br.com.fiap.TrabalhoCap8.model;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Entity
@Table(name = "fluxo_trafego")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FluxoTrafego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_FLUXO_TRAFEGO", sequenceName = "SEQ_FLUXO_TRAFEGO", allocationSize = 1)
    private Long idFluxo;

    private Date dataHora;
    private Integer volume;
    private Integer sentido;

    @ManyToOne
    @JoinColumn(name = "tb_semaforo_id_semaforo")
    private Semaforo semaforo;

}
