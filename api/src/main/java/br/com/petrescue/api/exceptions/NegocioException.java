package br.com.petrescue.api.exceptions;

import org.springframework.http.HttpStatus;

public class NegocioException extends ErroAbstratoException {

    public NegocioException(String mensagem) {
        super(mensagem, HttpStatus.BAD_REQUEST);
    }
}
