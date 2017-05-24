package com.bstore.services.persistence.enums;

/**
 *
 * @author gtrejo
 */
public enum TipoEnvioEmail {
    MAIL_REGISTRO("1"),
    MAIL_PASSWORD("2"),
    MAIL_CONTACTO("3");

    private String tipo;

    private TipoEnvioEmail(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
