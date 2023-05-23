package com.shanepaulus.model;

import java.time.LocalDateTime;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

public record ErrorMessage(String message, LocalDateTime date) {

}
