package br.com.fiap.numberone.serviceorder.infrastructure.notifications;

import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderBudgetApprovalNotificationGateway;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderBudget;
import br.com.fiap.numberone.serviceorder.infrastructure.config.ServiceOrderApprovalProperties;
import br.com.fiap.numberone.shared.application.gateways.EmailGateway;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Component
public class EmailServiceOrderBudgetApprovalNotificationGateway implements ServiceOrderBudgetApprovalNotificationGateway {

    private final EmailGateway emailGateway;
    private final ServiceOrderApprovalProperties properties;

    public EmailServiceOrderBudgetApprovalNotificationGateway(
            EmailGateway emailGateway,
            ServiceOrderApprovalProperties properties
    ) {
        this.emailGateway = emailGateway;
        this.properties = properties;
    }

    @Override
    public void sendApprovalRequest(ServiceOrderBudget serviceOrderBudget, String recipientEmail) {
        String approvalUrl = buildDecisionUrl(serviceOrderBudget.getId(), "approve");
        String rejectionUrl = buildDecisionUrl(serviceOrderBudget.getId(), "reject");

        String subject = "Budget approval required for service order " + serviceOrderBudget.getServiceOrder().getId();
        String body = """
                A service order budget is waiting for approval.

                Service order ID: %s
                Budget ID: %s
                Quoted amount: %s

                Approve:
                %s

                Reject:
                %s
                """.formatted(
                serviceOrderBudget.getServiceOrder().getId(),
                serviceOrderBudget.getId(),
                serviceOrderBudget.getQuotedAmount(),
                approvalUrl,
                rejectionUrl
        );

        emailGateway.send(recipientEmail, subject, body);
    }

    private String buildDecisionUrl(UUID budgetId, String decision) {
        return UriComponentsBuilder.fromUriString(properties.baseUrl())
                .path("/api/service-order-budgets/{id}/approval/{decision}")
                .buildAndExpand(budgetId, decision)
                .toUriString();
    }
}
