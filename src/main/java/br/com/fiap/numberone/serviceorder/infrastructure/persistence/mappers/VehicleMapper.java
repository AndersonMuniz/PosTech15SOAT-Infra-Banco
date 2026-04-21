package br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers;

import br.com.fiap.numberone.serviceorder.domain.valueobjects.Vehicle;
import br.com.fiap.numberone.veiculo.domain.entities.VeiculoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VehicleMapper {
    @Mapping(target = "idCliente", source = "customerId")
    VeiculoEntity toEntity(Vehicle vehicle);

    @Mapping(target = "customerId", source = "idCliente")
    Vehicle toDomain(VeiculoEntity entity);
}
