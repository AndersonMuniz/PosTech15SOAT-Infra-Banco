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
        String approvalUrl = buildDecisionUrl(serviceOrderBudget.getId(), "aprovar");
        String rejectionUrl = buildDecisionUrl(serviceOrderBudget.getId(), "rejeitar");

        String subject = "Aprovacao de orcamento da ordem de servico " + serviceOrderBudget.getServiceOrder().getId();
        String body = """
                Um orcamento de ordem de servico esta aguardando aprovacao.

                ID da ordem de servico: %s
                ID do orcamento: %s
                Valor orcado: %s

                Aprovar:
                %s

                Rejeitar:
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
                .path("/api/public/orcamentos-ordem-servico/{id}/aprovacao/{decision}")
                .buildAndExpand(budgetId, decision)
                .toUriString();
    }
}
