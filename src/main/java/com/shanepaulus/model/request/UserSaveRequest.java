package com.shanepaulus.model.request;

import lombok.Builder;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 24-May-2023.
 */

@Builder
public record UserSaveRequest(Integer id,
                              String name,
                              String surname,
                              String nickName) {
}
