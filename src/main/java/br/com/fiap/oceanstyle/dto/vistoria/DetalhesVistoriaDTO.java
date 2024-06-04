package br.com.fiap.oceanstyle.dto.vistoria;

import br.com.fiap.oceanstyle.model.Vistoria;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.oceanstyle.dto.inspetor.DetalhesInspetorDTO;
import br.com.fiap.oceanstyle.dto.veiculo.DetalhesVeiculoDTO;

public record DetalhesVistoriaDTO(
        Long id,
        LocalDate data,
        Integer nivelRuido,
        String resultado,
        String observacoes,
        DetalhesVeiculoDTO veiculo,
        List<DetalhesInspetorDTO> inspetores)

{

    public DetalhesVistoriaDTO(Vistoria vistoria) {
        this(
                vistoria.getId(),
                vistoria.getData(),
                vistoria.getNivelRuido(),
                vistoria.getResultado(),
                vistoria.getObservacoes(),
                new DetalhesVeiculoDTO(vistoria.getVeiculo()),
                vistoria.getInspetores().stream().map(DetalhesInspetorDTO::new).toList());
    }
}