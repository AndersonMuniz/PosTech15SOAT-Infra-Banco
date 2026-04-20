package br.com.fiap.numberone.ordemservico.domain.entities;

import br.com.fiap.numberone.ordemservico.domain.exceptions.StatusOrdemServicoInvalidoException;
import br.com.fiap.numberone.ordemservico.domain.enums.StatusOrdemServico;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Cliente;
import br.com.fiap.numberone.ordemservico.domain.valueobjects.Veiculo;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class OrdemServico {

    private UUID id;
    private String descricaoInicial;
    private String descricaoDiagnostico;
    private String descricaoDiagnosticoFinal;
    private String observacao;
    private Cliente cliente;
    private Veiculo veiculo;
    private StatusOrdemServico status;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraPrevista;
    private LocalDateTime dataHoraEntrega;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void aplicarDiagnosticoFinal(String descricao, String observacao) {
        if (this.status != StatusOrdemServico.RECEBIDA) {
            throw new StatusOrdemServicoInvalidoException(
                    "Não é possível adicionar diagnostico final em uma ordem de servico com status: " + this.status
            );
        }

        this.descricaoDiagnosticoFinal = descricao;
        this.status = StatusOrdemServico.EM_DIAGNOSTICO;

        if (observacao != null && !observacao.isBlank()) {
            this.observacao = observacao;
        }
    }

    public void vincularCliente(Cliente cliente) {
        if(!cliente.getAtivo()){
            throw new StatusOrdemServicoInvalidoException(
                    "Não é possível vincular cliente inativo"
            );
        }

        this.cliente = cliente;
    }

    public void vincularVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
