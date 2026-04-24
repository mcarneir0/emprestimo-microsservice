package br.gov.caixa.caixaverso.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ClienteResponseDTO(UUID id, String nome, String documento) {
}
