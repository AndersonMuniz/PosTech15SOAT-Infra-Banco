package br.com.fiap.numberone.veiculo.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class VeiculoEntity {

    private UUID id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String idCliente;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}