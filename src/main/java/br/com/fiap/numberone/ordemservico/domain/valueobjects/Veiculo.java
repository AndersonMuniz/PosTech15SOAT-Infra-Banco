package br.com.fiap.numberone.ordemservico.domain.valueobjects;

import lombok.Getter;


import java.time.LocalDateTime;

@Getter
public class Veiculo {

    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String idCliente;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}