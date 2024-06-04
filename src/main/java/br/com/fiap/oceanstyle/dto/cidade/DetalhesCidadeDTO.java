package br.com.fiap.oceanstyle.dto.cidade;

import br.com.fiap.oceanstyle.dto.estado.DetalhesEstadoDTO;
import br.com.fiap.oceanstyle.model.Cidade;

public record DetalhesCidadeDTO(
        Long id,
        String nome,
        DetalhesEstadoDTO estado) {

    public DetalhesCidadeDTO(Cidade cidade) {
        this(
                cidade.getId(),
                cidade.getNome(),
                new DetalhesEstadoDTO(cidade.getEstado()));
    }

}