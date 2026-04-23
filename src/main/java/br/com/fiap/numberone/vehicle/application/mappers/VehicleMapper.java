package br.com.fiap.numberone.vehicle.application.mappers;

import br.com.fiap.numberone.vehicle.api.dtos.requests.VehicleRequest;
import br.com.fiap.numberone.vehicle.api.dtos.responses.VehicleResponse;
import br.com.fiap.numberone.vehicle.domain.entities.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle toEntity(VehicleRequest request) {
        return Vehicle.builder()
                .placa(request.placa())
                .marca(request.marca())
                .modelo(request.modelo())
                .ano(request.ano())
                .idClient(request.idClient())
                .build();
    }

    public VehicleResponse toResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getPlaca(),
                vehicle.getMarca(),
                vehicle.getModelo(),
                vehicle.getAno(),
                vehicle.getIdClient(),
                vehicle.getCreatedAt(),
                vehicle.getUpdatedAt()
        );
    }
}
