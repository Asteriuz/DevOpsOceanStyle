package br.com.fiap.oceanstyle.dto.inspetor;

import br.com.fiap.oceanstyle.model.Inspetor;

public record DetalhesInspetorDTO(
        Long id,
        String rg,
        String nome,
        String especializacao,
        String telefone) {

    public DetalhesInspetorDTO(Inspetor inspetor) {
        this(
                inspetor.getId(),
                inspetor.getRg(),
                inspetor.getNome(),
                inspetor.getEspecializacao(),
                inspetor.getTelefone());

    }
}