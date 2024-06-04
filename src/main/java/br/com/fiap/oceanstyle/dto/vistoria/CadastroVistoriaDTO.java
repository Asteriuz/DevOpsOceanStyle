package br.com.fiap.oceanstyle.dto.vistoria;

import java.time.LocalDate;

public record CadastroVistoriaDTO(
                LocalDate data,
                Integer nivelRuido,
                String resultado,
                String observacoes,
                Long veiculoId) {

}
