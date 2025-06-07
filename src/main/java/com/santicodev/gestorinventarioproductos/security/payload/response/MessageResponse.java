package com.santicodev.gestorinventarioproductos.security.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Para facilitar la creación de mensajes de respuesta
public class MessageResponse {
    private String message;
}
