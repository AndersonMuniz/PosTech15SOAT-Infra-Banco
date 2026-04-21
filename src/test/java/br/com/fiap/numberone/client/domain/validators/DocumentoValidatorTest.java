package br.com.fiap.numberone.client.domain.validators;

import br.com.fiap.numberone.client.domain.enums.TipoDocumento;
import br.com.fiap.numberone.client.domain.exceptions.DocumentoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DocumentoValidatorTest {

    @Test
    void deveValidarCpfValido() {
        assertDoesNotThrow(() -> DocumentoValidator.validar(TipoDocumento.PESSOA_FISICA, "529.982.247-25"));
    }

    @Test
    void deveLancarExcecaoParaCpfInvalido() {
        DocumentoException exception = assertThrows(DocumentoException.class,
                () -> DocumentoValidator.validar(TipoDocumento.PESSOA_FISICA, "111.111.111-11"));

        assertEquals("CPF inválido", exception.getMessage());
    }

    @Test
    void deveValidarCnpjValido() {
        assertDoesNotThrow(() -> DocumentoValidator.validar(TipoDocumento.PESSOA_JURIDICA, "11.444.777/0001-61"));
    }

    @Test
    void deveLancarExcecaoParaCnpjInvalido() {
        DocumentoException exception = assertThrows(DocumentoException.class,
                () -> DocumentoValidator.validar(TipoDocumento.PESSOA_JURIDICA, "11.111.111/1111-11"));

        assertEquals("CNPJ inválido", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoDocumentoNulo() {
        DocumentoException exception = assertThrows(DocumentoException.class,
                () -> DocumentoValidator.validar(TipoDocumento.PESSOA_FISICA, null));

        assertEquals("CPF inválido", exception.getMessage());
    }
}
