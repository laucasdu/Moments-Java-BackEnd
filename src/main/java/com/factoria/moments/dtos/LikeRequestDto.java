package com.factoria.moments.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeRequestDto {
    private Long userId;
    private Long momentId;

}
