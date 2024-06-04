package br.com.fiap.oceanstyle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.oceanstyle.dto.vistoria.AtualizacaoVistoriaDTO;
import br.com.fiap.oceanstyle.dto.vistoria.CadastroVistoriaDTO;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "gs_vistoria")
@EntityListeners(AuditingEntityListener.class)
public class Vistoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vistoria")
    @SequenceGenerator(name = "vistoria", sequenceName = "gs_vistoria_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "nivel_ruido", nullable = false)
    private Integer nivelRuido;

    @Column(name = "resultado", nullable = false, length = 30)
    private String resultado;

    @Column(name = "observacoes", nullable = false, length = 100)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", referencedColumnName = "tie")
    private Veiculo veiculo;

    @ManyToMany
    @JoinTable(name = "gs_inspetor_vistoria", joinColumns = @JoinColumn(name = "vistoria_id"), inverseJoinColumns = @JoinColumn(name = "inspetor_id"))
    Set<Inspetor> inspetores;

    public Vistoria(CadastroVistoriaDTO dto) {
        this.data = dto.data();
        this.nivelRuido = dto.nivelRuido();
        this.resultado = dto.resultado();
        this.observacoes = dto.observacoes();
        this.inspetores = new HashSet<>();
    }

    public void atualizar(AtualizacaoVistoriaDTO dto) {
        this.data = dto.data();
        this.nivelRuido = dto.nivelRuido();
        this.resultado = dto.resultado();
        this.observacoes = dto.observacoes();
    }
}
