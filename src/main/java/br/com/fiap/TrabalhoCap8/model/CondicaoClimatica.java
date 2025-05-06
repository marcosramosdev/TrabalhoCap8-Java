package br.com.fiap.TrabalhoCap8.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;



@Entity
@Table(name = "tb_condicao_climatica")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CondicaoClimatica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SEQ_CONDICOES_CLIMATICAS", sequenceName = "SEQ_CONDICOES_CLIMATICAS", allocationSize = 1)
    private Long idCondicao;

    private Date dataHora;
    private Integer temperatura;
    private Integer umidade;
    private String condicao;

    @ManyToOne
    @JoinColumn(name = "tb_semaforo_id_semaforo")
    private Semaforo semaforo;
}
