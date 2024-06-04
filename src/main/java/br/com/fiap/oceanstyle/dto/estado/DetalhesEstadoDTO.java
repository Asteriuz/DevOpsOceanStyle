package br.com.fiap.oceanstyle.dto.estado;

import br.com.fiap.oceanstyle.model.Estado;

public record DetalhesEstadoDTO(
        Long id,
        String nome) {

    public DetalhesEstadoDTO(Estado estado) {
        this(
                estado.getId(),
                estado.getNome());
    }
}