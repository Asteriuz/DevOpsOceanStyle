package br.com.fiap.oceanstyle.dto.endereco;

public record CadastroEnderecoDTO(
                String rua,
                String numero,
                String cep,
                Long cidadeId) {
}
