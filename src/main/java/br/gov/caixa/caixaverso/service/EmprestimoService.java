package br.gov.caixa.caixaverso.service;

import br.gov.caixa.caixaverso.dto.EmprestimoRequestDTO;
import br.gov.caixa.caixaverso.dto.EmprestimoResponseDTO;
import br.gov.caixa.caixaverso.entity.Emprestimo;
import br.gov.caixa.caixaverso.entity.Parcelas;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EmprestimoService {

    public EmprestimoResponseDTO criarEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO) {

        List<Parcelas> parcelas = calcularParcelas(
                emprestimoRequestDTO.valorTotal(),
                emprestimoRequestDTO.quantidadeParcelas(),
                emprestimoRequestDTO.taxaJurosMensal(),
                emprestimoRequestDTO.tipoAmortizacao()
        );

        Emprestimo novoEmprestimo = new Emprestimo(
                UUID.randomUUID(),
                emprestimoRequestDTO.clienteId(),
                emprestimoRequestDTO.valorTotal(),
                emprestimoRequestDTO.quantidadeParcelas(),
                emprestimoRequestDTO.tipoAmortizacao(),
                "PENDENTE",
                emprestimoRequestDTO.taxaJurosMensal(),
                parcelas
                );

        return EmprestimoResponseDTO.from(novoEmprestimo);
    }

    public static List<Parcelas> calcularParcelas(
            BigDecimal saldoDevedor,
            Integer quantidadeParcelas,
            Integer taxaJurosMensal,
            String tipoAmortizacao
    ) {

        List<Parcelas> parcelas = new ArrayList<>();

        if (tipoAmortizacao.equalsIgnoreCase("SAC")) {

            BigDecimal valorAmortizacao = saldoDevedor.divide(
                    new BigDecimal(quantidadeParcelas), 2, RoundingMode.HALF_UP);
            LocalDate dataVencimento = LocalDate.now();

            for (int i = 1; i <= quantidadeParcelas; i++){

                BigDecimal valorJuros = saldoDevedor.multiply(BigDecimal.valueOf((double) taxaJurosMensal / 100));
                BigDecimal valorPrestacao = valorAmortizacao.add(valorJuros);
                saldoDevedor = saldoDevedor.subtract(valorAmortizacao);
                dataVencimento = dataVencimento.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());

                parcelas.add(new Parcelas(
                        UUID.randomUUID(),
                        i,
                        dataVencimento,
                        valorAmortizacao,
                        valorJuros.setScale(2, RoundingMode.HALF_UP),
                        valorPrestacao.setScale(2, RoundingMode.HALF_UP),
                        "PENDENTE",
                        saldoDevedor.setScale(2, RoundingMode.HALF_UP)
                ));
            }
        }

        else {
            // TODO: Calcular usando método PRICE
            return null;
        }

        return parcelas;    //  Retornar lista de Parcelas (array)
    }
}
