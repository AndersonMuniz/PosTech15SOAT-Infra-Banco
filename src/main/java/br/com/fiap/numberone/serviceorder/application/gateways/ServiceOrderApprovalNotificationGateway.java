package br.com.fiap.numberone.serviceorder.application.gateways;

import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;

public interface ServiceOrderApprovalNotificationGateway {

    void sendApprovalRequest(ServiceOrder serviceOrder, String recipientEmail);
}
