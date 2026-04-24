package br.gov.caixa.caixaverso.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Parcelas {

    private UUID id;
    private Integer ordem;
    private LocalDate dataVencimento;
    private BigDecimal valorAmortizacao;
    private BigDecimal valorJuros;
    private BigDecimal valorPrestacao;
    private String status;  // para ENUM
    private BigDecimal saldoDevedor;
}
