package br.com.fiap.numberone.veiculo.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private String placa;
    @Transient
    private String marca;
    @Transient
    private String modelo;
    @Transient
    private Integer ano;
    @Transient
    private String idCliente;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}