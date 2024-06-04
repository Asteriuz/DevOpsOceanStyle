package br.com.fiap.oceanstyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.oceanstyle.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
