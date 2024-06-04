package br.com.fiap.oceanstyle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.oceanstyle.dto.estado.AtualizacaoEstadoDTO;
import br.com.fiap.oceanstyle.dto.estado.CadastroEstadoDTO;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "gs_estado")
@EntityListeners(AuditingEntityListener.class)
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado")
    @SequenceGenerator(name = "estado", sequenceName = "gs_estado_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    @Column(name = "sigla", nullable = false, length = 2)
    private String sigla;

    public Estado(CadastroEstadoDTO dto) {
        nome = dto.nome();
        sigla = dto.sigla();
    }

    public void atualizar(AtualizacaoEstadoDTO dto) {
        nome = dto.nome();
        sigla = dto.sigla();
    }
}