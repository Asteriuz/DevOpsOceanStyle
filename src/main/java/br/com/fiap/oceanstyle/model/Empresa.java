package br.com.fiap.oceanstyle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.oceanstyle.dto.empresa.AtualizacaoEmpresaDTO;
import br.com.fiap.oceanstyle.dto.empresa.CadastroEmpresaDTO;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "gs_empresa")
@EntityListeners(AuditingEntityListener.class)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa")
    @SequenceGenerator(name = "empresa", sequenceName = "gs_empresa_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    public Empresa(CadastroEmpresaDTO dto) {
        nome = dto.nome();
        cnpj = dto.cnpj();
        telefone = dto.telefone();
        email = dto.email();
    }

    public void atualizar(AtualizacaoEmpresaDTO dto) {
        nome = dto.nome();
        cnpj = dto.cnpj();
        telefone = dto.telefone();
        email = dto.email();
    }
}