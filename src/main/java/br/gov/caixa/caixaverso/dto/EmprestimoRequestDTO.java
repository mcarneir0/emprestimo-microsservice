package br.gov.caixa.caixaverso.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record EmprestimoRequestDTO(
        UUID clienteId,
        BigDecimal valorTotal,
        Integer quantidadeParcelas,
        String tipoAmortizacao,
        Integer taxaJurosMensal) {
}
