package br.com.fiap.oceanstyle.dto.empresa;

public record CadastroEmpresaDTO(
                String cnpj,
                String nome,
                String telefone,
                String email) {
}