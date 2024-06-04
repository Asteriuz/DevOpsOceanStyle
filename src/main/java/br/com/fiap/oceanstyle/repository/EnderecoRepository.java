package br.com.fiap.oceanstyle.repository;

import br.com.fiap.oceanstyle.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    // TODO: Investigate the need for this suppression
    @SuppressWarnings("null")
    Optional<Endereco> findById(Long empresaId);
}
