package br.com.fiap.numberone.ordemservico.domain.valueobjects;

import lombok.Getter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

<<<<<<< HEAD:src/main/java/br/com/fiap/numberone/ordemservico/domain/valueobjects/Veiculo.java
@Getter
=======
@Entity
@NoArgsConstructor
@AllArgsConstructor

>>>>>>> 65d4077237f478dc731313831077c62d8bfde942:src/main/java/br/com/fiap/numberone/veiculo/domain/entities/Veiculo.java
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