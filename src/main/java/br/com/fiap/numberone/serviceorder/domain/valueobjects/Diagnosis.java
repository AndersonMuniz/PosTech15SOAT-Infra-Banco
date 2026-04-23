package br.com.fiap.numberone.serviceorder.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {
    private String notes;
    private String finalDiagnosisDescription;
}
