package br.gov.caixa.caixaverso.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Cliente {

    private UUID id;
    private String nome;
    private String documento;
}
