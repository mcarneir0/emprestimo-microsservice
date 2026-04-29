package br.gov.caixa.caixaverso.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Emprestimo {

    private UUID id;
    private UUID clienteId;
    private BigDecimal valorTotal;
    private Integer quantidadeParcelas;
    private String tipoAmortizacao;     // para ENUM
    private String status;              // para ENUM
    private Integer taxaJurosMensal;
    private List<Parcela> parcelas;
}
