package br.com.fiap.numberone.serviceorder.api.controllers;

import br.com.fiap.numberone.serviceorder.api.mappers.ServiceOrderApiMapper;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderService;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/service-orders")
public class ServiceOrderTrackingController {

    private final ServiceOrderApiMapper orderApiMapper;
    private final ServiceOrderService serviceOrderService;

    public ServiceOrderTrackingController(
            ServiceOrderApiMapper orderApiMapper,
            ServiceOrderService serviceOrderService
    ) {
        this.orderApiMapper = orderApiMapper;
        this.serviceOrderService = serviceOrderService;
    }


}
