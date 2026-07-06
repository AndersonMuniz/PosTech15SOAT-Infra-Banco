package br.com.fiap.numberone.serviceorder.api.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.fiap.numberone.automotiveservice.domain.enums.ServiceType;
import br.com.fiap.numberone.customer.domain.enums.TipoDocumento;
import br.com.fiap.numberone.inventory.domain.enums.ItemType;
import br.com.fiap.numberone.inventory.domain.enums.UnitOfMeasure;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateServiceOrderRequest(
        @JsonProperty("descricaoInicial")
        @NotBlank(message = "descricaoInicial e obrigatoria") String initialDescription,
        @JsonProperty("descricaoDiagnostico")
        String diagnosisDescription,
        @JsonProperty("observacao")
        String notes,
        @JsonProperty("idCliente")
        UUID customerId,
        @JsonProperty("cliente")
        @Valid CustomerData customer,
        @JsonProperty("idVeiculo")
        UUID vehicleId,
        @JsonProperty("veiculo")
        @Valid VehicleData vehicle,
        @JsonProperty("servicos")
        @Valid
        @NotEmpty(message = "servicos e obrigatorio")
        List<ServiceItemData> serviceItems,
        @JsonProperty("dataHoraEntrada")
        @NotNull(message = "dataHoraEntrada e obrigatoria") LocalDateTime entryDateTime
) {
    public UUID customerId() {
        if (customerId != null) {
            return customerId;
        }
        return customer != null ? customer.id() : null;
    }

    public UUID vehicleId() {
        if (vehicleId != null) {
            return vehicleId;
        }
        return vehicle != null ? vehicle.id() : null;
    }

    @JsonIgnore
    @AssertTrue(message = "idCliente ou documento/tipoDocumento do cliente e obrigatorio")
    public boolean isCustomerReferencePresent() {
        return customerId() != null || customer != null && customer.hasStrongKey();
    }

    @JsonIgnore
    @AssertTrue(message = "idVeiculo ou placa do veiculo e obrigatorio")
    public boolean isVehicleReferencePresent() {
        return vehicleId() != null || vehicle != null && vehicle.hasStrongKey();
    }

    public record CustomerData(
            @JsonProperty("id")
            UUID id,
            @JsonProperty("nome")
            String name,
            @JsonProperty("tipoDocumento")
            TipoDocumento documentType,
            @JsonProperty("documento")
            String document,
            @JsonProperty("email")
            String email,
            @JsonProperty("telefone")
            String phone,
            @JsonProperty("endereco")
            String address,
            @JsonProperty("ativo")
            Boolean active
    ) {
        private boolean hasStrongKey() {
            return documentType != null && document != null && !document.isBlank();
        }
    }

    public record VehicleData(
            @JsonProperty("id")
            UUID id,
            @JsonProperty("placa")
            String licensePlate,
            @JsonProperty("marca")
            String brand,
            @JsonProperty("modelo")
            String model,
            @JsonProperty("ano")
            Integer year
    ) {
        private boolean hasStrongKey() {
            return licensePlate != null && !licensePlate.isBlank();
        }
    }

    public record ServiceItemData(
            @JsonProperty("idServico")
            UUID serviceId,
            @JsonProperty("codigo")
            String code,
            @JsonProperty("nome")
            String name,
            @JsonProperty("descricao")
            String description,
            @JsonProperty("tipoServico")
            ServiceType serviceType,
            @JsonProperty("valorBase")
            BigDecimal baseValue,
            @JsonProperty("tempoEstimadoMinutos")
            Integer estimatedTimeMinutes,
            @JsonProperty("valor")
            @Positive(message = "valor deve ser maior que zero")
            BigDecimal value,
            @JsonProperty("opcional")
            Boolean optional,
            @JsonProperty("pecas")
            @Valid
            List<SupplyData> supplies
    ) {
        @JsonIgnore
        @AssertTrue(message = "idServico ou codigo do servico e obrigatorio")
        public boolean isServiceReferencePresent() {
            return serviceId != null || code != null && !code.isBlank();
        }
    }

    public record SupplyData(
            @JsonProperty("idItemEstoque")
            UUID inventoryItemId,
            @JsonProperty("codigo")
            String code,
            @JsonProperty("nome")
            String name,
            @JsonProperty("descricao")
            String description,
            @JsonProperty("tipoItem")
            ItemType itemType,
            @JsonProperty("unidadeMedida")
            UnitOfMeasure unitOfMeasure,
            @JsonProperty("custoUnitario")
            BigDecimal costPerUnit,
            @JsonProperty("precoVenda")
            BigDecimal salePrice,
            @JsonProperty("quantidadeEstoque")
            Integer inventoryQuantity,
            @JsonProperty("estoqueMinimo")
            Integer minimumInventoryQuantity,
            @JsonProperty("marca")
            String brand,
            @JsonProperty("veiculoAplicavel")
            String applicableVehicle,
            @JsonProperty("quantidadeUsada")
            @NotNull(message = "quantidadeUsada e obrigatoria")
            @Positive(message = "quantidadeUsada deve ser maior que zero")
            Integer quantityUsed
    ) {
        @JsonIgnore
        @AssertTrue(message = "idItemEstoque ou codigo da peca e obrigatorio")
        public boolean isInventoryItemReferencePresent() {
            return inventoryItemId != null || code != null && !code.isBlank();
        }
    }
}
