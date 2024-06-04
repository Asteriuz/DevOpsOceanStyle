package br.com.fiap.oceanstyle.dto.empresa;

import br.com.fiap.oceanstyle.model.Empresa;

public record DetalhesEmpresaDTO(
        Long id,
        String cnpj,
        String nome,
        String telefone,
        String email) {

    public DetalhesEmpresaDTO(Empresa empresa) {
        this(
                empresa.getId(),
                empresa.getCnpj(),
                empresa.getNome(),
                empresa.getTelefone(),
                empresa.getEmail());
    }

}