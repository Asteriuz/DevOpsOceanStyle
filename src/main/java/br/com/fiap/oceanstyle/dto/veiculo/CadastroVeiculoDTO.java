package br.com.fiap.oceanstyle.dto.veiculo;

public record CadastroVeiculoDTO(
                String nome,
                String tipo,
                String tipoMotor,
                String sonar,
                Long empresaId,
                String linkImagem) {

}