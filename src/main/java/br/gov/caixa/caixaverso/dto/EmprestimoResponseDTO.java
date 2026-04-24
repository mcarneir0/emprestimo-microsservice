package br.gov.caixa.caixaverso.dto;

import br.gov.caixa.caixaverso.entity.Emprestimo;
import br.gov.caixa.caixaverso.entity.Parcelas;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record EmprestimoResponseDTO(
        UUID id,
        UUID clienteId,
        BigDecimal valorTotal,
        Integer quantidadeParcelas,
        String tipoAmortizacao,
        Integer taxaJurosMensal,
        String status,
        List<Parcelas> parcelas
) {
    public static EmprestimoResponseDTO from(Emprestimo emprestimo) {
        return EmprestimoResponseDTO
                .builder()
                .id(emprestimo.getId())
                .clienteId(emprestimo.getClienteId())
                .valorTotal(emprestimo.getValorTotal())
                .quantidadeParcelas(emprestimo.getQuantidadeParcelas())
                .tipoAmortizacao(emprestimo.getTipoAmortizacao())
                .taxaJurosMensal(emprestimo.getTaxaJurosMensal())
                .status(emprestimo.getStatus())
                .parcelas(emprestimo.getParcelas())
                .build();
    }
}
