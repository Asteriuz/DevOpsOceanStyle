package br.com.fiap.oceanstyle.model;

import br.com.fiap.oceanstyle.dto.endereco.CadastroEnderecoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "gs_endereco")
@EntityListeners(AuditingEntityListener.class)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco")
    @SequenceGenerator(name = "endereco", sequenceName = "gs_endereco_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "rua", length = 100)
    private String rua;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "cep", length = 20)
    private String cep;

    @OneToOne
    @JoinColumn(name = "empresa_id", unique = true)
    private Empresa empresa;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public Endereco(CadastroEnderecoDTO dto) {
        rua = dto.rua();
        numero = dto.numero();
        cep = dto.cep();
    }
}