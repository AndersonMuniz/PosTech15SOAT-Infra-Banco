package br.com.fiap.numberone.inventory.domain.entities;

import br.com.fiap.numberone.inventory.domain.enums.ItemType;
import br.com.fiap.numberone.inventory.domain.enums.UnitOfMeasure;
import br.com.fiap.numberone.inventory.domain.exceptions.InventoryItemAlreadyActiveException;
import br.com.fiap.numberone.inventory.domain.exceptions.InventoryItemAlreadyInactiveException;
import br.com.fiap.numberone.inventory.domain.exceptions.InvalidInventoryItemDataException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryItem {

    private UUID id;
    private String code;
    private String name;
    private String description;
    private ItemType itemType;
    private UnitOfMeasure unitOfMeasure;
    private BigDecimal costPerUnit;
    private BigDecimal salePrice;
    private Integer stockQuantity;
    private Integer minimumStock;
    private String brand;
    private String applicableVehicle;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public InventoryItem(
            UUID id,
            String code,
            String name,
            String description,
            ItemType itemType,
            UnitOfMeasure unitOfMeasure,
            BigDecimal costPerUnit,
            BigDecimal salePrice,
            Integer stockQuantity,
            Integer minimumStock,
            String brand,
            String applicableVehicle,
            Boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.unitOfMeasure = unitOfMeasure;
        this.costPerUnit = costPerUnit;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.minimumStock = minimumStock;
        this.brand = brand;
        this.applicableVehicle = applicableVehicle;
        this.active = active != null ? active : true;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        validate();
    }

    public void update(
            String code,
            String name,
            String description,
            ItemType itemType,
            UnitOfMeasure unitOfMeasure,
            BigDecimal costPerUnit,
            BigDecimal salePrice,
            Integer stockQuantity,
            Integer minimumStock,
            String brand,
            String applicableVehicle
    ) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.unitOfMeasure = unitOfMeasure;
        this.costPerUnit = costPerUnit;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.minimumStock = minimumStock;
        this.brand = brand;
        this.applicableVehicle = applicableVehicle;
        this.updatedAt = LocalDateTime.now();

        validate();
    }

    public void activate() {
        if (Boolean.TRUE.equals(this.active)) {
            throw new InventoryItemAlreadyActiveException("O item de estoque já está ativo");
        }

        this.active = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        if (Boolean.FALSE.equals(this.active)) {
            throw new InventoryItemAlreadyInactiveException("O item de estoque já está inativo");
        }

        this.active = false;
        this.updatedAt = LocalDateTime.now();
    }

    private void validate() {
        if (code == null || code.isBlank()) {
            throw new InvalidInventoryItemDataException("O código do item é obrigatório");
        }

        if (name == null || name.isBlank()) {
            throw new InvalidInventoryItemDataException("O nome do item é obrigatório");
        }

        if (itemType == null) {
            throw new InvalidInventoryItemDataException("O tipo do item é obrigatório");
        }

        if (unitOfMeasure == null) {
            throw new InvalidInventoryItemDataException("A unidade de medida do item é obrigatória");
        }

        if (costPerUnit == null) {
            throw new InvalidInventoryItemDataException("O custo unitário do item é obrigatório");
        }

        if (costPerUnit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInventoryItemDataException("O custo unitário do item deve ser maior que zero");
        }

        if (salePrice == null) {
            throw new InvalidInventoryItemDataException("O preço de venda do item é obrigatório");
        }

        if (salePrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInventoryItemDataException("O preço de venda do item deve ser maior que zero");
        }

        if (stockQuantity == null) {
            throw new InvalidInventoryItemDataException("A quantidade em estoque é obrigatória");
        }

        if (stockQuantity < 0) {
            throw new InvalidInventoryItemDataException("A quantidade em estoque não pode ser negativa");
        }

        if (minimumStock == null) {
            throw new InvalidInventoryItemDataException("O estoque mínimo é obrigatório");
        }

        if (minimumStock < 0) {
            throw new InvalidInventoryItemDataException("O estoque mínimo não pode ser negativo");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public String getBrand() {
        return brand;
    }

    public String getApplicableVehicle() {
        return applicableVehicle;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}