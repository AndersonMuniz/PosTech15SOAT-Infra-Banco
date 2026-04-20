package br.com.fiap.numberone.inventory.domain.entities;

import br.com.fiap.numberone.inventory.domain.enums.ItemType;
import br.com.fiap.numberone.inventory.domain.enums.UnitOfMeasure;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(BigDecimal costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getApplicableVehicle() {
        return applicableVehicle;
    }

    public void setApplicableVehicle(String applicableVehicle) {
        this.applicableVehicle = applicableVehicle;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void updateFrom(InventoryItem newData) {
        if (newData.code != null) this.code = newData.code;
        if (newData.name != null) this.name = newData.name;
        if (newData.description != null) this.description = newData.description;
        if (newData.itemType != null) this.itemType = newData.itemType;
        if (newData.unitOfMeasure != null) this.unitOfMeasure = newData.unitOfMeasure;
        if (newData.costPerUnit != null) this.costPerUnit = newData.costPerUnit;
        if (newData.salePrice != null) this.salePrice = newData.salePrice;
        if (newData.stockQuantity != null) this.stockQuantity = newData.stockQuantity;
        if (newData.minimumStock != null) this.minimumStock = newData.minimumStock;
        if (newData.brand != null) this.brand = newData.brand;
        if (newData.applicableVehicle != null) this.applicableVehicle = newData.applicableVehicle;
        if (newData.active != null) this.active = newData.active;
    }
}