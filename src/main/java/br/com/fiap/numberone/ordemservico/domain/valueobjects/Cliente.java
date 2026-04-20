package br.com.fiap.numberone.ordemservico.domain.valueobjects;

import br.com.fiap.numberone.cliente.domain.enums.TipoDocumento;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Cliente {

    private UUID id;
    private String nome;
    private TipoDocumento tipoDocumento;
    private String documento;
    private String telefone;
    private String endereco;
    private Boolean ativo;

}
