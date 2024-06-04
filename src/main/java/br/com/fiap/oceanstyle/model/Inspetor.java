package br.com.fiap.oceanstyle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.oceanstyle.dto.inspetor.AtualizacaoInspetorDTO;
import br.com.fiap.oceanstyle.dto.inspetor.CadastroInspetorDTO;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "gs_inspetor")
@EntityListeners(AuditingEntityListener.class)
public class Inspetor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inspetor")
    @SequenceGenerator(name = "inspetor", sequenceName = "gs_inspetor_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "rg", nullable = false, length = 11)
    private String rg;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "especializacao", nullable = false, length = 50)
    private String especializacao;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @ManyToMany(mappedBy = "inspetores")
    List<Vistoria> vistorias;

    public Inspetor(CadastroInspetorDTO dto) {
        rg = dto.rg();
        nome = dto.nome();
        especializacao = dto.especializacao();
        telefone = dto.telefone();
    }

    public void atualizar(AtualizacaoInspetorDTO dto) {
        rg = dto.rg();
        nome = dto.nome();
        especializacao = dto.especializacao();
        telefone = dto.telefone();
    }
}