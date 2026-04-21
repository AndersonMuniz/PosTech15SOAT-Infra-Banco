package br.com.fiap.numberone.serviceorder.infrastructure.notifications;

import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderApprovalNotificationGateway;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.infrastructure.config.ServiceOrderApprovalProperties;
import br.com.fiap.numberone.shared.application.gateways.EmailGateway;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Component
public class EmailServiceOrderApprovalNotificationGateway implements ServiceOrderApprovalNotificationGateway {

    private final EmailGateway emailGateway;
    private final ServiceOrderApprovalProperties properties;

    public EmailServiceOrderApprovalNotificationGateway(
            EmailGateway emailGateway,
            ServiceOrderApprovalProperties properties
    ) {
        this.emailGateway = emailGateway;
        this.properties = properties;
    }

    @Override
    public void sendApprovalRequest(ServiceOrder serviceOrder, String recipientEmail) {
        String approvalUrl = buildDecisionUrl(serviceOrder.getId(), "approve");
        String rejectionUrl = buildDecisionUrl(serviceOrder.getId(), "reject");

        String subject = "Approval required for service order " + serviceOrder.getId();
        String body = """
                A service order is waiting for approval.

                Service order ID: %s
                Customer: %s
                Vehicle plate: %s

                Approve:
                %s

                Reject:
                %s
                """.formatted(
                serviceOrder.getId(),
                serviceOrder.getCustomer() != null ? serviceOrder.getCustomer().getName() : "N/A",
                serviceOrder.getVehicle() != null ? serviceOrder.getVehicle().getLicensePlate() : "N/A",
                approvalUrl,
                rejectionUrl
        );

        emailGateway.send(recipientEmail, subject, body);
    }

    private String buildDecisionUrl(UUID serviceOrderId, String decision) {
        return UriComponentsBuilder.fromUriString(properties.baseUrl())
                .path("/api/service-orders/{id}/approval/{decision}")
                .buildAndExpand(serviceOrderId, decision)
                .toUriString();
    }
}
