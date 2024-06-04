package br.com.fiap.oceanstyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.oceanstyle.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
