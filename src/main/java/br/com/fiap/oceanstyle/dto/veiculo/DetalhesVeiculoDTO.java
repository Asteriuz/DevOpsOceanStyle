package br.com.fiap.oceanstyle.dto.veiculo;

import br.com.fiap.oceanstyle.dto.empresa.DetalhesEmpresaDTO;
import br.com.fiap.oceanstyle.model.Veiculo;

public record DetalhesVeiculoDTO(
        Long tie,
        String nome,
        String tipo,
        String tipoMotor,
        String sonar,
        DetalhesEmpresaDTO empresa,
        String linkImagem) {
    public DetalhesVeiculoDTO(Veiculo cidade) {
        this(
                cidade.getTie(),
                cidade.getNome(),
                cidade.getTipo(),
                cidade.getTipoMotor(),
                cidade.getSonar(),
                new DetalhesEmpresaDTO(cidade.getEmpresa()),
                cidade.getLinkImagem());
    }

}