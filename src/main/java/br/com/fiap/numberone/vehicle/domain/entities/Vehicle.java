package br.com.fiap.numberone.vehicle.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private UUID id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private UUID idClient;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Vehicle updateFrom(Vehicle novoVeiculo) {
        return Vehicle.builder()
                .id(this.id)
                .placa(novoVeiculo.placa)
                .marca(novoVeiculo.marca)
                .modelo(novoVeiculo.modelo)
                .ano(novoVeiculo.ano)
                .idClient(novoVeiculo.idClient)
                .createdAt(this.createdAt)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
