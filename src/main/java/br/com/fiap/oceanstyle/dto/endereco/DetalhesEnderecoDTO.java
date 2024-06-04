package br.com.fiap.oceanstyle.dto.endereco;

import br.com.fiap.oceanstyle.dto.cidade.DetalhesCidadeDTO;
import br.com.fiap.oceanstyle.model.Endereco;

public record DetalhesEnderecoDTO(
        Long id,
        String rua,
        String numero,
        String cep,
        DetalhesCidadeDTO cidade) {

    public DetalhesEnderecoDTO(Endereco endereco) {
        this(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCep(),
                new DetalhesCidadeDTO(endereco.getCidade()));
    }
}