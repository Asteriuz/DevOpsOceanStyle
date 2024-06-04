package br.com.fiap.oceanstyle.dto.veiculo;

public record AtualizacaoVeiculoDTO(
        String nome,
        String tipo,
        String tipoMotor,
        String sonar,
        Long empresaId,
        String linkImagem) {

}