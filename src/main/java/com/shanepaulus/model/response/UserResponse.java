package com.shanepaulus.model.response;

import lombok.Builder;

@Builder
public record UserResponse(Integer id,
                           String name,
                           String surname,
                           String nickName) {
}
