package br.com.fiap.oceanstyle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.oceanstyle.dto.veiculo.AtualizacaoVeiculoDTO;
import br.com.fiap.oceanstyle.dto.veiculo.CadastroVeiculoDTO;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "gs_veiculo")
@EntityListeners(AuditingEntityListener.class)
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo")
    @SequenceGenerator(name = "veiculo", sequenceName = "gs_veiculo_seq", allocationSize = 1)
    @Column(name = "tie")
    private Long tie;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "tipo_motor", nullable = false, length = 50)
    private String tipoMotor;

    @Column(name = "sonar", nullable = false, length = 50)
    private String sonar;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(name = "link_imagem", nullable = false, length = 100)
    private String linkImagem;

    public Veiculo(CadastroVeiculoDTO dto) {
        nome = dto.nome();
        tipo = dto.tipo();
        tipoMotor = dto.tipoMotor();
        sonar = dto.sonar();
        linkImagem = dto.linkImagem();
    }

    public void atualizar(AtualizacaoVeiculoDTO dto) {
        nome = dto.nome();
        tipo = dto.tipo();
        tipoMotor = dto.tipoMotor();
        sonar = dto.sonar();
        linkImagem = dto.linkImagem();
    }
}
