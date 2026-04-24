package br.gov.caixa.caixaverso.service;

import br.gov.caixa.caixaverso.dto.EmprestimoRequestDTO;
import br.gov.caixa.caixaverso.dto.EmprestimoResponseDTO;
import br.gov.caixa.caixaverso.entity.Emprestimo;
import br.gov.caixa.caixaverso.entity.Parcelas;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
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
                emprestimoRequestDTO.clienteID(),
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
            BigDecimal valorTotal,
            Integer quantidadeParcelas,
            Integer taxaJurosMensal,
            String tipoAmortizacao
    ) {
        if (tipoAmortizacao.equalsIgnoreCase("SAC")) {
            // TODO: Calcular usando método SAC
        }

        else {
            // TODO: Calcular usando método PRICE
        }

        return null;    //  Retornar lista de Parcelas (array)
    }
}
