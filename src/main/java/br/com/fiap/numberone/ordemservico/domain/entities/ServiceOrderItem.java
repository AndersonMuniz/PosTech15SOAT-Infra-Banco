package br.com.fiap.numberone.ordemservico.domain.entities;

import br.com.fiap.numberone.ordemservico.domain.valueobjects.AutomotiveService;

import java.util.UUID;


public class ServiceOrderItem {

    private UUID id;
    private ServiceOrder serviceOrder;
    private AutomotiveService automotiveService;

}
