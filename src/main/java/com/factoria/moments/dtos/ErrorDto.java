package com.factoria.moments.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    // S'HA D'INDICAR EL CODI QUE TÈ L'EMPRESA
    private String code;
    private String message;

}
