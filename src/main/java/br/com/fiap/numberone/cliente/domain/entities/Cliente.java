package br.com.fiap.numberone.cliente.domain.entities;

import br.com.fiap.numberone.cliente.domain.enums.TipoDocumento;

import java.util.UUID;
import java.time.LocalDateTime;

public class Cliente {

    private UUID id;
    private String nome;
    private String tipoDocumento;
    private TipoDocumento documento;
    private String telefone;
    private String endereco;
    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
