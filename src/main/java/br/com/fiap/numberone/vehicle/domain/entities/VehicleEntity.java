package br.com.fiap.numberone.vehicle.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Transient
    private String placa;
    @Transient
    private String marca;
    @Transient
    private String modelo;
    @Transient
    private Integer ano;
    @Transient
    private String idClient;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}